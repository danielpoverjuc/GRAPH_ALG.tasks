package com.rekka;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    // functie care aplica algoritmul de colorare consecutiva (greedy)
    public static Map<Integer, Integer> greedyColoring(Map<Integer, List<Integer>> graph, List<Integer> order) {

        // map care retine culoarea fiecarui varf
        Map<Integer, Integer> color = new HashMap<>();

        // parcurgem varfurile in ordinea specificata
        for (int v : order) {

            // multime pentru culorile deja folosite de vecini
            Set<Integer> usedColors = new HashSet<>();

            // parcurgem vecinii si colectam culorile lor
            for (int neighbor : graph.get(v)) {
                if (color.containsKey(neighbor)) {
                    usedColors.add(color.get(neighbor));
                }
            }

            // gasim cea mai mica culoare pozitiva care nu este folosita de vecini
            int chosenColor = 1;
            while (usedColors.contains(chosenColor)) {
                chosenColor++;
            }

            // atribuim culoarea varfului v
            color.put(v, chosenColor);
        }

        return color;
    }

    public static void main(String[] args) {

        // definire graf prin liste de adiacenta (exemplu generic)
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // fiecare varf are lista lui de vecini
        graph.put(1, Arrays.asList(2, 5));
        graph.put(2, Arrays.asList(1, 3));
        graph.put(3, Arrays.asList(2, 4));
        graph.put(4, Arrays.asList(3, 8));
        graph.put(5, Arrays.asList(1, 6));
        graph.put(6, Arrays.asList(5, 7));
        graph.put(7, Arrays.asList(6, 8));
        graph.put(8, Arrays.asList(4, 7));
        graph.put(9, Arrays.asList(3, 10));
        graph.put(10, Arrays.asList(9, 11));
        graph.put(11, Arrays.asList(10, 12));
        graph.put(12, Arrays.asList(11));

        // ordinea varfurilor (conform exemplului tau)
        List<Integer> order = Arrays.asList(
                1,2,3,4,5,6,7,8,9,10,11,12
        );

        // aplicam algoritmul greedy de colorare
        Map<Integer, Integer> result = greedyColoring(graph, order);

        // afisam rezultatele
        for (int v : order) {
            System.out.println("v. " + v + " -> c. " + result.get(v));
        }
    }
}