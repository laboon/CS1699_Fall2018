// These imports allow us to do SHA-256 hashing - we need not only MessageDigest
// which contains the algorithm itself, but the ancillary class StandardCharsets and
// NoSuchAlgorithmException for feeding in data and running it

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPointer {

    // Reference to some object

    private HashableObject _reference;

    // The hash of _reference

    private String _hash;

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
     * Given some HashableObject, determine a string representation of it
     * (all HashableObjects have this as part of the interface), and
     * return the hash of the string representation.
     * @param x Some object which meets the HashableObject interface
     * @return String version of the hash of that object's data
     */

    public static String calculateHash(HashableObject x) {
	if (x == null) {
	    return "0";
	}
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

    /**
     * Accessor to return _reference (read-only, no mutator)
     * @return _reference object
     */

    public HashableObject getReference() {
	return _reference;
    }

    /**
     * Accessor to return _hash (read-only, no mutator)
     * @return _hash
     */

    public String getHash() {
	return _hash;
    }

    /**
     * Called to determine whether or not this hash pointer is valid
     * (i.e., the data of the object to which it points has not been
     * tampered with).
     * @return boolean - true if valid, false if not
     */

    public boolean referenceValid() throws InvalidHashException {
	// null should always equal special hash "0"
	if (_reference == null && _hash == "0") {
	    return true;
	}

	String calculatedHash = calculateHash(_reference);
	if (_hash.equals(calculatedHash)) {
	    return true;
	} else {
	    throw new InvalidHashException(_hash, calculatedHash);
	}
    }

    /**
     * Constructor.
     * Given some HashableObject, create a pointer (reference) to
     * it and calculate its initial hash value.
     * After creation, both of these values are read-only.
     * @param reference Some object which implements HashableObject.
     */

    public HashPointer(HashableObject reference) {
	_reference = reference;
	_hash = calculateHash(_reference);
    }
}
