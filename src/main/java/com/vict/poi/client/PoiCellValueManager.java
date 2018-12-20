package com.vict.poi.client;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * POI get Cell Value Manager
 *
 * @author
 * @create 2018-12-10 9:27
 */
public class PoiCellValueManager {
    private static final Pattern patternDouble = Pattern.compile(".0$");
    private static DecimalFormat    decimalFormat = new DecimalFormat("0");
    /**
     * @author gf.zhou
     * @description //TODO 获取Cell String Value
     * @date  2018/12/13
     * @param [cell]
     * @return java.lang.String
     */
    public static String getWorkCellStringValue(Cell cell) {
        if(cell==null){
            return "";
        }
        Object o = null;
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_BLANK:
                o = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                o = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_ERROR:
                o = "Bad value!";
                break;
            case Cell.CELL_TYPE_NUMERIC:
                o = getValueOfNumericCell(cell);
                break;
            case Cell.CELL_TYPE_FORMULA:
                try {
                    o = getValueOfNumericCell(cell);
                } catch (IllegalStateException e) {
                    try {
                        o = cell.getRichStringCellValue().toString();
                    } catch (IllegalStateException e2) {
                        o = "";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                o = cell.getRichStringCellValue().getString();
        }
        return o.toString();
    }


    /**
     * @param [cell]
     * @return java.lang.Object
     * @author gf.zhou
     * @description //TODO 获取数字类型的cell值
     * @date 2018/12/10
     */
    private static Object getValueOfNumericCell(Cell cell) {
        Boolean isDate = DateUtil.isCellDateFormatted(cell);
        Double d = cell.getNumericCellValue();
        Object o = null;
        if (isDate) {
//            o = DateFormat.getDateTimeInstance().format(cell.getDateCellValue());
            o = getSimpleDate(cell.getDateCellValue());
        } else {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            o = getRealStringValueOfDouble(cell.getStringCellValue());
        }
        return o;
    }


    private static String getSimpleDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * @param
     * @return
     * @author gf.zhou
     * @description //TODO  处理科学计数法与普通计数法的字符串显示，尽最大努力保持精度
     * @date 2018/12/10
     */
    private static String getRealStringValueOfDouble(Double d) {
        String doubleStr = d.toString();
        return getRealStringValueOfDouble(doubleStr);
    }
    /**
     * @param
     * @return
     * @author gf.zhou
     * @description //TODO  处理科学计数法与普通计数法的字符串显示，尽最大努力保持精度
     * @date 2018/12/10
     */
    private static String getRealStringValueOfDouble(String doubleStr) {
        boolean b = doubleStr.contains("E");
        if (b) {
            BigDecimal bigDecimal =new BigDecimal(doubleStr,MathContext.DECIMAL128);
            doubleStr = bigDecimal.toString();
        } else {
            java.util.regex.Matcher m = patternDouble.matcher(doubleStr);
            if (m.find()) {
                doubleStr = doubleStr.replace(".0", "");
            }
        }
        return doubleStr;
    }

    public static void main(String[] args) {
        System.out.println(getRealStringValueOfDouble(0.8878787878787785645642));
        System.out.println(getRealStringValueOfDouble(123456780009.887878787878778564564));
        System.out.println(decimalFormat.format(1.887878787878778564564));
        System.out.println(getRealStringValueOfDouble(123456789000.987654321023456789));
        System.out.println(new BigDecimal("123456789.987654321E2"));
    }
}
