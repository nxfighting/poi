package com.vict.poi.utils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gf.zhou
 * @description //TODO
 * @date  2018/12/13
 * @param
 * @return
 */
@SuppressWarnings("AlibabaConstantFieldShouldBeUpperCase")
public class ImportDateUtil {
    private static SimpleDateFormat simpleDateFormat = null;
    private static final String FMT_DATE_TIME="yyyy-MM-dd HH:mm:ss";
    private static final String FMT_DATE="yyyy-MM-dd";
    private static final String PATTERN_DIGITS ="^([0-9]+\\.[0-9]+)|([1-9]{1}[0-9]*)$";
    private static final String PATTERN_DATE ="^[1-9]{1}[0-9]{3}(/|-){1}(0|1){1}[0-9]{1}(/|-){1}(0|1|2|3){1}[0-9]{1}$";
    private static final String PATTERN_TIME ="^(0|1|2){1}[0-9]{1}:[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}$";
    private static final String PATTERN_MILLION_SECONDS ="[0-9]+";
    private static final String DOT="\\.{1}";
    private static final String PREFIX="^";
    private static final String SUFFIX="$";
    private static final String PATTERN_SPACE="\\s{1}";
    private static final int DATE_CHAR_LENGTH=10;
    private static String getPatternDateTime(){
        return new StringBuilder(PATTERN_DATE.substring(0,PATTERN_DATE.length()-1))
                   .append(PATTERN_SPACE)
                   .append(PATTERN_TIME.substring(1,PATTERN_TIME.length())).toString();
    }
    private static String getPatternDateTimeWithms(){
        return new StringBuilder(PATTERN_DATE.substring(0,PATTERN_DATE.length()-1))
            .append(PATTERN_SPACE)
            .append(PATTERN_TIME.substring(1,PATTERN_TIME.length()-1))
            .append(DOT)
            .append(PATTERN_MILLION_SECONDS)
            .append(SUFFIX).toString();
    }
    public static Date cal(String value) {
        simpleDateFormat = new SimpleDateFormat(FMT_DATE_TIME);
        try {
            if(value.length()==DATE_CHAR_LENGTH){
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            }
            return simpleDateFormat.parse(value);
        } catch (Exception ex) {}
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        Double d = Double.parseDouble(value);
        LocalDateTime localDt = LocalDateTime.parse("1900-01-01");
        localDt = localDt.plusDays(d.intValue());
        double timeValue = d - d.intValue();
        localDt = localDt.plusHours((int) (timeValue * 24));
        localDt = localDt.plusMinutes((int) ((timeValue * 24 * 60) % 60));
        return localDt.toDate();
    }
    public static Date format(String value) {
        if(value!=null){
            if(value.matches(getPatternDateTime())){
                simpleDateFormat = new SimpleDateFormat(FMT_DATE_TIME);
                try {
                    return simpleDateFormat.parse(value);
                } catch (ParseException e) {}
            }
            if(value.matches(PATTERN_DATE)){
                simpleDateFormat = new SimpleDateFormat(FMT_DATE);
                try {
                    return simpleDateFormat.parse(value);
                } catch (ParseException e) {}
            }

        }
        Double d = Double.parseDouble(value);
        LocalDateTime localDt = LocalDateTime.parse("1900-01-01");
        localDt = localDt.plusDays(d.intValue());
        double timeValue = d - d.intValue();
        localDt = localDt.plusHours((int) (timeValue * 24));
        localDt = localDt.plusMinutes((int) ((timeValue * 24 * 60) % 60));
        return localDt.toDate();
    }
    public static void main(String [] args) {
        System.out.println(cal("43011.50333"));
        System.out.println(cal("1980-08-08 11:11:11"));
        System.out.println(cal("1980-08-01"));
        System.out.println("145.234536".matches(PATTERN_DIGITS));
        System.out.println("1".matches(PATTERN_DIGITS));
        System.out.println("1988/19/09".matches(PATTERN_DATE));
        System.out.println("12:12:12".matches(PATTERN_TIME));
        System.out.println("22:22:22".matches(PATTERN_TIME));
        System.out.println("33:33:33".matches(PATTERN_TIME));
        System.out.println(getPatternDateTime());
        String patternDatetime = getPatternDateTime();
        String patternDatetimeWithms=getPatternDateTimeWithms();
        System.out.println(patternDatetimeWithms);
        System.out.println("2018-11-11 12:12:12".matches(patternDatetime));
        System.out.println("2018-12-22 12:59:59".matches(patternDatetime));
        System.out.println("2018-22-22 12:12:12.222".matches(patternDatetimeWithms));
    }
}
