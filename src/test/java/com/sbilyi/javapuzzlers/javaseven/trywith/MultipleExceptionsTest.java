package com.sbilyi.javapuzzlers.javaseven.trywith;

import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * https://docs.oracle.com/javase/7/docs/technotes/guides/language/catch-multiple.html
 */
public class MultipleExceptionsTest {

    private String exceptionName;

    @Before
    public void setUp() {
        exceptionName = "io";
    }

    @Test
    public void multipleExceptions() {
        try {
            if (isIOException()) {
                throw new IOException();
            } else {
                throw new SQLException();
            }
        } catch (IOException | SQLException ex) {
            assertEquals(ex.getClass(), IOException.class);
        }

        try {
            if (!isIOException()) {
                throw new IOException();
            } else {
                throw new SQLException();
            }
        } catch (IOException | SQLException ex) {
            assertEquals(ex.getClass(), SQLException.class);
        }
    }

    @Test
    public void catchVariableIsFinal() {
        try {
            if (isIOException()) {
                throw new IOException();
            } else {
                throw new SQLException();
            }
        } catch (IOException | SQLException ex) {
            /**
             * Note: If a catch block handles more than one exception type, then the catch parameter is implicitly final.
             * In this example, the catch parameter ex is final and therefore you cannot assign any values to it within the catch block.
             */
//            ex = null;
        }
    }


    @Test(expected = NotImplementedException.class)
    public void catchVariableIsNonFinalIfOneException() throws Exception {
        try {
            if (isIOException()) {
                throw new IOException();
            } else {
                throw new SQLException();
            }
        } catch (Exception ex) {
//             * If there is only one Exception handled it could be changed
            ex = new NotImplementedException();
            throw ex;
        }
    }

    @SuppressWarnings("")
    @Test(expected = SQLException.class)
    public void retrowException() throws IOException, SQLException {
        try {
            if (!isIOException()) {
                throw new IOException();
            } else {
                throw new SQLException();
            }
        } catch (Exception e) {
            throw e;
        }

    }

    private boolean isIOException() {
        return exceptionName.equals("io");
    }
}
