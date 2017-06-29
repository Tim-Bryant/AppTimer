package com.app.timer.business.pojo;

import javax.persistence.Column;
import javax.persistence.Table;
import org.beetl.sql.core.annotatoin.TableTemplate;
import com.app.timer.utils.BasePojo;

/**
 * 定时器对象基础类
 * 
 * @author liuxf
 * 
 */
@Table(name="app_ot_timer")
@TableTemplate("order by code asc ")
public class AppTimer extends BasePojo {
	private static final long serialVersionUID = 1L;
	/** 定时器编码 */
	private String code;
	/** 定时器名称 */
	private String name;
	/** 定时器描述 */
	private String description;
	/** 定时器执行时间表达式 */
	private String optTime;
	/** 执行的路径 */
	private String url;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="opt_time")
	public String getOptTime() {
		return optTime;
	}

	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
