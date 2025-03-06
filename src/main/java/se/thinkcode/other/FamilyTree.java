package se.thinkcode.other;

public class FamilyTree {
    String longest = "";

    public String longestName(Person myFamily) {
        System.out.println(myFamily.namn());


        if (myFamily.namn().length() > longest.length()) {
            longest = myFamily.namn();
        }
        if (myFamily.mor() != null) {
            longestName(myFamily.mor());
        }
        if (myFamily.far() != null) {
            longestName(myFamily.far());
        }
        return longest;
    }

    public void print(Person person) {
        System.out.println(person.namn());

        if (person.mor() != null) {
            print(person.mor());
        }

        if (person.far() != null) {
            print(person.far());
        }
    }
}
