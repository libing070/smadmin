package com.aspire.wifi.wap.mapper;

import java.util.List;
import java.util.Map;

import com.aspire.wifi.wap.entity.StudentReport;

public interface StudentReportMapper {
	List<StudentReport> getStudentReport(String mobile) throws Exception;
	void insertNewStudent(StudentReport studentReport) throws Exception;
	void updateReportFootStatus(String id) throws Exception;
	void updateStudentReport(StudentReport studentReport) throws Exception;
    List<Map<String, Object>> queryStudentReportForMyActive(StudentReport studentReport) throws Exception;
	void updateReportPicStatus(StudentReport studentReport) throws Exception;
}
