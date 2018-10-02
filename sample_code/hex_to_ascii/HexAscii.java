/**
 * Given strings of hexadecimal values w/ no delimiters, print out
 * their equivalent ASCII values (two hexits = one ASCII char)
 */

import java.util.*;

public class HexAscii {

    // Example
    // 0308480841d6e9f7f0ab3b7a41d6e9f7efbc9c742f4254432e544f502ffabe6d6d7aea36277344a18738693d73d00d18f7ee78cc056dcfc891203d644c1b05f4218000000000000000bf008dcb0000202021660000"

    /**
     * Given a string of hex value, create a String of its
     * equivalent ASCII characters and return.
     * If an error occurs, will give up and return the string
     * up to that point.
     * @param hex Hex representation (no delimiter, 2 hexits/char)
     * @return String ASCII representation (no delimiters)
     */

    public static String hex2Ascii(String hex) {
        StringBuilder toReturn = new StringBuilder();
        char asciiChar;
        String doubleHex;
        try {
            for (int j = 0; j < hex.length(); j += 2) {
                doubleHex = hex.substring(j, j + 2);
                asciiChar = (char) Integer.parseInt(doubleHex, 16);
                toReturn.append(asciiChar);
            }
        } catch (Exception ex) {
            System.out.println("Exception " + ex);
            // Just exit loop - this could happen if there is
            // an odd number of chars
        }
        return toReturn.toString();

    }

    public static void main(String[] args) {
        for (String s : args) {
            System.out.println(hex2Ascii(s));
        }
    }
}
