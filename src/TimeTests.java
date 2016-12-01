import java.util.HashSet;
import java.util.Set;

/**
 * Created by AaronR on 12/1/16.
 */
public class TimeTests {
    public static void main(String[] args) {
        Set<Weekday> days = new HashSet<Weekday>();
        days.add(Weekday.MONDAY);
        days.add(Weekday.WEDNESDAY);
        days.add(Weekday.FRIDAY);
        Course c1 = new Course("HTC 121", 3, days,new Time(12, 0, true), 15);
        System.out.println(c1.getEndTime());
        System.out.println(c1.getStartTime().getTimeInMinutes());
    }
}
