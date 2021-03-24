package util;

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
}
