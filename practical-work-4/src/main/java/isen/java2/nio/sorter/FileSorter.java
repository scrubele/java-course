package isen.java2.nio.sorter;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSorter {

    private static final String ARCHIVE_DIR = "archive";
    private static final String BY_EXT_DIR = "byext";
    private static final String ANIMALS_DIR = "animals";

    private Path root;
    private Path archive;
    private Path byExtension;
    private Path animals;

    public FileSorter(String rootDir) throws FileSortException {
        try {
            root = Paths.get(rootDir);
            animals = root.resolve(ANIMALS_DIR);
            archive = prepareDirectory(ARCHIVE_DIR, root);
            byExtension = prepareDirectory(BY_EXT_DIR, root);
        } catch (IOException e) {
            throw new FileSortException("Directories can't be created.", e);
        }
    }

    public Path prepareDirectory(String newDir, Path base) throws IOException {
        Path path = base.resolve(newDir);
        System.out.println("Creating " + newDir + "...");
        System.out.println(path);
        Files.createDirectories(path);

        return path;
    }

    public int sortFiles() throws FileSortException {
        System.out.println("animals " + animals);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(animals)) {
            int count = 0;
            for (Path entry : stream) {
                String extension = getExtension(entry);
                Path directory = prepareDirectory(extension, byExtension);
                System.out.println("directory " + directory);
                copyFile(entry, directory);
                moveFileToArchive(entry);
            }
            return count;
        } catch (IOException e) {
            throw new FileSortException("Some error has appeared during the sorting.", e);
        }
    }

    protected void moveFileToArchive(Path entry) throws IOException {
        String filename = entry.getFileName().toString();
        Files.move(entry, archive.resolve(filename));
    }

    protected void copyFile(Path entry, Path directory) throws IOException {
        String filename = entry.getFileName().toString();
        Path target = directory.resolve(filename);
        Files.copy(entry, target);
    }

    protected String getExtension(Path entry) {
        String fileName = String.valueOf(entry.getFileName());
        String extension = fileName.substring(fileName.lastIndexOf("."));
        return extension;
    }

}
