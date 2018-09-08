/**
 * A single block on the blockchain.
 */

public class Block implements HashableObject {

    // Data stored in this block
    private String _data;

    // Hash pointer to predecessor
    private HashPointer _hp;

    public String getDataAsString() {
	return _data;
    }

    public Block(Block predecessor, String data) {
	_data = data;
	_hp = new HashPointer(predecessor);
    }
}
