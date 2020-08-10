package seng202.group10;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        testGetStartUpMessage();
//        assertTrue( true );
    }

    public void testGetStartUpMessage() {
        App app = new App();
        assertEquals(app.getStartUpMessage(), "Kia ora, y'all");
    }

    public void testGoodbyeWorld() {
        App app = new App();
        assertEquals(app.goodbyeWorld(), "Goodbye World!");
    }

    public void testZacMethod() {
        App testApp = new App();
        assertEquals(20, testApp.zacMethod(10));
    }


    public void testIntSubtract() {
        App app = new App();

        // Blue Sky
        int int1 = 10;
        int int2 = 5;
        int diff = app.intSubtract(int1, int2);
        assertEquals(int1 - int2, diff);
    }

    public void testTroyMethod() {
        App app = new App();
        assertEquals(app.troyMethod(), "This is dumb");
    }

    public void testGoodMorningVietnam() {
        App app = new App();
        assertEquals(app.goodMorningVietnam(), "Good Morning Vietnam");
    }
}