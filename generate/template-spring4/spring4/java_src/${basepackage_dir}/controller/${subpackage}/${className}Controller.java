<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.controller.${subpackage};

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import  ${basepackage}.service.${subpackage}. ${className}Service;

@Controller("${classNameLower}Controller")
public class ${className}Controller {

	private static Logger logger = Logger.getLogger(${className}Controller.class.getName());

	@Autowired
	private ${className}Service ${classNameLower}Service;


	@Auth
	@RequestMapping(value={"/${classNameLower}"})
	public ModelAndView ${classNameLower}(){
		ModelAndView mv = new ModelAndView("/${classNameLower}");
		return mv;
	}

	@RequestMapping(value = "${classNameLower}/query", method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public Object query(${className}DTO params) throws Exception {
			PageDataTable page = new PageDataTable();
			try {
				page = ${classNameLower}Service.find${className}(params);
			}
			catch (Exception br){
				logger.error(ExceptionType.product_msg, br);
				throw new Exception(br);
			}

			return page;
	}

	
	@RequestMapping(value = "${classNameLower}/add", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody  
    public Map add (${className}DTO params) {
			Map m = new HashMap();
			int flag = -1;
			try {
				flag  =  ${classNameLower}Service.insert${className}(params);
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

	
	@RequestMapping(value = "${classNameLower}/modify", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody  
    public Map modify(${className}DTO params) {
			int flag = -1;
			Map m = new HashMap();
			try {
					flag =  ${classNameLower}Service.update${className}(params);
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
	
	
	
	@RequestMapping(value = "${classNameLower}/delete",  method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody  
    public Map delete${className}(${className}DTO params) {
		int flag = -1;
		Map m = new HashMap();
		try {
			flag =  ${classNameLower}Service.delete${className}(params.getId());
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
