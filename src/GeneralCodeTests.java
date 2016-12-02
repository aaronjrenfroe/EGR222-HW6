import java.util.HashSet;
import java.util.Set;

/**
 * Created by AaronR on 12/1/16.
 */
public class GeneralCodeTests {
    public static void main(String[] args) {
        Set<Weekday> days1 = new HashSet<Weekday>();
        days1.add(Weekday.MONDAY);
        days1.add(Weekday.WEDNESDAY);
        days1.add(Weekday.FRIDAY);

        Set<Course> courses = new HashSet<Course>();
        courses.add(new Course("Chm 123", 3,days1, new Time(12, 15, true), 15 ));

    }
}
