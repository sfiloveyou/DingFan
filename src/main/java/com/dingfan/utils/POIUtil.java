package com.dingfan.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.contrib.CellUtil;

public class POIUtil {
	
	
	public static Workbook getExcelWorkbook(String sheetName,List<String> titles, List<List<String>> dataList) {
	    Workbook wb = new HSSFWorkbook();
	    Sheet sheet = wb.createSheet(sheetName);
	    setExcelRow(sheet, 0, titles);
        for (int i = 0; i < dataList.size(); i++) {
        	List<String> lineList = (List<String>) dataList.get(i);
        	setExcelRow(sheet, i+1, lineList);
        }
        for (int i = 0; i < titles.size(); i++) {
        	sheet.autoSizeColumn(i);
        }
        
	    return wb;
	}
	public static void setExcelRow(Sheet sheet,int rowIndex,List<String> cellList){
	    Row row = sheet.createRow(rowIndex);
	    int cellIndex=0;
	    for (String cellString : cellList) {
	    	CellUtil.createCell(row, cellIndex++, cellString);
		}
	}
	/**
	 * 获取map形式数据
	 * @param childSheet	HSSFSheet
	 * @param rowStart		row的起始
	 * @param rowEnd		row的结束
	 * @param include		包含的column数
	 * @param exclude		去除的column数
	 * @return
	 */
	public static Map<Integer, String[]> getMapFromSheet(final HSSFSheet childSheet, Integer rowStart,Integer rowEnd,
			final Integer[] include, Integer[] exclude) {
		Map<Integer, String[]> result = new TreeMap<Integer, String[]>();
		exclude = exclude==null? new Integer[0]:exclude;
		List<String[]> a=new ArrayList<String[]>();
		if (childSheet != null) {
			rowStart = rowStart == null ? 0 : rowStart;
			final int lastRowNum = rowEnd == null ? childSheet.getLastRowNum() : rowEnd;
			for (int i = rowStart; i < lastRowNum; i++) {
				List<String> values = new LinkedList<String>();
				final HSSFRow row = childSheet.getRow(i);

				if (include != null) {
					for (int c = 0; c < include.length; c++) {
						if (ArrayUtils.indexOf(exclude, include[c]) < 0) {
							values.add(row.getCell(include[c]).toString());
						}
					}
				} else {
					short lastCellNum = row.getLastCellNum();
					for (int c = 0; c < lastCellNum; c++) {
						if (ArrayUtils.indexOf(exclude, c) < 0) {
							values.add(row.getCell(c).toString());
						}
					}
				}
				a.add(values.toArray(new String[values.size()]));
				result.put(i, values.toArray(new String[values.size()]));
			}
		}
		return result;
	}	
}
