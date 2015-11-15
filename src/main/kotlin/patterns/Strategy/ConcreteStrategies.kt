package patterns.Strategy
import java.security.MessageDigest;
public class MD5(){
    public fun computeHash(password : String) : String {
        val md = MessageDigest.getInstance("MD5")
        md.update(password.toByteArray())
        val byteData = md.digest()
        val sb = StringBuilder()
        for (b in byteData) {
            sb.append("%02x".format(b))
        }
        return sb.toString()
    }
}
public class SHA256 (){
    public fun computeHash(password : String) : String {
        val md = MessageDigest.getInstance("SHA")
        md.update(password.toByteArray())
        val byteData = md.digest()
        val sb = StringBuilder()
        for (b in byteData) {
            sb.append("%02x".format(b))
        }
        return sb.toString()
    }
}