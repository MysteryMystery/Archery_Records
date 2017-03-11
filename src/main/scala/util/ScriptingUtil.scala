package util

import java.security.{MessageDigest, NoSuchAlgorithmException}

/**
  * Created by James on 08/03/2017.
  */
trait ScriptingUtil {

  def hash(toHash: String): String ={
    var hashed: String = ""
    try {
      var messageDigest: MessageDigest = MessageDigest.getInstance("SHA-256")
      messageDigest.update(toHash.getBytes())
      hashed = new String(messageDigest.digest());
    }
    catch {
      case e: NoSuchAlgorithmException => e.printStackTrace()
    }
    return hashed
  }

  def checkPass(pass1: String, pass2:String):Boolean = {
    pass1.equals(pass2)
  }

}
