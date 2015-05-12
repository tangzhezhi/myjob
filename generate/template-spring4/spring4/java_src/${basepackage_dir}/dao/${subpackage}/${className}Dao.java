<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  

package ${basepackage}.dao.${subpackage};

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import ${basepackage}.utils.page.PageDataTable;
import ${basepackage}.dto.${subpackage}.${className}DTO;

@Repository
public interface ${className}Dao  {
	
    public int insert${className}(${className}DTO dto);  
    
    public int update${className}(${className}DTO dto);
     
    public int delete${className}(String ${classNameLower}Id);  
     
    public PageDataTable<?> select${className}Page(PageDataTable<?> page);
    
}
