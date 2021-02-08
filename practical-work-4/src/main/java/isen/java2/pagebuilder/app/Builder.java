package main.java.isen.java2.pagebuilder.app;

import isen.java2.pagebuilder.builder.PageBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Builder {

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.load(Builder.class.getClassLoader().getResourceAsStream("builder.properties"));
            Path startFile = Paths.get(properties.getProperty("start.file"));
            Path outputFile = Paths.get(properties.getProperty("output.file"));
            PageBuilder builder = new PageBuilder(startFile, outputFile);

            builder.build();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
