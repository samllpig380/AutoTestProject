package com.kail.util.file.poi;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiExcel2007ReadHandler implements PoiExcelReadHandler{

	
	private String filePath = "";
	
	public PoiExcel2007ReadHandler(String filePath)
	{
		this.filePath = filePath;
	}
	
	
	public List<List<String>> readExcel(
			int sheetIndex, List<Integer> allowRows, List<Integer> allowCloumns)
			throws FileNotFoundException, IOException {
		XSSFWorkbook xssfWorkbook = getXSSFWorkbook(); 
		if (xssfWorkbook == null) {
			return null;
		}
		if (sheetIndex < 0) {
			return null;
		}
		if (allowRows == null || allowRows.size() < 1) {
			return null;
		}
		if (allowCloumns == null || allowCloumns.size() < 1) {
			return null;
		}
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(sheetIndex);
		// rows集合
		List<List<String>> rowsList = null;
		// cell集合
		List<String> cellList = null;
		int rows = xssfSheet.getPhysicalNumberOfRows();
		if (rows > 0) {
			rowsList = new ArrayList<List<String>>();
			for (int j = 0; j < rows; j++) {
				XSSFRow xssfRow = xssfSheet.getRow(j);
				if (xssfRow == null) {
					continue;
				}
				if (!allowRows.contains(j)) {
					continue;
				}
				int coloums = xssfRow.getPhysicalNumberOfCells();
				if (coloums > 0) {
					cellList = new ArrayList<String>();
					for (int k = 0; k < coloums; k++) {
						if (xssfRow == null) {
							continue;
						}
						if (!allowCloumns.contains(k)) {
							continue;
						}
						XSSFCell xssfCell = xssfRow.getCell(k);
						cellList.add(getCellValue(xssfCell));
					}
				}
				rowsList.add(cellList);
			}
		}
		return rowsList;
	}

	
	public List<List<String>> readExcel(
			int sheetIndex, int startRow, int startCloumn)
			throws FileNotFoundException, IOException {
		XSSFWorkbook xssfWorkbook = getXSSFWorkbook(); 
		if (xssfWorkbook == null) {
			return null;
		}
		if (sheetIndex < 0) {
			return null;
		}
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(sheetIndex);
		// rows集合
		List<List<String>> rowsList = null;
		// cell集合
		List<String> cellList = null;
		int rows = xssfSheet.getPhysicalNumberOfRows();
		if (rows > 0) {
			rowsList = new ArrayList<List<String>>();
			for (int j = 0; j < rows; j++) {
				XSSFRow xssfRow = xssfSheet.getRow(j);
				if (xssfRow == null) {
					continue;
				}
				if (j < startRow) {
					continue;
				}
				int coloums = xssfRow.getPhysicalNumberOfCells();
				if (coloums > 0) {
					cellList = new ArrayList<String>();
					for (int k = 0; k < coloums; k++) {
						if (xssfRow == null) {
							continue;
						}
						if (k < startCloumn) {
							continue;
						}
						XSSFCell xssfCell = xssfRow.getCell(k);
						cellList.add(getCellValue(xssfCell));
					}
				}
				rowsList.add(cellList);
			}
		}
		return rowsList;
	}

	
	public List<List<String>> readExcel(
			int sheetIndex) throws FileNotFoundException, IOException {
		XSSFWorkbook xssfWorkbook = getXSSFWorkbook(); 
		if (xssfWorkbook == null) {
			return null;
		}
		if (sheetIndex < 0) {
			return null;
		}
		
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(sheetIndex);
		// rows集合
		List<List<String>> rowsList = null;
		// cell集合
		List<String> cellList = null;
		int rows = xssfSheet.getPhysicalNumberOfRows();
		if (rows > 0) {
			rowsList = new ArrayList<List<String>>();
			for (int j = 0; j < rows; j++) {
				XSSFRow xssfRow = xssfSheet.getRow(j);
				if (xssfRow == null) {
					continue;
				}
				int coloums = xssfRow.getPhysicalNumberOfCells();
				if (coloums > 0) {
					cellList = new ArrayList<String>();
					for (int k = 0; k < coloums; k++) {
						if (xssfRow == null) {
							continue;
						}
						XSSFCell xssfCell = xssfRow.getCell(k);
						cellList.add(getCellValue(xssfCell));
					}
				}
				rowsList.add(cellList);
			}
		}
		return rowsList;
	}

	
	public List<List<List<String>>> readExcel()
			throws FileNotFoundException, IOException {
		// sheet集合
				List<List<List<String>>> sheetList = null;
				// rows集合
				List<List<String>> rowsList = null;
				// cell集合
				List<String> cellList = null;
				XSSFWorkbook xssfWorkbook = getXSSFWorkbook();
				int sheetCount = xssfWorkbook.getNumberOfSheets();
				if (sheetCount > 0) {
					sheetList = new ArrayList<List<List<String>>>();
					for (int i = 0; i < sheetCount; i++) {
						XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
						if (xssfSheet == null) {
							continue;
						}
						int rows = xssfSheet.getPhysicalNumberOfRows();
						if (rows > 0) {
							rowsList = new ArrayList<List<String>>();
							for (int j = 0; j < rows; j++) {
								XSSFRow xssfRow = xssfSheet.getRow(j);
								if (xssfRow == null) {
									continue;
								}
								int coloums = xssfRow.getPhysicalNumberOfCells();
								if (coloums > 0) {
									cellList = new ArrayList<String>();
									for (int k = 0; k < coloums; k++) {
										if (xssfRow == null) {
											continue;
										}
										XSSFCell xssfCell = xssfRow.getCell(k);
										cellList.add(getCellValue(xssfCell));
									}
								}
								rowsList.add(cellList);
							}
						}
						sheetList.add(rowsList);
					}
				}
				return sheetList;
	}

	
	public XSSFWorkbook getXSSFWorkbook()
			throws FileNotFoundException, IOException {
		if (filePath == null || "".equals(filePath)) {
			return null;
		}
		return new XSSFWorkbook(new FileInputStream(filePath));
	}

	
	public int getSheetCount(XSSFWorkbook xssfWorkbook) {
		if (xssfWorkbook == null) {
			return 0;
		}
		return xssfWorkbook.getNumberOfSheets();
	}

	
	public List<String> getSheetList(XSSFWorkbook xssfWorkbook) {
		if (xssfWorkbook == null) {
			return null;
		}
		List<String> sheetNameList = new ArrayList<String>();
		int sheetCount = xssfWorkbook.getNumberOfSheets();
		if (sheetCount < 1) {
			return null;
		}
		for (int i = 0; i < sheetCount; i++) {
			String sheetName = xssfWorkbook.getSheetName(i);
			sheetNameList.add(sheetName);
		}
		return sheetNameList;
	}

	
	public int getRowCount(XSSFSheet xssfSheet) {
		if (xssfSheet == null) {
			return 0;
		}
		return xssfSheet.getPhysicalNumberOfRows();
	}

	
	public int getCloumnCount(XSSFRow xssfRow) {
		if (xssfRow == null) {
			return 0;
		}
		return xssfRow.getPhysicalNumberOfCells();
	}

	
	public String getCellValue(XSSFCell cell) {
		String value = "";
		if (cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC: // 数值型
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				// 如果是date类型则 ，获取该cell的date值
				Date date = new Date();
				date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
				SimpleDateFormat SDF = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				value = SDF.format(date);
			} else {// 纯数字
				cell.setCellType(Cell.CELL_TYPE_STRING);
				value = cell.getRichStringCellValue().toString();
				// value = String.valueOf(cell.getNumericCellValue());//这会出现1.0
				// 2.0的情况
			}
			break;
		// 此行表示单元格的内容为string类型
		case HSSFCell.CELL_TYPE_STRING: // 字符串型
			value = cell.getRichStringCellValue().toString();
			break;
		case HSSFCell.CELL_TYPE_FORMULA:// 公式型
			// 读公式计算值
			value = String.valueOf(cell.getNumericCellValue());
			if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串

				value = cell.getRichStringCellValue().toString();
			}
			// cell.getCellFormula();读公式
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:// 布尔
			value = " " + cell.getBooleanCellValue();
			break;
		// 此行表示该单元格值为空
		case HSSFCell.CELL_TYPE_BLANK: // 空值
			value = "";
			break;
		case HSSFCell.CELL_TYPE_ERROR: // 故障
			value = "";
			break;
		default:
			value = cell.getRichStringCellValue().toString();
		}
		return value;
	}

}
