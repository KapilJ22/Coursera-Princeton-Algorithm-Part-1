package Week1;/* *****************************************************************************
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
        assertFalse("sites not blocked initially", tester.isOpen(2, 1));

    }

    @Test
    public void allSitesAreNotFullInitially() {
        Percolation tester = new Percolation(5); // MyClass is tested

        // assert statements
        //  assertEquals(false("blocked" tester.isOpen(2,1)));
        assertFalse("sites not blocked initially", tester.isFull(2, 1));

    }


    //     @Test
    //     public void siteAddressMAppingFromRowAndAddressIsCorrect() {
    //         Percolation tester = new Percolation(5); // MyClass is tested
    //
    //         // assert statements
    //         //  assertEquals(false("blocked" tester.isOpen(2,1)));
    //         //assertFalse("sites not blocked initially", tester.isOpen(2,1));
    // assertEquals(6,tester.oneDimensionSiteAddress(1,1));
    //     }


    @Test
    public void systemPercolates() {
        Percolation tester = new Percolation(5); // MyClass is tested
        // generate and print n numbers between lo and hi

        // assert statements
        tester.open(1, 1);
        tester.open(2, 1);
        tester.open(3, 1);
        tester.open(4, 1);
        tester.open(5, 1);

        assertTrue(tester.percolates());
    }

    @Test
    public void systemDoesNotPercolates() {
        Percolation tester = new Percolation(5); // MyClass is tested
        // generate and print n numbers between lo and hi

        // assert statements
        tester.open(1, 1);
        tester.open(2, 1);
        tester.open(3, 1);
        tester.open(4, 1);
        // tester.open(5,1);

        assertFalse(tester.percolates());
    }

    @Test
    public void SitesOpen() {
        Percolation tester = new Percolation(5); // MyClass is tested
        // generate and print n numbers between lo and hi

        // assert statements
        tester.open(1, 1);
        tester.open(2, 1);
        tester.open(3, 1);
        tester.open(4, 1);
        // tester.open(5,1);

        assertEquals("4 sites open", 4, tester.numberOfOpenSites());
    }

    @Test
    public void SitesOpen4() {
        Percolation tester = new Percolation(5); // MyClass is tested
        // generate and print n numbers between lo and hi

        // assert statements
        tester.open(1, 1);
        tester.open(2, 1);
        tester.open(3, 1);
        tester.open(2, 2);
        tester.open(3, 1);

        // tester.open(5,1);

        assertEquals("4 sites open", 4, tester.numberOfOpenSites());
    }

    @Test
    public void iSSiteFull() {
        Percolation tester = new Percolation(6); // MyClass is tested

        // assert statements
        //  assertEquals(false("blocked" tester.isOpen(2,1)));
        tester.open(1, 6);
        tester.open(2, 6);
        tester.open(3, 6);
        tester.open(4, 6);
        tester.open(5, 6);
        tester.open(5, 5);
        tester.open(4, 4);
        tester.open(3, 4);
        tester.open(2, 4);
        tester.open(2, 3);
        tester.open(2, 2);
        tester.open(2, 1);
        assertFalse("sites is full", tester.isFull(2, 1));

    }

}