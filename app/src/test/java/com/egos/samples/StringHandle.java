package com.egos.samples;

/**
 * Created by Egos on 2016/12/28.
 */
public class StringHandle {
    // 检查字符串是否可正常转换为数字
    public boolean isNumber(String source) {
        boolean isNumber = true;
        try {
            Integer.parseInt(source);
        } catch (Exception e) {
            isNumber = false;
        }
        return isNumber;
    }
}
