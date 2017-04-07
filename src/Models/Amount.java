/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author Merik
 */
public class Amount implements ICalculate {

    private HashSet words;
    private int amount = 0;

    public Amount() {
        words = new HashSet();
    }

    @Override
    public void split(String s) {
        String[] splitWords = s.replaceAll(",|\\.", " ").split("\\s+|\r\n");
        amount = 0;
        words.removeAll(words);
        for (int i = 0; i < splitWords.length; i++) {
            words.add(splitWords[i]);
            amount = i;
        }
    }

    @Override
    public String calculate(String s) {
        split(s);
        return "Aantal woorden: " + Integer.toString(amount + 1) + " Aantal verschillende woorden: " + Integer.toString(words.size());
    }

}
