/**
 * A relatively generic class which implements HashableObject.
 * An account consists of a name and how many dollars that account
 * has.
 */

public class Account implements HashableObject {

    // Name associated with account

    public String _name = "";

    // Number of dollars in accounts

    public String _dollars = "";

    /**
     * Return data stored in this object as a string.
     * Think of it as an alternative to .toString().
     * Format is "name/dollars".
     * @return String representation of this object
     */

    public String getDataAsString() {
	StringBuilder toReturn = new StringBuilder();
	toReturn.append(_name);
	toReturn.append("/");
	toReturn.append(_dollars);
	return toReturn.toString();
    }

    /**
     * Constructor.
     * Creates a new account with a name and dollars associated
     * with the account.
     * @param name - name of account owner
     * @param dollars - dollars in account
     */

    public Account(String name, String dollars) {
	_name = name;
	_dollars = dollars;
    }

}
