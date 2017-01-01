package com.egos.samples;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;







import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("before class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("after class");
    }

    @BeforeClass
    public static void before() {
        System.out.println("before");
    }

    @AfterClass
    public static void after() {
        System.out.println("after");
    }

    @Test
    public void addition_isCorrect() throws Exception {
        // assert中有一大堆方法来判断是否相同，判断
//        Assert.assertArrayEquals();
//        Assert.assertFalse();
//        Assert.assertNotEquals();
//        etc...
        assertEquals(4, 2 + 2);
        double d = 100;
        assertThat(d, is(100.0));
        assertThat(d, notNullValue());
    }

    @Test(timeout = 3000)
    public void testTimeout() throws InterruptedException {
        Thread.sleep(2000); // 试试暂停4秒钟
        assertTrue(true);
    }

    /**
     * 这里的意思是预计会有一个ArithmeticException，没有的话就会报错。
     */
    @Test(expected = ArithmeticException.class)
    public void testException() {
        int i = 9 / 0; // 试试除以3 }
    }
}