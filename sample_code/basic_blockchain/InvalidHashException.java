public class InvalidHashException extends Exception {

    String _expected;

    String _observed;

    public InvalidHashException(String expected, String observed) {
	_expected = expected;
	_observed = observed;
    }

    public String toString() {
	return "Expected hash '" + _expected + "' but calculated hash was '" + _observed + "'.";
    }
}
