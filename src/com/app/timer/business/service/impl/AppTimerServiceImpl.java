package com.app.timer.business.service.impl;

import java.util.List;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.timer.business.dao.AppTimerDao;
import com.app.timer.business.pojo.AppTimer;
import com.app.timer.business.service.AppTimerService;

@Service
public  class AppTimerServiceImpl implements AppTimerService {

	@Autowired
	private SQLManager sqlManager;
	
	@Autowired
	private AppTimerDao appTimerDao;
	
	@Override
	public List<AppTimer> listAllTimers(PageQuery<AppTimer> query) throws Exception {
		List<AppTimer> list=null;
		if(query==null){
			list=appTimerDao.all();
		}else{
			//需要分页的情况
			sqlManager.pageQuery("apptimer.queryalltimers", AppTimer.class, query);
			list=query.getList();
		}
		return list;
	}

	@Override
	public AppTimer selectbyCodeAndName(String code) throws Exception {
		AppTimer selectbyCode= appTimerDao.selectbyCode(code);
		return selectbyCode;
	}

	@Override
	public void saveAppTimer(AppTimer appTimer) throws Exception {
		//保存对象，如果ID数据库存在，则更新否则插入新的记录
		AppTimer single = this.appTimerDao.single(appTimer.getId());
		if(single==null){
			this.appTimerDao.insert(appTimer, true);
		}else{
			this.appTimerDao.updateById(appTimer);
		}
	}

	@Override
	public int deleteAppTimer(String id) throws Exception {
		int deleteById = this.appTimerDao.deleteById(id);
		return deleteById;
	}

	@Override
	public AppTimer getById(String id) throws Exception {
		AppTimer single = this.appTimerDao.single(id);
		return single;
	}

}
