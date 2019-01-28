package xf.product.service.impl;

import xf.product.entity.Product;
import xf.product.mapper.ProductMapper;
import xf.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjh
 * @since 2019-01-28
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
