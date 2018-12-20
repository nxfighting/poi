package com.vict.poi.domain;

import com.vict.poi.domain.enmuration.ResolveStatus;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2018-12-20 10:27
 */
public class ResolveExcelDTO {
    private ResolveStatus status;

    private StringBuffer message;

    private Map<Class,List> resultMap;

    public ResolveStatus getStatus() {
        return status;
    }

    public void setStatus(ResolveStatus status) {
        this.status = status;
    }

    public StringBuffer getMessage() {
        return message;
    }

    public void setMessage(StringBuffer message) {
        this.message = message;
    }

    public Map<Class, List> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<Class, List> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public String toString() {
        return "ResolveExcelDTO [status=" + status + ", message=" + message + ", resultMap=" + resultMap + "]";
    }
}
