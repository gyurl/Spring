package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello SSSSS World!!!";
	}
	
	@GetMapping("/jump")
	@ResponseBody
	public String jump() {
		return  "점프투 스프링 부트";
	}
}
