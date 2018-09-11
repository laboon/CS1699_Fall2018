
public class Blockchain {

    private HashPointer _head = null;

    /**
     * Generates a block and adds it to the end of the blockchain.
     * NOTE: Ordinarily we would need to have a direct reference
     * to the generated block.  We should only reference blocks
     * via HashPointer.  I am including it so that I can
     * directly modify it later and show what happens when a HashPointer
     * is invalid..
     * @param data - The data to be added to the block
     * @return A direct reference to the generated block
     */

    public Block addBlock(String data) {
	Block b = null;
	if (_head == null) {
	    b = new Block(null, data);
	} else {
	    b = new Block((Block) _head.getReference(), data);
	}

	_head = new HashPointer(b);
	return b;
    }

    public boolean iterateAndVerify() {
	// Trivial case - no blocks in blockchain, it's valid
	if (_head == null) {
	    return true;
	}
	// Otherwise, iterate through all of the blocks until you get to null
	// If hashes don't match up, blockchain is invalid
	Block _current = (Block) _head.getReference();
	try {
	    while (_current != null) {
		System.out.println("Block data: " + _current.getDataAsString());
		_current = _current.previousBlock();

	    }
	} catch (InvalidHashException ihex) {
	    System.out.println(ihex);
	    return false;
	}
	// We have iterated through the entire blockchain without an error, thus
	// it is valid.
	return true;
    }



}
