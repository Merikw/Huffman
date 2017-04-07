/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Merik
 */
public class CollectionCalculater {
    
    private Amount amount;
    private Concordance concordance;
    private Frequency frequency;
    private Sorter sorter;
    
    public CollectionCalculater(){
        amount = new Amount();
        concordance = new Concordance();
        frequency = new Frequency();
        sorter = new Sorter();
    }
    
    public String calculateAmount(String s){
        return amount.calculate(s);
    }
    
    public String calculateConcordance(String s){
        return concordance.calculate(s);
    }
    
    public String calculateFrequency(String s){
        return frequency.calculate(s);
    }
    
    public String calculateSorter(String s){
        return sorter.calculate(s);
    }
    
}
