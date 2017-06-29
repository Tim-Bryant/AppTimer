package com.app.timer.business.dao;

import java.util.List;
import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import com.app.timer.business.pojo.AppTimer;

/**
 * 定时器的Dao数据访问层
 * @author liuxf
 *
 */
public interface AppTimerDao extends BaseMapper<AppTimer> {
	/**
	 * 根据编码获取定时器对象
	 * @param code
	 * @return
	 */
	// or 使用params一一对应  方法名和MD文件中的===名要一样
	@SqlStatement(params = "code")
	public AppTimer selectbyCode(String code);
	
	/**
	 * 根据ID修改定时器的名称
	 * @param age
	 * @param id
	 */
	// 使用sqlready
	@Sql(value = "update app_ot_timer set name = ? where id = ? ")
	public void updateName(String age, String id);

	/**
	 * 获取所有定时器的中文名列表
	 * @return
	 */
	@Sql(value = " select name from app_ot_timer", returnType = String.class)
	public List<String> allAppTimerNames();

}
