package com.kail.testbean;

public class Report {
	
	public String projectName;//项目名称
	public String version;//项目版本号
	public String modifyVersion;//修改记录_修改版本号
	public String writeDate;//编写日期
	public String performer;//执行着
	public String tStartTime;//测试开始时间
	public String tEndTime;//测试结束时间
	public String tPlace;//测试地点
	public String infoDisk;//硬盘信息
	public String infoCpu;//cpu信息
	public String infoMem;//内存信息
	public String infoSystem;//操作系统信息
	public String infoRobot;//测试框架信息
	public String infoOffice;//office信息
	public String execTime;//测试执行时间=测试开始时间-测试结束时间
	public String execTc;//执行的测试用例数
	public String passTc;//成功执行的测试用例数
	public String failTc;//失败执行的测试用例数
	

	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getModifyVersion() {
		return modifyVersion;
	}
	public void setModifyVersion(String modifyVersion) {
		this.modifyVersion = modifyVersion;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getPerformer() {
		return performer;
	}
	public void setPerformer(String performer) {
		this.performer = performer;
	}
	public String gettStartTime() {
		return tStartTime;
	}
	public void settStartTime(String tStartTime) {
		this.tStartTime = tStartTime;
	}
	public String gettEndTime() {
		return tEndTime;
	}
	public void settEndTime(String tEndTime) {
		this.tEndTime = tEndTime;
	}
	public String gettPlace() {
		return tPlace;
	}
	public void settPlace(String tPlace) {
		this.tPlace = tPlace;
	}
	public String getInfoDisk() {
		return infoDisk;
	}
	public void setInfoDisk(String infoDisk) {
		this.infoDisk = infoDisk;
	}
	public String getInfoCpu() {
		return infoCpu;
	}
	public void setInfoCpu(String infoCpu) {
		this.infoCpu = infoCpu;
	}
	public String getInfoMem() {
		return infoMem;
	}
	public void setInfoMem(String infoMem) {
		this.infoMem = infoMem;
	}
	public String getInfoSystem() {
		return infoSystem;
	}
	public void setInfoSystem(String infoSystem) {
		this.infoSystem = infoSystem;
	}
	public String getInfoRobot() {
		return infoRobot;
	}
	public void setInfoRobot(String infoRobot) {
		this.infoRobot = infoRobot;
	}
	public String getInfoOffice() {
		return infoOffice;
	}
	public void setInfoOffice(String infoOffice) {
		this.infoOffice = infoOffice;
	}
	public String getExecTime() {
		return execTime;
	}
	public void setExecTime(String execTime) {
		this.execTime = execTime;
	}
	public String getExecTc() {
		return execTc;
	}
	public void setExecTc(String execTc) {
		this.execTc = execTc;
	}
	public String getPassTc() {
		return passTc;
	}
	public void setPassTc(String passTc) {
		this.passTc = passTc;
	}
	public String getFailTc() {
		return failTc;
	}
	public void setFailTc(String failTc) {
		this.failTc = failTc;
	}

}
