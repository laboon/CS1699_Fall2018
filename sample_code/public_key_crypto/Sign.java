/**
 * Given a string message and a hex string version of a secret key,
 * will return a signature for that message.
 * Example:
 * (24136) $ java Sign "meow" 3081c60201003081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca404160214556d46e1888b30bccf9c4a5ea71b41c107b5d219
 * 302c02145a8df3a3741fdf3ca4431c17f031c9e34366858302145de150637d9e80ee5281d92ca7edd9b8e8c3fda6
 */

public class Sign {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java sign *msg* *secret_key*");
            System.exit(1);
        }
        try {
            String sig = PublicKeyDemo.signMessage(args[0], args[1]);
            System.out.println("Message:");
            System.out.println(args[0]);
            System.out.println("Signature:");
            System.out.println(sig);
        } catch (Exception ex) {
            System.err.println("Exception: " + ex);
            System.exit(1);
        }

    }
}
