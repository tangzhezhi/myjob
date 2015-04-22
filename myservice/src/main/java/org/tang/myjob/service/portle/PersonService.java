package org.tang.myjob.service.portle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.myjob.dao.product.OrderDao;
import org.tang.myjob.dto.product.OrderDTO;

import java.util.List;

/**
 * Created by Administrator on 2015/4/21.
 */

@Service
public class PersonService {
    private static Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private OrderDao orderDao;


    public List<OrderDTO> getPersonOrder(String userid) {
        List list = null;
        try {
            OrderDTO dto = new OrderDTO();
            dto.setUserId(userid);
            list = orderDao.selectOrder(dto);
        } catch (Exception e) {
            logger.error("获取用户订单出错:", e);
        }
        return list;
    }
}