/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Comparator;

/**
 *
 * @author Merik
 */
public class HuffNode implements Comparator<HuffNode> {

    public Character character;
    public int freq;
    public HuffNode left;
    public HuffNode right;

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

    @Override
    public int compare(HuffNode o1, HuffNode o2) {
        return Integer.compare(o1.getFreq(), o2.getFreq());
    }

}
