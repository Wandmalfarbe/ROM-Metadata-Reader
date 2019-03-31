package de.pascalwagler.rommetadatareader.roms.nds;

import de.pascalwagler.rommetadatareader.BinUtil;
import de.pascalwagler.rommetadatareader.roms.MetadataReader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

@SuppressWarnings("FieldCanBeLocal")
public class NdsMetadataReader implements MetadataReader {

    private final int HEADER_LENGTH = 100; // ??

    private final int TITLE_OFFSET = 0; // 0x000
    private final int TITLE_LENGTH = 12;

    private final int GAME_CODE_OFFSET = 12; // 0x00C
    private final int GAME_CODE_LENGTH = 4;

    private final int MAKER_CODE_OFFSET = 16; // 0x010
    private final int MAKER_CODE_LENGTH = 2;

    private final int UNIT_CODE_OFFSET = 18; // 0x012

    public NdsMetadata getMetadata(Path path) throws IOException {

        RandomAccessFile file2 = new RandomAccessFile(path.toString(), "r");
        byte[] ndsHeader = new byte[HEADER_LENGTH];
        file2.read(ndsHeader);
        file2.close();

        String title = BinUtil.asciiToString(ndsHeader, TITLE_OFFSET, TITLE_LENGTH);
        String gameCode = BinUtil.asciiToString(ndsHeader, GAME_CODE_OFFSET, GAME_CODE_LENGTH);
        String makerCode = BinUtil.asciiToString(ndsHeader, MAKER_CODE_OFFSET, MAKER_CODE_LENGTH);
        String unitCode = BinUtil.byteToHex(ndsHeader[UNIT_CODE_OFFSET]);

        NdsMetadata metadata = new NdsMetadata();
        metadata.setTitle(title);
        metadata.setGameCode(gameCode);
        metadata.setMakerCode(makerCode);
        metadata.setUnitCode(unitCode);

        return metadata;
    }
}
