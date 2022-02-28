package a1;

/** NetId: jal545. Time spent: 4 hours, 0 minutes. <br>
 * What I thought about this assignment: It was helpful in getting to know Java and Eclipse but not
 * super exciting.<br>
 * <br>
 * An instance maintains info about the Phd of a person. */
public class Phd {
    /** Name of the person with the Phd, length > 0. */
    private String name;
    /** Year Phd was awarded, must be > 1000. */
    private int yearAwarded;
    /** Month Phd was awarded, in 1..12 with 1 meaning January, etc. */
    private int monthAwarded;
    /** First advisor of the Phd student, null if unknown. */
    private Phd firstAdvisor;
    /** Second advisor of the Phd student, null if unknown or none. */
    private Phd secondAdvisor;
    /** The number of advisees to the Phd student */
    private int numAdvisees;

    /** Constructor: an instance for a person with name n, Phd year y, <br>
     * Phd month m. Its advisors are unknown, and it has no advisees.<br>
     * Precondition: n has at least 1 char, y > 1000, and m is in 1..12 */
    public Phd(String n, int y, int m) {
        assert n.length() > 0;
        assert y > 1000;
        assert m >= 1 && m <= 12;

        name= n;
        yearAwarded= y;
        monthAwarded= m;
        firstAdvisor= null;
        secondAdvisor= null;
        numAdvisees= 0;
    }

    /** Constructor: a Phd with name n, Phd year y, Phd month m, <br>
     * first advisor a1, and second advisor a2.<br>
     * Precondition: n has at least 1 char, y > 1000, m is in 1..12,<br>
     * a1 and a2 are not null, and a1 and a2 are different. */
    public Phd(String n, int y, int m, Phd a1, Phd a2) {
        this(n, y, m);
        setAdvisor1(a1);
        setAdvisor2(a2);
    }

    /** = the name of this person. */
    public String name() {
        return name;
    }

    /** = the date on which this person got the Phd. In the form "month/year", <br>
     * with no blanks, e.g. "6/2007" */
    public String date() {
        return monthAwarded + "/" + yearAwarded;
    }

    /** = the first advisor of this Phd (null if unknown). */
    public Phd advisor1() {
        return firstAdvisor;
    }

    /** = the second advisor of this Phd (null if unknown or non-existent). */
    public Phd advisor2() {
        return secondAdvisor;
    }

    /** = the number of Phd advisees of this person. */
    public int nAdvisees() {
        return numAdvisees;
    }

    /** Make p the first advisor of this person.<br>
     * Precondition: the first advisor is unknown and p is not null. */
    public void setAdvisor1(Phd p) {
        assert p != null;
        assert firstAdvisor == null;

        firstAdvisor= p;
        p.numAdvisees+= 1;

    }

    /** Make p the second advisor of this person.<br>
     * Precondition: The first advisor (of this person) is known, the second advisor <br>
     * is unknown, p is not null, and p is different from the first advisor. */
    public void setAdvisor2(Phd p) {
        assert p != null;
        assert firstAdvisor != null;
        assert secondAdvisor == null;
        assert p != firstAdvisor;

        secondAdvisor= p;
        p.numAdvisees+= 1;
    }

    /** = "this Phd has no advisees",<br>
     * i.e. true if this Phd has no advisees and false otherwise. */
    public boolean hasNoAdvisees() {
        return numAdvisees == 0;
    }

    /** = "p is not null and this person got the Phd before p.” */
    public boolean gotBefore(Phd p) {
        return yearAwarded < p.yearAwarded ||
            yearAwarded == p.yearAwarded && monthAwarded < p.monthAwarded;
    }

    /** = "this person and p are intellectual siblings."<br>
     * Precondition: p is not null. */
    public boolean areSibs(Phd p) {
        assert p != null;

        boolean differentPhd= this != p;
        boolean same11= advisor1() != null && advisor1() == p.advisor1();
        boolean same12= advisor1() != null && advisor1() == p.advisor2();
        boolean same22= advisor2() != null && advisor2() == p.advisor2();
        boolean same21= advisor2() != null && advisor2() == p.advisor1();
        return differentPhd && (same11 || same12 || same22 || same21);
    }
}
