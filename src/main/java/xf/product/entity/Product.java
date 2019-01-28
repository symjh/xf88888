package xf.product.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hjh
 * @since 2019-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @TableId
    private Integer pid;

    private String name;

    private BigDecimal price;

    private String description;

    private String path;


}
