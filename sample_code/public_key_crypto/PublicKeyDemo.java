/**
 * This class demonstrates creating a public/private keypair.
 * For more information, see https://docs.oracle.com/javase/tutorial/security/apisign/index.html
 */


import java.io.*;
import java.security.*;
import java.security.spec.*;

public class PublicKeyDemo {

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
     * Generates a public/private key pair.
     * @return KeyPair - Public/private key pair
     */

    private static KeyPair getKeyPair() throws Exception {
	KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
	SecureRandom random = new SecureRandom(); // .getInstance("SHA1PRNG", "SUN");
	keyGen.initialize(1024, random);
	return keyGen.generateKeyPair();
    }

    /**
     * Signs a message with a secret key sk and returns signature in
     * byte array format.
     * @param toSign - string to sign
     * @param sk - secret key
     * @return byte[] signature of string signed with sk
     */

    private static byte[ ] sign(String toSign, PrivateKey sk) throws Exception {
	Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
	dsa.initSign(sk);

	byte[] bytes = toSign.getBytes();
	dsa.update(bytes, 0, bytes.length);
	byte[] sig = dsa.sign();
	return sig;
    }

    /**
     * Given a string toCheck, its signature, and a PUBLIC key pk, verifies
     * that the string was signed by the corresponding secret key sk to pk.
     * @param toCheck - string to check
     * @param sig - signature in byte array form
     * @parak pk - public key
     * @return boolean - true if valid, false otherwise
     */

    private static boolean verify(String toCheck, byte[] sig, PublicKey pk)
	throws Exception {
	    Signature sig2 = Signature.getInstance("SHA1withDSA", "SUN");
	    byte[] bytes = toCheck.getBytes();
	    sig2.initVerify(pk);
	    sig2.update(bytes, 0, bytes.length);
	    return sig2.verify(sig);
    }

    /**
     * Demo.
     * Creates a key pair, signs a message with secret key, then
     * tries to verify different combinations of original/modified
     * key and string.
     * @param args - Ignored
     */

    public static void main(String[] args) {
        try {
	    // Generate a key pair and display
	    KeyPair pair = getKeyPair();
	    PrivateKey sk = pair.getPrivate();
	    PublicKey pk = pair.getPublic();
	    System.out.println("Public key (pk): " +
			       convertBytesToHexString(pk.getEncoded()));
	    System.out.println("Private key (sk): " +
			       convertBytesToHexString(sk.getEncoded()));

	    // Given a string, sign it with our secret (private) key
	    // Note that we will get back a byte array for the signature
	    String toSign = "This was written by Bill Laboon";
	    byte[] sig = sign(toSign, sk);
	    System.out.println("String to sign: " + toSign);
	    System.out.println("Sig: " + convertBytesToHexString(sig));

	    // Verify signature with original string
	    String toVerify = toSign;
	    boolean verifies = verify(toVerify, sig, pk);
	    System.out.println("Original string / original signature verifies: " + verifies);

	    // Try to verify with same signature but a modified string
	    toVerify = "modified string";
	    verifies = verify(toVerify, sig, pk);
	    System.out.println("Modified string / original signature verifies: " + verifies);

	    // Try to verify a signature from a DIFFERENT string but original string
	    toVerify = toSign;
	    sig = sign("wocka wocka", sk);
	    verifies = verify(toVerify, sig, pk);
	    System.out.println("Original string / modified signature verifies: " + verifies);

	    // Try to verify after modifying both string and signature
	    toVerify = "modified string";
	    verifies = verify(toVerify, sig, pk);
	    System.out.println("Modified string / modified signature verifies: " + verifies);

        } catch (Exception e) {
            System.err.println("Exception: " + e.toString());
        }
    }
}
