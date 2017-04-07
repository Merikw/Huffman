/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Merik
 */
public class HuffNode implements Comparator<HuffNode> {

    public Character character;
    public int freq;
    public HuffNode left;
    public HuffNode right;

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public HuffNode getLeft() {
        return left;
    }

    public void setLeft(HuffNode left) {
        this.left = left;
    }

    public HuffNode getRight() {
        return right;
    }

    public void setRight(HuffNode right) {
        this.right = right;
    }

    public HuffNode() {

    }

    public HuffNode(Character character, int freq) {
        this.character = character;
        this.freq = freq;
    }

    public HuffNode(Character character, int freq, HuffNode left, HuffNode right) {
        this.character = character;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public HuffNode getRoot(PriorityQueue<HuffNode> uncompressedQueue) {
        while (uncompressedQueue.size() > 1) {
            HuffNode node1 = uncompressedQueue.poll();
            HuffNode node2 = uncompressedQueue.poll();
            HuffNode newNode = new HuffNode('\0', node1.getFreq() + node2.getFreq(), node1, node2);
            uncompressedQueue.add(newNode);
        }
        return uncompressedQueue.remove();
    }

    public LinkedHashMap<Character, String> build(HuffNode root) {
        LinkedHashMap<Character, String> chars = new LinkedHashMap<>();
        buildTree(root, "", chars);
        return chars;
    }

    public void buildTree(HuffNode root, String number, LinkedHashMap<Character, String> decripted) {
        if (root.left == null && root.right == null) {
            decripted.put(root.getCharacter(), number);
            return;
        }
        buildTree(root.getLeft(), number + "1", decripted);
        buildTree(root.getRight(), number + "0", decripted);
    }

    @Override
    public int compare(HuffNode o1, HuffNode o2) {
        return Integer.compare(o1.getFreq(), o2.getFreq());
    }
}
