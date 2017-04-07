/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.LinkedHashMap;

/**
 *
 * @author Merik
 */
public class HuffmanHelper {

    private Frequency frequencyClass;
    private HuffmanCalculator huffmanCalculatorClass;
    private TextHelperClass textHelperClass;
    private LinkedHashMap<Character, String> charsValueCombi;
    private FileHelperClass fileHelperClass;
    private String compromisedString;
    private TimeStamp ts;

    public HuffmanHelper() {
        this.frequencyClass = new Frequency();
        this.huffmanCalculatorClass = new HuffmanCalculator();
        this.textHelperClass = new TextHelperClass();
        this.fileHelperClass = new FileHelperClass();
        this.ts = new TimeStamp();
    }

    public String getCompleteTree10k(String text){
        return getCompleteTree(this.textHelperClass.textToNAmountOfWords(text, 10000));
    }
    
    public String getCompleteTree1m(String text){
        return getCompleteTree(this.textHelperClass.textToNAmountOfWords(text, 1000000));
    }
    
    public String getCompleteTree(String text){
       ts = new TimeStamp();
       this.frequencyClass.setText(text);
       ts.setBegin();
       this.charsValueCombi = this.huffmanCalculatorClass.getComrpomisedData(this.frequencyClass.getHuffNodes());
       this.compromisedString = this.huffmanCalculatorClass.compromise(charsValueCombi, text);
       ts.setEnd();
       System.out.println("Getting the complete tree took: " + ts.toString());
       return this.compromisedString;
    }
    
    public void generateFile(){
        TimeStamp ts = new TimeStamp();
        ts.setBegin();
        this.fileHelperClass.generateFile(charsValueCombi, compromisedString, this.frequencyClass.getText());
        ts.setEnd();
        System.out.println("Creating and saving of the file took: " + ts.toString());
    }
    
    public String readFile(){
        this.ts = new TimeStamp();
        ts.setBegin();
        String result = this.fileHelperClass.loadFile();
        ts.setEnd();
        System.out.println("Reading the file took: " + ts.toString());
        return result;
    }
}
