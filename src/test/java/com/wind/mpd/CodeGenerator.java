package com.wind.mpd;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author wind
 */
public class CodeGenerator {

    private static String url = "jdbc:mysql://localhost:3306/node_test";
    private static String username = "root";
    private static String password = "123456";

    private static String outDir = "D:\\project\\github\\mybatis-plus-demo\\src\\main\\java";
    private static String xmlOutDir = outDir + "\\com\\wind\\mpd\\auto\\entity\\mapper";

    public static void main(String[] args) {
        generateCode();
    }

    public static void generateCode() {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("lxz") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.wind.mpd.auto")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, xmlOutDir)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("node")
                            .mapperBuilder()
                            .enableMapperAnnotation()
                            .controllerBuilder()
                            .enableRestStyle(); // 设置需要生成的表名
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
