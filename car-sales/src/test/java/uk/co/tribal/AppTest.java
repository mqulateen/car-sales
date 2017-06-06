package uk.co.tribal;

import java.util.Arrays;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    protected App app;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }
    
    protected void setUp() throws Exception{
        super.setUp();
        
        //populate object with sale items
        app = new App();
        app.addSale("Passat", "London")
           .addSale("Golf", "London")
           .addSale("Passat", "Bristol")
           .addSale("Sharan", "Leeds")
           .addSale("Golf", "London");
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        List<String> modelList = app.mostPopularModelOf("London");
        List<String> expectedModelList = Arrays.asList("Golf");
        
        assertTrue( modelList.equals(expectedModelList) );
    }
    
    public void testSalesCount()
    {
        long count = app.sales("Golf");
        
        assertEquals(count, 2);
    }
    
    public void testPopularModel()
    {
        List<String> modelList = app.mostPopularModel();
        List<String> expectedModelList = Arrays.asList("Golf","Passat");
        
        assertTrue( modelList.equals(expectedModelList) );
    }
}
