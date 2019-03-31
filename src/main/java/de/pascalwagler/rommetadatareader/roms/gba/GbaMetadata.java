package de.pascalwagler.rommetadatareader.roms.gba;

import de.pascalwagler.rommetadatareader.roms.RomMetadata;

import lombok.Data;

@Data
public class GbaMetadata implements RomMetadata {

    private String title;
    private String gameCode;
    private String makerCode;
    private String unitCode;
    private String versionCode;
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
