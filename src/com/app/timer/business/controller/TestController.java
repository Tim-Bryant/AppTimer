package com.app.timer.business.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beetl.sql.core.SQLManager;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.timer.business.pojo.AppTimer;
import com.app.timer.business.service.AppTimerService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Spring MVC测试
 * @author liuxf
 * 
 */
@Controller
@RequestMapping(value="/test")
public class TestController {
	
	@Autowired
	private SQLManager sqlManager;
	
	@Autowired
	private AppTimerService appTimerService;
	
	@RequestMapping(value="/go",method=RequestMethod.POST)
	public ModelAndView goHome() {
		List<AppTimer> all = sqlManager.all(AppTimer.class);
		for(AppTimer tm:all){
			System.out.println(tm.getId());
		}
		return new ModelAndView("test");
	}
	
	@RequestMapping(value="/go",method=RequestMethod.GET)
	public ModelAndView goGet() {
		
		return new ModelAndView("test");
	}
	
	@RequestMapping("/xml")
	public @ResponseBody String asXml(HttpServletRequest request,HttpServletResponse response) throws DocumentException, IOException {
		ModelAndView mView=new ModelAndView();
        response.setContentType("text/xml; charset=UTF-8");  
        Document parseText = DocumentHelper.parseText("<response><opt>111111111飞卡刷放开手发的11</opt></response>");
        PrintWriter out = response.getWriter();  
        out.print(parseText.asXML());  
        out.flush();  
        out.close();
		return null;
	}
	
	@RequestMapping("/obj")
	public @ResponseBody Gson asObject(HttpServletRequest request,HttpServletResponse response) throws DocumentException, IOException {
		Gson gson=new Gson();
		AppTimer appTimer=null;
		try {
			List<AppTimer> listAllTimers = this.appTimerService.listAllTimers(null);
			appTimer=listAllTimers.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//gson.addProperty("datas","20170906");
		//gson.toJson(appTimer);
		return gson;
	}
}
