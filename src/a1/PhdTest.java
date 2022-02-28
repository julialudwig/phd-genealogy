package a1;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PhdTest {

    @Test
    void testConstructor1() {
        Phd p= new Phd("Julia Ludwig", 2000, 12);
        assertThrows(AssertionError.class, () -> { new Phd("", 1234, 5); });
        assertThrows(AssertionError.class, () -> { new Phd("hey", 1000, 5); });
        assertThrows(AssertionError.class, () -> { new Phd("hey", 1234, 13); });
        assertThrows(AssertionError.class, () -> { new Phd("", 1234, 5); });
        assertEquals("Julia Ludwig", p.name());
        assertEquals("12/2000", p.date());
        assertEquals(null, p.advisor1());
        assertEquals(null, p.advisor2());
        assertEquals(0, p.nAdvisees());
    }

    @Test
    void testSetters() {
        Phd p1= new Phd("Julia Ludwig", 2000, 12);
        Phd p2= new Phd("John Smith", 1970, 3);
        Phd p3= new Phd("Jane Doe", 1111, 10);
        Phd p5= new Phd("George", 1992, 12);
        p1.setAdvisor1(p2);
        assertThrows(AssertionError.class, () -> { p2.setAdvisor2(p3); });
        p1.setAdvisor2(p3);
        assertThrows(AssertionError.class, () -> { p1.setAdvisor2(p2); });
        assertThrows(AssertionError.class, () -> { p1.setAdvisor2(p5); });
        assertEquals(p2, p1.advisor1());
        assertEquals(p3, p1.advisor2());
        assertEquals(1, p2.nAdvisees());
        assertEquals(1, p3.nAdvisees());
    }

    @Test
    void testConstructor2() {
        Phd p1= new Phd("Julia Ludwig", 2000, 12);
        Phd p2= new Phd("John Smith", 1970, 3);
        assertEquals(0, p1.nAdvisees());
        assertEquals(0, p2.nAdvisees());
        Phd p3= new Phd("Jane Doe", 1111, 10, p1, p2);
        assertEquals("Jane Doe", p3.name());
        assertEquals("10/1111", p3.date());
        assertEquals(p1, p3.advisor1());
        assertEquals(p2, p3.advisor2());
        assertEquals(0, p3.nAdvisees());
        assertEquals(1, p1.nAdvisees());
        assertEquals(1, p2.nAdvisees());
    }

    @Test
    void testBooleanFunctions() {
        // testing hasNoAdvisees()
        Phd a1= new Phd("a1", 1555, 5);
        assertEquals(true, a1.hasNoAdvisees());
        Phd a2= new Phd("a2", 2000, 2);
        Phd a3= new Phd("a3", 2001, 6, a1, a2);
        assertEquals(true, a3.hasNoAdvisees());
        assertEquals(false, a1.hasNoAdvisees());
        assertEquals(false, a2.hasNoAdvisees());
        // testing gotBefore(Phd p)
        Phd mar99= new Phd("mar99", 1999, 3);
        Phd apr99= new Phd("apr99", 1999, 4);
        Phd may99= new Phd("may99", 1999, 5);
        assertEquals(true, mar99.gotBefore(apr99)); // same year, q’s month before p’s
        assertEquals(false, apr99.gotBefore(apr99)); // same year, same month
        assertEquals(false, may99.gotBefore(apr99)); // same year, q’s month after p’s
        Phd mar98= new Phd("mar98", 1998, 3);
        Phd apr98= new Phd("apr98", 1998, 4);
        Phd may98= new Phd("may98", 1998, 5);
        assertEquals(true, mar98.gotBefore(apr99)); // q’s year before p's, q’s month before p’s
        assertEquals(true, apr98.gotBefore(apr99)); // q’s year before p's, same month
        assertEquals(true, may98.gotBefore(apr99)); // q’s year before p's, q’s month after p’s
        Phd aug00= new Phd("aug00", 2000, 8);
        Phd sep99= new Phd("sep99", 1999, 9);
        Phd aug99= new Phd("aug99", 1999, 8);
        Phd jul99= new Phd("jul99", 1999, 7);
        assertEquals(false, aug00.gotBefore(sep99)); // q’s year after p's, q’s month before p’s
        assertEquals(false, aug00.gotBefore(aug99)); // q’s year after p's, same month
        assertEquals(false, aug00.gotBefore(jul99)); // q’s year after p's, q's month after p’s
        // testing areSibs(Phd p)
        Phd noAds1= new Phd("noAds1", 1222, 2);
        Phd noAds2= new Phd("noAds2", 1234, 4);
        assertThrows(AssertionError.class, () -> { noAds1.areSibs(null); });
        assertEquals(false, noAds1.areSibs(noAds2)); // No advisors.
        Phd sameNonNull= new Phd("sNN", 5555, 5, noAds1, noAds2);
        assertEquals(false, sameNonNull.areSibs(sameNonNull));// same object, same non-null first
                                                              // advisor.
        Phd other= new Phd("other", 9876, 1);
        Phd diffNonNull= new Phd("dNN", 4321, 4, noAds1, other);
        assertEquals(true, diffNonNull.areSibs(sameNonNull));// different objects, same first
                                                             // advisor (different 2nd).
        Phd diffSame2= new Phd("dS2", 6789, 6, other, noAds2);
        assertEquals(true, sameNonNull.areSibs(diffSame2));// different objects,ame second advisor
                                                           // (different 1st).
        Phd opposite= new Phd("opp", 1997, 12, noAds2, other);
        assertEquals(true, sameNonNull.areSibs(opposite));// different objects, the first advisor of
                                                          // one is the second advisor of the other
                                                          // (different opposite).
        Phd a= new Phd("a", 7777, 7, noAds1, noAds2);
        Phd b= new Phd("b", 2001, 10, other, diffNonNull);
        assertEquals(false, a.areSibs(b)); // different objects, different advisors.

    }

}
