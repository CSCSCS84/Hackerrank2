package MaximumFlowAlgorithm;

public class EdmondsKarpAlgorithm {
	private int maxFlow(GraphEdmondsKarp graph, int source, int target) {
		return 0;

	}

	private void initiliazeResidual(GraphEdmondsKarp graph) {
		for (NodeEdmondsKarp node : graph.getNodes()) {
			for (EdgeEdmondKarp edge : node.getEdges()) {
				edge.setWeightResidual(0);
			}
		}
	}
}
