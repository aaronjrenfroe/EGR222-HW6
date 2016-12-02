import java.io.PrintStream;
import java.util.*;

/**
 * Created by AaronR on 12/1/16.
 */
public class Schedule implements Cloneable{
    List<Course> courses = new ArrayList<Course>();;
    // constructor
    public void Schedule() {}

    //adds given course to Set Courses
    public void add(Course addCourse) {
        this.courses.add(addCourse);
    }

    // returns course if it takes place on that given time else returns null
    public Course getCourse(Weekday day, Time time) {
        Iterator<Course> courseIterator = this.courses.iterator();
        while (courseIterator.hasNext()){
            Course curCourse = courseIterator.next();
            if (curCourse.contains(day, time)){
                return curCourse;
            }
        }
        return null;
    }

    //removes course if it falls on given time
    public void remove(Weekday day, Time time){
        Iterator<Course> courseIterator = this.courses.iterator();
        while (courseIterator.hasNext()){
            Course curCourse = courseIterator.next();
            if (curCourse.contains(day, time)){
                this.courses.remove(curCourse);
            }
        }
    }
    //saves the schedule to the given file passed in the printstream sorted by the passed comparator
    public void save(PrintStream ps, Comparator comp){
        //prints courses one per line
        Collections.sort(this.courses, comp);
        Iterator<Course> courseIterator= this.courses.iterator();
        while (courseIterator.hasNext()){
            ps.println(courseIterator.next());
        }
    }
    // calculates total credits
    public int totalCredits(){
        int totalCred = 0;
        Iterator<Course> courseIterator = this.courses.iterator();
        while (courseIterator.hasNext()){
            Course curCourse = courseIterator.next();
                totalCred+= curCourse.getCredits();
        }
        return totalCred;
    }

    @Override
    public Schedule clone(){
        Schedule newSched = new Schedule();
        newSched.courses.addAll(this.courses);
        return newSched;
    }
}
