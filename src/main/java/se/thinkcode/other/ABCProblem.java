package se.thinkcode.other;

import java.util.List;

public class ABCProblem {
    public Boolean canMakeWord(String word, List<List<String>> blocks) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            String letter = word.charAt(i) + "";
            for (int j = 0; j < blocks.size(); j++) {
                List<String> box = blocks.get(j);
                if (box.contains(letter)) {
                    blocks.remove(j);
                    count++;
                    break;
                }
            }
        }
        return count == word.length();
    }

    public Boolean canMakeWordTwo(String word, List<List<String>> blocks) {
        for (int i = 0; i < word.length(); i++) {
            String letter = word.substring(i, i + 1);
            boolean found = false;
            for (int j = 0; j < blocks.size(); j++) {
                List<String> box = blocks.get(j);
                if (box.contains(letter)) {
                    blocks.remove(j);
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }

        }
        return true;
    }

    public Boolean canMakeWordThree(String word, List<Block> blocks) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            String letter = word.substring(i, i + 1);
            for (Block current : blocks) {
                if (!current.isUsed()) {
                    if (current.getA().equals(letter) || current.getB().equals(letter)) {
                        current.setUsed(true);
                        count++;
                        break;
                    }
                }
            }
        }
        return count == word.length();
    }
}
