package se.thinkcode.other;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class PriorityQTest {

    @Test
    void should_run_jobs_in_order() {
        PriorityQ priorityQ = new PriorityQ();
        ArrayList<Job> jobList = new ArrayList<>();
        Job job1 = new Job(10, "Job1");
        Job job2 = new Job(7, "Job2");
        Job job3 = new Job(4, "Job3");
        Job job4 = new Job(1, "Job4");
        jobList.add(job3);
        jobList.add(job2);
        jobList.add(job4);
        jobList.add(job1);
        ArrayList<Job> heap = priorityQ.runJobs(jobList);

        Job actual = priorityQ.getJob(heap);

        assertThat(actual).isEqualTo(job1);
    }
}
