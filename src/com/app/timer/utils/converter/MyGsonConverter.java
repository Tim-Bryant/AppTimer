package com.app.timer.utils.converter;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.google.gson.GsonBuilder;

/**
 * @responsebody
 * 我个人的对于Gson数据的转换逻辑
 * @author liuxf
 *
 */
public class MyGsonConverter extends GsonHttpMessageConverter {
	
	public MyGsonConverter() {
		//更换Gson转换器
		super.setGson(new GsonBuilder().serializeNulls()      //null值属性也需要序列化
						.setDateFormat("yyyy-MM-dd HH:mm:ss") //设置日期转换
						.setPrettyPrinting()
						.create());
	}

	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		return null;
	}

}
