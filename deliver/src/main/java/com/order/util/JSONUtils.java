package com.order.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JSONUtils {
	static ObjectMapper mapper = new ObjectMapper();
	static String newLine = "\\\\n";
	static String message;

	public static String toJSON(Object obj) {
		String result = "";
		if (obj != null) {
			try {
				result = mapper.writeValueAsString(obj);
			} catch (Exception e) {
				message = e.getMessage().replaceAll("\r\n", newLine).replaceAll("\n", newLine);
				throw new ServiceException("msg.JSONError", new String[] {message}, e.getMessage());
			}
		}
		return result;
	}

	public static <T> T parseJSON(String jsonString, Class<T> beanClass) {
		try {
			return mapper.readValue(jsonString, beanClass);
		} catch (Exception e) {
			message = e.getMessage().replaceAll("\r\n", newLine).replaceAll("\n", newLine);
			throw new ServiceException("msg.JSONError", new String[] {message}, e.getMessage());
		}
	}
}
