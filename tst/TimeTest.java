import org.junit.Test;
import org.junit.Assert;

import java.util.Set;
import java.util.HashSet;

/**
 * Created by AaronR on 11/9/16.
 */
public class TimeTest {
    private static void assertHelper(int h, int m, boolean b, Time t){

    }

    @Test
    public void constructorTest(){
        //Given
        Time t = new Time(12, 59, true);


        Time t2 = new Time(1, 0, false);

        //When/Then
        assertHelper(12, 59, true, t);
        //assertHelper(1, 0, false, t2);
    }

    private void constNegativeHelper(int h, int m, boolean b){
        try {
            Time t = new Time(h, m, b);
            Assert.fail("hour "+ h + " and/or minute " + m + "isPM "+ b + " is invalid");
        }catch(IllegalArgumentException e){
        }
    }

    @Test
    public void constNegativeTest(){
        constNegativeHelper(0,53, true); //00:59 PM is invalid
        constNegativeHelper(15,52, true); //13:59 PM is invalid
        constNegativeHelper(-2,31, false); //-5:59 AM is invalid
        constNegativeHelper(11,60, false); //11:60 AM is invalid
        constNegativeHelper(11,-10, false); //11:-1 AM is invalid
    }

    @Test
    public void fromStringTest(){
        Time t = Time.fromString("02:59 PM");
        assertHelper(2, 59, true, t);

        Time t2 = Time.fromString("12:59 AM");

        assertHelper(12, 59, false, t2);

        Time t3 = Time.fromString("05:05 PM");
        assertHelper(5, 5, true, t3);
    }

    @Test
    public void toStringTest() {
        Time t = new Time(3, 11, false);
        Assert.assertEquals("03:11 AM", t.toString());

        Time t2 = new Time(12, 21, false);
        Assert.assertEquals("12:21 AM", t2.toString());

        Time t3 = new Time(6, 01, true);
        Assert.assertEquals("06:01 PM", t3.toString());

    }

    @Test
    public void equalsTest(){
        Time t = new Time(5, 1, false);
        Time t2 = new Time(5, 1, false);
        Time t3 = t;

        Assert.assertTrue(t.equals(t2));
        Assert.assertTrue(t2.equals(t));
        Assert.assertTrue(t.equals(t3));
        Assert.assertEquals(t.hashCode(), t2.hashCode());
        Assert.assertEquals(t3.hashCode(), t.hashCode());
    }

    private void fromStringTestHelper(String str){
        try {
            Time.fromString(str);
            Assert.fail("str is invalid format");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void fromStringNegativeTest(){
        fromStringTestHelper("hello world");
        fromStringTestHelper("when is thanksgiving?");
        fromStringTestHelper("5:03 PM");
        fromStringTestHelper("05:03 pM");
        fromStringTestHelper("05;06 AM");
        fromStringTestHelper("05:065AM");
        fromStringTestHelper("05:05 XX");
        fromStringTestHelper("13:59 PM");
        fromStringTestHelper("12:60 AM");
    }

    @Test
    public void equalsNegativeTest(){
        Time t = new Time(5, 0, false);
        Time t2 = new Time(5, 0, true);
        Assert.assertFalse(t.equals(t2));
        Assert.assertFalse(t2.equals(t));
    }

    @Test
    public void hashCodeTest(){
        Time t = new Time(5, 1, false);
        t.shift(10);

        Time t2 = new Time(5, 11, false);
        Assert.assertTrue(t.equals(t2));
        Assert.assertTrue(t2.equals(t));
        Assert.assertEquals(t.hashCode(), t2.hashCode());
    }

    @Test
    public void hashSetTest(){
        Set<Time> set = new HashSet<Time>();
        Time t = new Time(12, 58, false);
        set.add(t);

        Time t2 = new Time(12, 58, false);
        Assert.assertTrue(set.contains(t2));

        set.remove(t2);
        Assert.assertFalse(set.contains(t2));
        Assert.assertFalse(set.contains(new Time(12, 58, false)));
    }

    @Test
    public void shiftTest(){
        Time t = new Time (12, 59, false); //12:59 AM
        t.shift(120); //02:59 AM
        Assert.assertEquals("02:59 AM", t.toString());

        t.shift(1); // Add 1 min
        Assert.assertEquals("03:00 AM", t.toString());

        t.shift(72); // Add 1 hour 12 min
        Assert.assertEquals("04:12 AM", t.toString());

        t.shift(1920); //Add 32 hours (24 hours + 8 hours) the same as adding 8 hours
        Assert.assertEquals("12:12 PM", t.toString());

        t.shift(717); //Add 11 hours 57 minute
        Assert.assertEquals("12:09 AM", t.toString());

        t.shift(1259); //Add 20 hours 59 minute
        Assert.assertEquals("09:08 PM", t.toString());

        t.shift(173); //Add 2 hours 53 min
        Assert.assertEquals("12:01 AM", t.toString());
    }
}