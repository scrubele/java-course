package main.java.isen.java2.nio.app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class Generator {

    public static void main(String[] args) {
        Random random = new SecureRandom();
        String[] extensions = {".doc", ".xls", ".png", ".bmp", ".jpg", ".gif", ".html", ".css", ".java", ".js"};
        try {
            Path path = Paths.get(Generator.class.getClassLoader().getResource("animals.txt").toURI());
            List<String> lignes = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String ligne : lignes) {
                String extension = extensions[random.nextInt(extensions.length)];
                Path fichier = Paths.get("tmp/data", ligne + extension);
                if (!Files.exists(fichier)) {
                    System.out.println(fichier.toUri());
                    Files.createFile(fichier);
                }

            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

}
