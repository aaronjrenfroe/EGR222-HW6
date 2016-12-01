
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


    public Course(String name, int credits, Set<Weekday> days, Time startTime, int duration){

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
    // test this
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

    @Override
    public int hashCode() {

        return 17*331 * this.name.hashCode() * this.startTime.hashCode();
    }
    // accessor methods


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

    public int getCredits(){
        return this.credits;

    }
    public int getDuration(){
        return this.duration;

    }
    public String getName(){
        return this.name;

    }

    public Time getStartTime(){
        return this.startTime;

    }
    public Time getEndTime(){
        Time endTime = this.startTime.clone();
        endTime.shift(this.duration);
        return endTime;

    }
    public Set<Weekday> getDays(){
        return this.days;
    }

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
