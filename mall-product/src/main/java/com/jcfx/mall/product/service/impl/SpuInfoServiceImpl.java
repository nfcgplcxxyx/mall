package com.jcfx.mall.product.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcfx.common.constant.ProductConstant;
import com.jcfx.common.to.SkuReductionTo;
import com.jcfx.common.to.SkuStockVO;
import com.jcfx.common.to.SpuBoundTo;
import com.jcfx.common.to.es.SkuESModel;
import com.jcfx.common.utils.PageUtils;
import com.jcfx.common.utils.Query;
import com.jcfx.common.utils.R;
import com.jcfx.mall.product.dao.SpuInfoDao;
import com.jcfx.mall.product.entity.*;
import com.jcfx.mall.product.feign.CouponFeignService;
import com.jcfx.mall.product.feign.ElasticFeignService;
import com.jcfx.mall.product.feign.WareFeignService;
import com.jcfx.mall.product.service.*;
import com.jcfx.mall.product.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private SpuInfoDescService spuInfoDescService;
    @Autowired
    private SpuImagesService imagesService;
    @Autowired
    private AttrService attrService;
    @Autowired
    private ProductAttrValueService attrValueService;
    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private SkuImagesService skuImagesService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    private CouponFeignService couponFeignService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private WareFeignService wareFeignService;
    @Autowired
    private ElasticFeignService elasticFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 后台新增商品
     *
     * @param vo 商品实体
     */
    @Transactional
    @Override
    public void saveSpuInfo(SpuSaveVo vo) {
        // 1.保存Spu基本信息
        SpuInfoEntity spuInfo = new SpuInfoEntity();
        BeanUtils.copyProperties(vo, spuInfo);
        spuInfo.setCreateTime(new Date());
        spuInfo.setUpdateTime(new Date());
        this.saveBaseSpuInfo(spuInfo);

        // 2.保存Spu的描述图片
        List<String> decript = vo.getDecript();
        SpuInfoDescEntity descEntity = new SpuInfoDescEntity();
        descEntity.setSpuId(spuInfo.getId());
        descEntity.setDecript(String.join(",", decript));
        spuInfoDescService.saveSpuInfoDesc(descEntity);

        // 3.保存spu的图片集
        List<String> images = vo.getImages();
        imagesService.saveImages(spuInfo.getId(), images);


        // 4.保存spu的规格参数
        List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
        List<ProductAttrValueEntity> collect = baseAttrs.stream().map(attr -> {
            ProductAttrValueEntity valueEntity = new ProductAttrValueEntity();

            valueEntity.setAttrId(attr.getAttrId());
            AttrEntity id = attrService.getById(attr.getAttrId());
            valueEntity.setAttrName(id.getAttrName());
            valueEntity.setAttrValue(attr.getAttrValues());
            valueEntity.setQuickShow(attr.getShowDesc());
            valueEntity.setSpuId(spuInfo.getId());

            return valueEntity;
        }).collect(Collectors.toList());
        attrValueService.saveProductAttr(collect);

        // 5.保存spu的积分信息
        Bounds bounds = vo.getBounds();
        SpuBoundTo spuBoundTo = new SpuBoundTo();
        BeanUtils.copyProperties(bounds, spuBoundTo);
        spuBoundTo.setSpuId(spuInfo.getId());
        R r = couponFeignService.saveSpuBounds(spuBoundTo);
        if (r.getCode() != 0) {
            log.error("远程保存spu积分信息失败");
        }

        // 5.保存spu对应的sku信息
        List<Skus> skus = vo.getSkus();
        if (!CollectionUtils.isEmpty(skus)) {
            // 5.1 sku基本信息
            skus.forEach(item -> {
                String defaultImg = "";
                for (Images image : item.getImages()) {
                    if (image.getDefaultImg() == 1) {
                        defaultImg = image.getImgUrl();
                    }
                }
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(item, skuInfoEntity);
                skuInfoEntity.setBrandId(spuInfo.getBrandId());
                skuInfoEntity.setCatalogId(spuInfo.getCatalogId());
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSpuId(spuInfo.getId());
                skuInfoEntity.setSkuDefaultImg(defaultImg);
                skuInfoService.saveSkuInfo(skuInfoEntity);

                // 5.2 sku的图片信息
                Long skuId = skuInfoEntity.getSkuId();
                List<SkuImagesEntity> imagesEntities = item.getImages().stream().map(img -> {
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    skuImagesEntity.setSkuId(skuId);
                    skuImagesEntity.setImgUrl(img.getImgUrl());
                    skuImagesEntity.setDefaultImg(img.getDefaultImg());
                    return skuImagesEntity;
                }).filter(entity -> !StringUtils.isEmpty(entity.getImgUrl())).collect(Collectors.toList());
                skuImagesService.saveBatch(imagesEntities);

                // 5.3 sku的销售属性信息
                List<Attr> attr = item.getAttr();
                List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attr.stream().map(a -> {
                    SkuSaleAttrValueEntity attrValueEntity = new SkuSaleAttrValueEntity();
                    BeanUtils.copyProperties(a, attrValueEntity);
                    attrValueEntity.setSkuId(skuId);
                    return attrValueEntity;
                }).collect(Collectors.toList());
                skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);

                // 5.4 sku的优惠、满减等信息(远程调用)
                SkuReductionTo skuReductionTo = new SkuReductionTo();
                BeanUtils.copyProperties(item, skuReductionTo);
                skuReductionTo.setSkuId(skuId);
                if (skuReductionTo.getFullCount() > 0 || skuReductionTo.getFullPrice().compareTo(new BigDecimal("0")) == 1) {
                    R r1 = couponFeignService.saveSkuReduction(skuReductionTo);
                    if (r1.getCode() != 0) {
                        log.error("远程保存sku优惠信息失败");
                    }
                }
            });
        }

    }

    @Override
    public void saveBaseSpuInfo(SpuInfoEntity spuInfo) {
        this.baseMapper.insert(spuInfo);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {

        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((w) -> {
                w.eq("id", key).or().like("spu_name", key);
            });
        }
        String status = (String) params.get("status");
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("publish_status", status);
        }

        String brandId = (String) params.get("brandId");
        if (!StringUtils.isEmpty(brandId) && !"0".equalsIgnoreCase(brandId)) {
            wrapper.eq("brand_id", brandId);
        }

        String catelogId = (String) params.get("catelogId");
        if (!StringUtils.isEmpty(catelogId) && !"0".equalsIgnoreCase(catelogId)) {
            wrapper.eq("catalog_id", catelogId);
        }

        IPage<SpuInfoEntity> page = this.page(new Query<SpuInfoEntity>().getPage(params), wrapper);

        return new PageUtils(page);

    }

    /**
     * @param spuId spuid
     * @return R
     * @title: up
     * @description: <p>商品上架</p>
     * @author: NFFive
     * @date: 2024/6/5 22:57
     */
    @Override
    public void up(Long spuId) {
        // 待上架的商品
        List<SkuESModel> products;

        // 1.查询spuId下的所有sku信息
        List<SkuInfoEntity> skuList = skuInfoService.getAllSkuBySpuId(spuId);

        // 收集skuId为List
        List<Long> skuIdList = skuList.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());

        // 2.查询所有可被用于检索的属性，attr表中search_type为1的
        // 2.1根据spuId查询所有属性并收集为List
        List<ProductAttrValueEntity> attrsBySpuId = attrValueService.baseAttrlistforspu(spuId);
        List<Long> attrIds = attrsBySpuId.stream().map(ProductAttrValueEntity::getId).collect(Collectors.toList());
        // 2.2过滤出可以被检索的属性
        HashSet<Long> idSet = attrService.getSearcheableAttrs(attrIds).stream().map(AttrEntity::getAttrId).collect(Collectors.toCollection(HashSet::new));
        // 2.3组装
        List<SkuESModel.Attrs> attrsList = attrsBySpuId.stream().filter(item -> idSet.contains(item.getAttrId())).map(attrEntity -> {
            SkuESModel.Attrs attrs = new SkuESModel.Attrs();
            attrs.setAttrId(attrEntity.getAttrId());
            attrs.setAttrName(attrEntity.getAttrName());
            attrs.setAttrValue(attrEntity.getAttrValue());
            return attrs;
        }).collect(Collectors.toList());

        // 3.查询sku的库存情况
        Map<Long, Boolean> booleanMap = null;
        try {
            // 一次性的查询各个sku是否有库存
            R hasStockR = wareFeignService.hasStock(skuIdList);
            TypeReference<List<SkuStockVO>> typeReference = new TypeReference<List<SkuStockVO>>() {
            };
            // 收集为map，key是skuId，value是它是否有库存的boolean
            booleanMap = hasStockR.getData(typeReference).stream().collect(Collectors.toMap(SkuStockVO::getSkuId, SkuStockVO::getHasStock));
        } catch (Exception e) {
            log.error("库存服务查询异常，原因：{}", e);
        }

        Map<Long, Boolean> finalBooleanMap = booleanMap;

        // 3.组装
        products = skuList.stream().map(sku -> {
            SkuESModel esModel = new SkuESModel();

            BeanUtils.copyProperties(sku, esModel);
            esModel.setSkuPrice(sku.getPrice());
            esModel.setSkuImg(sku.getSkuDefaultImg());

            if (finalBooleanMap == null) {
                esModel.setHasStock(true);
            } else {
                esModel.setHasStock(finalBooleanMap.get(sku.getSkuId()));
            }

            // 刚上架的商品热度评分默认给个0
            esModel.setHotScore(0L);

            BrandEntity brand = brandService.getById(sku.getBrandId());
            esModel.setBrandName(brand.getName());
            esModel.setBrandImg(brand.getLogo());

            CategoryEntity category = categoryService.getById(sku.getCatalogId());
            esModel.setCatalogName(category.getName());
            esModel.setAttrs(attrsList);

            return esModel;
        }).collect(Collectors.toList());

        R up = elasticFeignService.up(products);
        if (up.getCode() == 0) {
            // 上架成功，修改spu的状态
            this.update(new UpdateWrapper<SpuInfoEntity>().lambda()
                    .set(SpuInfoEntity::getPublishStatus, ProductConstant.StatusEnum.SPU_UP.getCode())
                    .eq(SpuInfoEntity::getId, spuId));
        } else {
            // TODO 重试机制
        }
    }

}