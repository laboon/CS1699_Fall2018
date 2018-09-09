/**
 * Demo of a basic blockchain (i.e., a linked list with hash pointers)
 */

public class BlockchainDemo {

    public static void main(String[] args) {

	Blockchain blockchain = new Blockchain();

	// Add a few blocks

	blockchain.addBlock("indeed");
	blockchain.addBlock("great");
	blockchain.addBlock("is");
	blockchain.addBlock("CS1699");

	// Verify

	boolean blockchainGood = blockchain.iterateAndVerify();
	if (blockchainGood) {
	    System.out.println("Blockchain is good!");
	} else {
	    System.out.println("Blockchain invalid!");
	}

	// Modify one of the internal blocks

	// Try to verify... failure!

	// As long as final hash result is known/uncorruptable,
	// computationally infeasible to modify any part of the
	// blockchain

    }
}
