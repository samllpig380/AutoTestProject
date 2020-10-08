package com.kail.util.file.poi;


import java.util.List;
@SuppressWarnings("all")
public interface PoiExcelWriteHandler {
	public void write(String sheetName, List<String> headList,List dataList);
	public void write(List<String> headList,List dataList);
	public void write(List dataList);
	public void write(String sheetName, List<String> headList, List dataList ,int startRow,int startColumn);
}
