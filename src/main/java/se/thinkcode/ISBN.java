package se.thinkcode;

import java.util.Objects;

public class ISBN {
    private final String isbn;

    public ISBN(String isbn) {
        boolean answer = valid(isbn);
        if (answer) {
            System.out.println("Det gick bra!");
        } else {
            System.out.println("Det gick dåligt!");
            throw new RuntimeException("Det gick dåligt!");
        }
        this.isbn = isbn;
    }

    private boolean valid(String isbn) {
        String isbn_without_space = isbn.replaceAll(" ", "");
        String isbn_without_hyphen = isbn_without_space.replaceAll("-", "");
        int sum = 0;
        if (isbn_without_hyphen.length() == 13) {
            for (int i = 0; i < isbn_without_hyphen.length() - 1; i++) {
                int number = Character.getNumericValue(isbn_without_hyphen.charAt(i));
                if ((i + 1) % 2 == 0) {
                    sum += number * 3;
                } else {
                    sum += number;
                }
            }
            int last_character = Character.getNumericValue(isbn_without_hyphen.charAt(12));
            int check_digit = (10 - (sum % 10)) % 10;
            return check_digit == last_character;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ISBN isbn1 = (ISBN) o;
        return Objects.equals(isbn, isbn1.isbn);
    }
}