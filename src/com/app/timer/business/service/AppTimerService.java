package com.app.timer.business.service;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import com.app.timer.business.pojo.AppTimer;

public interface AppTimerService {
	/**
	 * 获取所有定时器信息
	 * @return
	 * @throws Exception
	 */
	public List<AppTimer> listAllTimers(PageQuery<AppTimer> query) throws Exception;
	
	/**
	 * 根据code和name获取对象 code具有唯一性
	 * @return
	 * @throws Exception
	 */
	public AppTimer selectbyCodeAndName(String code) throws Exception;
	
	/**
	 * 根据ID获取定时器对象
	 * @return
	 * @throws Exception
	 */
	public AppTimer getById(String id) throws Exception;
	
	/**
	 * 保存定时器
	 * @return
	 * @throws Exception
	 */
	public void saveAppTimer(AppTimer appTimer) throws Exception;
	
	/**
	 * 删除定时器
	 * @return
	 * @throws Exception
	 */
	public int deleteAppTimer(String id) throws Exception;
}
