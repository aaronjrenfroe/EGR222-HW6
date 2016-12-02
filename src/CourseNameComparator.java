import java.util.Comparator;

/**
 * Created by AaronR on 12/1/16.
 */
public class CourseNameComparator implements Comparator<Course> {


    public int compare(Course c1, Course c2){

        return c1.getName().compareToIgnoreCase(c2.getName());
    }
}
