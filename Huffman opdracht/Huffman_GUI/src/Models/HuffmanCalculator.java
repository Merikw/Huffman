/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.LinkedHashMap;
import java.util.PriorityQueue;

/**
 *
 * @author Merik
 */
public class HuffmanCalculator {

    public HuffmanCalculator() {

    }

    /**
     * Methode die de gehele tree bouwt in 1 huffnode en deze returnt de root huffnode.
     * @param uncompressedQueue de priorityqueue met alle huffnodes erin. 
     * @return De root huffnode met de gehele boom.
     * 
     * TIJDSCOMPLEXITEIT: O(N).
     */
    public HuffNode buildTree(PriorityQueue<HuffNode> uncompressedQueue) {
        while (uncompressedQueue.size() > 1) {
            HuffNode node1 = uncompressedQueue.poll();
            HuffNode node2 = uncompressedQueue.poll();
            HuffNode newNode = new HuffNode('\0', node1.getFreq() + node2.getFreq(), node1, node2);
            uncompressedQueue.add(newNode);
        }
        return uncompressedQueue.remove();
    }

    /**
     * Zet de karakters naar de juiste nullen en éénen.
     * @param uncompressedQueue de priorityqueue van alle huffnodes. 
     * @return de linkedhashmap van de combinatie van de karakter en de compressed tekst. 
     * 
     * TIJDSCOMPLEXITEIT: O(N^2).
     */
    public LinkedHashMap<Character, String> getComrpomisedData(PriorityQueue<HuffNode> uncompressedQueue) {
        HuffNode root = buildTree(uncompressedQueue);
        LinkedHashMap<Character, String> chars = new LinkedHashMap<>();    
        buildCompromisedData(root, "", chars);
        return chars;
    }

    /**
     * Methode om de linkedhashmap te vullen met de karakters en de bijbehorende compressed tekst. 
     * @param root de root node. 
     * @param number het nummer (01010). 
     * @param decripted de linkedhashmap die gevult moet worden.
     * 
     * TIJDSCOMPLEXITEIT: O(N^2).
     */
    private void buildCompromisedData(HuffNode root, String number, LinkedHashMap<Character, String> decripted) {
        if (root.left == null && root.right == null) {
            decripted.put(root.getCharacter(), number);
            return;
        }
        buildCompromisedData(root.getLeft(), number + "1", decripted);
        buildCompromisedData(root.getRight(), number + "0", decripted);
    }

    /**
     * Methode die de volledige string genereerd van de karakters met de compressed nullen en ééntjes tekst. 
     * @param chars de linkedhashmap die dient als boom.
     * @param text de tekst die omgezet moet worden.
     * @return de volledige compressed string.
     * 
     * TIJDSCOMPLEXITEIT: O(N).
     */
    public String compromise(LinkedHashMap<Character, String> chars, String text) {
        StringBuilder result = new StringBuilder();
        for (Character key : text.toCharArray()) {
            result.append(chars.get(key));
        }
        return result.toString();
    }
}
