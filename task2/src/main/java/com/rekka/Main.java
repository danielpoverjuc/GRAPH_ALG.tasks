package com.rekka;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    /*
        algoritm de aproximare pentru multimea independenta maxima (mim)
        idee:
        - prioritizam varfurile cu grad mic
        - selectam un varf, il adaugam in multimea independenta
        - eliminam varful si toti vecinii sai pentru a pastra independenta
    */

    public static Set<Integer> greedyMIM(List<List<Integer>> graph) {

        // 1. initializam multimea independenta s vida
        Set<Integer> S = new HashSet<>();

        // set care contine toate varfurile ramase in graf
        Set<Integer> remaining = new HashSet<>();
        for (int i = 0; i < graph.size(); i++) {
            remaining.add(i);
        }

        // cat timp exista varfuri ramase in graf
        while (!remaining.isEmpty()) {

            // 2. alegem varful v cu grad minim
            int v = chooseMinDegreeVertex(graph, remaining);

            // 3. adaugam varful v in multimea independenta s
            S.add(v);

            // 4. determinam vecinii lui v (deja in graf)

            // 5. eliminam varful v si toti vecinii sai din graf
            removeVertexAndNeighbors(v, graph, remaining);
        }

        // 6. returnam s ca aproximare mim
        return S;
    }

    // functie care alege varful cu grad minim din setul de varfuri ramase
    private static int chooseMinDegreeVertex(List<List<Integer>> graph, Set<Integer> remaining) {
        int best = -1;
        int bestDegree = Integer.MAX_VALUE;

        for (int v : remaining) {

            // calculam gradul efectiv doar pentru vecinii care inca exista in graf
            int degree = 0;
            for (int neighbor : graph.get(v)) {
                if (remaining.contains(neighbor)) {
                    degree++;
                }
            }

            // alegem varful cu gradul cel mai mic
            if (degree < bestDegree) {
                bestDegree = degree;
                best = v;
            }
        }

        return best;
    }

    // functie care elimina un varf si vecinii sai din setul remaining
    private static void removeVertexAndNeighbors(int v, List<List<Integer>> graph, Set<Integer> remaining) {
        remaining.remove(v);
        for (int neighbor : graph.get(v)) {
            remaining.remove(neighbor);
        }
    }

    // functie de utilitate pentru a crea muchii intr-un graf neorientat
    private static void addEdge(List<List<Integer>> graph, int a, int b) {
        graph.get(a).add(b);
        graph.get(b).add(a);
    }

    // exemplu de utilizare
    public static void main(String[] args) {
        // exemplu de graf cu 7 noduri
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            graph.add(new ArrayList<>());
        }

        // adaugam muchii (graf neorientat)
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 4);
        addEdge(graph, 4, 5);
        addEdge(graph, 5, 6);

        // aplicam algoritmul greedy mim
        Set<Integer> result = greedyMIM(graph);

        System.out.println("multime independenta aproximata (mim greedy): " + result);
    }
}