package MaximumFlowAlgorithm;

public class EdgeEdmondKarp<U, V> {

	protected int sourceNode;
	protected int targetNode;
	protected V weight;
	protected U weightResidual;

	public EdgeEdmondKarp(int sourceNode, int targetNode, V weight, U weightResidual) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.weight = weight;
		this.weightResidual = weightResidual;
	}

	public U getWeightResidual() {
		return weightResidual;
	}

	public void setWeightResidual(U weightResidual) {
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

	public V getWeight() {
		return weight;
	}

	public void setWeight(V weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((weightResidual == null) ? 0 : weightResidual.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EdgeEdmondKarp other = (EdgeEdmondKarp) obj;
		if (weightResidual == null) {
			if (other.weightResidual != null)
				return false;
		} else if (!weightResidual.equals(other.weightResidual))
			return false;
		return true;
	}

}
