/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Merik
 */
public class Frequency {

    private String text;
    private PriorityQueue<HuffNode> huffNodes;

    public Frequency() {
        HuffNode hf = new HuffNode();
        this.huffNodes = new PriorityQueue<>(hf);
    }

    public Frequency(String text) {
        this.text = text;
        HuffNode hf = new HuffNode();
        this.huffNodes = new PriorityQueue<>(hf);
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public String getText(){
        return this.text;
    }

    /**
     * Genereer de priorityqueue voor de hashmap met de frequentie van de karakters.
     * @return de priority queue van de frequency van karakters.
     * 
     * TIJDSCOMPLEXITEIT: insertion van priority queue = O(1) en een for loop dus is het O(N).
     */
    public PriorityQueue<HuffNode> getHuffNodes() {
        getFrequencyOfChars().entrySet().stream().forEach((entry) -> {
            huffNodes.add(new HuffNode(entry.getKey(), entry.getValue()));
        });
        return huffNodes;
    }

    /**
     * Genereer een hashmap van de frequentie van alle karakters in de tekst. 
     * @return de hashmap van het karakter en hoe vaak deze voor komt in de tekst. 
     * 
     * TIJDSCOMPLETIXTEIT: O(N).
     */
    public HashMap<Character, Integer> getFrequencyOfChars() {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : text.toCharArray()) {
            Integer n = map.get(c);
            if (n == null) {
                n = 1;
            } else {
                n++;
            }
            map.put(c, n);
        }
        return map;
    }
}
