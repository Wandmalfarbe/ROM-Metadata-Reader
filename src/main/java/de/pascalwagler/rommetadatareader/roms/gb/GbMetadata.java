package de.pascalwagler.rommetadatareader.roms.gb;

import de.pascalwagler.rommetadatareader.roms.RomMetadata;

import lombok.Data;

@Data
public class GbMetadata implements RomMetadata {

    private String title;
    private String gameCode;
    private String cgbFlag;
    private String newLicenseeCode;
    private String sgbFlag;
    private String cartridgeType;
    private String romSize;
    private String ramSize;
    private String destinationCode;
    private String oldLicenseeCode;
    private String maskRomVersionNumber;
    private String headerChecksum;

    public String getGameTypeChar() {
        if (gameCode.length() == 4) {
            return String.valueOf(gameCode.charAt(0));
        }
        return "";
    }

    public String getCountryChar() {
        if (gameCode.length() == 4) {
            return gameCode.substring(3);
        }
        return "";
    }
}
