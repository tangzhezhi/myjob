package org.tang.myjob.dao.product;

import org.springframework.stereotype.Repository;
import org.tang.myjob.dto.product.ProductDTO;

import java.util.List;

/**
 * Created by Administrator on 2015/3/30.
 */
@Repository
public interface ProductDao {
    public List<ProductDTO> selectProduct();
}
