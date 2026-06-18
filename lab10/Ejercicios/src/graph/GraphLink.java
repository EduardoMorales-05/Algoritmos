package graph;

public class GraphLink<T> implements Graph<T> {
    private boolean directed;
    private structures.ArrayList<AdjList<T>> graph;

    public GraphLink() {
        this(false);
    }

    public GraphLink(boolean directed) {
        this.directed = directed;
        graph = new structures.ArrayList<AdjList<T>>();
    }

    public boolean isDirected() {
        return directed;
    }

    public boolean insertVertex(T vertex) {
        if (vertex == null || searchVertex(vertex)) {
            return false;
        }

        graph.add(new AdjList<T>(vertex));
        return true;
    }

    public boolean insertEdge(T origin, T destination) {
        return insertEdgeWeight(origin, destination, 1);
    }

    public boolean insertEdgeWeight(T origin, T destination, int weight) {
        if (origin == null || destination == null) {
            return false;
        }

        if (!searchVertex(origin)) {
            insertVertex(origin);
        }

        if (!searchVertex(destination)) {
            insertVertex(destination);
        }

        addOrUpdateEdge(origin, destination, weight);

        if (!directed) {
            addOrUpdateEdge(destination, origin, weight);
        }

        return true;
    }

    private void addOrUpdateEdge(T origin, T destination, int weight) {
        AdjList<T> originList = findAdjList(origin);

        for (int i = 0; i < originList.getEdges().size(); i++) {
            Edge<T> edge = originList.getEdges().get(i);

            if (equalsValue(edge.getDestination(), destination)) {
                edge.setWeight(weight);
                return;
            }
        }

        originList.getEdges().addLast(new Edge<T>(origin, destination, weight));
    }

    public boolean removeVertex(T vertex) {
        int index = findVertexIndex(vertex);

        if (index == -1) {
            return false;
        }

        graph.removeAt(index);

        for (int i = 0; i < graph.size(); i++) {
            removeEdgeFromList(graph.get(i), vertex);
        }

        return true;
    }

    public boolean removeEdge(T origin, T destination) {
        boolean removed = false;

        AdjList<T> originList = findAdjList(origin);

        if (originList != null) {
            removed = removeEdgeFromList(originList, destination);
        }

        if (!directed) {
            AdjList<T> destinationList = findAdjList(destination);

            if (destinationList != null) {
                removeEdgeFromList(destinationList, origin);
            }
        }

        return removed;
    }

    private boolean removeEdgeFromList(AdjList<T> list, T destination) {
        for (int i = 0; i < list.getEdges().size(); i++) {
            Edge<T> edge = list.getEdges().get(i);

            if (equalsValue(edge.getDestination(), destination)) {
                list.getEdges().remove(edge);
                return true;
            }
        }

        return false;
    }

    public boolean searchVertex(T vertex) {
        return findVertexIndex(vertex) != -1;
    }

    public boolean searchEdge(T origin, T destination) {
        AdjList<T> originList = findAdjList(origin);

        if (originList == null) {
            return false;
        }

        for (int i = 0; i < originList.getEdges().size(); i++) {
            Edge<T> edge = originList.getEdges().get(i);

            if (equalsValue(edge.getDestination(), destination)) {
                return true;
            }
        }

        return false;
    }

    public structures.ArrayList<T> adjacentVertices(T vertex) {
        structures.ArrayList<T> result = new structures.ArrayList<T>();
        AdjList<T> list = findAdjList(vertex);

        if (list == null) {
            return result;
        }

        for (int i = 0; i < list.getEdges().size(); i++) {
            result.add(list.getEdges().get(i).getDestination());
        }

        return result;
    }

    public int getWeight(T origin, T destination) {
        AdjList<T> originList = findAdjList(origin);

        if (originList == null) {
            return 999999999;
        }

        for (int i = 0; i < originList.getEdges().size(); i++) {
            Edge<T> edge = originList.getEdges().get(i);

            if (equalsValue(edge.getDestination(), destination)) {
                return edge.getWeight();
            }
        }

        return 999999999;
    }

    public int vertexCount() {
        return graph.size();
    }

    public int edgeCount() {
        int total = 0;

        for (int i = 0; i < graph.size(); i++) {
            total += graph.get(i).getEdges().size();
        }

        if (directed) {
            return total;
        }

        return total / 2;
    }

    public int undirectedEdgeCount() {
        int total = 0;

        for (int i = 0; i < graph.size(); i++) {
            for (int j = i + 1; j < graph.size(); j++) {
                if (hasUndirectedEdge(graph.get(i).getVertex(), graph.get(j).getVertex())) {
                    total++;
                }
            }
        }

        return total;
    }

    public structures.ArrayList<T> vertices() {
        structures.ArrayList<T> result = new structures.ArrayList<T>();

        for (int i = 0; i < graph.size(); i++) {
            result.add(graph.get(i).getVertex());
        }

        return result;
    }

    public structures.ArrayList<Edge<T>> edges() {
        structures.ArrayList<Edge<T>> result = new structures.ArrayList<Edge<T>>();

        for (int i = 0; i < graph.size(); i++) {
            T origin = graph.get(i).getVertex();

            for (int j = 0; j < graph.get(i).getEdges().size(); j++) {
                Edge<T> edge = graph.get(i).getEdges().get(j);

                if (directed || findVertexIndex(origin) <= findVertexIndex(edge.getDestination())) {
                    result.add(edge);
                }
            }
        }

        return result;
    }

