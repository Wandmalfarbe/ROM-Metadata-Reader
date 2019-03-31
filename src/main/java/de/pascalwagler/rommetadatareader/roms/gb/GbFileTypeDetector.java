package de.pascalwagler.rommetadatareader.roms.gb;

import de.pascalwagler.rommetadatareader.roms.RomFileTypeDetector;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * FileTypeDetector for Game Boy ROMs.
 */
@SuppressWarnings("FieldCanBeLocal")
public class GbFileTypeDetector extends RomFileTypeDetector {

    public GbFileTypeDetector() {

        extensions.add("gb");
        shortType = "gb";
    }

    /**
     * The bytes for the Nintendo logo as an array of unsigned ints.
     * When comparing with bytes one can use Byte.toUnsignedInt(byte).
     */
    private static final int[] NINTENDO_LOGO = {
            0xCE, 0xED, 0x66, 0x66, 0xCC, 0x0D, 0x00, 0x0B, 0x03, 0x73, 0x00, 0x83, 0x00, 0x0C, 0x00, 0x0D,
            0x00, 0x08, 0x11, 0x1F, 0x88, 0x89, 0x00, 0x0E, 0xDC, 0xCC, 0x6E, 0xE6, 0xDD, 0xDD, 0xD9, 0x99,
            0xBB, 0xBB, 0x67, 0x63, 0x6E, 0x0E, 0xEC, 0xCC, 0xDD, 0xDC, 0x99, 0x9F, 0xBB, 0xB9, 0x33, 0x3E
    };

    private final int NINTENDO_LOGO_OFFSET = 260; // 0x104 - 0x133

    @Override
    public String probeContentType(final Path path) throws IOException {

        String fileName = path.getFileName().toString();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        // Check the extensions first
        if (!extensions.contains(extension)) {
            return null;
        }

        final byte[] buf = new byte[NINTENDO_LOGO_OFFSET + NINTENDO_LOGO.length];
        final InputStream in = Files.newInputStream(path);

        // The file is not long enough (hasn't enough bytes to read the Nintendo logo).
        int readBytes = in.read(buf);
        if (readBytes != NINTENDO_LOGO_OFFSET + NINTENDO_LOGO.length) {
            return null;
        }

        // Check equality of the Nintendo logo
        for (int x = 0; x < NINTENDO_LOGO.length; x++) {
            if (Byte.toUnsignedInt(buf[NINTENDO_LOGO_OFFSET + x]) != NINTENDO_LOGO[x]) {
                return null;
            }
        }

        return "application/x-gb-rom";
    }
}