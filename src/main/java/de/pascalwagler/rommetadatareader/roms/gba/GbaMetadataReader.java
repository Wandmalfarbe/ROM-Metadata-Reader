package de.pascalwagler.rommetadatareader.roms.gba;

import de.pascalwagler.rommetadatareader.BinUtil;
import de.pascalwagler.rommetadatareader.roms.MetadataReader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

@SuppressWarnings("FieldCanBeLocal")
public class GbaMetadataReader implements MetadataReader {

    private final int HEADER_LENGTH = 192; // 0xC0

    private final int TITLE_OFFSET = 160; // 0xA0
    private final int TITLE_LENGTH = 12;

    private final int GAME_CODE_OFFSET = 172; // 0xAC
    private final int GAME_CODE_LENGTH = 4;

    private final int MAKER_CODE_OFFSET = 176; // 0xB0
    private final int MAKER_CODE_LENGTH = 2;

    private final int UNIT_CODE_OFFSET = 179; // 0xB3

    private final int VERSION_CODE_OFFSET = 188; // 0xBC

    private final int HEADER_CHECKSUM_OFFSET = 189; // 0xBD

    public GbaMetadata getMetadata(Path path) throws IOException {

        // The first 192 bytes in ROM are used as cartridge header.
        RandomAccessFile file2 = new RandomAccessFile(path.toString(), "r");
        byte[] gbaHeader = new byte[HEADER_LENGTH];
        file2.read(gbaHeader);
        file2.close();

        String title = BinUtil.asciiToString(gbaHeader, TITLE_OFFSET, TITLE_LENGTH);
        String gameCode = BinUtil.asciiToString(gbaHeader, GAME_CODE_OFFSET, GAME_CODE_LENGTH);
        String makerCode = BinUtil.asciiToString(gbaHeader, MAKER_CODE_OFFSET, MAKER_CODE_LENGTH);
        String unitCode = BinUtil.byteToHex(gbaHeader[UNIT_CODE_OFFSET]);
        String versionCode = BinUtil.byteToHex(gbaHeader[VERSION_CODE_OFFSET]);
        String headerChecksum = BinUtil.byteToHex(gbaHeader[HEADER_CHECKSUM_OFFSET]);

        GbaMetadata metadata = new GbaMetadata();
        metadata.setTitle(title);
        metadata.setGameCode(gameCode);
        metadata.setMakerCode(makerCode);
        metadata.setUnitCode(unitCode);
        metadata.setVersionCode(versionCode);
        metadata.setHeaderChecksum(headerChecksum);

        return metadata;
    }
}
