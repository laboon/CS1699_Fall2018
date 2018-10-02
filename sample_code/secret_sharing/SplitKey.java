import java.util.*;
import java.math.*;

public class SplitKey {


    /**
     * Print usage information and exit.
     * NOTE: EXITS PROGRAM WITH EXIT CODE 1.
     */
    public static void printUsageAndExit() {
        System.err.println("Usage: java SplitKey *128-bit # in hex*");
        System.exit(1);
    }

    /**
     * Given some BigInteger, convert it to a hex string.
     * @param bigint - Arbitrary BigInteger
     * @return String hex string version of byte array
     */

    private static String convertBigIntToHexString(BigInteger b) {
        byte[] bytes = b.toByteArray();
        StringBuffer toReturn = new StringBuffer();
        for (int j = 0; j < bytes.length; j++) {
            String hexit = String.format("%02x", bytes[j]);
            toReturn.append(hexit);
        }
        return toReturn.toString();
    }


    /**
     * Generates a pseudorandomly generated 128-bit integer.
     * @return pseudorandomly generated 128-bit integer
     */
    public static BigInteger generateRandom128BitInteger() {
        Random prng = new Random();
        return new BigInteger(128, prng);
    }

    /**
     * Given a 128-bit BigInteger S (secret key), generate a random
     * 128-bit number R, then return that and R XOR S.
     * @param s Secret key
     * @return array of BigIntegers, [0] is R and [1] is R XOR S
     */

    public static BigInteger[] splitKey(BigInteger s) {
        BigInteger[] toReturn = new BigInteger[2];
        BigInteger r = generateRandom128BitInteger();
        BigInteger x = s.xor(r);

        System.out.println("Secret s = " + s);
        System.out.println("Random r = " + r);
        System.out.println("XOR    x = " + x);
        toReturn[0] = r;
        toReturn[1] = x;
        return toReturn;
    }

    /**
     * Given a results array (a random BigInteger R and S XOR R
     * where S is the secret key to split), print them to the screen.
     * @param results - zeroth element is R, element 1 is S XOR R
     */

    public static void printResults(BigInteger[] results) {
        System.out.println("R      : " +
                           convertBigIntToHexString(results[0]));
        System.out.println("R XOR S: " +
                           convertBigIntToHexString(results[1]));
    }

    public static void main(String[] args) {

        if (args.length != 1 || args[0].length() != 32) {
            printUsageAndExit();
        }

        try {
            // Read as hex, i.e. radix = 16
            BigInteger toSplit = new BigInteger(args[0], 16);
            BigInteger[] results = splitKey(toSplit);
            printResults(results);
        } catch (Exception ex) {
            printUsageAndExit();
        }

    }
}
