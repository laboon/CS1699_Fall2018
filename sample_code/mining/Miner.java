import java.math.BigInteger;

/**
 * This program will attempt to find a nonce such that
 * H(nonce || block) < target
 * where the hashing function is SHA-256, block is argument 0, target is
 * argument 1 (interpreted as a hex string)
 * Example:
 * (24425) $ java Miner HuzzahForBitcoin 1000000000000000000000000000000000000000000000000000000000000
 * Hash rate = 51102 hashes per second
 * Nonce       = c79d
 * Full block  = 000000000000000000000000000000000000000000000000000000000000c79dHuzzahForBitcoin
 * Hash(block) = 00003556921b058ee69dff14ce2fa0c35791f5d3f54088c33768a300ccc7d261
 */

public class Miner {

    /**
     * This increments our String nonce by accepting a String version
     * and returning a String version.  For example:
     * "000A" -> "000B"
     * "FFFE" -> "FFFF"
     * @param nonce initial nonce
     * @return nonce incremented by one in string form
     */
    public static String incrementStringNonce(String nonce) {
        BigInteger bi = new BigInteger(nonce, 16);
        bi = bi.add(BigInteger.ONE);
        return bi.toString(16);
    }

    /**
     * Prepend a string with 0's until it is of length n.
     * Useful for printing out hash results.
     * @param str String to prepend 0's to
     * @param n correct size of string after being padded
     * @return String str left-padded with 0's
     */
    public static String leftPad(String str, int n) {
        return String.format("%1$" + n + "s", str).replace(' ', '0');
    }

    /**
     * Given a start time and end time in nanoseconds (courtesy of System.nanoTime),
     * and a number of hashes complete in this time, print out the number of hashes
     * per second.
     * @param numHashes - number of hashes completed
     * @param startTime - time hashing started
     * @param endTime - time hashing ended
     */
    public static void printHashRate(BigInteger numHashes,
                                     long startTime,
                                     long endTime) {
        long timeDiff = endTime - startTime;
        long seconds = timeDiff / 1000000000;
        BigInteger time = new BigInteger ((Long.valueOf(seconds)).toString());
        BigInteger hashesPerSecond = numHashes.divide(time);
        System.out.println("Hash rate = " + hashesPerSecond + " hashes per second");

    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: *block_string* *target_in_hex*");
            System.exit(1);
        }
        String nonce = "0";
        String block = args[0];
        BigInteger target = new BigInteger(args[1], 16);
        boolean lookingForTarget = true;
        String concat = "";
        BigInteger hash = null;

        BigInteger numHashes = new BigInteger("0");
        long startTime = System.nanoTime();

        // Keep looping until we find target...
        while (lookingForTarget) {

            // Increment number of hashes tried
            numHashes = numHashes.add(BigInteger.ONE);
            // Concatenate our nonce and our block
            concat = (leftPad(nonce, 64)) + block;

            // Uncomment to see failed attempts
            // System.out.println("Trying: " + concat);

            // Calculate the hash.  Kept in a BigInteger since we will want to compare it.
            hash = Sha256Hash.hashBigInteger(concat);

            // If our hash is less than the target, we have succeeded
            if (hash.compareTo(target) == -1) {
                long endTime = System.nanoTime();
                printHashRate(numHashes, startTime, endTime);
                lookingForTarget = false;
                System.out.println("Nonce       = " + nonce);
                System.out.println("Full block  = " + concat);
                System.out.println("Hash(block) = " + leftPad(hash.toString(16), 64));
            } else {
                // Uncomment to see failed attempts
                // System.out.println("Fail, hash "
                //            + leftPad(hash.toString(16), 64) + " >= "
                //            + leftPad(target.toString(16), 64));
                nonce = incrementStringNonce(nonce);
            }
        }
    }
}
