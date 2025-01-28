package se.thinkcode.library;

public record Author(String firstName, String surname) {

    @Override
    public String toString() {
        return "Author{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
