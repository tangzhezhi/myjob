package org.tang.myjob.dao.product;

import org.springframework.stereotype.Repository;
import org.tang.myjob.dto.product.OrderDTO;
import org.tang.myjob.utils.page.Page;
import org.tang.myjob.utils.page.PageDataTable;

import java.util.List;

/**
 * Created by Administrator on 2015/3/30.
 */
@Repository
public interface OrderDao {
    public List<OrderDTO> selectOrder(OrderDTO dto);

    public PageDataTable selectOrderPage(PageDataTable dto);
}
