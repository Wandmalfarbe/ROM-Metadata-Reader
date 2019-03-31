package de.pascalwagler.rommetadatareader;

import java.util.HashMap;
import java.util.Map;

public class GameBoyUtil {

    public static final Map<String, String> cardridgeTypes = new HashMap<>();

    static {
        cardridgeTypes.put("00", "ROM");
        cardridgeTypes.put("01", "ROM+MBC1");
        cardridgeTypes.put("02", "ROM+MBC1+RAM");
        cardridgeTypes.put("03", "ROM+MBC1+RAM+BATT");
        cardridgeTypes.put("05", "ROM+MBC2");
        cardridgeTypes.put("06", "ROM+MBC2+BATT");
        cardridgeTypes.put("0B", "ROM+MMM01");
        cardridgeTypes.put("0C", "ROM+MMM01+RAM");
        cardridgeTypes.put("0D", "ROM+MMM01+RAM+BATT");
        cardridgeTypes.put("0F", "ROM+MBC3+TIMER+BATT");
        cardridgeTypes.put("10", "ROM+MBC3+TIMER+RAM+BATT");
        cardridgeTypes.put("11", "ROM+MBC3");
        cardridgeTypes.put("12", "ROM+MBC3+RAM");
        cardridgeTypes.put("13", "ROM+MBC3+RAM+BATT");
        cardridgeTypes.put("19", "ROM+MBC5");
        cardridgeTypes.put("1A", "ROM+MBC5+RAM");
        cardridgeTypes.put("1B", "ROM+MBC5+RAM+BATT");
        cardridgeTypes.put("1C", "ROM+MBC5+RUMBLE");
        cardridgeTypes.put("1D", "ROM+MBC5+RUMBLE+RAM");
        cardridgeTypes.put("1E", "ROM+MBC5+RUMBLE+RAM+BATT");
        cardridgeTypes.put("22", "ROM+MBC7+BATT");
        cardridgeTypes.put("55", "GameGenie");
        cardridgeTypes.put("56", "GameShark V3.0");
        cardridgeTypes.put("FC", "ROM+POCKET CAMERA");
        cardridgeTypes.put("FD", "ROM+BANDAI TAMA5");
        cardridgeTypes.put("FE", "ROM+HuC-3");
        cardridgeTypes.put("FF", "ROM+HuC-1");
    }

    public static final Map<String, String> romSizesDescription = new HashMap<>();

    static {
        romSizesDescription.put("00", "value.rom_size.32kb");
        romSizesDescription.put("01", "value.rom_size.64kb");
        romSizesDescription.put("02", "value.rom_size.128kb");
        romSizesDescription.put("03", "value.rom_size.256kb");
        romSizesDescription.put("04", "value.rom_size.512kb");
        romSizesDescription.put("05", "value.rom_size.1mb");
        romSizesDescription.put("06", "value.rom_size.2mb");
        romSizesDescription.put("07", "value.rom_size.4mb");
        romSizesDescription.put("52", "value.rom_size.1_1mb");
        romSizesDescription.put("53", "value.rom_size.2_2mb");
        romSizesDescription.put("54", "value.rom_size.1_5mb");
    }

    public static final Map<String, String> ramSizesDescription = new HashMap<>();

    static {
        ramSizesDescription.put("00", "value.ram_size.0kb");
        ramSizesDescription.put("01", "value.ram_size.2kb");
        ramSizesDescription.put("02", "value.ram_size.8kb");
        ramSizesDescription.put("03", "value.ram_size.32kb");
        ramSizesDescription.put("04", "value.ram_size.128kb");
        ramSizesDescription.put("05", "value.ram_size.64kb");
    }

    public static final Map<String, String> destinationCodesDescription = new HashMap<>();

    static {
        destinationCodesDescription.put("00", "value.destination_code.japan");
        destinationCodesDescription.put("01", "value.destination_code.not_japan");
    }

    public static final Map<String, String> destinationCodesImage = new HashMap<>();

    static {
        destinationCodesImage.put("00", "japan");
        destinationCodesImage.put("01", "not-japan");
    }

    public static final Map<String, String> cgbFlagsDescription = new HashMap<>();

    static {
        cgbFlagsDescription.put("00", "value.unknown");
        cgbFlagsDescription.put("80", "value.cgb.cgb_support");
        cgbFlagsDescription.put("C0", "value.cgb.only_cgb_support");
    }

    public static final Map<String, String> sgbFlagsDescription = new HashMap<>();

    static {
        sgbFlagsDescription.put("00", "value.sgb.no_sgb_support");
        sgbFlagsDescription.put("03", "value.sgb.sgb_support");
    }

