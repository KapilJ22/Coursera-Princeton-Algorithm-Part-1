/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class MyTests {

    @Test
    public void allSitesAreBlockedInitially() {
        Percolation tester = new Percolation(5); // MyClass is tested

        // assert statements
      //  assertEquals(false("blocked" tester.isOpen(2,1)));
        assertFalse("sites not blocked initially", tester.isOpen(2,1));

    }

    @Test
    public void allSitesAreNotFullInitially() {
        Percolation tester = new Percolation(5); // MyClass is tested

        // assert statements
        //  assertEquals(false("blocked" tester.isOpen(2,1)));
        assertFalse("sites not blocked initially", tester.isFull(2,1));

    }



    @Test
    public void siteAddressMAppingFromRowAndAddressIsCorrect() {
        Percolation tester = new Percolation(5); // MyClass is tested

        // assert statements
        //  assertEquals(false("blocked" tester.isOpen(2,1)));
        //assertFalse("sites not blocked initially", tester.isOpen(2,1));
assertEquals(6,tester.oneDimensionSiteAddress(1,1));
    }


    @Test
    public void openARandomSite() {
        Percolation tester = new Percolation(5); // MyClass is tested
        // generate and print n numbers between lo and hi

        // assert statements
        tester.open(0,24);
        assertTrue(tester.isOpen(0,24));
    }

}