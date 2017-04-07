/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Merik
 */
public class TextHelperClass {

    public TextHelperClass() {

    }

    /**
     * Methode om de ingevoerde tekst naar N aantal woorden te brengen.
     * @param text de ingevoerde tekst.
     * @return de tekst met ongeveer N aantal woorden.
     * 
     * TIJDSCOMPLEXITEIT: O(N).
     */
    public String textToNAmountOfWords(String text, int N) {
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ((N / words.length) + 1); i++) {
            result.append(text);
        }

        return result.toString();
    }
}
