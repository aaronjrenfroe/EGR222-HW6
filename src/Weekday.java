import java.time.temporal.WeekFields;

/**
 * Created by AaronR on 11/15/16.
 */
public enum Weekday {

    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;

    // constructor accepts weekday in string format creates an enumeration
    public static Weekday fromString(String strDay) {
        strDay = strDay.toLowerCase();
        // I chose this way over another wecasue of readabilty
        switch (strDay){
            case "monday":
                return MONDAY;
            case "m":
                return MONDAY;
            case "tuesday":
                return TUESDAY;
            case "t":
                return TUESDAY;
            case "wednesday":
                return WEDNESDAY;
            case "w":
                return WEDNESDAY;
            case "thursday":
                return THURSDAY;
            case "r":
                return THURSDAY;
            case "friday":
                return FRIDAY;
            case "f":
                return FRIDAY;
            default:
                throw new IllegalArgumentException("User input did not match any know weekday");
        }
    }
    // returns short form of week day
    public String toShortName() {

        switch (this){
            case MONDAY:
                return "M";
            case TUESDAY:
                return "T";
            case WEDNESDAY:
                return "W";
            case THURSDAY:
                return "R";
            default:
                return "F";
        }
    }
    // returns String form of weekday
    @Override
    public String toString() {
        switch (this) {
            case MONDAY:
                return "Monday";
            case TUESDAY:
                return "Tuesday";
            case WEDNESDAY:
                return "Wednesday";
            case THURSDAY:
                return "Thursday";
            default:
                return "Friday";
        }
    }
}