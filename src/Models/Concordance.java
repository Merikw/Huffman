/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Merik
 */
public class Concordance implements ICalculate {

    private TreeMap<String, TreeSet<Integer>> words;

    public Concordance() {
        words = new TreeMap<>();
    }

    @Override
    public void split(String s) {
        String[] sentences = s.split("\\r?\\n");
        for (int i = 0; i < sentences.length; i++) {
            if (sentences[i].isEmpty()) {
                continue;
            }
            String[] splitWords = sentences[i].replaceAll(",|\\.", " ").split("\\s+|\r\n");
            for (int x = 0; x < splitWords.length; x++) {
                if (!words.containsKey(splitWords[x])) {
                    TreeSet ts = new TreeSet<>();
                    ts.add(i + 1);
                    words.put(splitWords[x], ts);
                } else {
                    TreeSet ts = words.get(splitWords[x]);
                    ts.add(i + 1);
                    words.put(splitWords[x], ts);
                }
            }
            System.out.println("Hee hoi " + Integer.toString(i));
        }
    }

    @Override
    public String calculate(String s) {
        split(s);
        return words.toString();
    }
}
