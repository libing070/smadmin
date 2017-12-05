package com.aspire.wifi.wap.service.intf;


import com.aspire.wifi.wap.entity.StudentReport;

import java.util.List;
import java.util.Map;

public interface StudentReportService {
	StudentReport getStudentReport(String msisdn) throws Exception;
	void insertNewStudent(StudentReport studentReport) throws Exception;
	void updateStudentReport(StudentReport studentReport) throws Exception;
	void updateReportPicStatus(StudentReport studentReport) throws Exception;
	void updateReportFootStatus(String id) throws Exception;
	List<Map<String, Object>> queryStudentReportForMyActive(StudentReport studentReport) throws Exception;
}
