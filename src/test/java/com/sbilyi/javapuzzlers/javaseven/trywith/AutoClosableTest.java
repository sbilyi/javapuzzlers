package com.sbilyi.javapuzzlers.javaseven.trywith;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import trywith.*;

import static org.junit.Assert.*;

/**
 * This test is about to show how AutoClosable works
 */
public class AutoClosableTest {

    private static final String STATUS = Resource.STATUS_OPEN;
    private CloseEventListener closeEventListener;

    @Before
    public void setUp() throws Exception {
        closeEventListener = new CloseEventListener();
    }

    @After
    public void tearDown() throws Exception {
        closeEventListener = null;
    }

    @Test
    public void successfullTryWith() throws Exception {
        try (Resource r = new RegularAutoClosableResource(closeEventListener)) {
            assertEquals(r.getStatus(), STATUS);
            assertFalse(closeEventListener.contains(RegularAutoClosableResource.class.getCanonicalName()));
        }
        assertTrue(closeEventListener.contains(RegularAutoClosableResource.class.getCanonicalName()));
    }

    /**
     * If constructor failed when building object there is no close call
     *
     * @throws Exception
     */
    @Test
    public void errorOnWhileOpen() throws Exception {
        try {
            try (Resource r = new FailToOpenResource(closeEventListener)) {
                assertEquals(r.getStatus(), STATUS);
            }
        } catch (NotImplementedException e) {
            assertEquals(0, closeEventListener.events());
            assertFalse(closeEventListener.contains(RegularAutoClosableResource.class.getCanonicalName()));
        }
    }

    @Test
    public void failOnTryWith() throws Exception {
        try {
            try (Resource r = new RegularAutoClosableResource(closeEventListener)) {
                throw new NotImplementedException();
            }
        } catch (NotImplementedException e) {
            assertEquals(1, closeEventListener.events());
            assertTrue(closeEventListener.contains(RegularAutoClosableResource.class.getCanonicalName()));
        }
    }

    @Test
    public void firstExceptionFromTryWithBlock() {
        try (Resource r = new FailToOpenResource(closeEventListener)) {
            throw new Exception();
        } catch (Exception e) {
            assertEquals(NotImplementedException.class.getCanonicalName(), e.getClass().getCanonicalName());
        }

    }
    @Test
    public void suppressedExceptions() {
        try (Resource r = new FailToCloseResource(closeEventListener); Resource r2 = new FailToCloseResource(closeEventListener)) {
            throw new Exception();
        } catch (Exception e) {
            assertEquals(Exception.class.getCanonicalName(), e.getClass().getCanonicalName());
            assertEquals(2, e.getSuppressed().length);
            assertEquals(NotImplementedException.class.getCanonicalName(), e.getSuppressed()[0].getClass().getCanonicalName());
            assertEquals(NotImplementedException.class.getCanonicalName(), e.getSuppressed()[1].getClass().getCanonicalName());
        }

    }
}
