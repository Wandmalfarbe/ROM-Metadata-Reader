package de.pascalwagler.rommetadatareader.roms;

import java.io.IOException;
import java.nio.file.Path;

public interface MetadataReader {

    RomMetadata getMetadata(Path path) throws IOException;
}
