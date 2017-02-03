package MaximumFlowAlgorithm;

import java.util.LinkedList;

public class GraphEdmondsKarp {

	protected int numOfNodes;
	protected NodeEdmondsKarp[] nodes;

	public GraphEdmondsKarp(int numOfNodes) {
		this.nodes = new NodeEdmondsKarp[numOfNodes];
	}

	public void removeNode(NodeEdmondsKarp node) {
		for (EdgeEdmondKarp edge : node.getEdges()) {
			int targetNode = edge.getTargetNode();
			// TODO why implantation like that?
			this.removeEdge(new EdgeEdmondKarp(targetNode, node.getNameOfNode(), 0, 0), this.nodes[targetNode]);
		}
		node.setEdges(new LinkedList<EdgeEdmondKarp>());
	}

	private void removeEdge(EdgeEdmondKarp edge, NodeEdmondsKarp node) {
		node.removeEdge(edge);
	}

	public int getNumOfNodes() {
		return numOfNodes;
	}

	public void setNumOfNodes(int numOfNodes) {
		this.numOfNodes = numOfNodes;
	}

	public NodeEdmondsKarp[] getNodes() {
		return nodes;
	}

	public void setNodes(NodeEdmondsKarp[] nodes) {
		this.nodes = nodes;
	}

}
