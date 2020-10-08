package com.kail.util.file;

import com.kail.util.file.poi.PoiExcel2003ReadHandler;
import com.kail.util.file.poi.PoiExcel2003WriteHandler;
import com.kail.util.file.poi.PoiExcel2007ReadHandler;
import com.kail.util.file.poi.PoiExcel2007WriteHandler;
import com.kail.util.file.poi.PoiExcelReadHandler;
import com.kail.util.file.poi.PoiExcelWriteHandler;

/**
 * 
 * <p>功能描述: [excel工具类]</p>
 * @author  yhao
 * @date:2014-11-4/下午5:29:56
 * @version 
 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
 */
public class ExcelUtil {
	
	/**
	 * 
	 * <p>功能描述:[读取excel文件工具类]</p>
	 * @param filePath
	 * @return
	 * @author:yhao
	 * @date:2014-11-4/下午5:30:11
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static PoiExcelReadHandler getPoiExcelReadHandler(String filePath)
	{
		if(filePath == null || "".equals(filePath))
		{
			return null;
		}
		PoiExcelReadHandler helper = null;
		if(filePath.indexOf(".xlsx")!=-1) {
			helper = new PoiExcel2007ReadHandler(filePath);
		}else {
			helper = new PoiExcel2003ReadHandler(filePath);
		}
		
		return helper;
	}
	
	/**
	 * 
	 * <p>功能描述:[写excel文件工具类]</p>
	 * @param filePath
	 * @return
	 * @author:yhao
	 * @date:2014-11-4/下午5:30:41
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static PoiExcelWriteHandler getPoiExcelWriteHandler(String filePath)
	{
		if(filePath == null || "".equals(filePath))
		{
			return null;
		}
		PoiExcelWriteHandler helper = null;
		if(filePath.indexOf(".xlsx")!=-1) {
			helper = new PoiExcel2007WriteHandler(filePath);
		}else {
			helper = new PoiExcel2003WriteHandler(filePath);
		}
		
		return helper;
	}

}
