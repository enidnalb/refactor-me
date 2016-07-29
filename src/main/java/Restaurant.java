import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;



/**
 * @author Hugh Glykod
 */
public class Restaurant {

    private String name;

    Clock clockNow = Clock.systemDefaultZone();

    OpenHours openHours = new OpenHours();

    private static DayOfWeek day;

    Map<DayOfWeek, OpenHours> timetable = new HashMap<>();


    public void mapFilling(Map<DayOfWeek, OpenHours> timetable) { //remplit le tableau du lundi au samedi
        for (int i = 1; i < 7; i++) {
            timetable.put(DayOfWeek.of(i), openHours);
        }
    }

    public boolean isOpen(Map<DayOfWeek, OpenHours> timetable, DayOfWeek day, Clock clockNow) {

        if (timetable.containsKey(day)) { //verifie que le jour envoyé est dans le tableau. si dimanche, pas dans le tableau donc retourne false
            OpenHours openHours = timetable.get(day); //recupere les horaires d'ouverture du jour envoyé

            if (LocalTime.now(clockNow).isAfter(LocalTime.parse(openHours.getOpenTime())) && LocalTime.now(clockNow).isBefore(LocalTime.parse(openHours.getCloseTime()))) {
                return true;
            } else {
                return false;
            }
        } else return false;
    }


    public static DayOfWeek getDay() {
        return day;
    }

    public static void setDay(DayOfWeek day) {
        Restaurant.day = day;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
