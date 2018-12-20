package com.vict.poi.client;
import com.vict.poi.domain.DataFormat;
import com.vict.poi.utils.ImportDateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * POI 表
 *
 * @author
 * @create 2018-12-12 15:16
 */
public class PoiWorkSheetManager {
    private static  final String ENTITY_NAME="PoiWorkSheetManager";
    private static Logger logger = LoggerFactory.getLogger(ENTITY_NAME);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public  static <T> List<T> parseSheetToList(T modal, Sheet sheet, int startRowNum,
                                                StringBuffer errorMsg, Map<String, DataFormat> formatMap)
        throws IllegalAccessException, InstantiationException, InvocationTargetException {
        List<T> list = new ArrayList<>();
        String sheetName = sheet.getSheetName();
        // 获取最后一行的行数，非索引值
        int rowNum = sheet.getLastRowNum() + 1;
        if (rowNum <= startRowNum + 1) {
            //logger.error("ship sheet:" + sheetName + " ,don't meet the requirements");
            //errorMsg.append("工作簿【" + sheetName + "】为空\n");
            return list;
        }
        // 获取表头所对应列
        Map<Integer, DataFormat> headMap = getSheetHeadData(sheet, startRowNum, formatMap);
        if (headMap.size() < formatMap.size()) {
            logger.error("ship sheet: " + sheetName + " ,head is error");
            errorMsg.append("工作簿【" + sheetName + "】列名不对应\n");
            return list;
        }
        // 校验解析excel
        Row row ;
        StringBuffer rowMsg;
        for (int i = startRowNum + 1; i < rowNum; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                rowMsg = new StringBuffer();
                T rowData = setRowValues2Bean(modal,headMap,row,rowMsg);
                if (StringUtils.isEmpty(rowMsg)) {
                    list.add(rowData);
                } else {
                    errorMsg.append(rowMsg);
                }
            }
        }
        return list;
    }
    private static <T> T setRowValues2Bean(T modal,Map<Integer, DataFormat> headMap,Row row,StringBuffer rowMsg) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        int cellNum = headMap.size();
        T rowData = (T) modal.getClass().newInstance();
        DataFormat dataFormat = null;
        Cell cell;
        Object value;
        for (int index = 0; index < cellNum; index++) {
            dataFormat = headMap.get(index);
            cell = row.getCell(index);
            value = getCellStringValue(cell);
            String[] prores = parseProperty(index + 1, String.valueOf(value == null ? "" : value), dataFormat);
            if (prores.length == 2) {
                rowMsg.append(prores[1]);
                continue;
            } else {
                valueInvoke(rowData, value, dataFormat);
            }
        }
        return rowData;
    }
    private static Map<Integer, DataFormat> getSheetHeadData(Sheet sheet, int startRowNum, Map<String, DataFormat> formatMap) {
        Map<Integer, DataFormat> headMap = new HashMap<Integer, DataFormat>();
        Row fristRow = sheet.getRow(startRowNum);
        // 获取最后一列index，再加上一
        int columnNum = fristRow.getLastCellNum();
        Cell cell ;
        String value ;
        DataFormat format ;
        for (int i = 0; i < columnNum; i++) {
            cell = fristRow.getCell(i);
            if (cell != null) {
                //cell.setCellType(Cell.CELL_TYPE_STRING);
                value = cell.getStringCellValue() == null ? null : cell.getStringCellValue().trim();
                format = formatMap.get(value);
                //logger.info(String.format("Column Number [%s] Current ColumnIndex [%s] Header [%s]",columnNum,i,value));
                if (format != null) {
                    format.setRowIndex(i + 1);
                    headMap.put(i, format);
                }
            }
        }
        return headMap;
    }

    public static void valueInvoke(Object rowData, Object value, DataFormat dataFormat) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        PropertyDescriptor targetPd = null;
        try {
            targetPd = new PropertyDescriptor(dataFormat.getProperty(),rowData.getClass());
        } catch (IntrospectionException e) {
        }
        if (targetPd != null) {
            Method writeMethod = targetPd.getWriteMethod();
            value = typeCast(writeMethod.getParameterTypes(),value!=null?value.toString():"");
            writeMethod.invoke(rowData, value);
        }
    }

    /**
     * @Author: weilf
     * @Date 2018/8/21 15:01
     * @Description 根据DTO属性类型对cell值进行类型转换
     * @Param parameterTypes
     * @Param value
     */
    private static Object typeCast(Class<?>[] parameterTypes, String value) {
        Class<?> clazzType = parameterTypes[0];
        if (clazzType.equals(Integer.class)) {
            return (int)Double.parseDouble(value);
        }else if  (clazzType.equals(Double.class)) {
            return Double.parseDouble(value);
        } else if (clazzType.equals(BigDecimal.class)) {
            return new BigDecimal(value);
        } else if (clazzType.equals(Timestamp.class)) {
            return Timestamp.valueOf(LocalDateTime.parse(value, dateTimeFormatter));
        } else if (clazzType.equals(java.util.Date.class)) {
            return ImportDateUtil.cal(value);
        } else {
            return value;
        }
    }

    private static String getCellStringValue(Cell cell) {
        return PoiCellValueManager.getWorkCellStringValue(cell);
    }
    private static String[] parseProperty(int rowNum, String propertyValue, DataFormat dataFormat) {
        String[] result = {propertyValue};
        if (dataFormat.isRequired()) {
            if (StringUtils.isEmpty(propertyValue)) {
                result = new String[]{propertyValue, "第" + rowNum + "行，第" + dataFormat.getRowIndex() + "列数据：" + dataFormat.getColumn() + "不能为空\r\n"};
            }
        }
        if (StringUtils.isNotEmpty(propertyValue)
            && StringUtils.isNotEmpty(dataFormat.getFormat())
            && !Pattern.matches(dataFormat.getFormat(), propertyValue)) {
            result = new String[]{propertyValue, "第" + rowNum + "行，第" + dataFormat.getRowIndex() + "列数据：["+propertyValue+"]" + dataFormat.getMessage() + "\r\n"};
        }
        return result;
    }


}