    public boolean isConexo() {
        if (graph.size() == 0) {
            return true;
        }

        boolean[] visited = new boolean[graph.size()];
        structures.Queue<Integer> queue = new structures.Queue<Integer>();

        visited[0] = true;
        queue.enqueue(0);

        while (!queue.isEmpty()) {
            int currentIndex = queue.dequeue();
            T currentVertex = graph.get(currentIndex).getVertex();

            for (int i = 0; i < graph.size(); i++) {
                if (!visited[i]) {
                    T possibleNeighbor = graph.get(i).getVertex();

                    if (hasUndirectedEdge(currentVertex, possibleNeighbor)) {
                        visited[i] = true;
                        queue.enqueue(i);
                    }
                }
            }
        }

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        return true;
    }

    public structures.ArrayList<T> shortPath(T start, T end) {
        structures.ArrayList<T> path = new structures.ArrayList<T>();

        int startIndex = findVertexIndex(start);
        int endIndex = findVertexIndex(end);

        if (startIndex == -1 || endIndex == -1) {
            return path;
        }

        DijkstraData data = executeDijkstra(startIndex);

        if (data.distance[endIndex] == 999999999) {
            return path;
        }

        structures.Stack<T> temp = new structures.Stack<T>();
        int current = endIndex;

        while (current != -1) {
            temp.push(graph.get(current).getVertex());
            current = data.previous[current];
        }

        while (!temp.isEmpty()) {
            path.add(temp.pop());
        }

        return path;
    }

    public structures.Stack<T> Dijsktra(T start, T end) {
        structures.Stack<T> stack = new structures.Stack<T>();

        int startIndex = findVertexIndex(start);
        int endIndex = findVertexIndex(end);

        if (startIndex == -1 || endIndex == -1) {
            return stack;
        }

        DijkstraData data = executeDijkstra(startIndex);

        if (data.distance[endIndex] == 999999999) {
            return stack;
        }

        int current = endIndex;

        while (current != -1) {
            stack.push(graph.get(current).getVertex());
            current = data.previous[current];
        }

        return stack;
    }

    public int shortPathCost(T start, T end) {
        int startIndex = findVertexIndex(start);
        int endIndex = findVertexIndex(end);

        if (startIndex == -1 || endIndex == -1) {
            return 999999999;
        }

        DijkstraData data = executeDijkstra(startIndex);
        return data.distance[endIndex];
    }

    private DijkstraData executeDijkstra(int startIndex) {
        int n = graph.size();
        DijkstraData data = new DijkstraData(n);
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            data.distance[i] = 999999999;
            data.previous[i] = -1;
            visited[i] = false;
        }

        data.distance[startIndex] = 0;

        structures.PriorityQueue<Integer> pq = new structures.PriorityQueue<Integer>();
        pq.add(startIndex, 0);

        while (!pq.isEmpty()) {
            structures.PriorityNode<Integer> node = pq.poll();
            int currentIndex = node.getData();

            if (visited[currentIndex]) {
                continue;
            }

            visited[currentIndex] = true;

            AdjList<T> currentList = graph.get(currentIndex);

            for (int i = 0; i < currentList.getEdges().size(); i++) {
                Edge<T> edge = currentList.getEdges().get(i);
                int neighborIndex = findVertexIndex(edge.getDestination());

                if (neighborIndex == -1) {
                    continue;
                }

                int newDistance = data.distance[currentIndex] + edge.getWeight();

                if (newDistance < data.distance[neighborIndex]) {
                    data.distance[neighborIndex] = newDistance;
                    data.previous[neighborIndex] = currentIndex;
                    pq.add(neighborIndex, newDistance);
                }
            }
        }

        return data;
    }

    private class DijkstraData {
        int[] distance;
        int[] previous;

        DijkstraData(int size) {
            distance = new int[size];
            previous = new int[size];
        }
    }

    public boolean hasUndirectedEdge(T a, T b) {
        return searchEdge(a, b) || searchEdge(b, a);
    }

    public int degreeUndirected(T vertex) {
        int degree = 0;

        for (int i = 0; i < graph.size(); i++) {
            T other = graph.get(i).getVertex();

            if (!equalsValue(vertex, other) && hasUndirectedEdge(vertex, other)) {
                degree++;
            }
        }

        return degree;
    }

    public int outDegree(T vertex) {
        AdjList<T> list = findAdjList(vertex);

        if (list == null) {
            return 0;
        }

        return list.getEdges().size();
    }

    public int inDegree(T vertex) {
        int count = 0;

        for (int i = 0; i < graph.size(); i++) {
            if (searchEdge(graph.get(i).getVertex(), vertex)) {
                count++;
            }
        }

        return count;
    }

    private AdjList<T> findAdjList(T vertex) {
        int index = findVertexIndex(vertex);

        if (index == -1) {
            return null;
        }

        return graph.get(index);
    }

    private int findVertexIndex(T vertex) {
        for (int i = 0; i < graph.size(); i++) {
            if (equalsValue(graph.get(i).getVertex(), vertex)) {
                return i;
            }
        }

        return -1;
    }

    private boolean equalsValue(T a, T b) {
        if (a == null && b == null) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        return a.equals(b);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < graph.size(); i++) {
            sb.append(graph.get(i).getVertex()).append(" -> ");

            for (int j = 0; j < graph.get(i).getEdges().size(); j++) {
                Edge<T> edge = graph.get(i).getEdges().get(j);
                sb.append(edge.getDestination())
                        .append("(")
                        .append(edge.getWeight())
                        .append(") ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
