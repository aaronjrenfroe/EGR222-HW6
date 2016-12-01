import org.junit.Test;
import org.junit.Assert;

import java.util.Collection;
import java.util.Iterator;

import java.util.Set;
import java.util.HashSet;

/**
 * Created by AaronR on 11/29/16.
 */
public class CourseTest {

    @Test
    public void constructorTest(){
        //Course(String name, int credits, Set<Weekday> days, Time startTime, int duration)
        Set<Weekday> days = new HashSet<Weekday>();
        days.add(Weekday.MONDAY);
        days.add(Weekday.WEDNESDAY);
        days.add(Weekday.FRIDAY);

        Course chem1 = new Course("CHE 101", 3, days, new Time(12, 00, true), 60);

        Assert.assertEquals(chem1.getName(), "CHE 101");
        Assert.assertEquals(chem1.getCredits(), 3);
        Assert.assertEquals(chem1.getStartTime(), new Time (12, 00, true));
        Assert.assertEquals(chem1.getDuration(), 60);

    }
    @Test
    public void conflictsWithTest(){
        Set<Weekday> days1 = new HashSet<Weekday>();
        days1.add(Weekday.MONDAY);
        days1.add(Weekday.WEDNESDAY);
        days1.add(Weekday.FRIDAY);

        Set<Weekday> days2 = new HashSet<Weekday>();
        days2.add(Weekday.THURSDAY);
        days2.add(Weekday.TUESDAY);

        Course chem1 = new Course("Che 222", 3, days1, new Time(12, 00, true), 2);
        Course chem2 = new Course("Che 223", 3, days1, new Time(12, 01, true), 1);
        Course EGR222 = new Course("EGR 222", 3, days2, new Time(12, 30, true), 60);
        Assert.assertEquals(true, chem1.conflictsWith(chem2));
        Assert.assertEquals(false, EGR222.conflictsWith(chem1));


    }

    @Test
    public void containsTest(){
        Set<Weekday> days1 = new HashSet<Weekday>();
        days1.add(Weekday.MONDAY);
        days1.add(Weekday.WEDNESDAY);
        days1.add(Weekday.FRIDAY);

        Course chem1 = new Course("CHE 101", 3, days1, new Time(12, 00, true), 60);
        Assert.assertEquals(true, chem1.contains(Weekday.FRIDAY, new Time(12, 30, true)));
        Assert.assertEquals(false, chem1.contains(Weekday.THURSDAY, new Time(12, 30, true)));

        Assert.assertEquals(false, chem1.contains(Weekday.THURSDAY, new Time(12, 1, false)));


    }

    @Test
    public void equalsTest(){
        Set<Weekday> days1 = new HashSet<Weekday>();
        days1.add(Weekday.MONDAY);
        days1.add(Weekday.WEDNESDAY);
        days1.add(Weekday.FRIDAY);

        Set<Weekday> days2 = new HashSet<Weekday>();
        days2.add(Weekday.THURSDAY);
        days2.add(Weekday.TUESDAY);

        Course chem1a = new Course("CHE 101", 3, days1, new Time(12, 00, true), 60);
        Course chem1b = new Course("CHE 101", 3, days1, new Time(12, 00, true), 60);
        Course chem2a = new Course("CHE 102", 3, days1, new Time(12, 30, true), 90);
        Course EGR222 = new Course("SWE 222", 3, days2, new Time(12, 30, true), 60);

        Assert.assertEquals(true, chem1a.equals(chem1b));
        Assert.assertEquals(false, chem1a.equals(EGR222));
        Assert.assertEquals(false, chem1a.equals(chem2a));
    }

    @Test
    public void toStringTest(){
        Set<Weekday> days1 = new HashSet<Weekday>();
        days1.add(Weekday.MONDAY);
        days1.add(Weekday.WEDNESDAY);
        days1.add(Weekday.FRIDAY);

        Set<Weekday> days2 = new HashSet<Weekday>();
        days2.add(Weekday.THURSDAY);
        days2.add(Weekday.TUESDAY);

        Course chem1a = new Course("Chm 101-A", 3, days1, new Time(12, 00, true), 60);

        Course egr222 = new Course("EGR 222", 3, days2, new Time(12, 30, true), 60);

        Assert.assertEquals("Chm 101-A, 3, MWF, 12:00 PM, 60", chem1a.toString());
        Assert.assertEquals("EGR 222, 3, TR, 12:30 PM, 60", egr222.toString());
    }


}