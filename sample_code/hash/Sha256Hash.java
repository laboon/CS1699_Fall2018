// These imports allow us to do SHA-256 hashing - we need not only MessageDigest
// which contains the algorithm itself, but the ancillary class StandardCharsets and
// NoSuchAlgorithmException for feeding in data and running it

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
     * Given some string, determine a string representation of it
     * return the SHA256 hash of it.
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

    public static void main(String[] args) {
	if (args.length != 1) {
	    System.err.println("Usage: java Sha256Hash *msg*");
	    System.exit(1);
	}
	try {
	    String msg = args[0];
	    System.out.println("Message:");
	    System.out.println(msg);
	    System.out.println("Hash:");
	    System.out.println(calculateHash(msg));
	} catch (Exception ex) {
	    System.err.println("Exception caught");
	    System.err.println(ex);
	    ex.printStackTrace();
	}
    }

}
