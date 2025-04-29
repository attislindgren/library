package se.thinkcode.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQ {
    private final List<Job> prioQueue = new ArrayList<>();

    public void insert(Job job) {
        prioQueue.add(job);
        int indexLast = prioQueue.size() - 1;
        heapify(prioQueue, indexLast);
    }

    private void heapify(List<Job> prioQueue, int indexChild) {
        if (indexChild != 0 && prioQueue.get(indexChild).prio() > prioQueue.get(parent(indexChild)).prio()) {
            Collections.swap(prioQueue, indexChild, parent(indexChild));
            heapify(prioQueue, parent(indexChild));
        }
    }

    private int parent(int indexChild) {
        return (indexChild - 1) / 2;
    }

    private int leftChild(int indexParent) {
        return 2 * indexParent + 1;
    }

    private int rightChild(int indexParent) {
        return 2 * indexParent + 2;
    }


    public Job peek() {
        return prioQueue.getFirst();
    }

    public Job getMax() {
        Job max = prioQueue.getFirst();
        Collections.swap(prioQueue, 0, prioQueue.size() - 1);
        prioQueue.remove(max);
        heapifyDown(0);

        return max;
    }

    private void heapifyDown(int current) {
        if ((prioQueue.size() - 1) > current) {
            Job largest = prioQueue.get(current);
            int left = leftChild(current);
            if (left < (prioQueue.size() - 1) && largest.prio() < prioQueue.get(left).prio()) {
                largest = prioQueue.get(left);
            }
            int right = rightChild(current);
            if (right < (prioQueue.size() - 1) && largest.prio() < prioQueue.get(right).prio()) {
                largest = prioQueue.get(right);
            }
            if (largest != prioQueue.get(current)) {
                int large = prioQueue.indexOf(largest);
                Collections.swap(prioQueue, current, large);
                heapifyDown(large);
            }
        }
    }
}
