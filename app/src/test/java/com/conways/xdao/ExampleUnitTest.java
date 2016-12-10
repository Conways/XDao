package com.conways.xdao;

import com.conways.xdao.db.XdaoDbManager;
import com.conways.xdao.utils.TimeUtil;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void Test1(){
        System.out.println("test");
        System.out.println(TimeUtil.getDataOfTypeOne(System.currentTimeMillis()));
    }
}