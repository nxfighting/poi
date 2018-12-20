package com.vict.poi.domain;

/**
 * @author
 * @create 2018-12-20 10:27
 */
public class DataFormat {
    private int rowIndex;

    private String property;

    private String column;

    private boolean isRequired;

    private String format;

    private String message;

    public DataFormat() {
    }

    public DataFormat(String property, String column, boolean isRequired, String format, String message) {
        this.property = property;
        this.column = column;
        this.isRequired = isRequired;
        this.format = format;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public String toString() {
        return "DataFormat [rowIndex=" + rowIndex + ", property=" + property + ", column=" + column + ", isRequired="
                + isRequired + ", format=" + format + ", message=" + message + "]";
    }

}
