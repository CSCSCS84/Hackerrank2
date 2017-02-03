package MaximumFlowAlgorithm;


public class EdmondsKarpAlgorithm<U, V> {
	private int maxFlow(GraphEdmondsKarp<U, V> graph, int source, int target) {
		return 0;

	}

	private void initialiseGraph(GraphEdmondsKarp<U, V> graph) {
		for (NodeEdmondsKarp<U, V> node : graph.getNodes()) {
			for (EdgeEdmondKarp<U, V> edge : node.getEdges()) {
				// edge.setWeightResidual(0);
			}
		}
	}
}
