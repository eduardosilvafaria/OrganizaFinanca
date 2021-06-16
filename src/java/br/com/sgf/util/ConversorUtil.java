package br.com.sgf.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConversorUtil {
	protected static final Logger log = LoggerFactory.getLogger(ConversorUtil.class);
	public static String getValorString(Object object) {
		if (object != null) {
			return String.valueOf(object);
		}
		return null;
	}

	public static Long getValorLong(Object object) {
		if (object != null) {
			try {
				return Long.parseLong(getValorString(object));
			} catch (Exception e) {
				log.info(e.getMessage());
			}
		}
		return null;
	}

	public static Integer getValorInt(Object object) {
		if (object != null) {
			try {
				return Integer.parseInt(getValorString(object));
			} catch (Exception e) {
				log.info(e.getMessage());
			}
		}
		return null;
	}
        private String convertStringToMd5(String valor) {
  MessageDigest mDigest;
  try {
      //Instanciamos o nosso HASH MD5, poderíamos usar outro como
      //SHA, por exemplo, mas optamos por MD5.
      mDigest = MessageDigest.getInstance("MD5");

      //Convert a String valor para um array de bytes em MD5
      byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

      //Convertemos os bytes para hexadecimal, assim podemos salvar
      //no banco para posterior comparação se senhas
      StringBuffer sb = new StringBuffer();
      for (byte b : valorMD5){
             sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
      }

      return sb.toString();

  } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
  } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
  }
}
}
