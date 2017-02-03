package MaximumFlowAlgorithm;

import java.util.LinkedList;

public class NodeEdmondsKarp<U, V> {
	protected int nameOfNode;
	protected LinkedList<EdgeEdmondKarp<U, V>> edges;

	public NodeEdmondsKarp(int nameOfNode) {
		this.nameOfNode = nameOfNode;
		edges = new LinkedList<EdgeEdmondKarp<U, V>>();
	}

	public void addEdge(EdgeEdmondKarp<U, V> edge) {
		this.edges.add(edge);
	}

	public LinkedList<EdgeEdmondKarp<U, V>> getEdges() {
		return edges;
	}

	public void setEdges(LinkedList<EdgeEdmondKarp<U, V>> edges) {
		this.edges = edges;
	}

	public int getNameOfNode() {
		return this.nameOfNode;
	}

	public void removeEdge(EdgeEdmondKarp<U, V> edgeToRemove) {
		this.edges.remove(edgeToRemove);
	}
}
