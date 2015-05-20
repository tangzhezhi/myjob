/*
 * Powered By tangzezhi
 * Since 2013 - 2015
 */

package org.tang.myjob.service.system;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.tang.myjob.dao.system.UserDao;
import org.tang.myjob.dto.system.UserDTO;
import org.tang.myjob.utils.page.PageDataTable;
import java.util.ArrayList;

@Service
public class UserService {

	private static Logger logger = Logger.getLogger(UserService.class.getName());

	@Autowired
	private UserDao userDao;
	
	/**
	 * 分页
	 * @param
	 * @return
	 */
	@Cacheable(cacheName = "CustomerCache")
	public  PageDataTable findUser(PageDataTable page){
		try {
			page.setAaData( userDao.selectUserPage(page) == null ? new ArrayList() : userDao.selectUserPage(page).getAaData());
		} catch (Exception e) {
			logger.error("获取分页出错:", e);
		}
		return page;
	}

	public int insertUser(UserDTO dto) throws Exception {
		int flag = 0;
		if(dto!=null){
			try{
				flag = userDao.insertUser(dto);
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

	@TriggersRemove(cacheName ={"CustomerCache"},removeAll=true)
	public int updateUser(UserDTO rdto) throws Exception{
		int flag = 0;
		if(rdto!=null){
			try{
				flag = userDao.updateUser(rdto);
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
	
	
	
	public int deleteUser(String userId) throws Exception{
		int flag = 0;
		if(StringUtils.hasText(userId)){
			try{
				flag = userDao.deleteUser(userId);
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


	public UserDTO getByUsername(String username) {
		try {
			if(StringUtils.hasText(username)){
				UserDTO user =  userDao.getByUsername(username);
				return  user;
			}
		} catch (Exception e) {
			logger.error("获取用户出错:", e);
		}
		return null;
	}
}
