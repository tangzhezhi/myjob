<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package org.tang.jpa.controller.${subpackage};

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;

@Controller("${classNameLower}Controller")  
@RequestMapping("${classNameLower}")  
@SessionAttributes("currentUser")
public class ${className}Controller {
	@Autowired
	private ${className}Service ${classNameLower}Service;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/query${className}", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> query${className}(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="${classNameLower}name",required=false) String ${classNameLower}Name
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        params.put("${classNameLower}name", ${classNameLower}Name);
        page.setParams(params);
        Page p = ${classNameLower}Service.find${className}(page);
        model.put("rows",p==null?0:p.getResults());
        model.put("total", p==null?0:p.getTotalRecord());
        return model;  
    }  
	
	
	@RequestMapping(value = "/add${className}", method = RequestMethod.POST)  
    @ResponseBody  
    public String add${className}(@ModelAttribute("currentUser") UserDTO dto,
    		 <#list table.columns as column>
				@RequestParam(value="${column.columnNameLower}",required=false) String ${column.columnNameLower}<#if column_has_next>,</#if>
			 </#list>
    		) {  
	        ${className}DTO rdto = new ${className}DTO();
	        <#list table.columns as column>
	        	rdto.set${column.columnName}(${column.columnNameLower});
        	</#list>
	       
	        int flag =  ${classNameLower}Service.insert${className}(rdto);
	        if(flag == 1){
	        	return MyConstants.ADDSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.ADDFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/modify${className}", method = RequestMethod.POST)  
    @ResponseBody  
    public String modify${className}(@ModelAttribute("currentUser") UserDTO dto,
	        <#list table.columns as column>
				@RequestParam(value="${column.columnNameLower}",required=false) String ${column.columnNameLower}<#if column_has_next>,</#if>
			</#list>
    		) {  
	        ${className}DTO rdto = new ${className}DTO();
	        <#list table.columns as column>
        		rdto.set${column.columnName}(${column.columnNameLower});
        	</#list>
	       
	        int flag =  ${classNameLower}Service.update${className}(rdto);
	        if(flag == 1){
	        	return MyConstants.MODIFYSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.MODIFYFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/delete${className}", method = RequestMethod.POST)  
    @ResponseBody  
    public String delete${className}(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="${classNameLower}id",required=true) String ${classNameLower}Id) {  
			${className}DTO rdto = new ${className}DTO();
	        int flag =  ${classNameLower}Service.delete${className}(${classNameLower}Id);
	        if(flag == 1){
	        	return MyConstants.DELSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.DELFAIL.getName();
	        }
    }
	
}
