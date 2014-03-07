/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilpack;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {

    public HashUtil() {
    }

//    public static void main(String[] args) {
//
//         new HashUtil().printHash("abc");
//    }

    private String sha256Hex;
    private String base64;

    public String getSha256Hex() {
        return sha256Hex;
    }

    public void setSha256Hex(String sha256Hex) {
        final String hash = DigestUtils.sha256Hex(sha256Hex);
        this.sha256Hex = hash;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {

        MessageDigest m;
        try {
            m = java.security.MessageDigest.getInstance("SHA-256");
            final byte bin[] = m.digest((base64).getBytes());

            this.base64 = Base64.encodeBase64String(bin);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   public void printHash(String testString){
       System.out.println("Passed String is: "+testString);
       setSha256Hex(testString);
       System.out.println("SHA-256 Hex is: "+getSha256Hex());
       setBase64(testString);
       System.out.println("base64 is: "+getBase64());
   }

}
