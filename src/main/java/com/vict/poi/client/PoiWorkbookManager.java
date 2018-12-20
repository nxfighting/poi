package com.vict.poi.client;

import com.vict.poi.domain.ExcelDataConfig;
import com.vict.poi.domain.ResolveExcelDTO;
import com.vict.poi.domain.enmuration.ResolveStatus;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取Excel文件到对象列表
 *
 * @author
 * @create 2018-12-18 13:22
 */
public class PoiWorkbookManager {
    static Logger logger = LoggerFactory.getLogger(PoiWorkbookManager.class.getName());
    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    /**
     * @return com.aiways.energyassets.web.rest.util.ResolveExcelDTO
     * @author gf.zhou
     * @description //TODO 读取Excel并返回数据
     * @date 2018/12/17
     * @params [fileStream io流, type 文件类型, configList Sheet配置信息]
     */
    public static ResolveExcelDTO read(InputStream fileStream, String type, List<ExcelDataConfig> configList) {
        ResolveExcelDTO result = new ResolveExcelDTO();
        result.setStatus(ResolveStatus.SUCCESS);
        result.setMessage(new StringBuffer());
        try {
            // 创建工作簿
            Workbook workBook = getWorkbook(fileStream, type);
            handleRead(workBook, configList, result);
        } catch (Exception e) {
            logger.error("exe readExcel error:", e);
            result.setStatus(ResolveStatus.ERROR);
            result.setMessage(result.getMessage().append(e.getMessage()));
        }
        return result;
    }

    public static ResolveExcelDTO read(Workbook workBook, List<ExcelDataConfig> configList) {
        ResolveExcelDTO result = new ResolveExcelDTO();
        result.setStatus(ResolveStatus.SUCCESS);
        result.setMessage(new StringBuffer());
        try {
            // 创建工作簿
            handleRead(workBook, configList, result);
        } catch (Exception e) {
            logger.error("exe readExcel error:", e);
            result.setStatus(ResolveStatus.ERROR);
            result.setMessage(result.getMessage().append(e.getMessage()));
        }
        return result;
    }

    private static void handleRead(Workbook workBook, List<ExcelDataConfig> configList, ResolveExcelDTO result) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (workBook != null) {
            getRowItems(configList, workBook, result);
            if (StringUtils.isNotEmpty(result.getMessage())) {
                result.setStatus(ResolveStatus.ERROR);
            }
            if (MapUtils.isEmpty(result.getResultMap())) {
                result.setStatus(ResolveStatus.ERROR);
            }
        } else {
            result.setStatus(ResolveStatus.ERROR);
        }
    }

    /**
     * @return com.aiways.energyassets.web.rest.util.ResolveExcelDTO
     * @author gf.zhou
     * @description //TODO 验证文件并读取结果
     * @date 2018/12/19
     * @params [fileStream, type, configs, msg]
     */
    public static ResolveExcelDTO readWithCheck(InputStream fileStream, String type, List<ExcelDataConfig> configs) {
        ResolveExcelDTO result = new ResolveExcelDTO();
        result.setStatus(ResolveStatus.SUCCESS);
        StringBuffer bufferMsg = new StringBuffer();
        try {
            Workbook workbook = getWorkbook(fileStream, type);
            StringBuilder msg = new StringBuilder();
            if (verifySheetsExists(workbook, configs, msg) == 0) {
                return read(workbook, configs);
            } else {
                bufferMsg.append(msg);
            }
        } catch (IOException ex) {
            bufferMsg.append("将数据流转换为Workbook时发生异常：" + ex.getMessage());
        } catch (Exception ex) {
            bufferMsg.append("读取文件数据时发生异常：" + ex.getMessage());
        }
        if (StringUtils.isEmpty(bufferMsg.toString())) {
            result.setStatus(ResolveStatus.ERROR);
            result.setMessage(bufferMsg);
        }
        return result;
    }

    private static int verifyWorkbookRequire(InputStream fileStream, String type, List<ExcelDataConfig> configs, StringBuilder msg) throws IOException {
        return verifySheetsExists(getWorkbook(fileStream, type), configs, msg);
    }

    private static int verifySheetsExists(Workbook book, List<ExcelDataConfig> configs, StringBuilder msg) {
        Map<String, Sheet> sheets = getSheetMap(book);
        int count = 0;
        for (ExcelDataConfig config : configs) {
            if (!sheets.containsKey(config.getSheetName())) {
                count++;
                msg.append(String.format("表[%s]在文件中不存在！", config.getSheetName()));
            }
        }
        return count;
    }

    private static Map<String, Sheet> getSheetMap(Workbook book) {
        Map<String, Sheet> sheets = new HashMap<>();
        int sheetCount = book.getNumberOfSheets();
        if (sheetCount > 0) {
            for (int i = 0; i < sheetCount; i++) {
                sheets.put(book.getSheetName(i), book.getSheetAt(i));
            }
        }
        return sheets;
    }

    /**
     * @return void
     * @author gf.zhou
     * @description //TODO 将Sheet中行数据按照对象存储
     * @date 2018/12/17
     * @params [configList, book, result]
     */
    private static void getRowItems(List<ExcelDataConfig> configList, Workbook book, ResolveExcelDTO result) throws IllegalAccessException, InvocationTargetException, InstantiationException {

        if (!CollectionUtils.isEmpty(configList)) {
            Map<Class, List> resultMap = new HashMap<>();
            for (ExcelDataConfig config : configList) {
                StringBuffer message = new StringBuffer();
                Sheet sheet = book.getSheet(config.getSheetName());
                List rel;
                if (sheet != null) {
                    rel = PoiWorkSheetManager.parseSheetToList(config.getClazzRef(), sheet, config.getStartRowNum(), message, config.getFormatMap());
                } else {
                    rel = new ArrayList(0);
                }
                if (StringUtils.isNotEmpty(message)) {
                    result.getMessage().append("工作簿【" + sheet.getSheetName() + "】解析错误：\n" + message);
                    rel = new ArrayList(0);
                }
                resultMap.put(config.getClazzRef().getClass(), rel);
                result.setResultMap(resultMap);
            }
        }
    }

    public static Workbook getWorkbook(InputStream fileStream, String type) throws IOException {
        Workbook workbook = null;
        return getWorkbook(fileStream, type, workbook);
    }

    public static Workbook getWorkbook(InputStream fileStream, String type, Workbook workbook) throws IOException {
        if (null != fileStream) {
            try{
                if (XLS.equals(type.trim().toLowerCase())) {
                    // 创建 Excel 2003 工作簿对象
                    workbook = new HSSFWorkbook(fileStream);
                    // 创建 Excel 2007 工作簿对象
                } else if (XLSX.equals(type.trim().toLowerCase())) {
                    workbook = new XSSFWorkbook(fileStream);
                }
            }catch(IOException ex){
                throw new IOException("读取文件流转换为Xls文档时发生错误:",ex);
            }
        }
        return workbook;
    }


}
