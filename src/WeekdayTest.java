import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by AaronR on 11/16/16.
 */

public class WeekdayTest {

    @Test
    public void fromStringTest() {
        Weekday newDay = Weekday.fromString("Monday");
        Weekday newDay1 = Weekday.fromString("Tuesday");
        Weekday newDay2 = Weekday.fromString("Wednesday");
        Weekday newDay3 = Weekday.fromString("ThursDAY");
        Weekday newDay4 = Weekday.fromString("FRIDAY");
        Weekday newDay5 = Weekday.fromString("m");
        Weekday newDay6 = Weekday.fromString("T");
        Weekday newDay7 = Weekday.fromString("W");
        Weekday newDay8 = Weekday.fromString("r");
        Weekday newDay9 = Weekday.fromString("f");
        Assert.assertEquals(Weekday.MONDAY, newDay);
        Assert.assertEquals(Weekday.MONDAY, newDay5);
        Assert.assertEquals(Weekday.TUESDAY, newDay1);
        Assert.assertEquals(Weekday.TUESDAY, newDay6);
        Assert.assertEquals(Weekday.WEDNESDAY, newDay2);
        Assert.assertEquals(Weekday.WEDNESDAY, newDay7);
        Assert.assertEquals(Weekday.THURSDAY, newDay3);
        Assert.assertEquals(Weekday.THURSDAY, newDay8);
        Assert.assertEquals(Weekday.FRIDAY, newDay4);
        Assert.assertEquals(Weekday.FRIDAY, newDay9);


    }

    @Test
    public void toShortName() {
        Assert.assertEquals("M", Weekday.MONDAY.toShortName());
        Assert.assertEquals("T", Weekday.TUESDAY.toShortName());
        Assert.assertEquals("W", Weekday.WEDNESDAY.toShortName());
        Assert.assertEquals("R", Weekday.THURSDAY.toShortName());
        Assert.assertEquals("F", Weekday.FRIDAY.toShortName());

    }

    @Test
    public void toStringTest() {
        System.out.println();
        Assert.assertEquals("Monday", Weekday.MONDAY.toString());
        Assert.assertEquals("Tuesday", Weekday.TUESDAY.toString());
        Assert.assertEquals("Wednesday", Weekday.WEDNESDAY.toString());
        Assert.assertEquals("Thursday", Weekday.THURSDAY.toString());
        Assert.assertEquals("Friday", Weekday.FRIDAY.toString());

    }

}