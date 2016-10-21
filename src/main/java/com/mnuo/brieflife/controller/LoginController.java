/**
 * LoginController.java created at 2016年10月21日 下午2:50:56
 */
package com.mnuo.brieflife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mnuo.brieflife.common.JsonResult;
import com.mnuo.brieflife.entity.Coffee;

/**
 * @author saxon
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@RequestMapping("/home")
	@ResponseBody
	public JsonResult home(){
		JsonResult jsonresult = new JsonResult();
		return jsonresult;
	}
	@RequestMapping("/home1")
	@ResponseBody
	public String home1(){
		JsonResult jsonresult = new JsonResult();
		return "11";
	}
	@RequestMapping(value="{name}")
	@ResponseBody
	public Coffee getCoffeeInXML(@PathVariable String name) {
		Coffee coffee = new Coffee(name, 100);
		return coffee;
	}
}
