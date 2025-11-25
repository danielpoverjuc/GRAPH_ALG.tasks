package com.rekka;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        // graf cu 10 noduri (0-9)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            graph.add(new ArrayList<>());
        }

        // adaugam muchii (graf neorientat)
        graph.get(5).add(2);
        graph.get(5).add(4);

        graph.get(2).add(5);
        graph.get(2).add(7);
        graph.get(2).add(1);

        graph.get(4).add(5);
        graph.get(4).add(3);

        graph.get(7).add(2);
        graph.get(7).add(6);

        graph.get(1).add(2);
        graph.get(1).add(8);

        graph.get(3).add(4);

        graph.get(6).add(7);
        graph.get(6).add(9);

        graph.get(8).add(1);

        graph.get(9).add(6);

        // rulam bfs din nodul 5
        bfs(5, graph);
    }

    // functie bfs
    public static void bfs(int start, List<List<Integer>> graph) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        // adaugam varful initial
        visited[start] = true;
        queue.add(start);

        System.out.print("ordine bfs: ");

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            // parcurgem vecinii
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}