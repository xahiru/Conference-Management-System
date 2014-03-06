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
     public static void main(String[] args){
        
        new HashUtil().setHex("abc");
    }
 

    private String hex;

    /**
     * Get the value of hex
     *
     * @return the value of hex
     */
    public String getHex() {
        return hex;
    }

    /**
     * Set the value of hex
     *
     * @param hex new value of hex
     */
    public void setHex(String hex) {
       
      MessageDigest m;
        try {
            m = java.security.MessageDigest.getInstance("SHA-256");
            final byte bin[] = m.digest((hex).getBytes());
            
            this.hex = Base64.encodeBase64String(bin);
            System.out.println(this.hex);
             System.out.println(m.digest((hex).getBytes()));
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
      
  setHex2(hex);
        
    }
    
    public void setHex2(String hex) {
        final String hash = DigestUtils.sha256Hex(hex); 
        System.out.println(hash); 
        System.out.println("this is the correct version that has to be used with HEX");
    }
}


