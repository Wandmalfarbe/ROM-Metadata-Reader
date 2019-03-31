package de.pascalwagler.rommetadatareader.roms.nds;

import de.pascalwagler.rommetadatareader.roms.RomMetadata;

import lombok.Data;

@Data
public class NdsMetadata implements RomMetadata {

    private String title;
    private String gameCode;
    private String makerCode;
    private String unitCode;
}
