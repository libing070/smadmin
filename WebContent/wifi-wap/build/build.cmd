@echo off
@rem ���û�������
set JAVA_HOME=D:\Program Files\Java\jdk1.6.0_29
set ANT_HOME=D:\software\apache-ant-1.8.4
set PATH=%JAVA_HOME%/bin;%ANT_HOME%/bin

set COMPONENT_SRC_PATH=E:\Project\operating_proj\��ҵ������ƽ̨�׼�\08У԰wifi\02�з�����\03����\wifi-wap

set RELEASE_HOME=%COMPONENT_SRC_PATH%\release


ant -f build.xml -l log.txt
pause