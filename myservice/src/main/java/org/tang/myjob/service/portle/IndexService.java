package org.tang.myjob.service.portle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.myjob.dao.login.UserDao;
import org.tang.myjob.dao.message.MessageDao;
import org.tang.myjob.dao.product.ProductDao;
import org.tang.myjob.dto.message.MessageDTO;
import org.tang.myjob.dto.product.ProductDTO;
import org.tang.myjob.service.exception.BusinessException;
import org.tang.myjob.service.exception.BusinessRuntimeException;
import org.tang.myjob.service.exception.ExceptionType;

import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */

@Service
public class IndexService {
    private static Logger logger = Logger.getLogger(IndexService.class.getName());

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private ProductDao productDao;

    public MessageDTO getMessage() throws Exception {
        MessageDTO dto = null;
        try {
            dto =  messageDao.selectMessage();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return dto;
    }

    public List<ProductDTO> getProduct(ProductDTO dto) throws Exception {
        try {
            return productDao.selectProduct(dto);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
