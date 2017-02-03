package MaximumFlowAlgorithm;

import java.util.LinkedList;

public class NodeEdmondsKarp {
	protected int nameOfNode;
	protected LinkedList<EdgeEdmondKarp> edges;

	public NodeEdmondsKarp(int nameOfNode) {
		this.nameOfNode = nameOfNode;
		edges = new LinkedList<EdgeEdmondKarp>();
	}

	public void addEdge(EdgeEdmondKarp edge) {
		this.edges.add(edge);
	}

	public LinkedList<EdgeEdmondKarp> getEdges() {
		return edges;
	}

	public void setEdges(LinkedList<EdgeEdmondKarp> edges) {
		this.edges = edges;
	}

	public int getNameOfNode() {
		return this.nameOfNode;
	}

	public void removeEdge(EdgeEdmondKarp edgeToRemove) {
		this.edges.remove(edgeToRemove);
	}
}
