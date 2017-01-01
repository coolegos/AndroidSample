package com.egos.samples.mock;

import org.junit.Test;







import static org.junit.Assert.*;

/**
 * Created by Egos on 2016/12/28.
 */
public class StringHandleMock {
    @Test
    public void splitString() {
        StringHandle stringHandle = new StringHandle();
        stringHandle.istring = new MockIString();
        String source = "333,111,222,666";
        Integer[] expect = {333, 111, 222, 666};
        Integer[] actual = stringHandle.splitString(source, ",");
        assertArrayEquals(expect, actual);
    }
}
