/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Merik
 */
public class Frequency {

    private String s;
    private PriorityQueue<HuffNode> huffNodes;
    private HuffNode hf;

    public PriorityQueue<HuffNode> getHuffNodes() {
        return huffNodes;
    }

    public void setHuffNodes(PriorityQueue<HuffNode> huffNodes) {
        this.huffNodes = huffNodes;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Frequency(String s) {
        this.s = s;
        hf = new HuffNode();
        this.huffNodes = new PriorityQueue<>(hf);
    }

    public void calculate() {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            Integer n = map.get(c);
            if (n == null) {
                n = 1;
            } else {
                n++;
            }
            map.put(c, n);
        }

        map.entrySet().stream().forEach((entry) -> {
            huffNodes.add(new HuffNode(entry.getKey(), entry.getValue()));
        });
        HuffNode root = hf.getRoot(huffNodes);
        System.out.println(root.build(root).toString());
        String compressed = this.compromise(root.build(root));
        generateFile(root.build(root), this.compromise(root.build(root)));
        String binary = new BigInteger(s.getBytes()).toString();
        String binaryCompressed = new BigInteger(compressed.getBytes()).toString();
        System.out.println("Normal binary = " + binary + " this has a length of: " + binary.length() * 8 + " bits");
        System.out.println("Compressed binary = " + compressed + " this has a length of: " + compressed.length() + " bits");
        decodeHuffmanTree();
    }

    public String compromise(LinkedHashMap<Character, String> chars) {
        StringBuilder result = new StringBuilder();

        for (Character key : s.toCharArray()) {
            result.append(chars.get(key));
        }

        return result.toString();
    }

    public void generateFile(LinkedHashMap<Character, String> chars, String compromisedString) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            StringBuilder charsString = new StringBuilder();
            for (Character key : s.toCharArray()) {
                charsString.append(key).append(" ").append(chars.get(key)).append(",");
            }

            String content = compromisedString + "\r\n" + charsString.toString();

            fw = new FileWriter("C:\\Users\\Merik\\huffmanTree.txt");
            bw = new BufferedWriter(fw);
            bw.write(content);

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String decodeHuffmanTree() {
        String result = "";
        String everything = "";
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Merik\\huffmanTree.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Frequency.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Frequency.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] parts = everything.split(",|\r\n|\\s+");
        HashMap<String, String> chars = new HashMap<>();
        String coded = parts[0];

        for (int i = 1; i < parts.length - 1; i = i + 2) {
            chars.put(parts[i + 1], parts[i]);
        }
        
        String found = "";
        for(int i = 0; i < coded.length(); i++){
            found += coded.toCharArray()[i];
            if(chars.containsKey(found)){
                result += chars.get(found);
                found = "";
            }
        }
        
        return result;
    }
}
