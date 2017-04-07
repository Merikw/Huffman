/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Models.CollectionCalculater;
import java.util.Random;
import java.util.TreeSet;
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
public class WordApplicationTest {

    private CollectionCalculater cc;
    private String words;
    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Heb je dan geen hoedje meer\n"
            + "Maak er één van bordpapier\n"
            + "Eén, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "En als het hoedje dan niet past\n"
            + "Zetten we 't in de glazenkas\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier";
    
    public WordApplicationTest() {
        cc = new CollectionCalculater();
        Random random = new Random();
        StringBuilder allWords = new StringBuilder(1000);
        for (int j = 0; j < 1000; j++) {
            StringBuilder word = new StringBuilder(10);
            for (int i = 0; i < 10; i++) {
                word.append((char) ('a' + random.nextInt(26)));
            }
            allWords.append(word).append(" ");
        }
        words = allWords.toString();
    }

    @Test
    public void testAmount() {
        String expected = "Aantal woorden: " + Integer.toString(1000) + " Aantal verschillende woorden: " + Integer.toString(1000);
        String result = cc.calculateAmount(words);
        assertEquals(expected, result);
    }

    @Test
    public void testConcordance() {
        String expected = "{'t=[17], als=[16], bordpapier=[7], dan=[6, 16], de=[17], drie=[1, 3, 8, 11, 13, 18], Een=[1, 3, 11, 13, 18], En=[16], er=[7], Eén=[8], geen=[6], glazenkas=[17], Heb=[6], het=[16], Hoedje=[2, 4, 6, 9, 12, 14, 16, 19], in=[17], je=[6], Maak=[7], meer=[6], niet=[16], papier=[4, 9, 14, 19], past=[16], twee=[1, 3, 8, 11, 13, 18], van=[2, 4, 7, 9, 12, 14, 19], vier=[1, 3, 8, 11, 13, 18], we=[17], Zetten=[17], één=[7]}";
        String result = cc.calculateConcordance(DEFAULT_TEXT);
        assertEquals(expected, result);
    }

    @Test
    public void testFrequency() {
        String expected = "{glazenkas=1, de=1, En=1, één=1, we=1, het=1, niet=1, Heb=1, in=1, past=1, als=1, Eén=1, er=1, Zetten=1, 't=1, meer=1, geen=1, Maak=1, je=1, bordpapier=1, dan=2, hoedje=4, papier=4, Een=5, drie=6, twee=6, vier=6, Hoedje=6, van=9}";
        String result = cc.calculateFrequency(DEFAULT_TEXT);
        assertEquals(expected, result);
    }

    @Test
    public void testSorter() {
        String expected = "[één, Zetten, we, vier, van, twee, past, papier, niet, meer, Maak, je, in, Hoedje, het, Heb, glazenkas, geen, Eén, er, En, Een, drie, de, dan, bordpapier, als, 't]";
        String result = cc.calculateSorter(DEFAULT_TEXT);
        assertEquals(expected, result);
    }
}
