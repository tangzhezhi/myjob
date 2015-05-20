
package org.tang.myjob.dao.system;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.myjob.utils.page.PageDataTable;
import org.tang.myjob.dto.system.UserDTO;

@Repository
public interface UserDao  {
	
    public int insertUser(UserDTO dto);  
    
    public int updateUser(UserDTO dto);
     
    public int deleteUser(String userId);  
     
    public PageDataTable<?> selectUserPage(PageDataTable<?> page);

    public UserDTO getByUsername(String username);
}
