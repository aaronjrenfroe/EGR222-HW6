
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by AaronR on 11/15/16.
 */

public class Course {

    private String name;
    private int credits;
    private int duration;
    private Time startTime;
    private Set<Weekday> days;

    // constructor
    public Course(String name, int credits, Set<Weekday> days, Time startTime, int duration){
        // check if args are invalid
        if (credits < 1 || credits > 5){
           dumbCredits(credits);
        }
        else if ( days == null || days.isEmpty()){
            emptyDays();
        }
        else if (name == null || name.equals("") || !(name.contains(" "))){
            invalidName();
        }
        else if (startTime == (null)){
            invalidTime();
        }
        else if (duration <1){
            invalidDuration();
        }

        else {

            this.name = name;
            this.credits = credits;
            this.days = days;
            this.startTime = startTime.clone();
            this.duration = duration;
        }
    }

    // checks if arg course conflicts with this course
    public boolean conflictsWith(Course course){
        Set<Weekday> days = course.getDays();
        Iterator<Weekday> dayIter = days.iterator();

        while (dayIter.hasNext()){
            Weekday day = dayIter.next();

            if (this.days.contains(day)){

                if (this.startTime.compareTo(course.getEndTime()) < 0 && (course.getStartTime().compareTo(this.getEndTime())) < 0){

                    return true;
                }
            }

        }
        return false;
    }
    // checks if given day and time conflicts with course
    public boolean contains(Weekday day, Time time){
        if(this.days.contains(day)){

            if (this.startTime.compareTo(time) <= 0) {
                if (this.getEndTime().compareTo(time) > 0){
                    return true;
                }
            }
            else if (time.compareTo(this.startTime) <= 0) {
                if (time.compareTo(this.getEndTime()) > 0){
                    return true;
                }
            }
        }
        return false;
    }
    // checks if given course is equal to this course
    @Override
    public boolean equals(Object o) {
        Course oCourse = (Course) o;

        boolean returnValue =  (this.name == oCourse.getName()
                && this.credits == oCourse.getCredits()
                && this.duration == oCourse.getDuration()
                && this.getStartTime().equals(oCourse.getStartTime())
                && this.days.equals(oCourse.getDays()));

        return returnValue;

    }
    // hash browns
    @Override
    public int hashCode() {

        return 17*331 * this.name.hashCode() * this.startTime.hashCode();
    }
    // accessor methods

    // converts course to a readable string format
    @Override
    public String toString() {
        String outString =  this.name + ", " + this.credits + ", ";

        if (this.days.contains(Weekday.MONDAY)){
            outString += "M";
        }
        if (this.days.contains(Weekday.TUESDAY)){
            outString += "T";
        }
        if (this.days.contains(Weekday.WEDNESDAY)){
            outString += "W";
        }
        if (this.days.contains(Weekday.THURSDAY)){
            outString += "R";
        }
        if (this.days.contains(Weekday.FRIDAY)){
            outString += "F";
        }
        outString += ", " + this.startTime.toString() + ", " + this.duration;

        return outString;
    }
    // returns this courses gredit value
    public int getCredits(){
        return this.credits;

    }
    // returns this courses duration in minutes
    public int getDuration(){
        return this.duration;

    }
    // returns this courses name as a string
    public String getName(){
        return this.name;

    }
    // returns this courses start time
    public Time getStartTime(){
        return this.startTime;

    }
    //returns this courses end time.
    public Time getEndTime(){

        Time endTime = this.startTime.clone();
        endTime.shift(this.duration);
        return endTime;

    }
    // returns the days this course is scheduled for
    public Set<Weekday> getDays(){
        return this.days;
    }

    // illegal argument throwers
    private void emptyDays(){
        throw new IllegalArgumentException(" Set of days was found to be Empty");
    }
    private void invalidName() { throw new IllegalArgumentException(" Name is invalid");}
    private void invalidTime() { throw new IllegalArgumentException(" Name is invalid");}
    private void invalidDuration() { throw new IllegalArgumentException(" Name is invalid");}

    private void dumbCredits(int credits){
        if (credits < 1){
            throw new IllegalArgumentException("Credits can not be less than 1. You entered " + credits);
        } else throw new IllegalArgumentException("Credits can not be greater than 5. You Entered " + credits);
    }

}
