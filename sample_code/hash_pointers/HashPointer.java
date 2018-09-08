// These imports allow us to do SHA-256 hashing - we need not only MessageDigest
// which contains the algorithm itself, but the ancillary class StandardCharsets and
// NoSuchAlgorithmException for feeding in data and running it

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPointer {

    private HashableObject _reference;

    private String _hash;

    private static String convertBytesToHexString(byte[] bytes) {
	StringBuffer toReturn = new StringBuffer();
	for (int j = 0; j < bytes.length; j++) {
	    String hexit = String.format("%02x", bytes[j]); // Integer.toHexString(hash[j]);
	    toReturn.append(hexit);
	}
	return toReturn.toString();
    }

    public static String calculateHash(HashableObject x) {
	String dataAsString = x.getDataAsString();
	byte[] hash = null;
	try {
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    hash = digest.digest(
				 dataAsString.getBytes(StandardCharsets.UTF_8));

	} catch (NoSuchAlgorithmException nsaex) {
	    System.err.println("No SHA-256 algorithm found.");
	    System.err.println("This generally should not happen...");
	    System.exit(1);
	}
	return convertBytesToHexString(hash);

    }

    public HashableObject getReference() {
	return _reference;
    }

    public String getHash() {
	return _hash;
    }

    public boolean referenceValid() {
	return _hash.equals(calculateHash(_reference));
    }

    public HashPointer(HashableObject reference) {
	_reference = reference;
	_hash = calculateHash(_reference);
    }
}
