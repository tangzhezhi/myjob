/*
 * Powered By tangzezhi
 * Since 2013 - 2015
 */

package org.tang.myjob.service.system;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.myjob.dao.system.RoleDao;
import org.tang.myjob.dto.system.RoleDTO;
import org.tang.myjob.utils.page.PageDataTable;
import java.util.ArrayList;

@Service
public class RoleService {

	private static Logger logger = Logger.getLogger(RoleService.class.getName());

	@Autowired
	private RoleDao roleDao;
	
	/**
	 * 分页
	 * @param
	 * @return
	 */
	public  PageDataTable findRole(PageDataTable page){
		try {
			page.setAaData( roleDao.selectRolePage(page) == null ? new ArrayList() : roleDao.selectRolePage(page).getAaData());
		} catch (Exception e) {
			logger.error("获取分页出错:", e);
		}
		return page;
	}
	
	public int insertRole(RoleDTO dto) throws Exception {
		int flag = 0;
		if(dto!=null){
			try{
				flag = roleDao.insertRole(dto);
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
	
	
	public int updateRole(RoleDTO rdto) throws Exception{
		int flag = 0;
		if(rdto!=null){
			try{
				flag = roleDao.updateRole(rdto);
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
	
	
	
	public int deleteRole(String roleId) throws Exception{
		int flag = 0;
		if(null !=roleId && roleId.length() > 0){
			try{
				flag = roleDao.deleteRole(roleId);
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
