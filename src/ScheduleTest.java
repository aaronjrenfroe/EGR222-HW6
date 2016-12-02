import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by AaronR on 12/2/16.
 */
public class ScheduleTest {

    public static void main(String[] args) {

        Set<Weekday> days1 = new HashSet<Weekday>();
        days1.add(Weekday.MONDAY);
        days1.add(Weekday.WEDNESDAY);
        days1.add(Weekday.FRIDAY);

        Course c1 = new Course("Che 222", 3, days1, new Time(12, 00, true), 60);
        Course c2 = new Course("Che 223", 3, days1, new Time(3,00 , true), 60);
        Course c3 = new Course("EGR 222", 3, days1, new Time(7, 30, false), 60);

        Course c4 = new Course("EGR 222", 3, days1, new Time(12, 30, true), 60);

        Schedule mySchedule = new Schedule();

        mySchedule.add(c1);
        mySchedule.add(c2);
        mySchedule.add(c3);
        mySchedule.add(c4);


        // 9 becasue there should have been a conflict and not added another 3
        Assert.assertEquals(true, mySchedule.totalCredits() == 9);

        mySchedule.remove(Weekday.MONDAY, new Time(12, 01, true));
        // should be 6 after removing the 3 unit class
        Assert.assertEquals(true, mySchedule.totalCredits() == 6);
        mySchedule.remove(Weekday.TUESDAY, new Time(3, 0, true));
        
        // should not have done anything

        Assert.assertEquals(true, mySchedule.totalCredits() == 6);




    }

}
