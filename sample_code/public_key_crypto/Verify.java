/**
 * Given a string message as well as hex string versions of the signature
 * public key, will let you know if the signature is valid or invalid
 * for that message.
 *
 * For example, assume we signed a messsage using the secret key in the Sign.java
 * file:
 * (24136) $ java Sign "meow" 3081c60201003081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca404160214556d46e1888b30bccf9c4a5ea71b41c107b5d219
 * 302c02145a8df3a3741fdf3ca4431c17f031c9e34366858302145de150637d9e80ee5281d92ca7edd9b8e8c3fda6
 * (24139) $ java Verify "meow" 302c02145a8df3a3741fdf3ca4431c17f031c9e34366858302145de150637d9e80ee5281d92ca7edd9b8e8c3fda6 3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002405b0656317dd257ec71982519d38b42c02621290656eba54c955704e9b5d606062ec663bdeef8b79daa2631287d854da77c05d3e178c101b2f0a1dbbe5c7d5e10
 * OK - VALID SIGNATURE
 *
 * I now modified the last character so that it is a 2 instead of a 0, so the public key is
 * is incorrect.
 * (24140) $ java Verify "meow" 302c02145a8df3a3741fdf3ca4431c17f031c9e34366858302145de150637d9e80ee5281d92ca7edd9b8e8c3fda6 3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002405b0656317dd257ec71982519d38b42c02621290656eba54c955704e9b5d606062ec663bdeef8b79daa2631287d854da77c05d3e178c101b2f0a1dbbe5c7d5e12
 * !!! INVALID SIGNATURE !!!
 *
 * Similarly, I now have correct public key but I modified the signature by changing the
 * last character to a 2 making it incorrect.
 (24142) $ java Verify "meow" 302c02145a8df3a3741fdf3ca4431c17f031c9e34366858302145de150637d9e80ee5281d92ca7edd9b8e8c3fda2 3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002405b0656317dd257ec71982519d38b42c02621290656eba54c955704e9b5d606062ec663bdeef8b79daa2631287d854da77c05d3e178c101b2f0a1dbbe5c7d5e10
 * !!! INVALID SIGNATURE !!!
 *
 * Finally I have modified the message itself, but left the key and signature alone.
 * (24144) $ java Verify "quack" 302c02145a8df3a3741fdf3ca4431c17f031c9e34366858302145de150637d9e80ee5281d92ca7edd9b8e8c3fda6 3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002405b0656317dd257ec71982519d38b42c02621290656eba54c955704e9b5d606062ec663bdeef8b79daa2631287d854da77c05d3e178c101b2f0a1dbbe5c7d5e10
 * !!! INVALID SIGNATURE !!!
 */


public class Verify {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java verify *msg* *sig* *pk*");
            System.exit(1);
        }
        try {
            boolean valid = PublicKeyDemo.verifyMessage(args[0], args[1], args[2]);
            if (valid) {
                System.out.println("OK - VALID SIGNATURE");
            } else {
                System.out.println("!!! INVALID SIGNATURE !!!");
            }

        } catch (Exception ex) {
            System.err.println("Exception: " + ex);
            System.exit(1);
        }

    }
}
