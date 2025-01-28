package se.thinkcode.other;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class MissionTakeOffTest {

    @Test
    void should_return_time_time_to_takeoff() {
        Mission one = new Mission("2025-01-20", "15:15", "15:30");

        LocalTime answer = MissionTakeOff.timeToTakeoff(one);

        assertThat(answer).isEqualTo(LocalTime.of(0, 15));
    }

    @Test
    void should_return_correct_time_to_takeoff_after_midnight() {
        Mission one = new Mission("2025-01-20", "23:15", "00:14");

        LocalTime answer = MissionTakeOff.timeToTakeoff(one);

        assertThat(answer).isEqualTo(LocalTime.of(0, 59));
    }
}
