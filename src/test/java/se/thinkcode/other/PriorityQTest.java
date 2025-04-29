package se.thinkcode.other;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PriorityQTest {
    PriorityQ priorityQ = new PriorityQ();
    @Test
    void should_add_job() {
        Job job1 = new Job(10, "Job1");
        Job job2 = new Job(7, "Job2");
        Job job3 = new Job(4, "Job3");
        Job job4 = new Job(1, "Job4");
        Job job5 = new Job(14, "Job5");
        priorityQ.insert(job2);
        priorityQ.insert(job4);
        priorityQ.insert(job3);
        priorityQ.insert(job1);
        priorityQ.insert(job5);

        Job actual = priorityQ.peek();
        assertThat(actual).isEqualTo(job5);
    }

    @Test
    void should_run_and_remove_jobs_in_order() {
        Job job1 = new Job(10, "Job1");
        Job job2 = new Job(7, "Job2");
        Job job3 = new Job(4, "Job3");
        Job job4 = new Job(1, "Job4");
        Job job5 = new Job(14, "Job5");
        priorityQ.insert(job2);
        priorityQ.insert(job4);
        priorityQ.insert(job3);
        priorityQ.insert(job1);
        priorityQ.insert(job5);

        assertThat(priorityQ.getMax()).isEqualTo(job5);
        assertThat(priorityQ.getMax()).isEqualTo(job1);
        assertThat(priorityQ.getMax()).isEqualTo(job2);
        assertThat(priorityQ.getMax()).isEqualTo(job3);
        assertThat(priorityQ.getMax()).isEqualTo(job4);
    }
}
