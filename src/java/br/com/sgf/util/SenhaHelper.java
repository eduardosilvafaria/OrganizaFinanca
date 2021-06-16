import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;


/**
 * Classe responsável por realizar operações com as senhas dos usuários, tais
 * como: Enviar email com nova senha gerada, gerar nova senha, criptografar
 * senha e etc.
 * */
public class SenhaHelper {
	private static Logger logger = Logger.getLogger(SenhaHelper.class);

	public static String convertToMd5(String senha) {
		
		if (isValidMD5(senha)){
			throw new RuntimeException("A Senha já está criptografada em MD5");
		}
		
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("MD5");
			byte[] valorMD5 = mDigest.digest(senha.getBytes("UTF-8"));

			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,
						3));
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

	public static String gerarSenhaAleatoria() {
		String[] carct = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
				"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
				"y", "z" };

		String senha = "";

		for (int x = 0; x < 10; x++) {
			int j = (int) (Math.random() * carct.length);
			senha += carct[j];

		}

		return senha;
	}
	
	public static boolean isValidMD5(String s) {
	    return s.matches("[a-fA-F0-9]{32}");
	}
	
}
