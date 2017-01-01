package com.egos.samples.mock;

/**
 * Created by Egos on 2016/12/28.
 */
public class MockIString implements IString {
    @Override
    public boolean isNumber(String source) {
        return true;
    }
}
