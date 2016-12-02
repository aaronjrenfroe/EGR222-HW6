import java.util.Comparator;

/**
 * Created by AaronR on 12/1/16.
 */
public class CourseTimeComparator implements Comparator<Course> {

    public int compare(Course c1, Course c2){
        int returnVal = c1.getStartTime().compareTo(c2.getStartTime());
            if (returnVal == 0){
                returnVal = c1.getEndTime().compareTo(c2.getEndTime());
                if (returnVal == 0){
                    return c1.getName().compareToIgnoreCase(c2.getName());
                }
            }
        return returnVal;
    }

}
