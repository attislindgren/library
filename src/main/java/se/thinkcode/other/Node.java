package se.thinkcode.other;

public class Node {
    int value;
    Node left;
    Node right;

    public Node(Integer value) {
        this.value = value;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}