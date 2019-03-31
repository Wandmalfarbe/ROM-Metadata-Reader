package de.pascalwagler.rommetadatareader.roms.gb;

import de.pascalwagler.rommetadatareader.BinUtil;
import de.pascalwagler.rommetadatareader.roms.MetadataReader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

@SuppressWarnings("FieldCanBeLocal")
public class GbMetadataReader implements MetadataReader {

    private final int HEADER_LENGTH = 335; // 0x014F

    private final int TITLE_OFFSET = 308; // 0x134
    private final int TITLE_LENGTH = 16;

    private final int GAME_CODE_OFFSET = 319; // 0x13F
    private final int GAME_CODE_LENGTH = 4;

    private final int CGB_FLAG_OFFSET = 323; // 0x143

    private final int NEW_LICENSEE_CODE_OFFSET = 324; // 0x144
    private final int NEW_LICENSEE_CODE_LENGTH = 2;

    private final int SGB_FLAG_OFFSET = 326; // 0x146

    private final int CARTRIDGE_TYPE_OFFSET = 327; // 0x147

    private final int ROM_SIZE_OFFSET = 328; // 0x148

    private final int RAM_SIZE_OFFSET = 329; // 0x149

    private final int DESTINATION_CODE_OFFSET = 330; // 0x14A

    private final int OLD_LICENSEE_CODE_OFFSET = 331; // 0x14B

    private final int MASK_ROM_VERSION_NUMBER_OFFSET = 332; // 0x14C

    private final int HEADER_CHECKSUM_OFFSET = 333; // 0x14A

    public GbMetadata getMetadata(Path path) throws IOException {

        // The first 335 bytes in ROM are used as cartridge header.
        RandomAccessFile file2 = new RandomAccessFile(path.toString(), "r");
        byte[] gbaHeader = new byte[HEADER_LENGTH];
        file2.read(gbaHeader);
        file2.close();

        String title = BinUtil.asciiToString(gbaHeader, TITLE_OFFSET, TITLE_LENGTH);
        String gameCode = BinUtil.asciiToString(gbaHeader, GAME_CODE_OFFSET, GAME_CODE_LENGTH);
        String cgbFlag = BinUtil.byteToHex(gbaHeader[CGB_FLAG_OFFSET]);
        String newLicenseeCode = BinUtil.asciiToString(gbaHeader, NEW_LICENSEE_CODE_OFFSET, NEW_LICENSEE_CODE_LENGTH);
        String sgbFlag = BinUtil.byteToHex(gbaHeader[SGB_FLAG_OFFSET]);
        String cartridgeType = BinUtil.byteToHex(gbaHeader[CARTRIDGE_TYPE_OFFSET]);
        String romSize = BinUtil.byteToHex(gbaHeader[ROM_SIZE_OFFSET]);
        String ramSize = BinUtil.byteToHex(gbaHeader[RAM_SIZE_OFFSET]);
        String destinationCode = BinUtil.byteToHex(gbaHeader[DESTINATION_CODE_OFFSET]);
        String oldLicenseeCode = BinUtil.byteToHex(gbaHeader[OLD_LICENSEE_CODE_OFFSET]);
        String maskRomVersion = BinUtil.byteToHex(gbaHeader[MASK_ROM_VERSION_NUMBER_OFFSET]);
        String headerChecksum = BinUtil.byteToHex(gbaHeader[HEADER_CHECKSUM_OFFSET]);

        GbMetadata metadata = new GbMetadata();
        metadata.setTitle(title);
        metadata.setGameCode(gameCode);
        metadata.setCgbFlag(cgbFlag);
        metadata.setNewLicenseeCode(newLicenseeCode);
        metadata.setSgbFlag(sgbFlag);
        metadata.setCartridgeType(cartridgeType);
        metadata.setRomSize(romSize);
        metadata.setRamSize(ramSize);
        metadata.setDestinationCode(destinationCode);
        metadata.setOldLicenseeCode(oldLicenseeCode);
        metadata.setMaskRomVersionNumber(maskRomVersion);
        metadata.setHeaderChecksum(headerChecksum);

        return metadata;
    }
}
