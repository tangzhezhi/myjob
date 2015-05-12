<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dto.${subpackage};
import org.apache.log4j.Logger;
import java.io.Serializable;

public class ${className}DTO  extends PageDataTable {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    <#list table.columns as column>
    private ${column.javaType} ${column.columnNameLower};
	</#list>
	
	<#list table.columns as column>
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
	</#list>	

}
