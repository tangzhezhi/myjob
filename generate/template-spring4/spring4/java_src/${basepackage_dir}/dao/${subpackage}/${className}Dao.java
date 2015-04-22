<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  

package ${basepackage}.dao.${subpackage};

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import ${basepackage}.utils.Page;

@Repository
public interface ${className}Dao  {
	
    public int insert${className}(${className}DTO dto);  
    
    public int update${className}(${className}DTO dto);
     
    public int delete${className}(String ${classNameLower}Id);  
     
    public Page<?> select${className}All(Page<?> page);  
    
}
