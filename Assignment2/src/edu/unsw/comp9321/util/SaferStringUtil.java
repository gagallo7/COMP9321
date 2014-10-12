package edu.unsw.comp9321.util;

import org.apache.commons.lang3.StringEscapeUtils;

public class SaferStringUtil {
	public static String convert(String string){
		return StringEscapeUtils.unescapeHtml4(string);
	}
}
