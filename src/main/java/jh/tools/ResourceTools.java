package jh.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * 获取系统图片
 * @author ruizzy
 *
 */
public class ResourceTools {
	
	public static byte[] getImgResource(String path) {
        
		byte[] rs = null;
		
		//获取容器资源解析器
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        
        try {
			Resource[] resources = resolver.getResources("classpath*:"+path);
			for (Resource resource : resources) {
				byte[] buff = new byte[1024];
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				int len = 0 ; 
				InputStream is = resource.getInputStream();
				while( (len=is.read(buff)) != -1 ) {
					
					out.write(buff);
					
				}
				rs = out.toByteArray();	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		return rs;
		
	}
	
	
}
