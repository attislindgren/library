package se.thinkcode.other;

public class MaxHeap {
    Node root;

    public void insert(int i) {
        root = insert(i, root);
        heapify(root);
    }

    public int peek() {
        return root.value;
    }

    private void heapify(Node root) {
        if (root == null) {
            return;
        }
        int leftChild = 0;
        int rightChild = 0;
        if (root.left != null) {
            leftChild = root.left.value;
        }
        if (root.right != null) {
            rightChild = root.right.value;
        }
        if (root.value < leftChild && root.left != null) {
            int temp = root.value;
            root.value = leftChild;
            root.left.value = temp;
            heapify(this.root);
        }
        if (root.value < rightChild && root.right != null) {
            int temp = root.value;
            root.value = rightChild;
            root.right.value = temp;
            heapify(this.root);
        }
        heapify(root.left);
        heapify(root.right);
    }


    public Node insert(int i, Node node) {
        if (node == null) {
            node = new Node(i);
            return node;
        }
        if (i < node.value) {
            node.left = insert(i, node.left);
        } else {
            node.right = insert(i, node.right);
        }
        return node;

    }


}
