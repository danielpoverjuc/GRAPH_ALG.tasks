package com.rekka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    // clasa pentru muchie
    static class Edge implements Comparable<Edge> {
        int u, v, cost;

        Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return this.cost - other.cost; // sortare crescatoare dupa cost
        }
    }

    // structura union-find pentru detectarea ciclurilor
    static class UnionFind {
        int parent[];
        int rank[];

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]); // compresia drumului
            return parent[x];
        }

        void unite(int a, int b) {
            a = find(a);
            b = find(b);

            if (a == b) return; // deja sunt in aceeasi componenta

            if (rank[a] < rank[b]) parent[a] = b;
            else if (rank[a] > rank[b]) parent[b] = a;
            else {
                parent[b] = a;
                rank[a]++;
            }
        }
    }

    // metoda principala pentru kruskal
    public static int kruskal(int n, List<Edge> edges) {

        /*
            algoritmul lui kruskal:
            1. sortam muchiile crescator dupa cost
            2. parcurgem muchiile una cate una
            3. adaugam muchia daca nu formeaza ciclu (union-find)
            4. continuam pana avem n - 1 muchii
        */

        Collections.sort(edges);

        UnionFind uf = new UnionFind(n);
        int mstCost = 0;
        int usedEdges = 0;

        for (Edge e : edges) {

            // verificam daca muchia formeaza ciclu
            if (uf.find(e.u) != uf.find(e.v)) {
                uf.unite(e.u, e.v);
                mstCost += e.cost;
                usedEdges++;

                System.out.println("adaug muchia (" + e.u + ", " + e.v + ") cost=" + e.cost);
            }

            if (usedEdges == n - 1) break; // am terminat
        }

        return mstCost;
    }

    public static void main(String[] args) {

        int n = 7; // numar varfuri

        List<Edge> edges = new ArrayList<>();

        // exemplu muchii (trebuie completate dupa graful tau)
        edges.add(new Edge(1, 6, 3));
        edges.add(new Edge(6, 7, 9));
        edges.add(new Edge(7, 2, 5));
        edges.add(new Edge(2, 5, 10));
        edges.add(new Edge(6, 4, 20));
        edges.add(new Edge(4, 3, 18));

        int cost = kruskal(n + 1, edges); // +1 daca folosesti varfuri 1..n
        System.out.println("cost total arbore partial minim (kruskal) = " + cost);
    }
}