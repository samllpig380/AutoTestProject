package com.kail.testbean;

public class Table1{
	
	private String funcName;//方法名称
	private String funcPass;//执行成功的用例数
	private String funcFail;//执行失败的用例数
	private String funcTotal;//用例总数=成功+失败
	
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getFuncPass() {
		return funcPass;
	}
	public void setFuncPass(String funcPass) {
		this.funcPass = funcPass;
	}
	public String getFuncFail() {
		return funcFail;
	}
	public void setFuncFail(String funcFail) {
		this.funcFail = funcFail;
	}
	public String getFuncTotal() {
		return funcTotal;
	}
	public void setFuncTotal(String funcTotal) {
		this.funcTotal = funcTotal;
	}
	
	
	
}
