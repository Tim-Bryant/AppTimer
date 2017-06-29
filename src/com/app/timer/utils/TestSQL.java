package com.app.timer.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.DefaultNameConversion;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.JPA2NameConversion;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SQLReady;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.ext.DebugInterceptor;

import com.app.timer.business.pojo.AppTimer;

public class TestSQL {
	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/app-timer";
		String userName = "root";
		String password = "passw0rd";
		ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
		DBStyle mysql = new MySqlStyle();
		// sql语句放在classpath的目录下的sql下
		SQLLoader loader = new ClasspathLoader("/sql");
		// 数据库的命名和JAVA命名一样,所以采用DefaultNameConversion
		// 还有一个是UnderLinedNameConversion 下划线风格的
		JPA2NameConversion nc = new JPA2NameConversion();
		//UnderlinedNameConversion uNameConversion=new UnderlinedNameConversion();
		// 最后创建一个SQLManager,       DebugInterceptor 不是必须要，但是可以查看sql执行情况
		SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[] { new DebugInterceptor() });

		RandomGUID uuidGuid=new RandomGUID();
		
		AppTimer appTimer=new AppTimer();
		appTimer.setId(uuidGuid.getUUID());
		appTimer.setCode("APP_Spring_MESSAGE");
		appTimer.setName("Spring定时器");
		appTimer.setCreateTime(new Date());
		appTimer.setModifyTime(new Date());
		appTimer.setOptTime("* * * * *");
		appTimer.setState(AppConstant.STATE_OPEN);
		appTimer.setDescription("定时器的测试");
		sqlManager.insert(AppTimer.class,appTimer);
		
		
		//long allCount = sqlManager.allCount(AppTimer.class);
		//List<AppTimer> all = sqlManager.all(AppTimer.class);
	}
}
