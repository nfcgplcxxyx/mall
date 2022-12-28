package com.jcfx.mall.thirdparty;

import com.aliyun.oss.OSSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallThirdPartyApplicationTests {

    @Autowired
    OSSClient ossClient;

    @Test
    public void test01() throws FileNotFoundException {
        InputStream is = new FileInputStream("HELP.md");
        ossClient.putObject("jjmall", "md", is);
        ossClient.shutdown();
    }

}
