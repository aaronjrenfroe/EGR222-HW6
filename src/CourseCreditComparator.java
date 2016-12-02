import java.util.Comparator;

/**
 * Created by AaronR on 12/1/16.
 */
public class CourseCreditComparator implements Comparator<Course> {

    public int compare(Course c1, Course c2){
        int returnVal = Integer.min(Integer.max(-1,c1.getCredits() - c2.getCredits()), 1);
        if (returnVal == 0){
            return c1.getName().compareToIgnoreCase(c2.getName());
        }
        return returnVal;
    }
}

