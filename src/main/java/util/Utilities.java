package util;

import java.text.Normalizer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author elialva
 */
public class Utilities {

    public static TreeMap<String, Integer> entries(String text) {
        //
        String[] split = StringUtils.split(text);
        TreeMap<String, Integer> map = new TreeMap();
        for (String string : split) {
            int frecuency = map.containsKey(string) ? map.get(string) + 1 : 1;
            map.put(string, frecuency);
        }
        return map;
    }

    public static String numegram(String word) {
        word = Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        word = StringUtils.replaceChars(word, "abcdefghijklmnopqrstuvwxyz", "22233344455566677778889999");
        return word;
    }

    public static Map<String, String> variations(String word) {
        //
        SeparaSilabas s = new SeparaSilabas(word);
        String[] split = StringUtils.split(s.silabear(), '-');
        List<String> hangers = Arrays.asList(split);
        List<List<Map.Entry<String, String>>> variationsAlignments = createVariantsAndAlignments(hangers);
        List<Map.Entry<String, String>> wordsX = assembleWords(variationsAlignments);
        Map map = new HashMap();
        for (Map.Entry<String, String> entry : wordsX) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    public static List<List<Map.Entry<String, String>>> createVariantsAndAlignments(List<String> hangers) {
        //
        List<List<Map.Entry<String, String>>> variants = new ArrayList<>();

        int i = 1;
        for (String hanger : hangers) {
            List tmpList = new ArrayList();
            String sequence = sequence(i, i + hanger.length());

            if (Cue.FONEMAS.containsKey(hanger)) {
                List<Map.Entry<String, String>> list = Cue.FONEMAS.get(hanger);
                for (Map.Entry<String, String> entry : list) {
                    String seqMod = offset(sequence, entry.getValue());
                    char[] toCharArray = seqMod.toCharArray();
                    String tmp = "";
                    for (char c : toCharArray) {
                        tmp = tmp + (int) c;
                    }
//                    tmpList.add(new SimpleEntry(entry.getKey(), seqMod));
                    tmpList.add(new AbstractMap.SimpleEntry(entry.getKey(), tmp));
                }
            }

            char[] toCharArray = sequence.toCharArray();
            String tmp = "";
            for (char c : toCharArray) {
                tmp = tmp + (int) c;
            }
            //tmpList.add(0, new SimpleEntry(hanger, sequence)); //Add the original word at first position
            tmpList.add(0, new AbstractMap.SimpleEntry(hanger, tmp)); //Add the original word at first position
            variants.add(tmpList);

            i = i + hanger.length();
        }

        return variants;
    }

    private static String sequence(int from, int to) {
        //
        String s = "";
        char[] seq = new char[to - from];
        int j = 0;
        for (int i = from; i < to; i++) {
            s = s + i;
            seq[j++] = (char) i;
        }

        return new String(seq);
    }

    /**
     *
     * @param subsequence the variant's sequence to process
     * @param matching
     * @return The normalized subsequence according to matching
     */
    private static String offset(String subsequence, String matching) {
        //
        String[] split = StringUtils.split(matching, ":");
        char[] charA = split[0].toCharArray();
        char[] charB = split[1].toCharArray();
        int j = 0;
        String tmp = "";
        for (int i = 0; i < charA.length; i++) {
            if (charA[i] != '_') {
                j++;
            }

            if (charB[i] == '_') {
                continue;
            }

            if (charA[i] == '_') {
                tmp = tmp + (char) 0;                    //Agrega huecos a la secuencia
            } else {
                tmp = tmp + subsequence.charAt(j - 1);   //Traslada la secuencia
            }
        }

        return tmp;
    }

    private static List<Map.Entry<String, String>> assembleWords(List<List<Map.Entry<String, String>>> segments) {
        //            
        List<Map.Entry<String, String>> resultList = new ArrayList();
        resultList.add(new AbstractMap.SimpleEntry("", ""));
        for (List<Map.Entry<String, String>> variants : segments) {
            List<Map.Entry<String, String>> tmp = new ArrayList();
            for (Map.Entry<String, String> orthoAlig : resultList) {
                for (Map.Entry<String, String> variant : variants) {
                    String orthogram = orthoAlig.getKey() + variant.getKey();
                    String alignment = orthoAlig.getValue() + variant.getValue();
                    tmp.add(new AbstractMap.SimpleEntry<>(orthogram, alignment));
                }
            }
            resultList = tmp;
        }
        return resultList;
    }

    public static void memory() {
        int mb = 1024 * 1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [MB] #####");

        //Print used memory
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        System.out.println("Free Memory:"
                + runtime.freeMemory() / mb);

        //Print total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);

        //Print Maximum available memory
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);
    }

    public static void main(String[] args) {
        Map<String, String> variations = variations("keso");
        System.out.println("Vops " + variations);
    }
}
