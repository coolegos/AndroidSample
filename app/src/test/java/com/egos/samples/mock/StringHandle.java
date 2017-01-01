package com.egos.samples.mock;

import java.util.Vector;

/**
 * Created by Egos on 2016/12/28.
 */
public class StringHandle {

    public IString istring; // 定义接口变量istring

    // 将字符串解析成数组
    public Integer[] splitString(String source, String delimiter) {
        Vector<Integer> vector = new Vector<Integer>();
        int position = source.indexOf(delimiter);
        while (source.contains(delimiter)) {
            String value = source.substring(0, position);
            if (istring.isNumber(value)) { // 将该行修改成istring.isNumber
                vector.add(Integer.parseInt(value));
            } else {
                Integer[] result = {1};
                return result;
            }
            source = source.substring(position + 1, source.length());
            position = source.indexOf(delimiter);
        }
        vector.add(Integer.parseInt(source));
        Integer[] array = new Integer[vector.size()];
        vector.copyInto(array);
        return array;
    }
}
