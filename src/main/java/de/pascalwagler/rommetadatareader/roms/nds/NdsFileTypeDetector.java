package de.pascalwagler.rommetadatareader.roms.nds;

import de.pascalwagler.rommetadatareader.roms.RomFileTypeDetector;

import java.nio.file.Path;

/**
 * FileTypeDetector for Game Boy Advance ROMs.
 */
public final class NdsFileTypeDetector extends RomFileTypeDetector {

    public NdsFileTypeDetector() {
        extensions.add("nds"); // Game Boy Advance
        shortType = "nds";
    }

    @Override
    public String probeContentType(final Path path) {

        String fileName = path.getFileName().toString();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        // Check the extensions first
        if (!extensions.contains(extension)) {
            return null;
        }
        return "application/x-nds-rom";
    }
}