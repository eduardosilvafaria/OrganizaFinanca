package br.com.sgf.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Formatador {
	private static final Logger log = LoggerFactory.getLogger(Formatador.class);

	private static DecimalFormat real;
	private static DecimalFormat numero;
	private static DecimalFormat percent;
	
	private static BigDecimal DIVISOR_100 = new BigDecimal(100);
	
	public static String formatar(String valor, String mascara){
		
		return aplicarFormato(valor, mascara, false, " ");
	}
	
	public static String formatarCPFCNPJ(String valor){
		valor = removerFormatacao(valor);
		if(valor.length() > 11){
			return formatarCNPJ(valor);
		}
		return formatarCPF(valor);
	}
	public static String formatarCPF(String valor){
		return formatar(valor, "###.###.###-##", "0");
	}
	public static String formatarCNPJ(String valor){
		return formatar(valor, "##.###.###/####-##", "0");
	}
	public static String formatarCEP(String valor){
		return formatar(valor, "##.###-###", "0");
	}
	
	public static String formatarTelefone(String valor){
		if(valor == null)
			return "";
		
		valor = removerFormatacao(valor);
		if(valor != null && valor.length() == 10){
			return formatar(valor, "(##) ####-####", "0");
		}
		if(valor != null && valor.length() == 9){
			return formatar(valor, "#####-####", "0");
		}
		if(valor != null && valor.length() < 9){
			return formatar(valor, "####-####", "0");
		}
		return formatar(valor, "(##) #####-####", "0");
	}
	
	public static String formatarTelefoneSemDDD(String valor){
		if(valor == null)
			return "";
		
		valor = removerFormatacao(valor);
		if(valor != null && valor.length() == 10){
			return formatar(valor, "####-####", "0");
		}
		if(valor != null && valor.length() == 9){
			return formatar(valor, "#####-####", "0");
		}
		if(valor != null && valor.length() < 9){
			return formatar(valor, "####-####", "0");
		}
		return formatar(valor, "#####-####", "0");
	}
	
	public static String formatarDDD(String valor){
		if(valor == null)
			return "";
		
		valor = removerFormatacao(valor);
		if(valor != null && (valor.length() == 10 || valor.length() == 11) ){
			valor = valor.substring(0, 2);
			return formatar(valor, "##", "0");
		}
		return formatar(valor, "##", "0");
	}
	
	public static String formatar(String valor, String mascara, String prencher){
		return aplicarFormato(valor, mascara, true, prencher);
	}
	
	private static String aplicarFormato(String valor, String mascara, boolean preencher, String valorPreencher){
		String retorno = "";
		if(valor == null){
			valor = "";
		}
		valor = removerFormatacao(valor);
		int pos = valor.length() -1;
		for(int i = (mascara.length() -1); i >= 0; i--){
			if(IsCharMascara(mascara.charAt(i))){
				if(pos >= 0){
					retorno = valor.charAt(pos)+retorno;
				}else{
					if(preencher)
						retorno = valorPreencher+retorno;
					else{
						retorno = " " +retorno;
					}
				}
				pos--;
			}else
				retorno = mascara.charAt(i)+retorno;
		}
		return retorno;
	}


	private static boolean IsCharMascara(char charAt) {
		switch (charAt) {
		case '#':
//		case '-':
//		case '.':
//		case '_':
//		case '%':
//		case '$':
//		case '+':
//		case '/':
//		case '(':
//		case ')':
			return true;
		default:
			break;
		}
		return false;
	}
	
	public static String removerFormatacao(String valor){
		return valor.replaceAll("-", "")
		.replaceAll("\\.", "")
		.replaceAll("_", "")
		.replaceAll(" ", "")
		.replaceAll("%", "")
		.replaceAll("$", "")
		.replaceAll("\\+", "")
		.replaceAll("/", "")
		.replaceAll("\\(", "")
		.replaceAll(",", "")
		.replaceAll("R\\$", "")
		.replaceAll("\\)", "");
	}
	
	public static String removeFormatoNumero(String valor){
		if(valor != null && valor.indexOf(",") != -1)
			return valor.replaceAll("\\.", "").replaceAll(",", "\\.");
		return valor;
	}
	
	public static String formatarMoeda(String valor, boolean divide100){
		if(valor == null)
			valor = "-";
		valor = removeFormatoNumero(valor);
		try {
			BigDecimal vlr = new BigDecimal(valor);
			if(vlr.doubleValue() == 0.0D){
				return "-";
			}
			if(divide100)
				vlr = vlr.divide(DIVISOR_100, 2, 2);			
			valor = getRealFormat().format(vlr);
			
		} catch (Exception e) {		
		}
		return valor;
	}

	public static String formatarMoeda(String valor){
		return formatarMoeda(valor, false);
	}
	
	public static String formatarPercent(String valor, boolean divide100){
		if(valor == null)
			valor = "-";
		valor = removeFormatoNumero(valor);
		try {
			BigDecimal vlr = new BigDecimal(valor);
			if(vlr.doubleValue() == 0.0D){
				return "-";
			}
			if(divide100)
				vlr = vlr.divide(DIVISOR_100,2, 2);	
			valor = getPercentFormat().format(vlr);
			
		} catch (Exception e) {		
		}
		return valor;
	}
	public static String formatarPercent(String valor){
		return formatarPercent(valor, false);
	}
	
	public static String formatarNumero(String valor){
		return formatarNumero(valor, false);
	}

	private static DecimalFormat getRealFormat(){
		if(real == null){
			Locale currentLocale = new Locale("pt", "BR");
			DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
			otherSymbols.setDecimalSeparator(',');
			otherSymbols.setGroupingSeparator('.'); 
			real = new DecimalFormat("R$ ,###,###,###,##0.00;(R$ ,###,###,###,##0.00)", otherSymbols);
		}
		return real;
	}
	
	private static DecimalFormat getNumeroFormat(){
		if(numero == null){
			Locale currentLocale = new Locale("pt", "BR");
			DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
			otherSymbols.setDecimalSeparator(',');
			otherSymbols.setGroupingSeparator('.'); 
			numero = new DecimalFormat("###,###,###,##0.00;(###,###,###,##0.00)", otherSymbols);
		}
		return numero;
	}
	private static DecimalFormat getPercentFormat(){
		if(percent == null){
			Locale currentLocale = new Locale("pt", "BR");
			DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
			otherSymbols.setDecimalSeparator(',');
			otherSymbols.setGroupingSeparator('.'); 
			percent = new DecimalFormat("###,###,###,##0.00'%';(###,###,###,##0.00'%')", otherSymbols);
		}
		return percent;
	}
	
	public static String formatarCodigoSusep(String valor){
		return formatar(valor, "#####.######/####-##", "0");
	}
	public static String formatarNumeroTitulo(Integer plano,Short serie,Long titulo,Short dv){
		String retorno=formatar(String.valueOf(plano), "###","0");
		retorno+=formatar(String.valueOf(serie), "###","0");
		retorno+=formatar(String.valueOf(titulo), "#######","0");
		retorno+=(String.valueOf(dv));
		return formatar(retorno,"###.###.#######-#", "0");
	}
//	
	public static void main(String[] args) {
//		System.out.println(formatarCPF("123456788"));
//		System.out.println(formatarCNPJ("156788000189"));
//		String  teste = formatar("156788000189", "R$ ###.###.###.###.###,##(+)(-)", "0");
//		System.out.println(teste + " - "+ removerFormatacao(teste));
//		System.out.println(formatar("156788000189", "R$ ###.###.###.###.###,##(+)(-)", "0"));
//		System.out.println(formatarTelefone("11982122004"));
//		System.out.println(formatarTelefone("1182122004"));
//		System.out.println(formatarTelefone("182122004"));
//		System.out.println(formatarTelefone("2122004"));
		
//		System.out.println(formatarMoeda("1000.00"));
//		System.out.println(formatarMoeda("1000.00", true));
//		
//		System.out.println(formatarNumero("1000.00"));
//		System.out.println(formatarPercent("1000.00"));
//		System.out.println(formatarCodigoSusep("100109541"));
//		System.out.println(formatarCodigoSusep("5631"));
		
		String sorteio = "%02d Sorteio(s) %s de %s";
		
		log.info(String.format(sorteio, Integer.valueOf("21"), "mensal(is)", formatarMoeda("200.000,00")));
		
	}

	public static String formatarNumero(String valor, boolean divide100) {
		if(valor == null)
			valor = "-";
		valor = removeFormatoNumero(valor);
		try {
			BigDecimal vlr = new BigDecimal(valor);
			if(vlr.doubleValue() == 0.0D){
				return "-";
			}
			if(divide100)
				vlr = vlr.divide(DIVISOR_100, 2, 2);			
			valor = getNumeroFormat().format(vlr);
			
		} catch (Exception e) {		
		}
		return valor;
	}
	
}
