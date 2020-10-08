package com.kail.util.file.poi;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
@SuppressWarnings("all")
public class PoiExcel2003WriteHandler implements PoiExcelWriteHandler {
	private String filaPath = "";

	public PoiExcel2003WriteHandler(String filaPath) {
		this.filaPath = filaPath;
	}

	public HSSFWorkbook getHsskWorkbook() {
		return new HSSFWorkbook();
	}

	public HSSFSheet getHSSFSheet(HSSFWorkbook hssfWorkbook, String title) {
		if (hssfWorkbook == null) {
			return null;
		}
		if (title == null || "".equals(title)) {
			return hssfWorkbook.createSheet();
		}
		return hssfWorkbook.createSheet(title);
	}

	public HSSFSheet getHSSFSheet(HSSFWorkbook hssfWorkbook) {
		if (hssfWorkbook == null) {
			return null;
		}
		return hssfWorkbook.createSheet();
	}

	public HSSFRow getHSSFRow(HSSFSheet hssfSheet, int rownum) {
		if (hssfSheet == null) {
			return null;
		}
		return hssfSheet.createRow(rownum);
	}

	public HSSFCell getHSSFCell(HSSFRow hssfRow, int column) {
		if (hssfRow == null) {
			return null;
		}
		return hssfRow.createCell(column);
	}

	public void setCellValue(HSSFCell cell, Object value) {
		if (cell == null || value == null) {
			return;
		}
		cell.setCellValue(value.toString());
	}

	public void write(HSSFWorkbook hssfWorkbook) {
		if (hssfWorkbook == null) {
			return;
		}

		OutputStream os = null;
		try {
			os = new FileOutputStream(filaPath);
			hssfWorkbook.write(os);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void write(String sheetName, List<String> headList,List dataList)
	{
		if(dataList == null || dataList.size() < 1)
		{
			return;
		}
		
		HSSFWorkbook hssfWorkbook = getHsskWorkbook();
		HSSFSheet hssfSheet = getHSSFSheet(hssfWorkbook, sheetName);
		HSSFRow hssfRow = null;
		HSSFCell hssfCell = null;
		if(headList != null && headList.size() > 0)
		{
			//第一行
			hssfRow = hssfSheet.createRow(0);
			for (int i = 0; i < headList.size(); i++) {
				hssfCell = hssfRow.createCell(i);
				hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
				hssfCell.setCellValue(headList.get(i));
			}
		}
		
		if(dataList != null && dataList.size() > 1)
		{
			//row
			for (int i = 0; i < dataList.size(); i++) {
				List rowList = (List) dataList.get(i);
				hssfRow = hssfSheet.createRow(i+1);
				if(rowList != null && rowList.size() > 0)
				{
					for (int j = 0; j < rowList.size(); j++) {
						String cellValue = (String) rowList.get(j);
						hssfCell = hssfRow.createCell(j);
						hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						hssfCell.setCellValue(cellValue);
					}
				}
			}
		}
		write(hssfWorkbook);
	}
	
	public void write(String sheetName, List<String> headList,List dataList,int startRow,int startColumn)
	{
		if(dataList == null || dataList.size() < 1)
		{
			return;
		}
		
		HSSFWorkbook hssfWorkbook = getHsskWorkbook();
		HSSFSheet hssfSheet = getHSSFSheet(hssfWorkbook, sheetName);
		HSSFRow hssfRow = null;
		HSSFCell hssfCell = null;
		if(headList != null && headList.size() > 0)
		{
			//第一行
			hssfRow = hssfSheet.createRow(0 + startRow);
			for (int i = 0; i < headList.size(); i++) {
				hssfCell = hssfRow.createCell(i + startColumn);
				hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
				hssfCell.setCellValue(headList.get(i));
			}
		}
		
		if(dataList != null && dataList.size() > 1)
		{
			//row
			for (int i = 0; i < dataList.size(); i++) {
				List rowList = (List) dataList.get(i);
				hssfRow = hssfSheet.createRow(i+1 +startRow);
				if(rowList != null && rowList.size() > 0)
				{
					for (int j = 0; j < rowList.size(); j++) {
						String cellValue = (String) rowList.get(j);
						hssfCell = hssfRow.createCell(j + startColumn);
						hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						hssfCell.setCellValue(cellValue);
					}
				}
			}
		}
		write(hssfWorkbook);
	}
	
	
	public void write(List<String> headList,List dataList)
	{
		if(dataList == null || dataList.size() < 1)
		{
			return;
		}
		
		HSSFWorkbook hssfWorkbook = getHsskWorkbook();
		HSSFSheet hssfSheet = getHSSFSheet(hssfWorkbook);
		HSSFRow hssfRow = null;
		HSSFCell hssfCell = null;
		if(headList != null && headList.size() > 0)
		{
			//第一行
			hssfRow = hssfSheet.createRow(0);
			for (int i = 0; i < headList.size(); i++) {
				hssfCell = hssfRow.createCell(i);
				hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
				hssfCell.setCellValue(headList.get(i));
			}
		}
		
		if(dataList != null && dataList.size() > 1)
		{
			//row
			for (int i = 0; i < dataList.size(); i++) {
				List rowList = (List) dataList.get(i);
				hssfRow = hssfSheet.createRow(i+1);
				if(rowList != null && rowList.size() > 0)
				{
					for (int j = 0; j < rowList.size(); j++) {
						String cellValue = (String) rowList.get(j);
						hssfCell = hssfRow.createCell(j);
						hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						hssfCell.setCellValue(cellValue);
					}
				}
			}
		}
		write(hssfWorkbook);
	}
	
	public void write(List dataList)
	{
		if(dataList == null || dataList.size() < 1)
		{
			return;
		}
		
		HSSFWorkbook hssfWorkbook = getHsskWorkbook();
		HSSFSheet hssfSheet = getHSSFSheet(hssfWorkbook);
		HSSFRow hssfRow = null;
		HSSFCell hssfCell = null;
		
		if(dataList != null && dataList.size() > 1)
		{
			
			//row
			for (int i = 0; i < dataList.size(); i++) {
				List rowList = (List) dataList.get(i);
				hssfRow = hssfSheet.createRow(i+1);
				if(rowList != null && rowList.size() > 0)
				{
					for (int j = 0; j < rowList.size(); j++) {
						String cellValue = (String) rowList.get(j);
						hssfCell = hssfRow.createCell(j);
						hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						hssfCell.setCellValue(cellValue);
					}
				}
			}
		}
		write(hssfWorkbook);
	}
}
