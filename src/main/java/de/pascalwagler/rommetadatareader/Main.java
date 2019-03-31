package de.pascalwagler.rommetadatareader;

import de.pascalwagler.rommetadatareader.roms.MetadataReader;
import de.pascalwagler.rommetadatareader.roms.RomMetadata;
import de.pascalwagler.rommetadatareader.roms.gb.GbFileTypeDetector;
import de.pascalwagler.rommetadatareader.roms.gb.GbMetadataReader;
import de.pascalwagler.rommetadatareader.roms.gba.GbaFileTypeDetector;
import de.pascalwagler.rommetadatareader.roms.gba.GbaMetadataReader;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileTypeDetector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Main {

    private static final HashMap<String, MetadataReader> readers = new HashMap<>();

    static {
        readers.put("application/x-gb-rom", new GbMetadataReader());
        readers.put("application/x-gba-rom", new GbaMetadataReader());
    }

    private static final List<FileTypeDetector> fileTypeDetectors = new ArrayList<>();

    static {
        fileTypeDetectors.add(new GbFileTypeDetector());
        fileTypeDetectors.add(new GbaFileTypeDetector());
    }

    private static MetadataReader chooseMetadataReader(Path path) throws IOException {

        for (FileTypeDetector detector : fileTypeDetectors) {
            String contentType = detector.probeContentType(path);
            if (contentType != null) {
                return readers.get(contentType);
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("/Users/Pascal/Documents/Games/Game Boy Advance/");
        System.out.println(path.toAbsolutePath());

        List<Path> list = listSourceFiles(path);
        for (Path p : list) {
            System.out.println(p);

            MetadataReader reader = chooseMetadataReader(p);

            if (reader == null) {
                System.out.println("Not a recognized ROM file!");
                continue;
            }

            RomMetadata metadata = reader.getMetadata(p);
            System.out.println(metadata);
        }
    }

    private static List<Path> listSourceFiles(Path dir) throws IOException {

        List<Path> result = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*")) {
            for (Path entry : stream) {
                result.add(entry);
            }
        } catch (DirectoryIteratorException ex) {
            throw ex.getCause();
        }
        return result;
    }
}
