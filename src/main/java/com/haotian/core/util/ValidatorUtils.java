package com.haotian.core.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * hibernate-validator校验工具类 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author shanming.yang
 * @date 2018-7-5 10:50
 */
public class ValidatorUtils {


    public static Boolean isBlank(String... param) {
        for (String s : param) {
            if (StringUtils.isBlank(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测String内容 是否非数字
     *
     * @param param
     * @return
     */
    public static Boolean isNotNumberForString(String... param) {
        for (String s : param) {
            try {
                Double.parseDouble(s);
            } catch (NumberFormatException e) {
                return true;
            }
        }
        return false;
    }


    public static Boolean isReg(String reg, String... param) {
        for (String s : param) {
            if (!Pattern.matches(reg, s)) {
                return false;
            }
        }
        return true;
    }


    interface Time {
        String Reg_HH_mm_ss = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        String Reg_HHmmss = "^([0-1]?[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$";
        String Reg_yyyy_MM_dd__HH_mm_ss = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\s+([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        String REG_yyyyMMddHHmmss = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))([0-1]?[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$";
        String REG_yyyy_MM_dd = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))$";
        String REG_yyyyMMdd = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))$";
    }



}
