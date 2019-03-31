package de.pascalwagler.rommetadatareader;

public class BinUtil {

    public static String asciiToString(byte[] byteArray, int offset, int length) {

        StringBuilder sb = new StringBuilder();
        for (int x = offset; x < offset + length; x++) {
            byte b = byteArray[x];
            if (isValidAscii(b)) {
                char c = (char) (b & 0xFF);
                sb.append(c);
            }
        }
        return sb.toString().trim();
    }

    /**
     * Returns true when the given byte is a valid ascii char (with a value between 32 and 126).
     */
    private static boolean isValidAscii(byte c) {
        return c >= 0x20 && c <= 0x7E;
    }

    public static String byteToHex(byte b) {
        return String.format("%02X", b);
    }
}
