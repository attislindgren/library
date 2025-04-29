package se.thinkcode.other;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MaxHeapTest {



    @Test
    void should_add_element_to_heap() {
        MaxHeap maxHeap = new MaxHeap();
        
        maxHeap.insert(21);
        maxHeap.insert(10);
        maxHeap.insert(18);
        maxHeap.insert(19);
        maxHeap.insert(42);
        maxHeap.insert(11);
        maxHeap.insert(44);
        maxHeap.insert(33);
        maxHeap.insert(17);
        maxHeap.insert(4711);
        System.out.println();

        assertThat(maxHeap.peek()).isEqualTo(4711);

        printTree(maxHeap.getRoot(), 4);

        assertThat(maxHeap.getMax()).isEqualTo(4711);
        assertThat(maxHeap.getMax()).isEqualTo(44);
        assertThat(maxHeap.getMax()).isEqualTo(42);
        assertThat(maxHeap.getMax()).isEqualTo(33);
        assertThat(maxHeap.getMax()).isEqualTo(21);
        assertThat(maxHeap.getMax()).isEqualTo(19);
        assertThat(maxHeap.getMax()).isEqualTo(18);
        assertThat(maxHeap.getMax()).isEqualTo(17);
        assertThat(maxHeap.getMax()).isEqualTo(11);
        assertThat(maxHeap.getMax()).isEqualTo(10);
        assertThat(maxHeap.getMax()).isEqualTo(-1);
    }

    private void printTree(Node node, int level) {
        if (node != null) {
            printTree(node.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }
            System.out.println(node.value);
            printTree(node.left, level + 1);
        }
    }
}