    public static final Map<String, String> gameTypesDescription = new HashMap<>();

    static {
        gameTypesDescription.put("A", "value.game_type.normal_2001_2003");
        gameTypesDescription.put("B", "value.game_type.normal_2003");
        gameTypesDescription.put("C", "value.game_type.normal_newer");
        gameTypesDescription.put("F", "value.game_type.software_nes");
        gameTypesDescription.put("K", "value.game_type.acceleration_sensor");
        gameTypesDescription.put("P", "value.game_type.dot_code_scanner");
        gameTypesDescription.put("R", "value.game_type.rumble_z_gyro");
        gameTypesDescription.put("U", "value.game_type.real_time_clock_solar");
        gameTypesDescription.put("V", "value.game_type.rumble");
        gameTypesDescription.put("Z", "value.game_type.play_yan");
    }

    public static final HashMap<String, String> countriesDescription = new HashMap<>();

    static {
        countriesDescription.put("J", "value.country.japan");
        countriesDescription.put("E", "value.country.usa_english");
        countriesDescription.put("P", "value.country.europe_elsewhere");
        countriesDescription.put("D", "value.country.germany");
        countriesDescription.put("F", "value.country.france");
        countriesDescription.put("I", "value.country.italy");
        countriesDescription.put("S", "value.country.spain");
        //countriesDescription.put("Y", "value.unknown"); // ???
    }

    public static final HashMap<String, String> countriesImage = new HashMap<>();

    static {
        countriesImage.put("J", "japan");
        countriesImage.put("E", "usa");
        countriesImage.put("P", "europe");
        countriesImage.put("D", "germany");
        countriesImage.put("F", "france");
        countriesImage.put("I", "italy");
        countriesImage.put("S", "spain");
        //countriesImage.put("X", "unknown"); // Europe?
        //countriesImage.put("Y", "unknown"); // ???
    }

    public static final HashMap<String, String> manufacturers = new HashMap<>();

