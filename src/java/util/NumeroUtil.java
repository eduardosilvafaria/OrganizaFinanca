package br.com.cs.documento.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumeroUtil {
	
	private static BigDecimal cem = new BigDecimal(100D);
	public static DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt","BR"));
//	public static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	private static DecimalFormat df = new DecimalFormat("#,###.00");
	private static DecimalFormat formatMoney = new DecimalFormat("¤#,##0.00#;¤-#,##0.00#", symbols);
	
	public static BigDecimal converterBigDecimal(String valor){		
		BigDecimal retorno = null;
		if(valor != null){
			try {
				try {
					retorno = new BigDecimal(valor);
				}
				catch (Exception e) {
					valor = valor.replaceAll("\\.", "").replaceAll(",", "\\.");
					retorno = new BigDecimal(valor);
				}
			}
			catch (Exception e) {
			}
		}
		return retorno;
	}
	public static String converterBigDecimal(BigDecimal valor){	
		String retorno = null;
		if(valor != null){
			try {
				try {
					retorno = df.format(valor);
				}
				catch (Exception e) {
				}
			}
			catch (Exception e) {
			}
		}
		return retorno;
	}
	public static String converterBigDecimalMonetario(BigDecimal valor){		
 		String retorno = null;
		if(valor != null){
			try {
				try {
					retorno = formatMoney.format(valor);
				}
				catch (Exception e) {
				}
			}
			catch (Exception e) {
			}
		}
		return retorno;
	}
	
	public static BigDecimal converterBigDecimal(String valor, boolean dividirPorCem){
		BigDecimal retorno = converterBigDecimal(valor);
		if(retorno != null && dividirPorCem){
			return retorno.divide(cem);
		}
		return retorno;
	}
	
	public static Double converterDouble(String valor, boolean dividirPorCem){	
		Double retorno = converterDouble(valor);
		if(retorno != null && dividirPorCem){
			return retorno / cem.doubleValue();
		}
		return retorno;
	} 
	public static Double converterDouble(String valor){		
		Double retorno = null;
		if(valor != null){
			try {
				try {
					retorno = new Double(valor);
				} 
				catch (Exception e) {
					valor = valor.replaceAll("\\.", "").replaceAll(",", "\\.");
					retorno = new Double(valor);
				}
			} 
			catch (Exception e) {
				
			}
		}
		return retorno;
	}
	
	/**
	 * Soma os valores
	 * @param valorBig
	 * @param valorStr
	 * @return
	 */
	public static BigDecimal somarValores(final BigDecimal valorBig, String valorStr){
		BigDecimal vlr = converterBigDecimal(valorStr);
		if(vlr == null){
			return valorBig;
		}
		return valorBig.add(vlr);
		
	}
	
	public static void main(String[] args) {
		BigDecimal vlr = new BigDecimal("100.01");
	System.out.println(df.format(vlr));
	System.out.println(formatMoney.format(vlr));
	}
}
