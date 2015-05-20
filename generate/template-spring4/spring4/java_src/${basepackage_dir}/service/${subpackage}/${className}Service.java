<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service.${subpackage};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${basepackage}.dao.${subpackage}.${className}Dao;
import ${basepackage}.dto.${subpackage}.${className}DTO;
import ${basepackage}.utils.page.PageDataTable;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

@Service
public class ${className}Service {

	private static Logger logger = Logger.getLogger(${className}Service.class.getName());

	@Autowired
	private ${className}Dao ${classNameLower}Dao;
	
	/**
	 * 分页
	 * @param page
	 * @return
	 */
	public  PageDataTable find${className}(PageDataTable page){
		try {
			page.setAaData( ${classNameLower}Dao.select${className}Page(page) == null ? new ArrayList() : ${classNameLower}Dao.select${className}Page(page).getAaData());
		} catch (Exception e) {
			logger.error("获取分页出错:", e);
		}
		return page;
	}
	
	public int insert${className}(${className}DTO dto) throws Exception {
		int flag = 0;
		if(dto!=null){
			try{
				flag = ${classNameLower}Dao.insert${className}(dto);
			}
			catch (Exception e){
				logger.error("插入出错:", e);
				throw new Exception(e);
			}
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int update${className}(${className}DTO rdto) throws Exception{
		int flag = 0;
		if(rdto!=null){
			try{
				flag = ${classNameLower}Dao.update${className}(rdto);
			}
			catch (Exception e){
				logger.error("更新出错:", e);
				throw new Exception(e);
			}
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int delete${className}(String ${classNameLower}Id) throws Exception{
		int flag = 0;
		if((StringUtils.hasText(${classNameLower}Id)){
			try{
				flag = ${classNameLower}Dao.delete${className}(${classNameLower}Id);
			}
			catch (Exception e){
				logger.error("删除出错:", e);
				throw new Exception(e);
			}
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
}
