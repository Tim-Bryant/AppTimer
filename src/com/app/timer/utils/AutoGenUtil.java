package com.app.timer.utils;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.DefaultNameConversion;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.MapperCodeGen;

public class AutoGenUtil {
	// 数据库常量
	public static final String driver = "com.mysql.jdbc.Driver";
	public static final String url = "jdbc:mysql://127.0.0.1:3306/app-timer";
	public static final String userName = "root";
	public static final String password = "passw0rd";

	public static void main(String[] args) throws Exception {
		ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
		DBStyle mysql = new MySqlStyle();
		// sql语句放在classpath的目录下的sql下
		SQLLoader loader = new ClasspathLoader("/sql");
		// 数据库的命名和JAVA命名一样,所以采用DefaultNameConversion
		// 还有一个是UnderLinedNameConversion 下划线风格的
		UnderlinedNameConversion nc = new UnderlinedNameConversion();

		SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[] { new DebugInterceptor() });

		GenConfig gConfig=new GenConfig();
		gConfig.setPreferBigDecimal(true);
		gConfig.setPreferDate(true);
		gConfig.setBaseClass("com.app.timer.utils.BasePojo");
		//动态生成Mapper代码
		MapperCodeGen mcg=new MapperCodeGen("com.app.timer.business.dao");
		
		gConfig.codeGens.add(mcg);
		sqlManager.genPojoCode("app-ot-timer","com.app.timer.business.pojo",gConfig);
	}
}
