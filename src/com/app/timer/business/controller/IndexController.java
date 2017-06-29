package com.app.timer.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 定时任务对象的控制层
 * @author liuxf
 *
 */
@Controller
public class IndexController {

	@RequestMapping("/index")
	public String goHome(){
		return "index";
	}
}
