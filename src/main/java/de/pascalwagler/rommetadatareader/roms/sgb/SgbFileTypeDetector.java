package de.pascalwagler.rommetadatareader.roms.sgb;

import de.pascalwagler.rommetadatareader.roms.gb.GbFileTypeDetector;

import java.io.IOException;
import java.nio.file.Path;


/**
 * FileTypeDetector for Super Game Boy ROMs.
 */
public class SgbFileTypeDetector extends GbFileTypeDetector {

    public SgbFileTypeDetector() {

        extensions.add("sgb"); // Super Game Boy (Game Boy module for the SNES)
        shortType = "sgb";
    }

    @Override
    public String probeContentType(final Path path) throws IOException {

        String contentType = super.probeContentType(path);

        if (contentType != null) {
            return "application/x-gb-rom";
        }
        return null;
    }
}