
package org.tang.myjob.dao.system;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.myjob.utils.page.PageDataTable;
import org.tang.myjob.dto.system.RoleDTO;

@Repository
public interface RoleDao  {
	
    public int insertRole(RoleDTO dto);  
    
    public int updateRole(RoleDTO dto);
     
    public int deleteRole(String roleId);  
     
    public PageDataTable<?> selectRolePage(PageDataTable<?> page);
    
}
