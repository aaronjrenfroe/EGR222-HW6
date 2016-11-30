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

        Course chem1 = new Course("Chemistry", 3, days, new Time(12, 00, true), 60);

        Assert.assertEquals(chem1.getName(), "Chemistry");
        Assert.assertEquals(chem1.getCredits(), 3);
        Assert.assertEquals(chem1.getTime(), new Time (12, 00, true));
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

        Course chem1 = new Course("Chemistry", 3, days1, new Time(12, 00, true), 60);
        Course chem2 = new Course("Chemistry2", 3, days1, new Time(12, 30, true), 60);
        Course EGR222 = new Course("Software Engineering", 3, days2, new Time(12, 30, true), 60);
        Assert.assertEquals(chem1.conflictsWith(chem2), true);
        Assert.assertEquals(EGR222.conflictsWith(chem1), false);

        //Assert.assertEquals();
    }

    @Test
    public void containsTest(){
        Set<Weekday> days1 = new HashSet<Weekday>();
        days1.add(Weekday.MONDAY);
        days1.add(Weekday.WEDNESDAY);
        days1.add(Weekday.FRIDAY);

        Course chem1 = new Course("Chemistry", 3, days1, new Time(12, 00, true), 60);
        Assert.assertEquals(chem1.contains(Weekday.FRIDAY, new Time(12, 30, true)), true);
        Assert.assertEquals(chem1.contains(Weekday.THURSDAY, new Time(12, 30, true)), false);

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

        Course chem1a = new Course("Chemistry", 3, days1, new Time(12, 00, true), 60);
        Course chem1b = new Course("Chemistry", 3, days1, new Time(12, 00, true), 60);
        Course chem2a = new Course("Chemistry2", 3, days1, new Time(12, 30, true), 90);
        Course EGR222 = new Course("Software Engineering", 3, days2, new Time(12, 30, true), 60);

        Assert.assertEquals(chem1a.equals(chem1b), true);
        Assert.assertEquals(chem1a.equals(EGR222), false);
        Assert.assertEquals(chem1a.equals(chem2a), false);
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

        Assert.assertEquals(chem1a.toString(), "Chm 101-A, 3, MWF, 12:00 PM, 60");
        Assert.assertEquals(egr222.toString(), "EGR 222, 3, TR, 12:30 PM, 60");
    }


}