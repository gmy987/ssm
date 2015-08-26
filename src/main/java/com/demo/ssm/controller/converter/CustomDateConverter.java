package com.demo.ssm.controller.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gmy on 15/8/5.
 */
public class CustomDateConverter implements Converter<String,Date> {

    @Override
    public Date convert(String source) {
        // 实现将日期串转换成日期类型(格式为: yyyy-MM-dd HH:mm:ss)
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(source);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
