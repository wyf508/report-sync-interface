package com.djhu.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 拦截所有参数，并且校验是否含有sql 关键字
 * @author wuping.deng
 */
@Component
public class IllegalCharacterInterceptor implements HandlerInterceptor  {
	private Logger logger= LoggerFactory.getLogger(IllegalCharacterInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg) throws Exception {
		  
		  //  String url=parseUrl(request.getServletPath());
		    Enumeration<?> params = request.getParameterNames();
		    StringBuffer sql = new StringBuffer();
	        while (params.hasMoreElements()) {
	            // 得到参数名
	            String name = params.nextElement().toString();
	            // 得到参数对应值
	            String[] value = request.getParameterValues(name);
	            if(value==null || value.length<0){
	            	continue;
	            }
	            for (int i = 0; i < value.length; i++) {
	            	sql.append(value[i]).append(",");
	            }
	        }
	        if (sqlValidate(sql.toString().toLowerCase())) {
	        	  logger.error(request.getRequestURI()+ "该请求参数中带有sql关键字本次不允许发送请求！！！");
	        	  response.setStatus(403);
	        	return false;
	        } else {
	        	return true;
	        }
	}
	
	 // 校验
    protected  boolean sqlValidate(String str) {
        str = str.toLowerCase();// 统一转为小写
        // String badStr = "and|exec";
        String badStr = "and|exec|execute|insert|select|delete|update|drop|chr|mid|master|truncate|char|declare|sitename|net user|xp_cmdshell|or|like|group_concat|column_name|table|from|grant|information_schema.columns|table_schema|union|where|order|by|count|*|;|--|+|//|%|#|'";
        
        // 过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) != -1) {
                logger.error("请求参数中带有sql关键字："+badStrs[i]);
                return true;
            }
        }
        return false;
    }
    
	/**
     * 去掉servlet path所带的参数
     */
    private String parseUrl(String pathUrl) {
        String result = "";
        int index = pathUrl.indexOf("?");
        if (index >= 0)
            result = result.substring(index+1, pathUrl.length());

        index = result.indexOf("#");
        if (index >= 0)
            result = result.substring(index+1,pathUrl.length());

        return result;
    }

	

	
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
