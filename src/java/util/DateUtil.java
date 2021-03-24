package util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateUtil {
	private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);
	
	public static final String DATE_PATTERN_FORM = 	"yyyy-MM-dd'T'HH:mm:ss.SSS";
	public static final String DATE_PATTERN_COBOL = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN_DATEHOUR = "yyyy-MM-dd-HH:mm";
	public static final String DATE_PATTERN_TIMESTAMP = 	"yyyy-MM-dd HH:mm:ss.SSS";
	private static final SimpleDateFormat FORMAT_PATTERN_DATETIME = new SimpleDateFormat(DATE_PATTERN_COBOL);
	private static final SimpleDateFormat FORMAT_PATTERN_DATEHOUR = new SimpleDateFormat(DATE_PATTERN_DATEHOUR);
	private static final SimpleDateFormat FORMAT_PATTERN_TIMESTAMP = new SimpleDateFormat(DATE_PATTERN_TIMESTAMP);
	
	public static final String DATE_PATTERN_COBOL_STRING = "yyyy-MM-dd-HH.mm.ss";
	public static final String DATE_PATTERN_BASIC = "yyyy-MM-dd";
	public static final String DATE_PATTERN_BR = "dd/MM/yyyy";
	public static final String DATE_PATTERN_FULL_CLEAN = "yyyyMMddmmssSSS";
	public static final DateFormat DATE_FORMAT_BASIC = new SimpleDateFormat(DATE_PATTERN_BASIC);
	public static final String DATE_NULL_DATE = "1212-12-12";
	public static final String EMPTY_DATE_PATTERN = "9999-12-31";
	private static final String REGEX_DATE_FORMAT_ISO8601 = "^([0-9]{4}(-([0-9]{2})){2})T([0-9]{2})((:[0-9]{2}){2}).[0-9]{3}[+-][0-9]{2}:[0-9]{2}$";
	private static final int YEAR_START_GREGORIAN_CALENDAR = 1582;
	private static final int MONTH_START_GREGORIAN_CALENDAR = 9;
	private static final int DAY_START_GREGORIAN_CALENDAR = 15;
	private static Date defaultInitialDate;
	private static Date defaultFinalDate;
	public static SimpleDateFormat sdfIso = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
	
	private static Pattern DATE_EXP_DATE = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");//2019-09-23
	private static Pattern DATE_EXP_DATEHOUR = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}[- ]\\d{2}:\\d{2}$"); //2019-09-23 10:31
	private static Pattern DATE_EXP_DATETIME = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}[- ]\\d{2}:\\d{2}:\\d{2}$"); //2019-09-23 10:31:21
	private static Pattern DATE_EXP_TIMESTAMP = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}[- ]\\d{2}:\\d{2}:\\d{2}.\\d{3,6}$"); //2019-09-23 10:31:21.450
	private static Pattern DATE_EXP_ISO = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}[- T]\\d{2}:\\d{2}:\\d{2}.\\d{3,6}Z$"); //2019-09-23T10:31:21.450Z
	
	private DateUtil() {}
	
	public static boolean isNull(String stringDate){
		return  StringUtils.trimToNull(stringDate) == null || (EMPTY_DATE_PATTERN.equals(stringDate) || DATE_NULL_DATE.equals(stringDate));
	}
	

	/*
	 * Realiza um parse de uma String no formato "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" para um java.util.Date
	 * (Exemplo de formato: "2016-01-30T23:58:50.986+03:00").
	 * @return java.util.Date. If stringDateParse is null or blank, return null;
	 * @throws ParseException 
	 */
	public static Date parseStringInFormatISO8601ToDate(String stringDateParse) {
		if (StringUtils.isNotBlank(stringDateParse) 
				&& stringDateParse.matches(REGEX_DATE_FORMAT_ISO8601)) {
			if (isGregorianCalendarDateInFormatISO8601(stringDateParse)) {
				return parseStringInFormatISO8601ToGregorianDate(stringDateParse);
			}
			return parseStringInFormatISO8601ToJulianDate(stringDateParse);
		}
		return null;
	}

	private static Date parseStringInFormatISO8601ToGregorianDate(String stringDate) {
		return parseStringInFormatISO8601(stringDate, GregorianCalendar.AD);
	}
	
	private static Date parseStringInFormatISO8601ToJulianDate(String stringDate) {
		return parseStringInFormatISO8601(stringDate, GregorianCalendar.BC);
	}

	private static Date parseStringInFormatISO8601(String stringDate, int era) {
		if (stringDate.matches(REGEX_DATE_FORMAT_ISO8601)) {
			int ano = Integer.parseInt(stringDate.substring(0, 4));
			int mes = Integer.parseInt(stringDate.substring(5, 7));
			int dia = Integer.parseInt(stringDate.substring(8, 10));
			int hora = Integer.parseInt(stringDate.substring(11, 13));
			int minuto = Integer.parseInt(stringDate.substring(14, 16));
			int segundo = Integer.parseInt(stringDate.substring(17, 19));
			int milissegundo = Integer.parseInt(stringDate.substring(20, 23));
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.ERA, era);
			calendar.set(Calendar.YEAR, ano);
			calendar.set(Calendar.MONTH, mes - 1);
			calendar.set(Calendar.DAY_OF_MONTH, dia);
			calendar.set(Calendar.HOUR_OF_DAY, hora);
			calendar.set(Calendar.MINUTE, minuto);
			calendar.set(Calendar.SECOND, segundo);
			calendar.set(Calendar.MILLISECOND, milissegundo);
			return calendar.getTime();
		}
		return null;
	}
	
	private static boolean isGregorianCalendarDateInFormatISO8601(String stringDate) {
		boolean retorno = false;
		
		if (stringDate.matches(REGEX_DATE_FORMAT_ISO8601)) {
			int ano = Integer.parseInt(stringDate.substring(0, 4));
			int mes = Integer.parseInt(stringDate.substring(5, 7));
			int dia = Integer.parseInt(stringDate.substring(8, 10));
			
			retorno = ano > YEAR_START_GREGORIAN_CALENDAR
					? true
					: ano == YEAR_START_GREGORIAN_CALENDAR 
							&& mes > (MONTH_START_GREGORIAN_CALENDAR + 1)
						? true
						: ano == YEAR_START_GREGORIAN_CALENDAR 
								&& mes == (MONTH_START_GREGORIAN_CALENDAR + 1)
								&& dia >= DAY_START_GREGORIAN_CALENDAR;
		}
		return retorno;
	}
	
	/**
	 * Data de início de vigência default, para os casos em que não temos uma data de início de vigência definida
	 * @return Date to 0001-01-01
	 */
	public static Date getDefaultInitDate(){
		if (defaultInitialDate == null) {
			String dateInString = "0001-01-01";
			
			try {
				defaultInitialDate = new Date(DATE_FORMAT_BASIC.parse(dateInString).getTime());
			} catch (ParseException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		
		return defaultInitialDate;
	}
	
	/**
	 * Data de início de vigência default para SQL, para os casos em que não temos uma data de início de vigência definida
	 * @return Date to 0001-01-01
	 */
	public static java.sql.Date getDefaultInitSqlDate() {
		return new java.sql.Date(getDefaultInitDate().getTime());
	}
	
	/**
	 * Data de fim de vigência default, para os casos em que não temos uma data de fim de vigência específica.
	 * @return Date to 9999-12-31
	 */
	public static Date getDefaultFinalDate() {
		if (defaultFinalDate == null) {
			String dateInString = "9999-12-31";
			
			try {
				defaultFinalDate = new Date(DATE_FORMAT_BASIC.parse(dateInString).getTime());
			} catch (ParseException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		
		return defaultFinalDate;
	}
	
	/**
	 * Data de fim de vigência default, para os casos em que não temos uma data de fim de vigência específica.
	 * @return Date to 1212-12-12
	 */
	public static Date getDefaultNullDate() {
		if (defaultFinalDate == null) {
			String dateInString = DATE_NULL_DATE;
			
			try {
				defaultFinalDate = new Date(DATE_FORMAT_BASIC.parse(dateInString).getTime());
			} catch (ParseException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		
		return defaultFinalDate;
	}
	
	/**
	 * Data de fim de vigência default para SQL, para os casos em que não temos uma data de fim de vigência específica.
	 * @return Date to 9999-12-31
	 */
	public static Date getDefaultFinalSqlDate() {
		return new java.sql.Date(getDefaultFinalDate().getTime());
	}
	
	/**
	 * Retorna o próximo dia (dia seguinte ao de hoje).
	 * @return Date to D + 1
	 */
	public static Date getNextDate(){
		Date nextDate = null;
		String stringDate = DATE_FORMAT_BASIC.format(Calendar.getInstance().getTime());
		
		try {
			nextDate = addDays(DATE_FORMAT_BASIC.parse(stringDate), 1);
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
		}
		return nextDate;
	}
	
	/**
	 * Retorna o dia atual.
	 * @return Date
	 */
	public static Date getToday(){
		Date today = null;
		String stringDate = DATE_FORMAT_BASIC.format(Calendar.getInstance().getTime());
		
		try {
			today = DATE_FORMAT_BASIC.parse(stringDate);
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
		}
		return today;
	}
	
	/**
	 * Adiciona ou subtrai a quantidade de dias (days) de uma data
	 * especificada (date)
	 * 
	 * @param date
	 * @param days
	 * @return Date to date + 1
	 */
	public static Date addDays(final Date date, final int days) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		
		return calendar.getTime();
	}
	
	/**
	 * Converte uma data string (PATTERN COBOL) para java.Util.Date
	 * @param data Data string pattern cobol
	 * @return 
	 */
	public static Date convertStringToDate(String data){

		if(data != null){
			if(data.length() > DATE_PATTERN_COBOL.length()){
				data = data.substring(0, data.length() - (data.length() - DATE_PATTERN_COBOL.length()));
				try {
					return FORMAT_PATTERN_DATETIME.parse(data);
				} catch (ParseException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}
	
	/**
	 * Converte uma data string para java.Util.Date
	 * @param data Data string pattern 
	 * @return 
	 */
	public static Date convertStringToDate(String data, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if(data != null){
			try {
				return sdf.parse(data);
			} catch (ParseException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return null;
	}
	
	/**
	 * Converte uma java.Util.Date para String no formato esperado pelo programa COBOL
	 * @param data
	 * @return Data string no formato yyyy-MM-dd HH:mm:ss.111111
	 */
	public static String convertDateToString(Date data){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_COBOL);
		StringBuilder dataString = new StringBuilder(sdf.format(data));
		dataString.append(".111111");
		return dataString.toString();
	}
	
	/**
	 * Converte uma java.Util.Date para String 
	 * @param data
	 * @param pattern
	 * @return
	 */
	public static String convertDateToString(Date data, String pattern){
		if(data == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(data);
	}
	
	public static final Date clearHourFromDate(Date data){
		if(data != null){
			String dataString = DateUtil.convertDateToString(data, DATE_PATTERN_BASIC);
			data = DateUtil.convertStringToDate(dataString, DATE_PATTERN_BASIC);
		}
		return data;
	}
	
	/**
	 * Verifica se uma data está dentro do intervalo de início e fim. Se a data de fim for null, será considerado apenas se a data é maior que a data de início.
	 * @param data
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */
	public static boolean isDataDentroDoIntervalo(Date data, Date dataInicio, Date dataFim){
		data = DateUtil.clearHourFromDate(data);
		dataInicio = DateUtil.clearHourFromDate(dataInicio);
		dataFim = DateUtil.clearHourFromDate(dataFim);
		return dataInicio != null && dataInicio.compareTo(data) <= 0 
				&& (dataFim == null || dataFim.compareTo(data) >= 0) ;
	}
	
    /**
     * Funções para lidar com:
     *
     * 1. Datas nulas (12-12-1212)
     * 2. Datas nulas (null)
     * 3. Datas anteriores ao ponto de corte do calendário gregoriano (15 de outubro de 1582, ou (new GregorianCalendar().getGregorianChange())
     *
     * Para mais informações, especialmente sobre a questão do calendário gregoriano, veja:
     * - http://stackoverflow.com/questions/13668603/timestamp-show-wrong-data
     * - http://stackoverflow.com/questions/16321193/date-change-when-converting-from-xmlgregoriancalendar-to-calendar
     * - ...ou fale com o Dirley.
     */

    @SuppressWarnings("unused")
	private static final Date NULL_DATE;
    static {
        NULL_DATE = DateUtil.getDefaultNullDate();
    }
    
    /**
     * Retorna String no formato "yyyy-MM-dd'T'HH:mm:ss'Z'"
     * @param data
     * @return
     */
    public static String getDate(Date data){
    	return sdfIso.format(data);
    }
    /**
     * Retorna String no formato "yyyy-MM-dd'T'HH:mm:ss'Z'"
     * @param data
     * @return
     */
    public static Date getDateIso(String data){
    	try {
			return sdfIso.parse(data);
		} catch (ParseException e) {
			LOG.info(e.getMessage());
			return null;
		}
    }
    /**
     * Retorna String no formato "yyyy-MM-dd'T'HH:mm:ss'Z'"
     * @param data
     * @return
     */
    public static String getDate(Date data, String formato){
    	if(data != null){
	    	SimpleDateFormat sdf = new SimpleDateFormat(formato);
	    	return sdf.format(data);
    	}
    	return null;
    }
    
    /**
     * Parce da string no formato "yyyy-MM-dd'T'HH:mm:ss'Z'" para date
     * @param data
     * @return
     */
    public static String getDate(String data){
    	return sdfIso.format(data);
    }
    
    public static void main(String[] args) throws ParseException {
//		Date dt = new Date();
//		SimpleDateFormat sdfIso = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
//		String dts = sdfIso.format(dt);
//		System.out.println("data: "+dts);
//		try {
//			System.out.println("data: "+sdfIso.parse(dts));
//			System.out.println("data: "+sdfIso.parse("2017-12-22T14:50:00.000Z"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	    Date dataDe = sdf.parse("2014-02-05");
//	    Date dataAte = sdf.parse("2015-04-05");
//	 
//	    long diferencaSegundos = (dataAte.getTime() - dataDe.getTime()) / (1000);
//	    long diferencaMinutos = (dataAte.getTime() - dataDe.getTime()) / (1000*60);
//	    long diferencaHoras = (dataAte.getTime() - dataDe.getTime()) / (1000*60*60);
//	    long diferencaDias = (dataAte.getTime() - dataDe.getTime()) / (1000*60*60*24);
//	    long diferencaMeses = (dataAte.getTime() - dataDe.getTime()) / (1000*60*60*24) / 30;
//	    long diferencaAnos = ((dataAte.getTime() - dataDe.getTime()) / (1000*60*60*24) / 30) / 12;
//	 
//	    System.out.println(String.format("Diferen�a em Segundos: %s", diferencaSegundos));
//	    System.out.println(String.format("Diferen�a em Minutos: %s", diferencaMinutos));
//	    System.out.println(String.format("Diferen�a em Horas: %s", diferencaHoras));
//	    System.out.println(String.format("Diferen�a em Dias: %s", diferencaDias));
//	    System.out.println(String.format("Diferen�a em Meses: %s", diferencaMeses));
//	    System.out.println(String.format("Diferen�a em Anos: %s", diferencaAnos));
    	
//		String data = "2019-09-23";
//		System.out.println("Data banco: "+ getDateFormats(data));
//		data = "2019-09-23-03:00";
//		System.out.println("Data banco: "+ getDateFormats(data));
//		data = "2019-09-23 03:00";
//		System.out.println("Data banco: "+ getDateFormats(data));
//		data = "2019-09-23 10:31:21.450";
//		System.out.println("Data banco: "+ getDateFormats(data));
		
//		data = "2019-09-23 10:31:21.450000";
//		System.out.println("Data banco: "+ getDateFormats(data));
		
//		data = "2019-09-23T10:31:21.450Z";
//		System.out.println("Data banco: "+ getDateFormats(data));
		
//		data = "2019-09-23-03:00";
//		System.out.println("Data banco: "+ getDateFormats(data));
		
	}
    
    /**
     * Retorna a diferen�a de anos entre duas datas, pom aproxima��o ou 0 (zero) caso uma das datas seja nula  
     * @param d1
     * @param d2
     * @return
     */
    public static Integer getDiferencaAnos(Date d1, Date d2){
    	if(d1 != null && d2 != null){
    		return (int) ((d2.getTime() - d1.getTime()) / (1000*60*60*24) / 30) / 12;
    	}
    	return 0;
    }
    
    /**
     * Retorna a diferen�a de meses entre duas datas, pom aproxima��o ou 0 (zero) caso uma das datas seja nula  
     * @param d1
     * @param d2
     * @return
     */
    public static Integer getDiferencaMeses(Date d1, Date d2){
    	if(d1 != null && d2 != null){
    		return (int) ((d2.getTime() - d1.getTime()) / (1000*60*60*24) / 30);
    	}
    	return 0;
    }
    
    public static boolean isDataBanco(String date) {
        return DATE_EXP_DATE.matcher(date).matches() 
        		|| DATE_EXP_DATETIME.matcher(date).matches()
        		|| DATE_EXP_TIMESTAMP.matcher(date).matches();
    }
    
    
    /**
     * Identifica o formato da String e converte para data (YYYY-mm_DD, YYYY-mm_DD:hh:mm, YYYY-mm_DD:hh:mm:ss...) 
     * @param data
     * @return
     */
    public static Date getDateFormats(String data) {
    	
    	try {
	    	if(DATE_EXP_DATE.matcher(data).matches()) {
					return DATE_FORMAT_BASIC.parse(data);
	    	}
	    	if(DATE_EXP_DATEHOUR.matcher(data).matches()) {
	    		return FORMAT_PATTERN_DATEHOUR.parse(data);
	    	}
	    	if(DATE_EXP_DATETIME.matcher(data).matches()) {
	    		return FORMAT_PATTERN_DATETIME.parse(data);
	    	}
	    	if(DATE_EXP_TIMESTAMP.matcher(data).matches()) {
	    		return FORMAT_PATTERN_TIMESTAMP.parse(data);
	    	}
	    	if(DATE_EXP_ISO.matcher(data).matches()) {
	    		return getDateIso(data);
	    	}
    	} catch (Exception e) {
    		// n�o faz nada
    	}
    	return null;
    }
}