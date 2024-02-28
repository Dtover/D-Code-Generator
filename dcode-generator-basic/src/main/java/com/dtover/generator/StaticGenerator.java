package com.dtover.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;

public class StaticGenerator {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        File projectFile = new File(projectPath);
        // 输入路径：ACM实例代码模板目录
        String inputPath = new File(projectFile, "dcode-generator-demo-project/acm-template").getAbsolutePath();
        System.out.println(inputPath);
        System.out.println(projectPath);
        copyFilesByHutool(inputPath, projectPath);
    }

    /**
     * 拷贝文件（Hutool)
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByHutool(String inputPath, String outputPath){
        FileUtil.copy(inputPath, outputPath, false);
    }
}
