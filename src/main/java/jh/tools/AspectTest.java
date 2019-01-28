package jh.tools;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.apache.ibatis.javassist.CannotCompileException;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.expr.ExprEditor;
import org.apache.ibatis.javassist.expr.MethodCall;

//import com.alibaba.fastjson.JSON;

import xf.product.service.impl.ProductServiceImpl;


public class AspectTest {

	public static List<Class> enhanceClass() throws Exception {

		List<Class> rs = new ArrayList<>();
		
        ClassPool classPool = ClassPool.getDefault();
        
        List<String> classNames = getClassName("xf.product.service.impl");
        
        for (String className : classNames) {
			
        	//获取目标类
        	CtClass targetClass = classPool.getCtClass(className);
        	
        	//获取织入的连接点
        	CtMethod[] ctMethods = targetClass.getDeclaredMethods();
    		
    		for (CtMethod doSomething : ctMethods) {
        	
    			try {
					System.out.println(targetClass.getName()+"."+doSomething.getName());
		        	doSomething.getMethodInfo();
		        	
		        	doSomething.getParameterTypes();
		        	//织入增强代码，这里会重新加载字节码
		        	doSomething.insertBefore("jh.tools.AspectTest.printLog($args,\"asd\");");
		        	doSomething.instrument(new ExprEditor() {  
		                public void edit(MethodCall m) throws CannotCompileException {  
		                    m.replace("{ long stime = System.currentTimeMillis(); $_ = $proceed($$);System.out.println(\""
		                            + m.getClassName() + "." + m.getMethodName()
		                            + " cost:\" + (System.currentTimeMillis() - stime) + \" ms\");}");
		                }
		            });
		        	doSomething.insertAfter("jh.tools.AspectTest.printLog($_,\"asd\");");
		        	
		        	Date AspectLogDate = new Date();
		        	System.out.println(new Date().getTime()-AspectLogDate.getTime());
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    		rs.add(targetClass.toClass());
        }
		return rs;
    }


	
	public static <T> T printLog(T a,String c) {
//		System.out.println(JSON.toJSONString(a));
//		System.out.println(JSON.toJSONString(c));
		return a;
	}
	
	
	public static void main(String[] args) throws Exception {

		enhanceClass();
		
		ProductServiceImpl i = new ProductServiceImpl();
//		i.test();
		
	}
	
	public static List<String> getClassName(String packageName) throws Exception{
		
		String nowResourcesPath = packageName.replaceAll("[.]", "/");
		
		Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(nowResourcesPath);
		
		List<String> classeNames = new ArrayList<>();
		while(urls.hasMoreElements()) {
			URL url = urls.nextElement();
			String protocol = url.getProtocol();
			if("file".equals(protocol)) {
				findClass(new File(url.getFile()),classeNames,packageName);
			}
		}
		return classeNames;

	}
	
	
	public static void findClass(File startFile,List<String> classeNames,String packageResult) {
		
		
		if(startFile.isDirectory()) {
			packageResult = packageResult+".";
			File[] dirfiles = startFile.listFiles();
			for (File file : dirfiles) {
				String nextPackageResult = packageResult+file.getName();
				findClass(file, classeNames ,nextPackageResult);
			}
		}else {
			classeNames.add(packageResult.replaceAll(".class", ""));
			return;
		}
		
		
	}
	
	
}
