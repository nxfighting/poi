package com.vict.poi.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vict.poi.domain.ExcelExportDataConfig;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author
 * @create 2018-12-20 13:50
 */
public class PoiExportManager {
    private static final Logger logger = LoggerFactory.getLogger(PoiExportManager.class);
    public static String NO_DEFINE = "no_define";
    //未定义的字段
    public static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    //默认日期格式
    public static int DEFAULT_COLOUMN_WIDTH = 17;
    public static String[] static_properties;
    public static String[] static_headers;
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static ZoneId zone = ZoneId.systemDefault();
    private static CellStyle cellStyle = null;
    private static CellStyle headerStyle = null;
    private static CellStyle titleStyle = null;

    /**
     * 导出Excel 97(.xls)格式 ，少量数据
     *
     * @param title       标题行
     * @param headMap     属性-列名
     * @param jsonArray   数据集
     * @param datePattern 日期格式，null则用默认日期格式
     * @param colWidth    列宽 默认 至少17个字节
     * @param out         输出流
     */
    public static void exportExcel(String title, Map<String, String> headMap, JSONArray jsonArray, String datePattern, int colWidth, OutputStream out) {
        if (datePattern == null) datePattern = DEFAULT_DATE_PATTERN;
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        workbook.createInformationProperties();
        workbook.getDocumentSummaryInformation().setCompany("*****公司");
        SummaryInformation si = workbook.getSummaryInformation();
        si.setAuthor("JACK");  //填加xls文件作者信息
        si.setApplicationName("导出程序"); //填加xls文件创建程序信息
        si.setLastAuthor("最后保存者信息"); //填加xls文件最后保存者信息
        si.setComments("JACK is a programmer!"); //填加xls文件作者信息
        si.setTitle("POI导出Excel"); //填加xls文件标题信息
        si.setSubject("POI导出Excel");//填加文件主题信息
        si.setCreateDateTime(new Date());
        //表头样式
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 20);
        titleFont.setBoldweight((short) 700);
        titleStyle.setFont(titleFont);
        // 列头样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerStyle.setFont(headerFont);
        // 单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont cellFont = workbook.createFont();
        cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        cellStyle.setFont(cellFont);
        // 生成一个(带标题)表格
        HSSFSheet sheet = workbook.createSheet();
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("JACK");
        //设置列宽
        int minBytes = colWidth < DEFAULT_COLOUMN_WIDTH ? DEFAULT_COLOUMN_WIDTH : colWidth;//至少字节数
        int[] arrColWidth = new int[headMap.size()];
        // 产生表格标题行,以及设置列宽
        String[] properties = new String[headMap.size()];
        String[] headers = new String[headMap.size()];
        int ii = 0;
        for (Iterator<String> iter = headMap.keySet().iterator(); iter
                .hasNext(); ) {
            String fieldName = iter.next();

            properties[ii] = fieldName;
            headers[ii] = fieldName;

            int bytes = fieldName.getBytes().length;
            arrColWidth[ii] = bytes < minBytes ? minBytes : bytes;
            sheet.setColumnWidth(ii, arrColWidth[ii] * 256);
            ii++;
        }
        // 遍历集合数据，产生数据行
        int rowIndex = 0;
        for (Object obj : jsonArray) {
            if (rowIndex == 65535 || rowIndex == 0) {
                if (rowIndex != 0) sheet = workbook.createSheet();//如果数据超过了，则在第二页显示

                HSSFRow titleRow = sheet.createRow(0);//表头 rowIndex=0
                titleRow.createCell(0).setCellValue(title);
                titleRow.getCell(0).setCellStyle(titleStyle);
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headMap.size() - 1));

