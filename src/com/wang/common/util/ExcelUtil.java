package com.wang.common.util;

import java.util.HashMap;
import java.util.List;

import com.wang.model.common.PageData;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


public class ExcelUtil {
	/**
	 * 
	 * 功能描述：导出excel文件.
	 * 
	 * 
	 * 备注：支持xlsx格式
	 * 
	 * @param orderList
	 * @param fileName
	 * @param titles
	 * @param fields
	 * @return
	 * @throws Exception
	 * 
	 * @author jianglq
	 * 
	 * @since 2015-10-15
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static SXSSFWorkbook generateWorkbook2007(List<PageData> orderList,
			String fileName, String[] titles, String[] fields,HashMap<String, String> dictMap) throws Exception {
		SXSSFWorkbook wb = new SXSSFWorkbook(1000);
		Sheet sheet = wb.createSheet(fileName);	
		createExcelTitle2007(wb, sheet, titles);
		CreationHelper createHelper = wb.getCreationHelper();
		int k = 0;
		int orderSize = orderList == null ? 0 : orderList.size();
		Row row =null;
		
		CellStyle cellStyle2 = wb.createCellStyle();  
        DataFormat format = wb.createDataFormat();  
        cellStyle2.setDataFormat(format.getFormat("@"));  
		
		for (int i = 0; i < orderSize; i++) {
			k++;
			row = sheet.createRow(k);
			addExcelContent2007(cellStyle2,row, orderList.get(i), fields, createHelper,dictMap);
		}
		return wb;
	}
	public static SXSSFWorkbook generateWorkbook2007(List<PageData> orderList,
			String fileName, String[] titles, String[] fields,SXSSFWorkbook wb,Sheet sheet,int startRowNum) throws Exception {	
		if (startRowNum==Common.EXPORT_START_PAGE) {
			createExcelTitle2007(wb, sheet, titles);
		}		
		CreationHelper createHelper = wb.getCreationHelper();
		int k = (startRowNum-1)*Common.EXPORT_PAGE_COUNT;
		int orderSize = orderList == null ? 0 : orderList.size();
		
		//设置单元格使用文本格式
		CellStyle cellStyle2 = wb.createCellStyle();  
        DataFormat format = wb.createDataFormat();  
        cellStyle2.setDataFormat(format.getFormat("@"));  
		
		for (int i = 0; i < orderSize; i++) {
			k++;
			Row row = sheet.createRow(k);
			addExcelContent2007(cellStyle2,row, orderList.get(i), fields, createHelper,null);
		}
		return wb;
	}
	/**
	 * 
	 * 功能描述：导出excel文件.
	 * 
	 * 
	 * 备注：支持xls格式，会出现IndexOutOfBoundsException: Row number must be between 0 and
	 * 65535, was <65536>
	 * 
	 * @param orderList
	 * @param fileName
	 * @param titles
	 * @param fields
	 * @return
	 * @throws Exception
	 * 
	 * @author jianglq
	 * 
	 * @since 2015-10-15
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	private static HSSFWorkbook generateWorkbook2003(List<PageData> orderList,
			String fileName, String[] titles, String[] fields) throws Exception {
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名
		HSSFSheet sheet = wb.createSheet(fileName);

		// 冻结标题(width * height)
		// sheet.createFreezePane(15, 1);
		// 给单子名称一个长度
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = wb.createCellStyle();
		// 设置表头颜色
		HSSFFont font = wb.createFont();
		font.setColor(HSSFColor.BLUE.index);
		style.setFont(font);
		// 水平布局 居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 上下居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 不锁定
		style.setLocked(false);
		// 设置边框
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 创建第一行（也可以称为表头）
		HSSFRow row = sheet.createRow(0);
		// 样式字体居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		createExcelTitle(row, style, titles);
		int k = 0;
		int orderSize = orderList == null ? 0 : orderList.size();
		for (int i = 0; i < orderSize; i++) {
			k++;
			row = sheet.createRow(k);
			addExcelContent(row, orderList.get(i), fields);
		}
		return wb;
	}

	/**
	 * 
	 * 功能描述：创建工单title.
	 * 
	 * @param row
	 * @param style
	 * 
	 * @author jianglq
	 * 
	 * @since 2015-9-21
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	private static void createExcelTitle(HSSFRow row, HSSFCellStyle style,
			String[] titles) {
		for (int i = 0; i < titles.length; i++) {
			String title = titles[i];
			// 给表头第一行一次创建单元格
			HSSFCell cell = row.createCell((short) i);
			cell.setCellValue(title);
			cell.setCellStyle(style);
		}
	}

	private static void createExcelTitle2007(Row row, CellStyle style,
			String[] titles) {
		Cell cell = null;
		for (int i = 0; i < titles.length; i++) {
			String title = titles[i];
			// 给表头第一行一次创建单元格
			cell = row.createCell((short) i);
			cell.setCellValue(title);
			cell.setCellStyle(style);
		}
		cell=null;
	}

	private static void createExcelTitle2007(SXSSFWorkbook wb, Sheet sheet,
			String[] titles) {
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		CellStyle style = wb.createCellStyle();
		// 设置表头颜色
		Font font = wb.createFont();
		font.setColor(HSSFColor.BLUE.index);
		style.setFont(font);
		// 水平布局 居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 上下居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 不锁定
		style.setLocked(false);
		// 设置边框
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 创建第一行（也可以称为表头）
		Row row = sheet.createRow(0);
		// 样式字体居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		createExcelTitle2007(row, style, titles);
		row=null;
	}

	/**
	 * 
	 * 功能描述：增加一条数据记录.
	 * 
	 * @param row
	 * @param pageData
	 * 
	 * @author jianglq
	 * 
	 * @since 2015-9-21
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	private static void addExcelContent(HSSFRow row, PageData pageData,
			String[] fields) {
		for (int i = 0; i < fields.length; i++) {
			String field = fields[i];
			String value = StringUtil.isNullObject(pageData
					.get(field) + "");
			if (StringUtil.isNumeric(value)) {
				// System.out.println(value+"-----------------");
				// java.text.DecimalFormat myformat=new
				// java.text.DecimalFormat("0.00");
				try {
					row.createCell((short) i).setCellValue(
							Double.parseDouble(value));
				} catch (Exception e) {
					row.createCell((short) i).setCellValue(
							new HSSFRichTextString(value));
				}
			} else {
				row.createCell((short) i).setCellValue(
						new HSSFRichTextString(value));
			}

		}
	}

	private static void addExcelContent2007(CellStyle cellStyle2,Row row, PageData pageData,
			String[] fields, CreationHelper createHelper,HashMap<String, String> dictMap) {
		RichTextString richTextString=null;	
		Cell cell=null;
		for (int i = 0; i < fields.length; i++) {
			String field = fields[i];
			String value = StringUtil.isNullObject(pageData
					.get(field) + "");
			cell = row.createCell((short) i);   
//            if(cell.getCellType()!=1){  
//                cell.setCellType(HSSFCell.CELL_TYPE_STRING);    
//            }  
//            cell.setCellStyle(cellStyle2);//设置使用文本格式  
			if (StringUtil.isNumeric(value)) {				
				try {//int最大值2147483648
					if (Double.parseDouble(value)>2147483647) {//超过int最大值
						richTextString = createHelper
								.createRichTextString(value);
						cell.setCellValue(richTextString);
					}else{
						if (dictMap==null) {
							if (StringUtil.isDouble(value)) {
								cell.setCellValue(Double.parseDouble(value));
							}else{
								cell.setCellValue(Integer.parseInt(value));
							}
							
						}else{
							cell.setCellValue(getRealValue(dictMap,field,value));
						}
					}
				} catch (Exception e) {
					richTextString = createHelper
							.createRichTextString(value);
					cell.setCellValue(richTextString);
				}
			} else {
				richTextString = createHelper
						.createRichTextString(value);
				cell.setCellValue(richTextString);
			}

		}
		cell=null;
		richTextString=null;
	}
	private static String getRealValue(HashMap<String, String> dictMap,String field,String value){		
		if (dictMap==null) {
			return value;
		}
		String fieldvalue=dictMap.get(field+value);		
		return fieldvalue==null?value:fieldvalue;
	}
	
}
