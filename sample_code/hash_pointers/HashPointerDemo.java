public class HashPointerDemo {

    public static void main(String[] args) {

	// Create accounts for Jill and Jane.  Jill has $100
	// and Jane has $500.

	Account jillAccount = new Account("Jill", "100");
	Account janeAccount = new Account("Jane", "500");

	// Now create hash pointers to these accounts.  At the time of creation,
	// a hash will be generated which we can use to detect tampering.

	HashPointer toJill = new HashPointer(jillAccount);
	HashPointer toJane = new HashPointer(janeAccount);

	// Hash pointers should be valid now - we just created them and did not modify

	System.out.println("Pointer to Jill account valid? " +
			   toJill.referenceValid());
	System.out.println("Pointer to Jane account valid? " +
			   toJane.referenceValid());

	System.out.println("Why?");
	System.out.println("Hash in pointer to Jill account = " + toJill.getHash());
	System.out.println("Hash of data in Jill account = " +
			   HashPointer.calculateHash(jillAccount));
	System.out.println("Hash in pointer to Jane account = " + toJane.getHash());
	System.out.println("Hash of data in Jane account = " +
			   HashPointer.calculateHash(janeAccount));


	// Now we are going to be sneaky and modify the underlying data in Jill's
	// account, and hope that the pointer which points to it will not notice
	// Obviously this is a hopeless endeavor!

	System.out.println("\nModifying data on Jill's account - now _dollars = 100000000.\n");
	jillAccount._dollars = "100000000";

	// Original hash on Jill's account does not match hash of current data
	// The hash pointer toJill indicates that treachery is afoot!

	System.out.println("Pointer to Jill account valid? " + toJill.referenceValid());
	System.out.println("Pointer to Jane account valid? " + toJane.referenceValid());

	System.out.println("Why?");
	System.out.println("Hash in pointer to Jill account = " + toJill.getHash());
	System.out.println("Hash of data in Jill account = " +
			   HashPointer.calculateHash(jillAccount));
	System.out.println("Hash in pointer to Jane account = " + toJane.getHash());
	System.out.println("Hash of data in Jane account = " +
			   HashPointer.calculateHash(janeAccount));

    }
}
