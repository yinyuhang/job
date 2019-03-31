package com.hfnu.job;

import com.hfnu.job.pojo.Company;
import com.hfnu.job.pojo.Employment;
import com.shark.generator.FileType;
import com.shark.generator.Generator;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class TempGenerator {
    @Test
    public void test1() throws IOException, URISyntaxException {
        FileType[] types = {FileType.REPOSITORY, FileType.JS, FileType.HTML, FileType.CONTROLLER};
        Class<?>[] clses = {Company.class, Employment.class};
        for (FileType type : types) {
            for (Class<?> cls : clses) {
                Generator.init(cls, type);
            }
        }
    }
}
