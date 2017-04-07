/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Merik
 */
public class FileHelperClass {

    public FileHelperClass() {

    }

    /**
     * Het maken van de bit set om deze op te slaan in een bestand om het bestand op deze wijze kleiner te maken. Een '0' in de compressed tekst
     * is in dit geval false en de 1 is in dit geval true. 
     * @param text de gecompressed tekst van nullen en éénen.
     * @return De correcte bitset vna de tekst
     * 
     * TIJDSCOMPLEXITEIT: O(N).
     */
    public BitSet createBitSet(String text) {
        BitSet bitset = new BitSet(text.length());
        int i = 0;
        for (i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '0') {
                bitset.set(i, false);
            } else {
                bitset.set(i, true);
            }
        }
        return bitset;
    }

    /**
     * Het genereren en opslaan van een file in de project map waar het .JAR bestand opgeslagen is. Deze schrijft de compressed text van nullen en éénen en de complete boom
     * naar een bestand.
     * @param chars de linkedhashmap van de karakters en de nullen en éénen die daarbij horen bijv. A = 010.
     * @param compromisedString de compressed tekst van nullen en éénen. 
     * @param text de daadwerkelijke tekst.
     * 
     * TIJDSCOMPLEXITEIT: O(N).
     */
    public void generateFile(LinkedHashMap<Character, String> chars, String compromisedString, String text) {
        BitSet bits = createBitSet(compromisedString);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("huffmanTree.txt"))) {
            oos.writeObject(bits.toByteArray());

            StringBuilder charsString = new StringBuilder();
            TreeSet<Character> charSet = new TreeSet<>();
            for (int i = 0; i < text.length(); i++) {
                charSet.add(text.charAt(i));
            }
            charSet.stream().forEach((Character key) -> {
                charsString.append(key).append("\r\n").append(chars.get(key)).append("\r\n");
                try {
                    oos.writeObject(key.toString());
                    oos.writeObject(chars.get(key));
                } catch (IOException ex) {
                    Logger.getLogger(FileHelperClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            oos.writeObject("stop");
            oos.close();
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(FileHelperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Het inladen van een bestand die de tekst terug zet van een compressed tekst van nullen en éénen en de boom naar een leesbare tekst. 
     * @return De string wat de leesbare tekst is.
     * 
     * TIJDSCOMPLEXITEIT: O(N).
     */
    public String loadFile() {
        StringBuilder result = new StringBuilder();
        String everything = "";
        BitSet bitset = null;
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("huffmanTree.txt"))) {
            StringBuilder sb = new StringBuilder();
            byte[] bitsetArray = (byte[]) ois.readObject();
            bitset = BitSet.valueOf(bitsetArray);

            while (true) {
                String objectString = (String) ois.readObject();
                if (!"stop".equals(objectString)) {
                    sb.append("\r\n").append(objectString);
                    everything = sb.toString();
                } else{
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FileHelperClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileHelperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] parts = everything.split("\r\n");
        HashMap<String, String> chars = new HashMap<>();
        
        StringBuilder coded = new StringBuilder();

        for (int i = 1; i < parts.length - 1; i = i + 2) {
            chars.put(parts[i + 1], parts[i]);
        }

        StringBuilder found = new StringBuilder();
        for (int i = 0; i < bitset.length() + 1; i++) {
            if(bitset.get(i) == false){
                found.append('0');
            }
            else{
                found.append('1');
            }
            String str = chars.get(found.toString());
            if(str != null){
                result.append(str);
                found = new StringBuilder();
            }
        }
        return result.toString();
    }
}
