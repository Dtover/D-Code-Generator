package com.dtover.generator;

import com.dtover.constant.CommonConstant;
import com.dtover.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 */
public class MainGenerator {
    /**
     * 生成
     */
    public static void doGenerator(Object model) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        // 输入路径
        String inputPath = new File(projectPath, "dcode-generator-demo-project/acm-template").getAbsolutePath();
        String outputPath = projectPath;

        // 生成静态文件
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);
        // 生成动态文件
        String projectPathBasic = projectPath + File.separator + "dcode-generator-basic";
        String inputDynamicFilePath = projectPathBasic + File.separator + CommonConstant.TMEPLATE_DIR_PATH + File.separator + "MainTemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/dtover/acm/MainTemplate.java";
        DynamicGenerator.doGenerator(inputDynamicFilePath, outputDynamicFilePath, model);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        // 模板填值
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("鎖夢");
        // 不使用循环
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerator(mainTemplateConfig);
    }
}
