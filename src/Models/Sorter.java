/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Merik
 */
public class Sorter implements ICalculate {

    private TreeSet<String> words;

    public Sorter() {
        this.words = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
    }

    @Override
    public void split(String s) {
        String[] splitWords = s.replaceAll(",|\\.", " ").split("\\s+|\r\n");
        for (int i = 0; i < splitWords.length; i++) {
            words.add(splitWords[i]);
        }
    }

    @Override
    public String calculate(String s) {
        split(s);
        return words.descendingSet().toString();
    }

}
