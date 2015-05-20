/*
 * Powered By tangzezhi
 * Since 2013 - 2015
 */

package org.tang.myjob.service.system;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.tang.myjob.dao.system.PermissionDao;
import org.tang.myjob.dto.system.PermissionDTO;
import org.tang.myjob.utils.page.PageDataTable;
import java.util.ArrayList;

@Service
public class PermissionService {

	private static Logger logger = Logger.getLogger(PermissionService.class.getName());

	@Autowired
	private PermissionDao permissionDao;
	
	/**
	 * 分页
	 * @param
	 * @return
	 */
	public  PageDataTable findPermission(PageDataTable page){
		try {
			page.setAaData( permissionDao.selectPermissionPage(page) == null ? new ArrayList() : permissionDao.selectPermissionPage(page).getAaData());
		} catch (Exception e) {
			logger.error("获取分页出错:", e);
		}
		return page;
	}
	
	public int insertPermission(PermissionDTO dto) throws Exception {
		int flag = 0;
		if(dto!=null){
			try{
				flag = permissionDao.insertPermission(dto);
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
	
	
	public int updatePermission(PermissionDTO rdto) throws Exception{
		int flag = 0;
		if(rdto!=null){
			try{
				flag = permissionDao.updatePermission(rdto);
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
	
	
	
	public int deletePermission(String permissionId) throws Exception{
		int flag = 0;

		if(StringUtils.hasText(permissionId)){
			try{
				flag = permissionDao.deletePermission(permissionId);
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
