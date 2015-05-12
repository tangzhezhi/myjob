/*
 * Powered By tangzezhi
 * Since 2013 - 2015
 */

package org.tang.myjob.service.business;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.myjob.dao.business.MeetDao;
import org.tang.myjob.dto.business.MeetDTO;
import org.tang.myjob.utils.page.PageDataTable;

import java.util.ArrayList;

@Service
public class MeetService {

	private static Logger logger = Logger.getLogger(MeetService.class.getName());

	@Autowired
	private MeetDao meetDao;

	/**
	 * 分页
	 * @return
	 */
	public  PageDataTable findMeet(PageDataTable page){
		try {
			page.setAaData( meetDao.selectMeetPage(page) == null ? new ArrayList() : meetDao.selectMeetPage(page).getAaData());
		} catch (Exception e) {
			logger.error("获取分页出错:", e);
		}
		return page;
	}

	public int insertMeet(MeetDTO dto)  throws Exception {
		int flag = 0;
		if(dto!=null){
			flag = meetDao.insertMeet(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}


	public int updateMeet(MeetDTO rdto) throws Exception{
		int flag = 0;
		if(rdto!=null){
			flag = meetDao.updateMeet(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}



	public int deleteMeet(int id) throws Exception{
		int flag = 0;
		if(id>0){
			flag = meetDao.deleteMeet(id);
		}
		else{
			flag = 0;
		}
		return flag;
	}



}
