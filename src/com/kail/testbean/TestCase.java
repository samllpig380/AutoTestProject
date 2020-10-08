package com.kail.testbean;

import java.util.List;
import java.util.Map;

/**
 * 测试用例，只要不是太过于复杂的测试流程，都可以直接写在Excel文件中，
 * 当然，需要符合一定的规范，很显然，这离自然语言还是有一定的距离 。这种形式比较适合step-by-step的测试用例，
 * 并且粒度比较粗，减少了测试用例的步骤数
 * 
 * @author kail
 *
 */
public class TestCase {
	
	private String bizName;//业务名称，对应脚本文件的名称，要唯一
	private String taskName;//对应具体的执行方法名称，与bizName关联
	//数据所在Sheet的名称。
	//如果引用的是其它Excel文件，需要在Sheet名称前面加上excel文件名。
	//如"Login.login"，用"."隔开
	private String bizDataTable;
	private String filter;//数据执行条件
	private String description;//描述步骤的备注信息
	private Map<String,List<String>> tdata;//测试数据加载
	
	public Map<String,List<String>> getTdata() {
		return tdata;
	}
	public void setTdata(Map<String,List<String>> tdata) {
		this.tdata = tdata;
	}
	public String getBizName() {
		return bizName;
	}
	public void setBizName(String bizName) {
		this.bizName = bizName;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getBizDataTable() {
		return bizDataTable;
	}
	public void setBizDataTable(String bizDataTable) {
		this.bizDataTable = bizDataTable;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
}
