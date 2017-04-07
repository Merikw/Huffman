/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *
 * @author Merik
 */
public class Frequency implements ICalculate {

    private Map<String, Integer> words;
    private Comparer comparer;

    public Frequency() {
        words = new HashMap();
        comparer = new Comparer();
    }

    @Override
    public void split(String s) {
        String[] splitWords = s.replaceAll(",|\\.", " ").split("\\s+|\r\n");
        for (int i = 0; i < splitWords.length; i++) {
            if (!words.containsKey(splitWords[i])) {
                words.put(splitWords[i], 1);
            } else {
                words.put(splitWords[i], words.get(splitWords[i]) + 1);
            }
        }
    }

    @Override
    public String calculate(String s) {
        split(s);
        return comparer.sortByValue(words).toString();
    }
}
