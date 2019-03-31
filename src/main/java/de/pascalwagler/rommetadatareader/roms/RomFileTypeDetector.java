package de.pascalwagler.rommetadatareader.roms;

import lombok.Getter;

import java.nio.file.spi.FileTypeDetector;
import java.util.ArrayList;
import java.util.List;

public abstract class RomFileTypeDetector extends FileTypeDetector {

    @Getter
    protected final List<String> extensions = new ArrayList<>();

    @Getter
    protected String shortType = "";
}
