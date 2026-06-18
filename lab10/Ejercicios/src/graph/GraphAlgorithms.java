package graph;

public class GraphAlgorithms {

    public static <T> boolean isConexo(GraphLink<T> graph) {
        return graph.isConexo();
    }

    public static <T> boolean isIsomorfo(GraphLink<T> g1, GraphLink<T> g2) {
        if (g1.vertexCount() != g2.vertexCount()) {
            return false;
        }

        if (g1.edgeCount() != g2.edgeCount()) {
            return false;
        }

        structures.ArrayList<T> v1 = g1.vertices();
        structures.ArrayList<T> v2 = g2.vertices();

        int n = v1.size();
        int[] mapping = new int[n];
        boolean[] used = new boolean[n];

        for (int i = 0; i < n; i++) {
            mapping[i] = -1;
            used[i] = false;
        }

        return backtrackIsomorphism(0, mapping, used, v1, v2, g1, g2);
    }

    private static <T> boolean backtrackIsomorphism(
            int index,
            int[] mapping,
            boolean[] used,
            structures.ArrayList<T> v1,
            structures.ArrayList<T> v2,
            GraphLink<T> g1,
            GraphLink<T> g2
    ) {
        if (index == v1.size()) {
            return true;
        }

        for (int candidate = 0; candidate < v2.size(); candidate++) {
            if (used[candidate]) {
                continue;
            }

            if (!sameDegree(v1.get(index), v2.get(candidate), g1, g2)) {
                continue;
            }

            if (compatible(index, candidate, mapping, v1, v2, g1, g2)) {
                mapping[index] = candidate;
                used[candidate] = true;

                if (backtrackIsomorphism(index + 1, mapping, used, v1, v2, g1, g2)) {
                    return true;
                }

                mapping[index] = -1;
                used[candidate] = false;
            }
        }

        return false;
    }

    private static <T> boolean sameDegree(T a, T b, GraphLink<T> g1, GraphLink<T> g2) {
        if (g1.isDirected() || g2.isDirected()) {
            return g1.inDegree(a) == g2.inDegree(b)
                    && g1.outDegree(a) == g2.outDegree(b);
        }

        return g1.degreeUndirected(a) == g2.degreeUndirected(b);
    }

    private static <T> boolean compatible(
            int index,
            int candidate,
            int[] mapping,
            structures.ArrayList<T> v1,
            structures.ArrayList<T> v2,
            GraphLink<T> g1,
            GraphLink<T> g2
    ) {
        T a1 = v1.get(index);
        T a2 = v2.get(candidate);

        for (int i = 0; i < index; i++) {
            if (mapping[i] == -1) {
                continue;
            }

            T b1 = v1.get(i);
            T b2 = v2.get(mapping[i]);

            if (g1.searchEdge(a1, b1) != g2.searchEdge(a2, b2)) {
                return false;
            }

            if (g1.searchEdge(b1, a1) != g2.searchEdge(b2, a2)) {
                return false;
            }
        }

        return true;
    }

    public static <T> boolean isPlanoBasico(GraphLink<T> graph) {
        int n = graph.vertexCount();
        int m = graph.undirectedEdgeCount();

        if (n <= 4) {
            return true;
        }

        if (m > (3 * n) - 6) {
            return false;
        }

        if (isBipartito(graph) && m > (2 * n) - 4) {
            return false;
        }

        if (containsK5(graph)) {
            return false;
        }

        if (containsK33(graph)) {
            return false;
        }

        return true;
    }

    private static <T> boolean isBipartito(GraphLink<T> graph) {
        structures.ArrayList<T> vertices = graph.vertices();
        int n = vertices.size();
        int[] color = new int[n];

        for (int i = 0; i < n; i++) {
            color[i] = -1;
        }

        for (int start = 0; start < n; start++) {
            if (color[start] != -1) {
                continue;
            }

            structures.Queue<Integer> queue = new structures.Queue<Integer>();
            color[start] = 0;
            queue.enqueue(start);

            while (!queue.isEmpty()) {
                int current = queue.dequeue();

                for (int i = 0; i < n; i++) {
                    if (current == i) {
                        continue;
                    }

                    if (!graph.hasUndirectedEdge(vertices.get(current), vertices.get(i))) {
                        continue;
                    }

                    if (color[i] == -1) {
                        color[i] = 1 - color[current];
                        queue.enqueue(i);
                    } else if (color[i] == color[current]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static <T> boolean containsK5(GraphLink<T> graph) {
        structures.ArrayList<T> v = graph.vertices();
        int n = v.size();

        for (int a = 0; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                for (int c = b + 1; c < n; c++) {
                    for (int d = c + 1; d < n; d++) {
                        for (int e = d + 1; e < n; e++) {
                            int[] set = {a, b, c, d, e};

                            if (isCompleteSubgraph(graph, v, set)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private static <T> boolean isCompleteSubgraph(
            GraphLink<T> graph,
            structures.ArrayList<T> v,
            int[] set
    ) {
        for (int i = 0; i < set.length; i++) {
            for (int j = i + 1; j < set.length; j++) {
                if (!graph.hasUndirectedEdge(v.get(set[i]), v.get(set[j]))) {
                    return false;
                }
            }
        }

        return true;
    }

    private static <T> boolean containsK33(GraphLink<T> graph) {
        structures.ArrayList<T> v = graph.vertices();
        int n = v.size();

        if (n < 6) {
            return false;
        }

        for (int a = 0; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                for (int c = b + 1; c < n; c++) {
                    for (int x = 0; x < n; x++) {
                        if (x == a || x == b || x == c) {
                            continue;
                        }

                        for (int y = x + 1; y < n; y++) {
                            if (y == a || y == b || y == c) {
                                continue;
                            }

                            for (int z = y + 1; z < n; z++) {
                                if (z == a || z == b || z == c) {
                                    continue;
                                }

                                int[] left = {a, b, c};
                                int[] right = {x, y, z};

                                if (isCompleteBipartite(graph, v, left, right)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private static <T> boolean isCompleteBipartite(
            GraphLink<T> graph,
            structures.ArrayList<T> v,
            int[] left,
            int[] right
    ) {
        for (int i = 0; i < left.length; i++) {
            for (int j = 0; j < right.length; j++) {
                if (!graph.hasUndirectedEdge(v.get(left[i]), v.get(right[j]))) {
                    return false;
                }
            }
        }

        return true;
    }

    public static <T> GraphLink<T> complemento(GraphLink<T> graph) {
        GraphLink<T> complement = new GraphLink<T>(graph.isDirected());
        structures.ArrayList<T> vertices = graph.vertices();

        for (int i = 0; i < vertices.size(); i++) {
            complement.insertVertex(vertices.get(i));
        }

        if (graph.isDirected()) {
            for (int i = 0; i < vertices.size(); i++) {
                for (int j = 0; j < vertices.size(); j++) {
                    if (i != j && !graph.searchEdge(vertices.get(i), vertices.get(j))) {
                        complement.insertEdge(vertices.get(i), vertices.get(j));
                    }
                }
            }
        } else {
            for (int i = 0; i < vertices.size(); i++) {
                for (int j = i + 1; j < vertices.size(); j++) {
                    if (!graph.hasUndirectedEdge(vertices.get(i), vertices.get(j))) {
                        complement.insertEdge(vertices.get(i), vertices.get(j));
                    }
                }
            }
        }

        return complement;
    }

    public static <T> boolean isAutoComplementario(GraphLink<T> graph) {
        return isIsomorfo(graph, complemento(graph));
    }
}
