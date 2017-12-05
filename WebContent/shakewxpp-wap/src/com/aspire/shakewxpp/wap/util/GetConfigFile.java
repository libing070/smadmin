package com.aspire.shakewxpp.wap.util;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.aspire.shakewxpp.wap.entity.ConfigPojo;

public class GetConfigFile {
	private static GetConfigFile instance;
	private List<ConfigPojo> configPojoList;
	private List<ConfigPojo> nextConfigPojoList;
	private String refreshDate;
	
	private static Properties props = null;
	private static int user_c = 0;
	private static int user_c_r = 0;
	
	private GetConfigFile() throws Exception{
		// 根据资源文件system.properties
		props = new Properties();
		props.load(new FileInputStream(System.getProperty("user.dir")+System.getProperty("file.separator")+"shakewxpp-wap"+System.getProperty("file.separator")+"conf"+System.getProperty("file.separator")+"system.properties"));
	}
	
	public synchronized static GetConfigFile getInstance() throws Exception{
		if(instance ==null || props == null){
			instance = new GetConfigFile();
		}
		return instance;
	}
	
	public String getProperties(String key) throws Exception
	{	
		user_c = user_c%100000;//避免整型越界
		++user_c;//使用一次加1，用来标志是否正在取数据
		String value = props.getProperty(key);
		if(StringUtils.isEmpty(value)){
			throw new Exception("配置项:"+key+"不存在");
		}
		user_c_r = user_c;//表示数据已取出,可以刷新props
		return new String(value.getBytes("ISO-8859-1"),"utf-8");
	}

	public static void refreshSystemConfig() throws Exception{
			int wait_c = 0;
			while(user_c_r != user_c && wait_c < 1000){
				Thread.sleep(10);
				wait_c++;
			}
			instance = new GetConfigFile();
	}
	
	public Set viewProps(){
		Set set = props.keySet();
		return set;
	}

	public List<ConfigPojo> getConfigPojoList() {
		if(nextConfigPojoList!=null && StringUtils.isNotEmpty(refreshDate)){
			String currentDate = DateUtil.getCurDate(DateUtil.yyyyMMdd_EN);
			
			if(!currentDate.equals(refreshDate)){
				configPojoList = nextConfigPojoList;
				refreshDate=null;
			}
		}
		return configPojoList;
	}

	public void setConfigPojoList(List<ConfigPojo> configPojoList) {
		this.configPojoList = configPojoList;
	}

	public List<ConfigPojo> getNextConfigPojoList() {
		return nextConfigPojoList;
	}

	public void setNextConfigPojoList(List<ConfigPojo> nextConfigPojoList) {
		this.nextConfigPojoList = nextConfigPojoList;
	}

	public String getRefreshDate() {
		return refreshDate;
	}

	public void setRefreshDate(String refreshDate) {
		this.refreshDate = refreshDate;
	}

	
}
