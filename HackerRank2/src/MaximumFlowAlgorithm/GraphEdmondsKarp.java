package MaximumFlowAlgorithm;

import java.util.LinkedList;

public class GraphEdmondsKarp<U, V> {

	protected int numOfNodes;
	protected NodeEdmondsKarp<U, V>[] nodes;

	public GraphEdmondsKarp(int numOfNodes) {
		this.nodes = new NodeEdmondsKarp[numOfNodes];
	}

	public void removeNode(NodeEdmondsKarp<U, V> node) {
		for (EdgeEdmondKarp<U, V> edge : node.getEdges()) {
			int targetNode = edge.getTargetNode();
			this.removeEdge(new EdgeEdmondKarp<U, V>(targetNode, node.getNameOfNode(), null, null),
					this.nodes[targetNode]);
		}
		node.setEdges(new LinkedList<EdgeEdmondKarp<U, V>>());
	}

	private void removeEdge(EdgeEdmondKarp<U, V> edge, NodeEdmondsKarp<U, V> node) {
		node.removeEdge(edge);
	}

}
