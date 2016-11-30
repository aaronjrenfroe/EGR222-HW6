
import java.util.Collection;
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
        else if (days.isEmpty()){
            emptyDays();
        }
        // does 'else' this have to be here?
        else {

            this.name = name;
            this.credits = credits;
            this.days = days;
            this.startTime = startTime;
            this.duration = duration;
        }
    }
    // test this
    public boolean conflictsWith(Course course){
        Set<Weekday> days = course.getDays();
        for (Weekday testDay: days) {

            if (this.days.contains (testDay)){
                int startTimeInMin = course.getTime().getTotalMinutes();
                int endTimeInMinutes = startTimeInMin + course.getDuration();
                int thisStart = this.getTime().getTotalMinutes();
                int thisEnd = thisStart + this.getDuration();
                if (startTimeInMin <= thisStart || thisStart <= thisEnd){
                    return true;
                }

            }
        }
        return false;

    }

    public boolean contains(Weekday day, Time time){
        if(this.days.contains(day)){

            if (this.startTime.getTotalMinutes() <= time.getTotalMinutes()
                    || time.getTotalMinutes() <= this.startTime.getTotalMinutes()+duration) {
                return true;
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
                && this.getTime().equals(oCourse.getTime())
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

    public Time getTime(){
        return this.startTime;

    }
    public Set<Weekday> getDays(){
        return this.days;
    }

    private void emptyDays(){
        throw new IllegalArgumentException(" Set of days was found to be Empty");
    }

    private void dumbCredits(int credits){
        if (credits < 1){
            throw new IllegalArgumentException("Credits can not be less than 1. You entered " + credits);
        } else throw new IllegalArgumentException("Credits can not be greater than 5. You Entered " + credits);
    }

}
