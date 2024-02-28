package com.dtover.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class StaticGenerator {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        File projectFile = new File(projectPath);
        // 输入路径：ACM实例代码模板目录
        String inputPath = new File(projectFile, "dcode-generator-demo-project/acm-template").getAbsolutePath();
        copyFilesByRecursive(inputPath, projectPath);
    }

    /**
     * 拷贝文件（Hutool)
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByHutool(String inputPath, String outputPath){
        FileUtil.copy(inputPath, outputPath, false);
    }

    /**
     * 递归拷贝文件（递归实现）
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByRecursive(String inputPath, String outputPath){
       File inputFile = new File(inputPath);
       File outputFile = new File(outputPath);
       try{
           copyFileByRecursive(inputFile, outputFile);
       }catch(Exception e){
           System.err.println("文件复制失败");
           e.printStackTrace();
       }
    }

    /**
     * 文件A => 目录B,则文件A放在目录B下
     * 文件A => 文件B,则文件A覆盖文件B
     * 目录A => 目录B,则目录A放在目录B下
     *
     * 核心思路：先创建目录，然后便利目录内的文件，依次复制
     * @param inputFile
     * @param outputFile
     * @throws IOException
     */
    private static void copyFileByRecursive(File inputFile, File outputFile) throws IOException{
        // 区分是文件还是目录
        if(inputFile.isDirectory()){
            System.out.println(inputFile.getName());
            File destOutputFile = new File(outputFile, inputFile.getName());
            // 如果是目录，首先创建目标目录
            if(!destOutputFile.exists()){
                destOutputFile.mkdirs();
            }
            // 获取目录下的所有文件和子目录
            File[] files = inputFile.listFiles();
            // 无子文件，直接结束
            if(ArrayUtil.isEmpty(files)){
                return;
            }
            for(File file: files){
                // 递归拷贝下一层文件
                copyFileByRecursive(file, destOutputFile);
            }
        }else{
            // 是文件，直接复制到目标目录下
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
