package com.kail.testbean;

/**
 * 管理测试用例文件，在这里，扮演了TD的管理测试用例和脚本的角色
 * @author kail
 *
 */
public class TestSet {
	
	private String name;//测试用例的名称
	private String table;//测试用例所在的Excel文件名
	private String sheet;//测试用例所在的sheet名称
	private String prority;//测试用例的执行优先级
	private String description;//对测试用例的描述
	private String version;//当前测试用例的版本号
	private String performer;//当前测试用例的执行者
	private String tPlace;//测试地点
	private String PcCpu;//执行自动化测试的计算机信息
	private String PcDisk;//
	private String PcMem;//
	private String PcOffice;
	private String PcRobot;//
	private String PcOS;
	private String modifyVersion;//修订版本号
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getSheet() {
		return sheet;
	}
	public void setSheet(String sheet) {
		this.sheet = sheet;
	}
	public String getPrority() {
		return prority;
	}
	public void setPrority(String prority) {
		this.prority = prority;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPerformer() {
		return performer;
	}
	public void setPerformer(String performer) {
		this.performer = performer;
	}
	public String gettPlace() {
		return tPlace;
	}
	public void settPlace(String tPlace) {
		this.tPlace = tPlace;
	}
	
	public String getPcCpu(){
		return PcCpu;
	}
	public void setPcCpu(String cpu){
		this.PcCpu = cpu;
	}
	
	public String getPcDisk(){
		return PcDisk;
	}
	public void setPcDisk(String disk){
		this.PcDisk = disk;
	}
	public String getPcMem(){
		return PcMem;
	}
	public void setPcMem(String mem){
		this.PcMem = mem;
	}
	public String getPcOffice(){
		return PcOffice;
	}
	public void setPcOffice(String office){
		this.PcOffice = office;
	}
	public String getPcRobot(){
		return PcRobot;
	}
	public void setPcRobot(String robot){
		this.PcRobot = robot;
	}
	
	public String getPcOS(){
		return PcOS;
	}
	public void setPcOS(String pcOS){
		this.PcOS = pcOS;
	}
	
	public String getModifyVersion(){
		return modifyVersion;
	}
	
	public void setModifyVersion(String mVersion){
		this.modifyVersion = mVersion;
	}
}
