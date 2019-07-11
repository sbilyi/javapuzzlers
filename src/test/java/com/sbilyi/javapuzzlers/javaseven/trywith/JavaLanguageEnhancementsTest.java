package com.sbilyi.javapuzzlers.javaseven.trywith;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


public class JavaLanguageEnhancementsTest {
    /**
     * https://docs.oracle.com/javase/7/docs/technotes/guides/language/binary-literals.html
     */
    @Test
    public void binaryLiterals() {
        long ten = 0b1010;
        byte three = 0b0011;
        short eight = 0b1000;
        int twenty = 0b10100;
        assertEquals(10l, ten);
        assertEquals(3, three);
        assertEquals(8, eight);
        assertEquals(20, twenty);
    }

    /**
     * https://docs.oracle.com/javase/7/docs/technotes/guides/language/underscores-literals.html
     */
    @Test
    public void underscoreSupport() {
        assertThat(19891.23, is(198_91.2_3));
    }

    /**
     * https://docs.oracle.com/javase/7/docs/technotes/guides/language/strings-switch.html
     */
    @Test
    public void stringAsCaseInSwitch() {
        String direction = "LEFT";
        switch (direction) {
            case "RIGHT":
                fail();
                break;
            case "LEFT":
                break;
            default:
                fail();
                break;
        }

    }
}
