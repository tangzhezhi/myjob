package org.tang.myjob.service.portle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.myjob.dao.login.UserDao;
import org.tang.myjob.dao.message.MessageDao;
import org.tang.myjob.dto.message.MessageDTO;
import org.tang.myjob.service.exception.BusinessException;
import org.tang.myjob.service.exception.ExceptionType;

/**
 * Created by Administrator on 2015/3/23.
 */

@Service
public class IndexService {
    private static Logger logger = Logger.getLogger(IndexService.class.getName());

    @Autowired
    private MessageDao messageDao;

    public MessageDTO getMessage() throws BusinessException {
        MessageDTO dto = null;
        dto =  messageDao.selectMessage();
        return dto;
    }

}
