package com.aspire.wifi.wap.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aspire.wifi.wap.entity.StudentReport;
import com.aspire.wifi.wap.exception.WifiException;
import com.aspire.wifi.wap.mapper.StudentReportMapper;
import com.aspire.wifi.wap.service.intf.StudentReportService;

@Service("studentReportService")
public class StudentReportServiceImpl implements StudentReportService {
	protected static final Logger logger =  LoggerFactory.getLogger(StudentReportServiceImpl.class);

	@Resource(name = "studentReportMapper")
	private StudentReportMapper studentReportMapper;

	public StudentReport getStudentReport(String msisdn) throws Exception {
		StudentReport result = null;
		try{
			List<StudentReport> list = studentReportMapper.getStudentReport(msisdn);
			if(list != null && list.size() > 0){
				result = list.get(0);
			}
		}catch(Exception e){
			throw new WifiException("查询新生信息异常!",e);
		}
		return result;
	}

	public void insertNewStudent(StudentReport studentReport) throws Exception {
		try{
			studentReportMapper.insertNewStudent(studentReport);
		}catch(Exception e){
			throw e;
		}
	}

	public void updateStudentReport(StudentReport studentReport) throws Exception {
		try{
			studentReportMapper.updateStudentReport(studentReport);
		}catch(Exception e){
			throw e;
		}
		
	}
	
	public void updateReportPicStatus(StudentReport studentReport) throws Exception {
		try{
			studentReportMapper.updateReportPicStatus(studentReport);
		}catch(Exception e){
			throw new WifiException("更新新生信息异常！",e);
		}
		
	}

    public List<Map<String, Object>> queryStudentReportForMyActive(StudentReport studentReport) throws Exception {
        try{
        return studentReportMapper.queryStudentReportForMyActive(studentReport);
        }catch(Exception e){
        	e.printStackTrace();
        	throw new WifiException("查询我的活动异常！",e);
        }
    }

	public void updateReportFootStatus(String id) throws Exception {
		try{
			studentReportMapper.updateReportFootStatus(id);
		}catch(Exception e){
			throw new WifiException("更新新生信息是否阅读状态异常！",e);
		}
	}
}
