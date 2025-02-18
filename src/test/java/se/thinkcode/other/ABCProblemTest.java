package se.thinkcode.other;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ABCProblemTest {
    ABCProblem abcProblem = new ABCProblem();

    @Test
    void make_word_A() {
        List<List<String>> blocks = getBlocks();
        List<Block> blocksBlock = getBlocks3();

        Boolean answer = abcProblem.canMakeWord("A", blocks);
        Boolean answer3 = abcProblem.canMakeWordThree("A", blocksBlock);

        assertThat(answer).isTrue();
        assertThat(answer3).isTrue();
    }

    @Test
    void make_word_BARK() {
        List<List<String>> blocks = getBlocks();
        List<Block> blocksBlock = getBlocks3();

        Boolean answer = abcProblem.canMakeWord("BARK", blocks);
        Boolean answer3 = abcProblem.canMakeWordThree("BARK", blocksBlock);

        assertThat(answer).isTrue();
        assertThat(answer3).isTrue();
    }

    @Test
    void cannot_make_word_BOOK() {
        List<List<String>> blocks = getBlocks();
        List<Block> blocksBlock = getBlocks3();

        Boolean answer = abcProblem.canMakeWord("BOOK", blocks);
        Boolean answer3 = abcProblem.canMakeWordThree("BOOK", blocksBlock);

        assertThat(answer).isFalse();
        assertThat(answer3).isFalse();
    }

    @Test
    void make_word_TREAT() {
        List<List<String>> blocks = getBlocks();
        List<Block> blocksBlock = getBlocks3();

        Boolean answer = abcProblem.canMakeWord("TREAT", blocks);
        Boolean answer3 = abcProblem.canMakeWordThree("TREAT", blocksBlock);

        assertThat(answer).isTrue();
        assertThat(answer3).isTrue();
    }

    @Test
    void make_word_COMMON() {

        Boolean answer = abcProblem.canMakeWord("COMMON", getBlocks());
        Boolean answer2 = abcProblem.canMakeWordTwo("COMMON", getBlocks());
        Boolean answer3 = abcProblem.canMakeWordThree("COMMON", getBlocks3());

        assertThat(answer).isFalse();
        assertThat(answer2).isFalse();
        assertThat(answer3).isFalse();
    }

    @Test
    void make_word_SQUAD() {

        Boolean answer = abcProblem.canMakeWord("SQUAD", getBlocks());
        Boolean answer2 = abcProblem.canMakeWordTwo("SQUAD", getBlocks());
        Boolean answer3 = abcProblem.canMakeWordThree("SQUAD", getBlocks3());

        assertThat(answer).isTrue();
        assertThat(answer2).isTrue();
        assertThat(answer3).isTrue();
    }

    @Test
    void make_word_CONFUSE() {
        List<List<String>> blocks = getBlocks();
        List<Block> blocksBlock = getBlocks3();

        Boolean answer = abcProblem.canMakeWord("CONFUSE", blocks);
        Boolean answer3 = abcProblem.canMakeWordThree("TREAT", blocksBlock);

        assertThat(answer).isTrue();
        assertThat(answer3).isTrue();
    }

    private List<List<String>> getBlocks() {
        List<List<String>> blocks = new ArrayList<>();
        blocks.add(new ArrayList<>(List.of("B", "O")));
        blocks.add(new ArrayList<>(List.of("X", "K")));
        blocks.add(new ArrayList<>(List.of("D", "Q")));
        blocks.add(new ArrayList<>(List.of("C", "P")));
        blocks.add(new ArrayList<>(List.of("N", "A")));
        blocks.add(new ArrayList<>(List.of("G", "T")));
        blocks.add(new ArrayList<>(List.of("R", "E")));
        blocks.add(new ArrayList<>(List.of("T", "G")));
        blocks.add(new ArrayList<>(List.of("Q", "D")));
        blocks.add(new ArrayList<>(List.of("F", "S")));
        blocks.add(new ArrayList<>(List.of("J", "W")));
        blocks.add(new ArrayList<>(List.of("H", "U")));
        blocks.add(new ArrayList<>(List.of("V", "I")));
        blocks.add(new ArrayList<>(List.of("A", "N")));
        blocks.add(new ArrayList<>(List.of("E", "R")));
        blocks.add(new ArrayList<>(List.of("F", "S")));
        blocks.add(new ArrayList<>(List.of("L", "Y")));
        blocks.add(new ArrayList<>(List.of("P", "C")));
        blocks.add(new ArrayList<>(List.of("Z", "M")));
        return blocks;
    }

    private List<Block> getBlocks3() {
        List<Block> blocksBlock = new ArrayList<>();
        blocksBlock.add(new Block("B", "O"));
        blocksBlock.add(new Block("X", "K"));
        blocksBlock.add(new Block("D", "Q"));
        blocksBlock.add(new Block("C", "P"));
        blocksBlock.add(new Block("N", "A"));
        blocksBlock.add(new Block("G", "T"));
        blocksBlock.add(new Block("R", "E"));
        blocksBlock.add(new Block("T", "G"));
        blocksBlock.add(new Block("Q", "D"));
        blocksBlock.add(new Block("F", "S"));
        blocksBlock.add(new Block("J", "W"));
        blocksBlock.add(new Block("H", "U"));
        blocksBlock.add(new Block("V", "I"));
        blocksBlock.add(new Block("A", "N"));
        blocksBlock.add(new Block("E", "R"));
        blocksBlock.add(new Block("F", "S"));
        blocksBlock.add(new Block("L", "Y"));
        blocksBlock.add(new Block("P", "C"));
        blocksBlock.add(new Block("Z", "M"));
        return blocksBlock;
    }
}
