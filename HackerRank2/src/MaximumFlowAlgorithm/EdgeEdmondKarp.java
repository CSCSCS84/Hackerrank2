package MaximumFlowAlgorithm;

public class EdgeEdmondKarp {

	protected int sourceNode;
	protected int targetNode;
	protected int weight;
	protected int weightResidual;

	public EdgeEdmondKarp(int sourceNode, int targetNode, int weight, int weightResidual) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.weight = weight;
		this.weightResidual = weightResidual;
	}

	public int getWeightResidual() {
		return weightResidual;
	}

	public void setWeightResidual(int weightResidual) {
		this.weightResidual = weightResidual;
	}

	public int getSourceNode() {
		return sourceNode;
	}

	public void setSourceNode(int sourceNode) {
		this.sourceNode = sourceNode;
	}

	public int getTargetNode() {
		return targetNode;
	}

	public void setTargetNode(int targetNode) {
		this.targetNode = targetNode;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sourceNode;
		result = prime * result + targetNode;
		result = prime * result + weight;
		result = prime * result + weightResidual;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EdgeEdmondKarp other = (EdgeEdmondKarp) obj;
		if (sourceNode != other.sourceNode)
			return false;
		if (targetNode != other.targetNode)
			return false;
		if (weight != other.weight)
			return false;
		if (weightResidual != other.weightResidual)
			return false;
		return true;
	}

}
