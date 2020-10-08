package com.kail.util.file.poi;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings("all")
public class PoiExcel2007WriteHandler implements PoiExcelWriteHandler {

	private String filaPath = "";

	public PoiExcel2007WriteHandler(String filaPath) {
		this.filaPath = filaPath;
	}

	public XSSFWorkbook getXssfWorkbook() {
		return new XSSFWorkbook();
	}

	public XSSFSheet getXSSFSheet(XSSFWorkbook xssfWorkbook, String title) {
		if (xssfWorkbook == null) {
			return null;
		}
		if (title == null || "".equals(title)) {
			return xssfWorkbook.createSheet();
		}
		
		return xssfWorkbook.createSheet(title);
	}

	public XSSFSheet getXSSFSheet(XSSFWorkbook xssfWorkbook) {
		if (xssfWorkbook == null) {
			return null;
		}
		return xssfWorkbook.createSheet();
	}

	public XSSFRow getXSSFRow(XSSFSheet xssfSheet, int rownum) {
		if (xssfSheet == null) {
			return null;
		}
		return xssfSheet.createRow(rownum);
	}

	public XSSFCell getXSSFCell(XSSFRow xssfRow, int column) {
		if (xssfRow == null) {
			return null;
		}
		return xssfRow.createCell(column);
	}

	public void setCellValue(XSSFCell cell, Object value) {
		if (cell == null || value == null) {
			return;
		}
		cell.setCellValue(value.toString());
	}

	public void write(XSSFWorkbook xssfWorkbook) {
		if (xssfWorkbook == null) {
			return;
		}

		OutputStream os = null;
		try {
			os = new FileOutputStream(filaPath);
			xssfWorkbook.write(os);
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

	public void write(String sheetName, List<String> headList,
			List dataList) {
		if (dataList == null || dataList.size() < 1) {
			return;
		}

		XSSFWorkbook xssfWorkbook = getXssfWorkbook();
		XSSFSheet xssfSheet = getXSSFSheet(xssfWorkbook, sheetName);
		XSSFRow xssfRow = null;
		XSSFCell xssfCell = null;
		if (headList != null && headList.size() > 0) {
			// 第一行
			xssfRow = xssfSheet.createRow(0);
			for (int i = 0; i < headList.size(); i++) {
				xssfCell = xssfRow.createCell(i);
				xssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
				xssfCell.setCellValue(headList.get(i));
			}
		}

		if(dataList != null && dataList.size() > 1)
		{
			//row
			for (int i = 0; i < dataList.size(); i++) {
				List rowList = (List) dataList.get(i);
				xssfRow = xssfSheet.createRow(i+1);
				if(rowList != null && rowList.size() > 0)
				{
					for (int j = 0; j < rowList.size(); j++) {
						String cellValue = (String) rowList.get(j);
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						xssfCell.setCellValue(cellValue);
					}
				}
			}
		}
		write(xssfWorkbook);
	}
	
	public void write(String sheetName, List<String> headList,
			List dataList ,int startRow,int startColumn) {
		if (dataList == null || dataList.size() < 1) {
			return;
		}

		XSSFWorkbook xssfWorkbook = getXssfWorkbook();
		XSSFSheet xssfSheet = getXSSFSheet(xssfWorkbook, sheetName);
		XSSFRow xssfRow = null;
		XSSFCell xssfCell = null;
		if (headList != null && headList.size() > 0) {
			// 第一行
			xssfRow = xssfSheet.createRow(0 +startRow);
			for (int i = 0; i < headList.size(); i++) {
				xssfCell = xssfRow.createCell(i + startColumn);
				xssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
				xssfCell.setCellValue(headList.get(i));
			}
		}

		if(dataList != null && dataList.size() > 1)
		{
			//row
			for (int i = 0; i < dataList.size(); i++) {
				List rowList = (List) dataList.get(i);
				xssfRow = xssfSheet.createRow(i+1 +startRow);
				if(rowList != null && rowList.size() > 0)
				{
					for (int j = 0; j < rowList.size(); j++) {
						String cellValue = (String) rowList.get(j);
						xssfCell = xssfRow.createCell(j + startColumn);
						xssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						xssfCell.setCellValue(cellValue);
					}
				}
			}
		}
		write(xssfWorkbook);
	}
	

	public void write(List<String> headList, List dataList) {
		if (dataList == null || dataList.size() < 1) {
			return;
		}

		XSSFWorkbook xssfWorkbook = getXssfWorkbook();
		XSSFSheet xssfSheet = getXSSFSheet(xssfWorkbook);
		XSSFRow xssfRow = null;
		XSSFCell xssfCell = null;
		if (headList != null && headList.size() > 0) {
			// 第一行
			xssfRow = xssfSheet.createRow(0);
			for (int i = 0; i < headList.size(); i++) {
				xssfCell = xssfRow.createCell(i);
				xssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
				xssfCell.setCellValue(headList.get(i));
			}
		}

		if(dataList != null && dataList.size() > 1)
		{
			//row
			for (int i = 0; i < dataList.size(); i++) {
				List rowList = (List) dataList.get(i);
				xssfRow = xssfSheet.createRow(i+1);
				if(rowList != null && rowList.size() > 0)
				{
					for (int j = 0; j < rowList.size(); j++) {
						String cellValue = (String) rowList.get(j);
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						xssfCell.setCellValue(cellValue);
					}
				}
			}
		}
		write(xssfWorkbook);
	}

	public void write(List dataList) {
		if (dataList == null || dataList.size() < 1) {
			return;
		}

		XSSFWorkbook xssfWorkbook = getXssfWorkbook();
		XSSFSheet xssfSheet = getXSSFSheet(xssfWorkbook);
		XSSFRow xssfRow = null;
		XSSFCell xssfCell = null;

		if(dataList != null && dataList.size() > 1)
		{
			//row
			for (int i = 0; i < dataList.size(); i++) {
				List rowList = (List) dataList.get(i);
				xssfRow = xssfSheet.createRow(i+1);
				if(rowList != null && rowList.size() > 0)
				{
					for (int j = 0; j < rowList.size(); j++) {
						String cellValue = (String) rowList.get(j);
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
						xssfCell.setCellValue(cellValue);
					}
				}
			}
		}
		write(xssfWorkbook);
	}

}
