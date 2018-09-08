public class Account implements HashableObject {

    public String _name = "";

    public String _dollars = "";

    public String getDataAsString() {
	StringBuilder toReturn = new StringBuilder();
	toReturn.append(_name);
	toReturn.append("/");
	toReturn.append(_dollars);
	return toReturn.toString();
    }

    public Account(String name, String dollars) {
	_name = name;
	_dollars = dollars;
    }

}
