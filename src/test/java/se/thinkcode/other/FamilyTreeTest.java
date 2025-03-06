package se.thinkcode.other;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FamilyTreeTest {
    @Test
    void find_family_member_with_longest_name() {
        FamilyTree familyTree = new FamilyTree();
        Person myFamily = setUpTree();

        String expected = "Sven-Erik";
        String actual = familyTree.longestName(myFamily);

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void find_member_with_longest_name() {
        FamilyTree familyTree = new FamilyTree();
        Person myFamily = otherTree();

        String expected = "Evelina";
        String actual = familyTree.longestName(myFamily);

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void should_print() {
        FamilyTree familyTree = new FamilyTree();
        Person myFamily = otherTree();
        familyTree.print(myFamily);
    }

    public Person setUpTree() {
        return new Person(new Person(new Person(null, null, "Berit"), new Person(null, null, "Walter"), "Ingrid"), new Person(new Person(null, null, "Ingrid"), new Person(null, null, "Sven-Erik"), "Magnus"), "Astrid");
    }

    public Person otherTree() {
        Person mormor = new Person(null, null, "Agnes");
        Person morfar = new Person(null, null, "Ola");
        Person mor = new Person(mormor, morfar, "Anna");
        Person farmor = new Person(null, null, "Evelina");
        Person farfar = new Person(null, null, "Robert");
        Person far = new Person(farmor, farfar, "August");
        return new Person(mor, far, "Emil");
    }
}
