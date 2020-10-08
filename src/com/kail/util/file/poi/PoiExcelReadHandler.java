package com.kail.util.file.poi;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface PoiExcelReadHandler {

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
	public List<List<String>> readExcel(int sheetIndex,
			List<Integer> allowRows, List<Integer> allowCloumns)
			throws FileNotFoundException, IOException;

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
	public List<List<String>> readExcel(int sheetIndex, int startRow,
			int startCloumn) throws FileNotFoundException, IOException;

	/**
	 * 读取第几个sheet内容
	 * 
	 * @param hssfWorkbook
	 * @param sheetIndex
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public List<List<String>> readExcel(int sheetIndex)
			throws FileNotFoundException, IOException;

	/**
	 * @description 获取全部excel内容
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public List<List<List<String>>> readExcel() throws FileNotFoundException,
			IOException;

}
