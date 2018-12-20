package com.vict.poi.domain;

import com.alibaba.fastjson.JSONArray;

import java.util.Map;

/**
 * @author
 * @create 2018-12-20 13:44
 */
public class ExcelExportDataConfig {
    private JSONArray exportDataArray;
    private Map<String,String> headsMap;
    private Map<String,DataFormat> headsFmtMap;
    private String sheetName;

    public JSONArray getExportDataArray() {
        return exportDataArray;
    }

    public void setExportDataArray(JSONArray exportDataArray) {
        this.exportDataArray = exportDataArray;
    }

    public Map<String, String> getHeadsMap() {
        return headsMap;
    }

    public void setHeadsMap(Map<String, String> headsMap) {
        this.headsMap = headsMap;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Map<String, DataFormat> getHeadsFmtMap() {
        return headsFmtMap;
    }

    public void setHeadsFmtMap(Map<String, DataFormat> headsFmtMap) {
        this.headsFmtMap = headsFmtMap;
    }
}
