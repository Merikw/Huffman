/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Merik
 */
public class Comparer {

    public Comparer() {

    }

    //Source: http://www.programcreek.com/2013/03/java-sort-map-by-value/
    //This methods recevies a hashmap. From the hashmap, it makes a linkedlist so you can go back and for in the list. The collections.sort method
    //implements the compare method and you compare the linkedlist on the values of the next item in the list. In the second part, this method creates a linkedhashmap from
    //the linked list. 
    public <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, (Map.Entry<K, V> e1, Map.Entry<K, V> e2) -> (e1.getValue()).compareTo(e2.getValue()));
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
