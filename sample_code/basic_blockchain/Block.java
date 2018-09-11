/**
 * A single block on the blockchain.
 */

public class Block implements HashableObject {

    // Data stored in this block
    public String _data;

    // Hash pointer to predecessor
    private HashPointer _hp;

    private String _prevHash;

    public String getDataAsString() {
	return _data + "/" + _prevHash;
    }

    public boolean validPointer() throws InvalidHashException {
	// If invalid, will throw an InvalidHashException

	return (_hp == null || _hp.referenceValid());
    }

    public Block previousBlock() throws InvalidHashException {
	if (validPointer()) {
	    return (Block) _hp.getReference();
	} else {
	    // This will actually never happen.
	    // If invalid, an InvalidHashException will be thrown by validPointer call
	    return null;
	}
    }

    public Block(Block predecessor, String data) {
	_data = data;
	if (predecessor == null) {
	    _prevHash = "0";
	} else {
	    _prevHash = HashPointer.calculateHash(predecessor);
	}
	_hp = new HashPointer(predecessor);
    }
}
