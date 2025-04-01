package se.thinkcode.other;

import java.util.ArrayList;
import java.util.Collections;

public class PriorityQ {
    public ArrayList<Job> runJobs(ArrayList<Job> jobList) {
        int i = ((jobList.size() / 2) - 1);
        ArrayList<Job> heap = heapify(jobList, i);
        return heap;
    }

    public ArrayList<Job> heapify(ArrayList<Job> jobList, int i) {
        if (i == 0) {
            if (jobList.get(i).prio() > jobList.get(leftChild(i)).prio() && jobList.get(i).prio() > jobList.get(rightChild(i)).prio()) {
                return jobList;
            }
            if (jobList.get(i).prio() < jobList.get(leftChild(i)).prio()) {
                if (jobList.get(leftChild(i)).prio() > jobList.get(rightChild(i)).prio()) {
                    Collections.swap(jobList, leftChild(i), i);
                    return jobList;
                } else {
                    Collections.swap(jobList, rightChild(i), i);
                }
            }
        } else {
            return heapify(jobList, (i - 1));
        }
        return jobList;
    }

    public int leftChild(int i) {
        return 2 * i + 1;
    }

    public int rightChild(int i) {
        return 2 * i + 2;
    }

    public Job getJob(ArrayList<Job> heap) {
        return heap.get(0);
    }
}
