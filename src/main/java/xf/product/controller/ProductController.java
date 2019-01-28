package xf.product.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import jh.tools.ResourceTools;
import xf.product.entity.Product;
import xf.product.service.IProductService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hjh
 * @since 2019-01-28
 */
@RestController
@RequestMapping("/product/product")
public class ProductController {
	
	@Autowired
	IProductService productService;
	
	@RequestMapping("/a")
	@ResponseBody
	public void a(HttpServletResponse response) throws Exception {
//		productService.getById("1");
//		List<Product> plist = productService.list();
//		plist.forEach((p)->{ResourceTools.getImgResource(p.getPath());});
		response.setContentType("image/png");
		
		OutputStream out = response.getOutputStream();
		
		out.write(ResourceTools.getImgResource(productService.getById("1").getPath()));
		out.flush();
		out.close();
//		return JSONObject.toJSONString(productService.list());
		
	}
	
	@RequestMapping("/info")
	@ResponseBody
	public String info(HttpServletResponse response) throws Exception {
		return JSONObject.toJSONString(productService.list());
	}
	
}
