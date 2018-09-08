
public class Blockchain {

    private HashPointer _head = null;

    public void addBlock(String data) {
	Block b = new Block((Block) _head.getReference(), data);
	_head = new HashPointer(b);
    }

    public boolean iterateAndVerify(boolean verbose) {
	HashPointer _back = _head;
	return true;
    }

}
