import java.util.Comparator;

/**
 * Created by AaronR on 11/9/16.
 */

public class Time implements Cloneable, Comparable{

    private int minute;
    //1440 is max
    //720 is 12:00


    public Time( int h, int m, boolean pm){
        if (checkArgs(h,m)){
            throw new IllegalArgumentException("Something went wrong when creating your time Class");
        }else {
            this.minute = 0;

            if (pm && h < 12) {
                this.minute = 720;
            }
            if (!pm && h == 12) {
                this.minute -= 720;
            }

            this.minute += h * 60 + m;
        }

    }

    //takes string ie 12:03 PM and converts into a time object
    public static Time fromString(String str) {
        int h;
        int m;
        boolean pm;

        if (str.length() == 8 && str.charAt(5) == ' ') {
            if ((str.charAt(2) == ':')
                    && (str.charAt(5) == ' ')
                    && (str.toLowerCase().charAt(7) == 'm')
                    && (str.charAt(6) == 'A' || str.charAt(6) == 'P')) {

                h = Integer.parseInt(str.substring(0, 2));
                m = Integer.parseInt(str.substring(3, 5));

                if (checkArgs(h,m)) {
                    throw new IllegalArgumentException("Numbers are out of bounds");
                }

                if (str.charAt(6) == 'A') {
                    pm = false;
                } else pm = true;

                return new Time(h, m, pm);
            }

        }
        throw new IllegalArgumentException("Check Time format");

    }

    //accessor methods
    public int getHour(){

        int hour = (minute/60);

        if (hour > 12){
            hour = hour%12;
        }
        if (hour == 0){
            return 12;
        }

        return hour;
    }
    public int getMinute(){

        return this.minute%60;
    }
    public int getTotalMinutes(){
        return this.minute;
    }


    //if am is false returns true

    public boolean isPM(){
        return this.minute >= 720 ;
    }

    public int getTimeInMinutes(){
        return this.minute;
    }

    public Time shift(int minutes){
        if (minutes < 0) {

            throw new IllegalArgumentException("Minutes shifted must be positive");
        }

        else this.minute += minutes;
        if (this.minute >= 1440){

            minute = minute%1440;
        }
        return this;
    }

    //returns true if given time object has same state as this one
    @Override
    public boolean equals(Object o) {
        // checks if object is a time object
        if (o.getClass() == this.getClass()) {
            //sets object to new time object
            Time timeObj = ((Time) o);
            // compares if hour minute and am/pm is equal

            if (timeObj.minute == this.minute) {

                return true;
            }

            return false;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (isPM()? 1 : 0);
        result = result* minute%60;
        result = result * minute/24;

        return result;
    }

    @Override
    public String toString() {
        // pads string with 0's width of 2
        String hourStr = String.format("%02d",getHour());
        String minStr = String.format("%02d", getMinute());
        String amOrPm;

        if (isPM()) {
            amOrPm = "PM";
        }

        else amOrPm = "AM";

        return hourStr +":"+ minStr +" "+ amOrPm;
    }

    @Override
    public Time clone(){
        Time clonedTime = new Time(this.getHour(), this.getMinute(), this.isPM());
        return clonedTime;
    }

    @Override
    public int compareTo(Object o) {
        Time cTime = (Time)o;
        //
        int t1 = this.getTotalMinutes();
        int t2 = cTime.getTimeInMinutes();
        if (t1 < t2){
            return -1;
        }
        else if (t1 == t2){
            return 0;

        }
        else {
            return 1;
        }
    }

    private static boolean checkArgs(int h, int m){
        return (h > 12 || m > 59 || h < 1 || m < 0);
    }
}
