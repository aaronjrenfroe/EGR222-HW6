import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.io.PrintStream;
import java.util.*;

/**
 * Created by AaronR on 12/1/16.
 */
public class Schedule implements Cloneable{

    private List<Course> courses = new ArrayList<Course>();;
    // constructor
    public void Schedule() {}

    //adds given course to Set Courses
    public void add(Course addCourse) {

        if (courses.size() == 0 || !addHelper(addCourse)){

            this.courses.add(addCourse);
        }
    }
    // helper method to check if course to be added conflicts with existing courses
    private boolean addHelper(Course addCourse){
        Iterator<Course> courseIterator = this.courses.iterator();
        while (courseIterator.hasNext()) {
            Course curCourse = courseIterator.next();
            try {
                if (curCourse.conflictsWith(addCourse)) {

                    throw new ScheduleConflictException("" + curCourse.getName() + " " + " conflicts with " + addCourse.getName());
                }
            } catch (ScheduleConflictException er) {
                System.out.println(er);
                return true;
            }
        }
        return false;
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

        if (day != null) {
            Iterator<Course> courseIterator = this.courses.iterator();
            while (courseIterator.hasNext()) {
                Course curCourse = courseIterator.next();
                if (curCourse.contains(day, time)) {

                    this.courses.remove(curCourse);

                    return;

                }
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
    // clones course
    public Schedule clone(){
        Schedule newSched = new Schedule();
        newSched.courses.addAll(this.courses);
        return newSched;
    }
}
