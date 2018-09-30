import java.util.*;
import java.math.*;

public class JoinKey {


    /**
     * Print usage information and exit.
     * NOTE: EXITS PROGRAM WITH EXIT CODE 1.
     */
    public static void printUsageAndExit() {
	System.err.println("Usage: java SplitKey *share1* *share2*");
	System.exit(1);
    }

    /**
     * Given some BigInteger, convert it to a hex string.
     * NOTE: After XORing, an additional byte was added to the
     * front of the result which is why this loop starts at 1
     * and not 0.
     * @param bigint - Arbitrary BigInteger
     * @return String hex string version of byte array
     */

    private static String convertBigIntToHexString(BigInteger b) {
	byte[] bytes = b.toByteArray();
	StringBuffer toReturn = new StringBuffer();
	for (int j = 1; j < bytes.length; j++) {
	    String hexit = String.format("%02x", bytes[j]);
	    toReturn.append(hexit);
	}
	return toReturn.toString();
    }


    /**
     * Given a 128-bit R and X (S XOR R), return the original secret key.
     * It should not matter what order they are in, and are thus referred
     * to as simply share1 and share2.
     * @param share1 One of the shares (either R or S XOR R)
     * @param share2 The other share (either R or S XOR R)
     * @return the original secret key
     */

    public static BigInteger joinKey(BigInteger share1,
					BigInteger share2) {
	return share1.xor(share2);
    }

    /**
     * Given a result, print it to the screen
     * @param result - original secret key
     */

    public static void printResults(BigInteger result) {
	System.out.println("S      : " +
			   convertBigIntToHexString(result));
    }

    public static void main(String[] args) {
    	if (args.length != 2) {
	    printUsageAndExit();
	}

	try {
	    // Read as hex, i.e. radix = 16
	    BigInteger share1 = new BigInteger(args[0], 16);
	    BigInteger share2 = new BigInteger(args[1], 16);
	    BigInteger result = joinKey(share1, share2);
	    printResults(result);
	} catch (Exception ex) {
	    printUsageAndExit();
	}

    }
}
