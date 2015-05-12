package org.tang.myjob.controller.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tang.myjob.controller.Interceptor.Auth;
import org.tang.myjob.dto.business.MeetDTO;
import org.tang.myjob.service.business.MeetService;
import org.tang.myjob.service.exception.ExceptionType;
import org.tang.myjob.utils.page.PageDataTable;


@Controller("meetController")
public class MeetController {

	private static Logger logger = Logger.getLogger(MeetController.class.getName());

	@Autowired
	private MeetService meetService;


	@Auth
	@RequestMapping(value={"/meet"})
	public ModelAndView meet(){
		ModelAndView mv = new ModelAndView("/business/Meet");
		return mv;
	}

	@RequestMapping(value = "meet/query", method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public Object query(MeetDTO params) throws Exception {
		PageDataTable page = new PageDataTable();
		try {
			page = meetService.findMeet(params);
		}
		catch (Exception br){
			logger.error(ExceptionType.product_msg, br);
			throw new Exception(br);
		}

		return page;
	}


	@RequestMapping(value = "meet/add", method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public Map add (MeetDTO params) {
		Map m = new HashMap();
		int flag = -1;
		try {
			flag  =  meetService.insertMeet(params);
			if(flag > 0){
				m.put("msg","success");
			}
			else{
				m.put("msg","false");
			}
		}
		catch (Exception br){
			logger.error(ExceptionType.msg, br);
		}
		return m;
	}


	@RequestMapping(value = "meet/modify", method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public Map modify(MeetDTO params) {
		Map<String ,Object> m = new HashMap<String ,Object>();
		int flag = -1;
		try {
			flag =  meetService.updateMeet(params);
			if(flag > 0){
				m.put("msg","success");
			}
			else{
				m.put("msg","false");
			}
		}
		catch (Exception br){
			logger.error(ExceptionType.msg, br);
		}
		return m;

	}



	@RequestMapping(value = "meet/delete",  method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public Map deleteMeet(MeetDTO params) {
		int flag = -1;
		Map<String ,Object> m = new HashMap<String ,Object>();
		try {
			flag =  meetService.deleteMeet(params.getId());
			if(flag > 0){
				m.put("msg","success");
			}
			else{
				m.put("msg","false");
			}
		}
		catch (Exception br){
			logger.error(ExceptionType.msg, br);
		}
		return m;

	}

}
