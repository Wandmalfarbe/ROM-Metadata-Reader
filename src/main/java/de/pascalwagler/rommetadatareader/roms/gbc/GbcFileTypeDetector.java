package de.pascalwagler.rommetadatareader.roms.gbc;

import de.pascalwagler.rommetadatareader.roms.gb.GbFileTypeDetector;

import java.io.IOException;
import java.nio.file.Path;


/**
 * FileTypeDetector for Game Boy Color ROMs.
 */
public final class GbcFileTypeDetector extends GbFileTypeDetector {

    public GbcFileTypeDetector() {

        extensions.add("gbc");
        extensions.add("cgb"); // Color Game Boy -> Game Boy Color

        shortType = "gbc";
    }

    @Override
    public String probeContentType(final Path path) throws IOException {

        String contentType = super.probeContentType(path);

        if (contentType != null) {
            return "application/x-gbc-rom";
        }
        return null;
    }
}