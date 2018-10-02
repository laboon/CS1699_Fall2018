// These imports allow us to do SHA-256 hashing - we need not only MessageDigest
// which contains the algorithm itself, but the ancillary class StandardCharsets and
// NoSuchAlgorithmException for feeding in data and running it

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Hash {

    /**
     * Given some arbitrary byte array bytes, convert it to a hex string.
     * Example: [0xFF, 0xA0, 0x01] -> "FFA001"
     * @param bytes arbitrary-length array of bytes
     * @return String hex string version of byte array
     */

    private static String convertBytesToHexString(byte[] bytes) {
        StringBuffer toReturn = new StringBuffer();
        for (int j = 0; j < bytes.length; j++) {
            String hexit = String.format("%02x", bytes[j]);
            toReturn.append(hexit);
        }
        return toReturn.toString();
    }

    /**
     * Given some string, return the SHA256 hash of it.
     * @param x Arbitrary string
     * @return String Hex version of the hash of that object's data
     */

    public static String calculateHash(String x) {
        if (x == null) {
            return "0";
        }
        byte[] hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(x.getBytes());
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println("No SHA-256 algorithm found.");
            System.err.println("This generally should not happen...");
            System.exit(1);
        }
        return convertBytesToHexString(hash);

    }

    /**
     * Given a string, returns the hash as a BigInteger
     * @param toHash - string to hash
     * @return BigInteger - hash of String toHash
     * @see calculateHash method, above
     */

    public static BigInteger hashBigInteger(String toHash) {
        return new BigInteger(calculateHash(toHash), 16);
    }


}
