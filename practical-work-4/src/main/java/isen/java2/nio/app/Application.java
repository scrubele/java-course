package isen.java2.nio.app;

import isen.java2.nio.sorter.FileSortException;
import isen.java2.nio.sorter.FileSorter;

import java.io.IOException;
import java.util.Properties;


public class Application {

    public static void main(String[] args) {
        try {
            FileSorter fileSorter = new FileSorter(getRootDir());
            int count = fileSorter.sortFiles();
            System.err.println("Played with " + count + " files !");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileSortException e) {
            e.printStackTrace();
        }

    }

    private static String getRootDir() throws IOException {
        Properties properties = new Properties();
        properties.load(Application.class.getClassLoader().getResourceAsStream("nio.properties"));
        return properties.getProperty("rootDir");
    }

}
