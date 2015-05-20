
package org.tang.myjob.dao.system;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.myjob.utils.page.PageDataTable;
import org.tang.myjob.dto.system.PermissionDTO;

@Repository
public interface PermissionDao  {
	
    public int insertPermission(PermissionDTO dto);  
    
    public int updatePermission(PermissionDTO dto);
     
    public int deletePermission(String permissionId);  
     
    public PageDataTable<?> selectPermissionPage(PageDataTable<?> page);
    
}
