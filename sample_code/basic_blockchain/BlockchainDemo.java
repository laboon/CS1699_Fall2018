/**
 * Demo of a basic blockchain (i.e., a linked list with hash pointers)
 */

public class BlockchainDemo {

    public static void main(String[] args) {

	Blockchain blockchain = new Blockchain();

	Block[] blocks = new Block[10];

	// Add a few blocks

	blocks[0] = blockchain.addBlock("indeed");
	blocks[1] = blockchain.addBlock("great");
	blocks[2] = blockchain.addBlock("is");
	blocks[3] = blockchain.addBlock("CS1699");

	// Verify

	boolean blockchainGood = blockchain.iterateAndVerify();
	if (blockchainGood) {
	    System.out.println("Blockchain is good!");
	} else {
	    System.out.println("Blockchain invalid!");
	}

	// Modify one of the internal blocks

	blocks[1]._data = "bad";

	// Try to verify... failure!
	// As long as final hash result is known/uncorruptable,
	// computationally infeasible to modify any part of the
	// blockchain

	blockchainGood = blockchain.iterateAndVerify();
	if (blockchainGood) {
	    System.out.println("Blockchain is good!");
	} else {
	    System.out.println("Blockchain invalid!");
	}
    }
}
