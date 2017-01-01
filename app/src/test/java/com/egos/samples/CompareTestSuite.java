package com.egos.samples;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Egos on 2016/12/28.
 * 测试集,一次测试多个
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExampleUnitTest.class, StringHandleParam.class})
public class CompareTestSuite {
}
