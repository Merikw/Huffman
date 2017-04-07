/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Models.Frequency;
import Models.HuffNode;
import Models.HuffmanCalculator;
import Models.HuffmanHelper;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Merik
 */
public class HuffmanTest {

    HuffmanHelper hh = new HuffmanHelper();
    String text = "Hoi, Merik";
    String compressedText = "01000101101100110001100000011101";

    HashMap<Character, Integer> map = new HashMap<>();

    HuffNode hf = new HuffNode();
    PriorityQueue<HuffNode> huffNodes = new PriorityQueue<>(hf);

    Frequency freq = new Frequency();
    HuffmanCalculator calc = new HuffmanCalculator();

    LinkedHashMap<Character, String> charsLinkedHash = new LinkedHashMap<>();

    public void createMap() {
        map.put('H', 1);
        map.put('o', 1);
        map.put('i', 2);
        map.put(',', 1);
        map.put('M', 1);
        map.put('e', 1);
        map.put('k', 1);
        map.put(' ', 1);
        map.put('r', 1);
    }

    public void createPriorityQueue() {
        createMap();

        freq.setText(text);

        map.entrySet().stream().forEach((entry) -> {
            huffNodes.add(new HuffNode(entry.getKey(), entry.getValue()));
        });

    }

    public void createCompressedChars() {
        charsLinkedHash.put('i', "11");
        charsLinkedHash.put('k', "101");
        charsLinkedHash.put('e', "100");
        charsLinkedHash.put(',', "011");
        charsLinkedHash.put('H', "010");
        charsLinkedHash.put(' ', "0011");
        charsLinkedHash.put('o', "0010");
        charsLinkedHash.put('M', "0001");
        charsLinkedHash.put('r', "0000");
    }

    @Test
    public void testCompletetreeNormal() {
        String result = hh.getCompleteTree(text);
        assertEquals(compressedText, result);
    }

    @Test
    public void testFileSavingAndLoading() {
        hh.getCompleteTree(text);
        hh.generateFile();
        String result = hh.readFile();
        assertEquals(text, result);
    }

    @Test
    public void testCharFreq() {
        createMap();
        freq.setText(text);
        HashMap<Character, Integer> resultMap = freq.getFrequencyOfChars();
        assertEquals(map.get('i'), resultMap.get('i'));
        assertEquals(map.size(), resultMap.size());
    }

    @Test
    public void testCreationPriorityQueue() {
        this.createPriorityQueue();

        PriorityQueue<HuffNode> result = freq.getHuffNodes();

        assertEquals(huffNodes.poll().getCharacter(), result.poll().getCharacter());
        assertEquals(huffNodes.size(), result.size());
    }

    @Test
    public void testRootNode() {
        this.createPriorityQueue();

        HuffNode result = calc.buildTree(huffNodes);
        HuffNode root = new HuffNode();
        root.setFreq(10);

        assertEquals(root.getFreq(), result.getFreq());
    }

    @Test
    public void testCompressedByChar() {
        this.createPriorityQueue();
        LinkedHashMap<Character, String> result = calc.getComrpomisedData(huffNodes);
        this.createCompressedChars();

        assertEquals(charsLinkedHash.get('M'), result.get('M'));
        assertEquals(charsLinkedHash.get('i'), result.get('i'));
        assertEquals(charsLinkedHash.size(), result.size());
    }
    
    @Test
    public void testCompressedMethod(){
        this.createPriorityQueue();
        this.createCompressedChars();
        
        String result = calc.compromise(charsLinkedHash, text);
        
        assertEquals(compressedText, result);
    }
}