    static {
        manufacturers.put("01", "Nintendo");
        manufacturers.put("02", "Rocket Games");
        manufacturers.put("08", "Capcom");
        manufacturers.put("09", "Hot B Co.");
        manufacturers.put("0A", "Jaleco");
        manufacturers.put("0B", "Coconuts Japan");
        manufacturers.put("0C", "Coconuts Japan/G.X.Media");
        manufacturers.put("0H", "Starfish");
        manufacturers.put("0L", "Warashi Inc.");
        manufacturers.put("0N", "Nowpro");
        manufacturers.put("0P", "Game Village");
        manufacturers.put("13", "Electronic Arts Japan");
        manufacturers.put("18", "Hudson Soft Japan");
        manufacturers.put("19", "S.C.P.");
        manufacturers.put("1A", "Yonoman");
        manufacturers.put("1G", "SMDE");
        manufacturers.put("1P", "Creatures Inc.");
        manufacturers.put("1Q", "TDK Deep Impresion");
        manufacturers.put("20", "Destination Software");
        manufacturers.put("22", "VR 1 Japan");
        manufacturers.put("25", "San-X");
        manufacturers.put("28", "Kemco Japan");
        manufacturers.put("29", "Seta");
        manufacturers.put("2H", "Ubisoft Japan");
        manufacturers.put("2K", "NEC InterChannel");
        manufacturers.put("2L", "Tam");
        manufacturers.put("2M", "Jordan");
        manufacturers.put("2N", "Smilesoft");
        manufacturers.put("2Q", "Mediakite");
        manufacturers.put("36", "Codemasters");
        manufacturers.put("37", "GAGA Communications");
        manufacturers.put("38", "Laguna");
        manufacturers.put("39", "Telstar Fun and Games");
        manufacturers.put("41", "Ubi Soft Entertainment");
        manufacturers.put("42", "Sunsoft");
        manufacturers.put("47", "Spectrum Holobyte");
        manufacturers.put("49", "IREM");
        manufacturers.put("4D", "Malibu Games");
        manufacturers.put("4F", "Eidos/U.S. Gold");
        manufacturers.put("4J", "Fox Interactive");
        manufacturers.put("4K", "Time Warner Interactive");
        manufacturers.put("4Q", "Disney");
        manufacturers.put("4S", "Black Pearl");
        manufacturers.put("4X", "GT Interactive");
        manufacturers.put("4Y", "RARE");
        manufacturers.put("4Z", "Crave Entertainment");
        manufacturers.put("50", "Absolute Entertainment");
        manufacturers.put("51", "Acclaim");
        manufacturers.put("52", "Activision");
        manufacturers.put("53", "American Sammy Corp.");
        manufacturers.put("54", "Take 2 Interactive");
        manufacturers.put("55", "Hi Tech");
        manufacturers.put("56", "LJN LTD.");
        manufacturers.put("58", "Mattel");
        manufacturers.put("5A", "Mindscape/Red Orb Ent.");
        manufacturers.put("5C", "Taxan");
        manufacturers.put("5D", "Midway");
        manufacturers.put("5F", "American Softworks");
        manufacturers.put("5G", "Majesco Sales Inc");
        manufacturers.put("5H", "3DO");
        manufacturers.put("5K", "Hasbro");
        manufacturers.put("5L", "NewKidCo");
        manufacturers.put("5M", "Telegames");
        manufacturers.put("5N", "Metro3D");
        manufacturers.put("5P", "Vatical Entertainment");
        manufacturers.put("5Q", "LEGO Media");
        manufacturers.put("5S", "Xicat Interactive");
        manufacturers.put("5T", "Cryo Interactive");
        manufacturers.put("5W", "Red Storm Ent./BKN Ent.");
        manufacturers.put("5X", "Microids");
        manufacturers.put("5Z", "Conspiracy Entertainment Corp.");
        manufacturers.put("60", "Titus Interactive Studios");
        manufacturers.put("61", "Virgin Interactive");
        manufacturers.put("62", "Maxis");
        manufacturers.put("64", "LucasArts Entertainment");
        manufacturers.put("67", "Ocean");
        manufacturers.put("69", "Electronic Arts");
        manufacturers.put("6E", "Elite Systems Ltd.");
        manufacturers.put("6F", "Electro Brain");
        manufacturers.put("6G", "The Learning Company");
        manufacturers.put("6H", "BBC");
        manufacturers.put("6J", "Software 2000");
        manufacturers.put("6L", "BAM! Entertainment");
        manufacturers.put("6M", "Studio 3");
        manufacturers.put("6Q", "Classified Games");
        manufacturers.put("6S", "TDK Mediactive");
        manufacturers.put("6U", "DreamCatcher");
        manufacturers.put("6V", "JoWood Productions");
        manufacturers.put("6W", "SEGA");
        manufacturers.put("6X", "Wannado Edition");
        manufacturers.put("6Y", "LSP");
        manufacturers.put("6Z", "ITE Media");
        manufacturers.put("70", "Infogrames");
        manufacturers.put("71", "Interplay");
        manufacturers.put("72", "JVC Musical Industries Inc");
        manufacturers.put("73", "Parker Brothers");
        manufacturers.put("75", "SCI");
        manufacturers.put("78", "THQ");
        manufacturers.put("79", "Accolade");
        manufacturers.put("7A", "Triffix Ent. Inc.");
        manufacturers.put("7C", "Microprose Software");
        manufacturers.put("7D", "Universal Interactive Studios");
        manufacturers.put("7F", "Kemco");
        manufacturers.put("7G", "Rage Software");
        manufacturers.put("7H", "Encore");
        manufacturers.put("7J", "Zoo");
        manufacturers.put("7K", "BVM");
        manufacturers.put("7L", "Simon & Schuster Interactive");
        manufacturers.put("7M", "Asmik Ace Entertainment Inc./AIA");
        manufacturers.put("7N", "Empire Interactive");
        manufacturers.put("7Q", "Jester Interactive");
        manufacturers.put("7T", "Scholastic");
        manufacturers.put("7U", "Ignition Entertainment");
        manufacturers.put("7W", "Stadlbauer");
        manufacturers.put("80", "Misawa");
        manufacturers.put("83", "LOZC");
        manufacturers.put("8B", "Bulletproof Software");
        manufacturers.put("8C", "Vic Tokai Inc.");
        manufacturers.put("8J", "General Entertainment");
        manufacturers.put("8N", "Success");
        manufacturers.put("8P", "SEGA Japan");
        manufacturers.put("91", "Chun Soft");
        manufacturers.put("92", "Video System");
        manufacturers.put("93", "BEC");
        manufacturers.put("96", "Yonezawa/S'pal");
        manufacturers.put("97", "Kaneko");
        manufacturers.put("99", "Victor Interactive Software");
        manufacturers.put("9A", "Nichibutsu/Nihon Bussan");
        manufacturers.put("9B", "Tecmo");
        manufacturers.put("9C", "Imagineer");
        manufacturers.put("9F", "Nova");
        manufacturers.put("9H", "Bottom Up");
        manufacturers.put("9L", "Hasbro Japan");
        manufacturers.put("9N", "Marvelous Entertainment");
        manufacturers.put("9P", "Keynet Inc.");
        manufacturers.put("9Q", "Hands-On Entertainment");
        manufacturers.put("A0", "Telenet");
        manufacturers.put("A1", "Hori");
        manufacturers.put("A4", "Konami");
        manufacturers.put("A6", "Kawada");
        manufacturers.put("A7", "Takara");
        manufacturers.put("A9", "Technos Japan Corp.");
        manufacturers.put("AA", "JVC");
        manufacturers.put("AC", "Toei Animation");
        manufacturers.put("AD", "Toho");
        manufacturers.put("AF", "Namco");
        manufacturers.put("AG", "Media Rings Corporation");
        manufacturers.put("AH", "J-Wing");
        manufacturers.put("AK", "KID");
        manufacturers.put("AL", "MediaFactory");
        manufacturers.put("AP", "Infogrames Hudson");
        manufacturers.put("AQ", "Kiratto. Ludic Inc");
        manufacturers.put("B0", "Acclaim Japan");
        manufacturers.put("B1", "ASCII");
        manufacturers.put("B2", "Bandai");
        manufacturers.put("B4", "Enix");
        manufacturers.put("B6", "HAL Laboratory");
        manufacturers.put("B7", "SNK");
        manufacturers.put("B9", "Pony Canyon Hanbai");
        manufacturers.put("BA", "Culture Brain");
        manufacturers.put("BB", "Sunsoft");
        manufacturers.put("BD", "Sony Imagesoft");
        manufacturers.put("BF", "Sammy");
        manufacturers.put("BG", "Magical");
        manufacturers.put("BJ", "Compile");
        manufacturers.put("BL", "MTO Inc.");
        manufacturers.put("BN", "Sunrise Interactive");
        manufacturers.put("BP", "Global A Entertainment");
        manufacturers.put("BQ", "Fuuki");
        manufacturers.put("C0", "Taito");
        manufacturers.put("C2", "Kemco");
        manufacturers.put("C3", "Square Soft");
        manufacturers.put("C5", "Data East");
        manufacturers.put("C6", "Tonkin House");
        manufacturers.put("C8", "Koei");
        manufacturers.put("CA", "Konami/Palcom/Ultra");
        manufacturers.put("CB", "Vapinc/NTVIC");
        manufacturers.put("CC", "Use Co.,Ltd.");
        manufacturers.put("CD", "Meldac");
        manufacturers.put("CE", "FCI/Pony Canyon");
        manufacturers.put("CF", "Angel");
        manufacturers.put("CM", "Konami Computer Entertainment Osaka");
        manufacturers.put("CP", "Enterbrain");
        manufacturers.put("D1", "Sofel");
        manufacturers.put("D2", "Quest");
        manufacturers.put("D3", "Sigma Enterprises");
        manufacturers.put("D4", "Ask Kodansa");
        manufacturers.put("D6", "Naxat");
        manufacturers.put("D7", "Copya System");
        manufacturers.put("D9", "Banpresto");
        manufacturers.put("DA", "TOMY");
        manufacturers.put("DB", "LJN Japan");
        manufacturers.put("DD", "NCS");
        manufacturers.put("DF", "Altron Corporation");
        manufacturers.put("DH", "Gaps Inc.");
        manufacturers.put("DN", "ELF");
        manufacturers.put("E2", "Yutaka");
        manufacturers.put("E3", "Varie");
        manufacturers.put("E5", "Epoch");
        manufacturers.put("E7", "Athena");
        manufacturers.put("E8", "Asmik Ace Entertainment Inc.");
        manufacturers.put("E9", "Natsume");
        manufacturers.put("EA", "King Records");
        manufacturers.put("EB", "Atlus");
        manufacturers.put("EC", "Epic/Sony Records");
        manufacturers.put("EE", "IGS");
        manufacturers.put("EL", "Spike");
        manufacturers.put("EM", "Konami Computer Entertainment Tokyo");
        manufacturers.put("EN", "Alphadream Corporation");
        manufacturers.put("F0", "A Wave");
        manufacturers.put("G1", "PCCW");
        manufacturers.put("G4", "KiKi Co Ltd");
        manufacturers.put("G5", "Open Sesame Inc.");
        manufacturers.put("G6", "Sims");
        manufacturers.put("G7", "Broccoli");
        manufacturers.put("G8", "Avex");
        manufacturers.put("G9", "D3 Publisher");
        manufacturers.put("GB", "Konami Computer Entertainment Japan");
        manufacturers.put("GD", "Square-Enix");
        manufacturers.put("HY", "Sachen");
    }
}
