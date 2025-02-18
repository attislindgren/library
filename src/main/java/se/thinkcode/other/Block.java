package se.thinkcode.other;


public class Block {
    private final String a;
    private final String b;
    private boolean used;

    public Block(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "Block{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", used=" + used +
                '}';
    }
}
