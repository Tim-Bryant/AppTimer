package com.app.timer.business.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.beetl.ext.fn.Json;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.timer.business.pojo.AppTimer;
import com.app.timer.business.service.AppTimerService;
import com.app.timer.utils.AppConstant;
import com.app.timer.utils.HttpRequestParametersUtil;
import com.app.timer.utils.RandomGUID;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 定时任务对象的控制层
 * @author liuxf
 *
 */
@Controller
@RequestMapping("/timer")
public class AppTimerController {

	@Autowired
	private AppTimerService appTimerService;
	
	@RequestMapping("/home")
	public String goHome(){
		return "area1";
	}
	
	@RequestMapping("/add")
	public String goAddPage(){
		return "apptimer/add";
	}
	
	@RequestMapping("/list")
	public ModelAndView goList(HttpServletRequest request){
		ModelAndView mView=new ModelAndView("apptimer/list");
		return mView;
	}
	
	//@RequestMapping(value="/listdata",produces = "application/json; charset=utf-8")
	@RequestMapping(value="/listdata")
	public @ResponseBody String goListData(HttpServletRequest request){
		try {
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			PageQuery<AppTimer> query=new PageQuery<AppTimer>();
			query.setPageNumber(Long.parseLong(pageNum));
			query.setPageSize(Long.parseLong(pageSize));
			//填写排序信息 推荐写法
			query.setOrderBy("modify_time desc,create_time desc");
			List<AppTimer> listAllTimers = appTimerService.listAllTimers(query);
			Map<String, Object> map=new HashMap<String, Object>();
			/** 
	         * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。 
	         * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。 
	         * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。 
	         * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。 
	         * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。 
	         * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。 
	         */  
	        ObjectMapper mapper = new ObjectMapper();
	        //map.put("data", listAllTimers);
	        map.put("page", query);
	        String writeValueAsString = mapper.writeValueAsString(map);
	        System.out.println(writeValueAsString);
	        return writeValueAsString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String, Object> saveAppTimer(HttpServletRequest request){
		Map<String, Object> map=new HashMap<String, Object>();
		//需要用到GSOn你转换器否则前台无法解析
		//JsonObject jsonObject=new JsonObject();
		try {
			Map<String, String> params = HttpRequestParametersUtil.getNormalFormParameters(request);
			//先查询页面隐藏域ID是否有值判断是否是修改对象
			String objId=StringUtils.isBlank(params.get("id"))?"":params.get("id").toString();
			AppTimer appTimer=null;
			if(StringUtils.isBlank(objId)){
				appTimer=new AppTimer();
				appTimer.setId(new RandomGUID().getUUID());
				appTimer.setCreateTime(new Date());
			}else{
				appTimer=this.appTimerService.getById(objId);
			}
			appTimer.setCode(StringUtils.isBlank(params.get("code"))?"":params.get("code").toString());
			appTimer.setName(StringUtils.isBlank(params.get("name"))?"":params.get("name").toString());
			if(!StringUtils.isBlank(params.get("state"))){
				String val=params.get("state").toString().equals("on")?AppConstant.STATE_OPEN:AppConstant.STATE_CLOSED;
				appTimer.setState(val);
			}else{
				appTimer.setState(AppConstant.STATE_CLOSED);
			}
			appTimer.setOptTime(StringUtils.isBlank(params.get("optTime"))?"":params.get("optTime").toString());
			appTimer.setDescription(StringUtils.isBlank(params.get("description"))?"":params.get("description").toString());
			appTimer.setUrl(StringUtils.isBlank(params.get("url"))?"":params.get("url").toString());
			appTimer.setModifyTime(new Date());
			this.appTimerService.saveAppTimer(appTimer);
			//jsonObject.addProperty("message", "恭喜，定时器信息保存成功");
			map.put("message", "恭喜，定时器信息保存成功");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("message", "对不起，定时器信息保存失败");
		return map;
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Map<String, Object> deleteAppTimer(HttpServletRequest request) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		//记录删除的条数
		int delcounts=0;
		if(!StringUtils.isBlank(ids)){
			String[] idArray = ids.split(",");
			for(String id:idArray){
				delcounts+=this.appTimerService.deleteAppTimer(id);
			}
		}
		map.put("message", "成功删除【"+delcounts+"】条记录");
		return map;
	}
	
	@RequestMapping(value="/detail/{id}",method=RequestMethod.GET)
	public ModelAndView detailAppTimer(@PathVariable(value = "id") String id,HttpServletRequest request) throws Exception{
		ModelAndView mView=new ModelAndView("apptimer/add");
		if(!StringUtils.isBlank(id)){
			AppTimer app = this.appTimerService.getById(id);
			mView.addObject("appTimer", app);
		}
		return mView;
	}
	
}
