package com.app.timer.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * HTTP请求将参数放入到MAP对象工具
 * 
 * @author liuxf
 * 
 */
public class HttpRequestParametersUtil {
	/**
	 * HTTP解析前台传递过来的参数(非muti-part文件方式提交)封装MAP中
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getNormalFormParameters(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> names = request.getParameterNames();
		for (; names.hasMoreElements();) {
			String name = (String) names.nextElement();
			String value = request.getParameter(name);
			map.put(name, value);
		}
		return map;
	}
}
