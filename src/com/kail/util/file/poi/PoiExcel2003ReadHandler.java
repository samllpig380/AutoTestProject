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
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class PoiExcel2003ReadHandler implements PoiExcelReadHandler {


	private String filePath = "";
	
	public PoiExcel2003ReadHandler(String filePath)
	{
		this.filePath = filePath;
	}
	
	/**
	 * @description 读取指定sheet、指定行、指定列的值
	 * @param hssfWorkbook
	 * @param sheetIndex
	 * @param allowRows
	 * @param allowCloumns
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public  List<List<String>> readExcel(
			int sheetIndex, List<Integer> allowRows, List<Integer> allowCloumns)
			throws FileNotFoundException, IOException {
		
		HSSFWorkbook hssfWorkbook = getHSSFWorkbook(); 
		
		if (hssfWorkbook == null) {
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
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(sheetIndex);
		// rows集合
		List<List<String>> rowsList = null;
		// cell集合
		List<String> cellList = null;
		int rows = hssfSheet.getPhysicalNumberOfRows();
		if (rows > 0) {
			rowsList = new ArrayList<List<String>>();
			for (int j = 0; j < rows; j++) {
				HSSFRow hssfRow = hssfSheet.getRow(j);
				if (hssfRow == null) {
					continue;
				}
				if (!allowRows.contains(j)) {
					continue;
				}
				int coloums = hssfRow.getPhysicalNumberOfCells();
				if (coloums > 0) {
					cellList = new ArrayList<String>();
					for (int k = 0; k < coloums; k++) {
						if (hssfRow == null) {
							continue;
						}
						if (!allowCloumns.contains(k)) {
							continue;
						}
						HSSFCell hssfCell = hssfRow.getCell(k);
						cellList.add(getCellValue(hssfCell));
					}
				}
				rowsList.add(cellList);
			}
		}
		return rowsList;
	}

	/**
	 * @description 读取第sheetIndex sheet，从startRow行开始，从startCloumn行开始
	 * @param hssfWorkbook
	 * @param sheetIndex
	 * @param startRow
	 * @param startCloumn
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public  List<List<String>> readExcel(
			int sheetIndex, int startRow, int startCloumn)
			throws FileNotFoundException, IOException {
		HSSFWorkbook hssfWorkbook = getHSSFWorkbook(); 
		if (hssfWorkbook == null) {
			return null;
		}
		if (sheetIndex < 0) {
			return null;
		}
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(sheetIndex);
		// rows集合
		List<List<String>> rowsList = null;
		// cell集合
		List<String> cellList = null;
		int rows = hssfSheet.getPhysicalNumberOfRows();
		if (rows > 0) {
			rowsList = new ArrayList<List<String>>();
			for (int j = 0; j < rows; j++) {
				HSSFRow hssfRow = hssfSheet.getRow(j);
				if (hssfRow == null) {
					continue;
				}
				if (j < startRow) {
					continue;
				}
				int coloums = hssfRow.getPhysicalNumberOfCells();
				if (coloums > 0) {
					cellList = new ArrayList<String>();
					for (int k = 0; k < coloums; k++) {
						if (hssfRow == null) {
							continue;
						}
						if (k < startCloumn) {
							continue;
						}
						HSSFCell hssfCell = hssfRow.getCell(k);
						cellList.add(getCellValue(hssfCell));
					}
				}
				rowsList.add(cellList);
			}
		}
		return rowsList;
	}

	/**
	 * 读取第几个sheet内容
	 * 
	 * @param hssfWorkbook
	 * @param sheetIndex
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public  List<List<String>> readExcel(
			int sheetIndex) throws FileNotFoundException, IOException {
		HSSFWorkbook hssfWorkbook = getHSSFWorkbook(); 
		if (hssfWorkbook == null) {
			return null;
		}
		if (sheetIndex < 0) {
			return null;
		}
		
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(sheetIndex);
		// rows集合
		List<List<String>> rowsList = null;
		// cell集合
		List<String> cellList = null;
		int rows = hssfSheet.getPhysicalNumberOfRows();
		if (rows > 0) {
			rowsList = new ArrayList<List<String>>();
			for (int j = 0; j < rows; j++) {
				HSSFRow hssfRow = hssfSheet.getRow(j);
				if (hssfRow == null) {
					continue;
				}
				int coloums = hssfRow.getPhysicalNumberOfCells();
				if (coloums > 0) {
					cellList = new ArrayList<String>();
					for (int k = 0; k < coloums; k++) {
						if (hssfRow == null) {
							continue;
						}
						HSSFCell hssfCell = hssfRow.getCell(k);
						cellList.add(getCellValue(hssfCell));
					}
				}
				rowsList.add(cellList);
			}
		}
		return rowsList;
	}

	/**
	 * @description 获取全部excel内容
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public  List<List<List<String>>> readExcel()
			throws FileNotFoundException, IOException {

		// sheet集合
		List<List<List<String>>> sheetList = null;
		// rows集合
		List<List<String>> rowsList = null;
		// cell集合
		List<String> cellList = null;
		HSSFWorkbook hssfWorkbook = getHSSFWorkbook();
		int sheetCount = hssfWorkbook.getNumberOfSheets();
		if (sheetCount > 0) {
			sheetList = new ArrayList<List<List<String>>>();
			for (int i = 0; i < sheetCount; i++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(i);
				if (hssfSheet == null) {
					continue;
				}
				int rows = hssfSheet.getPhysicalNumberOfRows();
				if (rows > 0) {
					rowsList = new ArrayList<List<String>>();
					for (int j = 0; j < rows; j++) {
						HSSFRow hssfRow = hssfSheet.getRow(j);
						if (hssfRow == null) {
							continue;
						}
						int coloums = hssfRow.getPhysicalNumberOfCells();
						if (coloums > 0) {
							cellList = new ArrayList<String>();
							for (int k = 0; k < coloums; k++) {
								if (hssfRow == null) {
									continue;
								}
								HSSFCell hssfCell = hssfRow.getCell(k);
								cellList.add(getCellValue(hssfCell));
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

	/**
	 * @description 获取工作区
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public  HSSFWorkbook getHSSFWorkbook()
			throws FileNotFoundException, IOException {
		if (filePath == null || "".equals(filePath)) {
			return null;
		}

		return new HSSFWorkbook(new FileInputStream(filePath));
	}

	/**
	 * @description 获取sheet的数量
	 * @param hssfWorkbook
	 * @return
	 */
	public  int getSheetCount(HSSFWorkbook hssfWorkbook) {
		if (hssfWorkbook == null) {
			return 0;
		}
		return hssfWorkbook.getNumberOfSheets();
	}

	/**
	 * @description 获取sheet名称的集合
	 * @param hssfWorkbook
	 * @return
	 */
	public  List<String> getSheetList(HSSFWorkbook hssfWorkbook) {
		if (hssfWorkbook == null) {
			return null;
		}
		List<String> sheetNameList = new ArrayList<String>();
		int sheetCount = hssfWorkbook.getNumberOfSheets();
		if (sheetCount < 1) {
			return null;
		}
		for (int i = 0; i < sheetCount; i++) {
			String sheetName = hssfWorkbook.getSheetName(i);
			sheetNameList.add(sheetName);
		}
		return sheetNameList;
	}

	/**
	 * @description 获取sheet的行数
	 * @param hssfSheet
	 * @return
	 */
	public  int getRowCount(HSSFSheet hssfSheet) {
		if (hssfSheet == null) {
			return 0;
		}
		return hssfSheet.getPhysicalNumberOfRows();

	}

	/**
	 * 获取每一行的总列数
	 * 
	 * @param hssfRow
	 * @return
	 */
	public  int getCloumnCount(HSSFRow hssfRow) {
		if (hssfRow == null) {
			return 0;
		}
		return hssfRow.getPhysicalNumberOfCells();
	}

	/**
	 * @description 获取cell的值
	 * @param cell
	 * @return
	 */
	public  String getCellValue(HSSFCell cell) {
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
