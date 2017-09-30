package com.zuoqing.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiUtils {

	public static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat timeFormatter = new SimpleDateFormat("HHmmss");
	public static DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * 读取单元格内容，支持获取函数内容
	 * 
	 * @param cell
	 * @return
	 */
	public static String getStringCellValue(Cell cell) {
		String strCell = "";
		if (cell == null)
			return strCell;
		switch (cell.getCellTypeEnum()) {
		case STRING: // get String data
			strCell = cell.getRichStringCellValue().getString().trim();
//			System.out.println("string:\t" + strCell);
			break;
		case NUMERIC: // get date or number data
			if (DateUtil.isCellDateFormatted(cell)) {
				strCell = formatter2.format(cell.getDateCellValue());
			} else {
				strCell = df.format(cell.getNumericCellValue());
			}
//			System.out.println("NUMERIC:\t" + strCell);
			break;
		case BOOLEAN: // get boolean data
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case FORMULA: // get expression data
			FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
			evaluator.evaluateFormulaCellEnum(cell);
			CellValue cellValue = evaluator.evaluate(cell);
			strCell = String.valueOf(cellValue.getNumberValue());
			break;
		default:
			strCell = "";
		}
		return strCell;
	}

	public static List<Map<String, String>> firstSheet(File file) throws Exception {
		List<List<Map<String, String>>> sheetList = new ArrayList<List<Map<String, String>>>();
		if (file.getName().endsWith(".xlsx")) {
			sheetList = iteratorCellX(file);
		} else {
			sheetList = iteratorCell(file);
		}
		return sheetList.get(0);
	}

	public static List<Map<String, String>> firstSheet(InputStream is, String fileName) throws Exception {
		List<List<Map<String, String>>> sheetList = new ArrayList<List<Map<String, String>>>();
		if (fileName.endsWith(".xlsx")) {
			sheetList = iteratorCellX(is);
		} else {
			sheetList = iteratorCell(is);
		}
		return sheetList.get(0);
	}

	public static List<Map<String, String>> parseSheet(Sheet sheet) {
		// 遍历row行
		List<Map<String, String>> sheetList = new ArrayList<Map<String, String>>();
		for (Row row : sheet) {
			// if (row.getRowNum() != 0) { // 忽略第一行
			// 获取cell的数量
			int colSize = row.getLastCellNum(); // 列数
			// getPhysicalNumberOfCells 是获取不为空的列个数。 
			// getLastCellNum 是获取最后一个不为空的列是第几个。 
			// 同样，HSSFSheet获取行也有类似两个办法，若是excel数据中存在空行或空列，必须用getLastCellNum的办法才干完全读取数据。
			HashMap<String, String> map = new HashMap<String, String>();
			for (int iCol = 0; iCol < colSize; iCol++) {
				Cell cell = row.getCell(iCol);
				String value = getStringCellValue(cell);
				// System.out.print(iCol + "\t" + value);
				map.put("cell" + iCol, value);
			}
			// System.out.println();
			sheetList.add(map);
			// }
		}
		return sheetList;
	}

	public static List<List<Map<String, String>>> iteratorCell(File file) throws Exception {
		return iteratorCell(new FileInputStream(file));
	}

	public static List<List<Map<String, String>>> iteratorCell(InputStream is) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook(is);
		List<List<Map<String, String>>> wbList = new ArrayList<List<Map<String, String>>>();
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sheet = wb.getSheetAt(i);
			wbList.add(parseSheet(sheet));
		}
		wb.close();
		return wbList;
	}

	public static List<List<Map<String, String>>> iteratorCellX(InputStream is) throws Exception {
		Workbook wb = new XSSFWorkbook(is);
		List<List<Map<String, String>>> wbList = new ArrayList<List<Map<String, String>>>();
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sheet = wb.getSheetAt(i);
			wbList.add(parseSheet(sheet));
		}
		wb.close();
		return wbList;
	}

	public static List<List<Map<String, String>>> iteratorCellX(File file) throws Exception {
		return iteratorCellX(new FileInputStream(file));
	}
	
	public static List<Map<String, String>> firstCsv(File file, int beginLine) throws Exception {
		return firstCsv(new FileInputStream(file), beginLine);
	}
	
	public static List<Map<String, String>> firstCsv(InputStream is, int beginLine) throws Exception {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "gbk"));
		String line = null;
		int lineNum = 0;
		int cols = -1;
		while ((line = reader.readLine()) != null) {
			lineNum++;
			if (lineNum < beginLine) {
				continue;
			}
			Map<String, String> row = new HashMap<String, String>();
			String[] rowEle = line.split(",");
			if (cols == -1) {
				cols = rowEle.length;
			}

			if (cols != rowEle.length) {
				break;
			}
			
			for (int i = 0; i < rowEle.length; i++) {
				row.put("cell" + i, rowEle[i].trim());
			}
			list.add(row);
		}
		reader.close();
		return list;
	}
}
