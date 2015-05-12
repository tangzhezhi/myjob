package org.tang.myjob.controller.business;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping("")
	public String home(){
		return "index";
	}
	
	@RequestMapping("/json")
	@ResponseBody
	public String json(){
		return "json";
	}
	
	@RequestMapping("/admin")
	@ResponseBody
	public List<String> admin(){
		return Arrays.asList("zhangsan", "lisi", "wangwu");
	}
}
