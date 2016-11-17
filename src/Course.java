
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

    public boolean conflictsWith(Course course){
        return true;

    }

    public boolean contains(Weekday day, Time time){
        return true;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    // accessor methods
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

    private void emptyDays(){
        throw new IllegalArgumentException(" Set of days was found to be Empty");
    }

    private void dumbCredits(int credits){
        if (credits < 1){
            throw new IllegalArgumentException("Credits can not be less than 1. You entered " + credits);
        } else throw new IllegalArgumentException("Credits can not be greater than 5. You Entered " + credits);
    }

}
