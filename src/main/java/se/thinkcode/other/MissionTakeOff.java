package se.thinkcode.other;

import java.time.LocalTime;

public class MissionTakeOff {
    public static LocalTime timeToTakeoff(Mission mission) {
        String alarm = mission.alarm();
        LocalTime alarmTime = LocalTime.parse(alarm);
        String takeOff = mission.takeOff();
        LocalTime takeOffTime = LocalTime.parse(takeOff);
        int alarmHour = alarmTime.getHour();
        int alarmMinute = alarmTime.getMinute();

        LocalTime alertToTakeOff = takeOffTime.minusHours(alarmHour);

        return alertToTakeOff.minusMinutes(alarmMinute);
    }
}
