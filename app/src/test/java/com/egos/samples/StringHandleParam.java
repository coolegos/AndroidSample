package com.egos.samples;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;







import static org.junit.Assert.*;

/**
 * Created by Egos on 2016/12/28.
 * 参数化，可以一次配置比较多的信息，方便输入比较多的测试例子
 */
@RunWith(Parameterized.class)
public class StringHandleParam {

    public String source;
    public boolean result;

    // 构造函数
    public StringHandleParam(String source, boolean result) {
        this.source = source;
        this.result = result;
    }

    @Parameterized.Parameters // 指定该方法为参数生成器
    @SuppressWarnings("unchecked") // 忽略警告信息
    public static Collection getParamters() {
        // 输入值与结果必须与构造函数定义一一对应
        Object[][] object = {{"12345", true}, {"TT123", false}, {"", false},
                {null, false}, {"!@#$%^&", false}};
        return Arrays.asList(object);
    }

    @Test
    public void isNumber_Param() {
        StringHandle sh = new StringHandle();
        boolean result = sh.isNumber(this.source);
        assertEquals(this.result, result);
    }

}
