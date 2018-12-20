package com.vict.poi.domain;
import java.util.Map;

public class ExcelDataConfig {
    /**
     * @author gf.zhou
     * @description //TODO 表名
     * @date  2018/12/17
     * @params 
     * @return 
     */
    private String sheetName;
    /**
     * @author gf.zhou
     * @description //TODO 要封装的数据对象
     * @date  2018/12/17
     * @params 
     * @return 
     */
    private Object  clazzRef;
    /**
     * @author gf.zhou
     * @description //TODO 数据校验
     * @date  2018/12/17
     * @params 
     * @return 
     */
    private Map<String, DataFormat>   formatMap;
    /**
     * @author gf.zhou
     * @description //TODO 数据开始列[标题开始列]
     * @date  2018/12/17
     * @params 
     * @return 
     */
    private int startRowNum =0;
    public ExcelDataConfig(){}
    public ExcelDataConfig(String sheetName, Object clazz, Map formatMap){
        this.sheetName=sheetName;
        this.clazzRef=clazz;
        this.formatMap=formatMap;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Object getClazzRef() {
        return clazzRef;
    }

    public void setClazzRef(Object clazzRef) {
        this.clazzRef = clazzRef;
    }

    public Map<String, DataFormat> getFormatMap() {
        return formatMap;
    }

    public void setFormatMap(Map<String, DataFormat> formatMap) {
        this.formatMap = formatMap;
    }

    public int getStartRowNum() {
        return startRowNum;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }
}
