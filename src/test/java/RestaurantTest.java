import org.junit.Test;
import java.time.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Hugh Glykod
 */
public class RestaurantTest {
    Restaurant myRestaurant = new Restaurant() ;
    Map<DayOfWeek, OpenHours> timetable= new HashMap<>();


    @Test
    public void testMapShouldBeFull(){

        myRestaurant.mapFilling(timetable);
        for (Map.Entry<DayOfWeek,OpenHours> e : timetable.entrySet()){
            System.out.println(e.getKey() + " : " + e.getValue());
            }
        assertFalse(timetable.isEmpty());
    }

    @Test
    public void testDefaultOpeningHours() {
        //creation d'une horloge fixe
        Instant imposedTime =Instant.parse("2016-07-01T11:00:00.00Z");
        ZoneId zone = ZoneId.of("CET");
        Clock fixed = Clock.fixed(imposedTime, zone);

        DayOfWeek day=DayOfWeek.MONDAY;

        //remplissage du map
        myRestaurant.mapFilling(timetable);

        boolean isOpen = myRestaurant.isOpen(timetable,day, fixed);
        assertTrue(isOpen);
    }

    @Test
    public void testShouldBeClosedOnSunday(){
        //creation d'une horloge fixe
        Instant imposedTime =Instant.parse("2016-07-27T09:00:00.00Z");
        ZoneId zone = ZoneId.of("CET");
        Clock fixed = Clock.fixed(imposedTime, zone);

        DayOfWeek day=DayOfWeek.SUNDAY;

        myRestaurant.mapFilling(timetable);

        boolean isOpen = myRestaurant.isOpen(timetable, day, fixed);
        assertFalse(isOpen);
    }

    @Test
    public void checkClockNow(){
        assertTrue(myRestaurant.clockNow.equals(Clock.systemDefaultZone()));
    }
}
