package com.aspire.shakewxpp.wap.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.aspire.shakewxpp.wap.service.ConfigService;
import com.aspire.shakewxpp.wap.util.GetConfigFile;

public class DBConfigLoadProcessor implements BeanPostProcessor { 
	protected static Logger logger = LoggerFactory.getLogger(DBConfigLoadProcessor.class);
	
	public Object postProcessAfterInitialization(Object obj, String arg1) throws BeansException {  
		 if(obj instanceof ConfigService) { 
			 try{
				 String configTable = GetConfigFile.getInstance().getProperties("provinceConfigTable");
				 ((ConfigService)obj).getConfigData(configTable);
			 }catch(Exception e){
				 logger.error("初始化配置表数据异常",e);
			 }
		}  
		return obj;
	}  
	  
    public Object postProcessBeforeInitialization(Object arg0, String arg1)  
            throws BeansException {  
        return arg0;  
    }  
}
