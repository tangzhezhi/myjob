<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service.${subpackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ${basepackage}.dao.${subpackage}.${className}Dao;
import ${basepackage}.dto.${subpackage}.${className}DTO;
import ${basepackage}.utils.Page;

@Service
public class ${className}Service {
	@Autowired
	private ${className}Dao ${classNameLower}Dao;
	
	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  Page find${className}(Page page){
		Page  pageList = (Page) ${classNameLower}Dao.select${className}All(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}
	
	public int insert${className}(${className}DTO dto){
		int flag = 0;
		if(dto!=null){
			flag = ${classNameLower}Dao.insert${className}(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int update${className}(${className}DTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = ${classNameLower}Dao.update${className}(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int delete${className}(String ${classNameLower}Id){
		int flag = 0;
		if(${classNameLower}Id!=null){
			flag = ${classNameLower}Dao.delete${className}(${classNameLower}Id);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
}
