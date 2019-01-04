package com.hc.demo;

import com.hc.demo.hc.entity.File;
import com.hc.demo.hc.mapper.FileMapper;
import com.hc.demo.hc.service.IFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private IFileService iFileService;
//    @Autowired
//    private FileMapper fileMapper;

    @Test
    public void contextLoads() {
        File file = new File();
//        file.setId(null);

        file.setFilename("sa");
        file.setMimetype("image/jpeg");
//        file.setFile(new byte[]{1,2,3});
        iFileService.save(file);
    }

}