                HSSFRow headerRow = sheet.createRow(1); //列头 rowIndex =1
                for (int i = 0; i < headers.length; i++) {
                    headerRow.createCell(i).setCellValue(headers[i]);
                    headerRow.getCell(i).setCellStyle(headerStyle);

                }
                rowIndex = 2;//数据内容从 rowIndex=2开始
            }
            JSONObject jo = (JSONObject) JSONObject.toJSON(obj);
            HSSFRow dataRow = sheet.createRow(rowIndex);
            for (int i = 0; i < properties.length; i++) {
                HSSFCell newCell = dataRow.createCell(i);

                Object o = jo.get(properties[i]);
                String cellValue = "";
                if (o == null) cellValue = "";
                else if (o instanceof Date) cellValue = new SimpleDateFormat(datePattern).format(o);
                else cellValue = o.toString();

                newCell.setCellValue(cellValue);
                newCell.setCellStyle(cellStyle);
            }
            rowIndex++;
        }
        // 自动调整宽度
        /*for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }*/
        try {
            workbook.write(out);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Workbook createWorkbook() {
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        //缓存
        workbook.setCompressTempFiles(true);
        //表头样式
        titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        Font titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 20);
        titleFont.setBoldweight((short) 700);
        //titleStyle.setFillBackgroundColor(IndexedColors.SEA_GREEN.getIndex());
        //titleStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        //titleStyle.setFillPattern((short)FillPatternType.SOLID_FOREGROUND.ordinal());
        titleStyle.setFont(titleFont);
        // 列头样式
        headerStyle = workbook.createCellStyle();
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyle.setFillBackgroundColor(IndexedColors.BLUE.getIndex());
        //headerStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        //headerStyle.setFillPattern((short)FillPatternType.SOLID_FOREGROUND.ordinal());
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerStyle.setFont(headerFont);
        // 单元格样式
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font cellFont = workbook.createFont();
        cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        cellStyle.setFont(cellFont);
        return workbook;
    }

    /**
     * 导出Excel 2007 OOXML (.xlsx)格式
     *
     * @param titles      标题行
     * @param headMaps    属性-列头
     * @param jsonArray   数据集
     * @param datePattern 日期格式，传null值则默认 年月日
     * @param colWidth    列宽 默认 至少17个字节
     */
    public static SXSSFWorkbook exportExcelX(List<ExcelExportDataConfig> configList, String datePattern, int colWidth) {
        if (datePattern == null) datePattern = DEFAULT_DATE_PATTERN;
        // 声明一个工作薄
        SXSSFWorkbook workbook = (SXSSFWorkbook) createWorkbook();
        int currentCount = 0;
        for (ExcelExportDataConfig excelExportDataConfig : configList) {
            // 生成一个(带标题)表格
            SXSSFSheet sheet = workbook.createSheet();
            sheet = (SXSSFSheet)assistant(excelExportDataConfig.getHeadsMap(), sheet, colWidth);
            workbook.setSheetName(currentCount, excelExportDataConfig.getSheetName());

            JSONArray jsonArray = excelExportDataConfig.getExportDataArray();
            int rowIndex = 0;
            buildHeaderRow(excelExportDataConfig,workbook,sheet,rowIndex,colWidth);
            rowIndex = 2;
            for (Object obj : jsonArray) {
                if (rowIndex - 1 > jsonArray.size()) {
                    currentCount++;
                    break;
                }
                buildRow(obj,sheet,rowIndex,datePattern);
                rowIndex++;
            }
            currentCount++;
        }

        return workbook;

    }
    private static void buildHeaderRow(ExcelExportDataConfig config, Workbook workbook,  Sheet sheet, int rowIndex, int colWidth){
        Map<String, String> headMap = config.getHeadsMap();
        String title  = config.getSheetName();
        if (rowIndex == 65535 || rowIndex == 0) {
            if (rowIndex != 0 ) {
                sheet = workbook.createSheet();
                //如果数据超过了，则在第二页显示或操作对象发生变化
                sheet = assistant(headMap, sheet, colWidth);
                workbook.setSheetName(workbook.getNumberOfSheets() - 1, title);
            }

            Row titleRow = sheet.createRow(rowIndex);
            //表头 rowIndex=0
            titleRow.createCell(0).setCellValue(title);
            titleRow.getCell(0).setCellStyle(titleStyle);
            int size = headMap.size();
            System.out.println(size);
            //合并单元格 如果导出列数刚好是1列，则会抛出Merged Region异常 故添加单列导出判断
            if (headMap.size() - 1 == 0) {
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
            } else {
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headMap.size() - 1));
            }

            Row headerRow = sheet.createRow(1);
            //列头 rowIndex =1
            for (int i = 0; i < static_headers.length; i++) {
                headerRow.createCell(i).setCellValue(static_headers[i]);
                headerRow.getCell(i).setCellStyle(headerStyle);

            }
            //数据内容从 rowIndex=2开始
            rowIndex=2;
        }

    }
    private static void buildRow(Object obj, SXSSFSheet  sheet,int rowIndex,String datePattern){
        JSONObject jo = (JSONObject) JSONObject.toJSON(obj);
        SXSSFRow dataRow = sheet.createRow(rowIndex);
        for (int i = 0; i < static_properties.length; i++) {
            SXSSFCell newCell = dataRow.createCell(i);

            Object o = jo.get(static_properties[i]);
            String cellValue = "";
            if (o == null) cellValue = "";
            else if (o instanceof Date || o instanceof Timestamp)
                cellValue = new SimpleDateFormat(datePattern).format(o);
            else if (o instanceof Float || o instanceof Double)
                cellValue = new BigDecimal(o.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            else cellValue = o.toString();

            newCell.setCellValue(cellValue);
            newCell.setCellStyle(cellStyle);
        }
    }

    public static Sheet assistant(Map<String, String> headMap, Sheet sheet, int colWidth) {
        //设置列宽
        int minBytes = colWidth < DEFAULT_COLOUMN_WIDTH ? DEFAULT_COLOUMN_WIDTH : colWidth;//至少字节数
        int[] arrColWidth = new int[headMap.size()];
        // 产生表格标题行,以及设置列宽
        static_properties = new String[headMap.size()];
        static_headers = new String[headMap.size()];
        int ii = 0;
        for (Iterator<String> iter = headMap.keySet().iterator(); iter
                .hasNext(); ) {
            String fieldName = iter.next();

            static_properties[ii] = fieldName;
            static_headers[ii] = headMap.get(fieldName);

            int bytes = fieldName.getBytes().length;
            arrColWidth[ii] = bytes < minBytes ? minBytes : bytes;
            sheet.setColumnWidth(ii, arrColWidth[ii] * 256);
            ii++;
        }
        return sheet;
    }

    //Web 导出excel
    public static void downloadExcelFile(List<ExcelExportDataConfig> configs, HttpServletResponse response) throws Exception {
        OutputStream outputStream = null;
        SXSSFWorkbook wb = exportExcelX(configs, null, 0);
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String((UUID.randomUUID().toString() + ".xlsx").getBytes(), "iso-8859-1"));
            outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            logger.error("导出excel异常", e);
            throw new Exception("export excel error:" + e.getMessage());
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    logger.error("导出excel异常", e);
                    throw new Exception("export excel error:" + e.getMessage());
                }
            }
        }
    }
}
