import java.util.ArrayList;
import java.util.List;

public class Node {

	private List<Treasure> treasures;
	private int value;
	private int weight;
	private int level;
	private int bound;

	public Node(Node node) {
		this.treasures = node.treasures;
		this.value = node.value;
		this.weight = node.weight;
		this.level = node.level;
		this.bound = node.bound;
	}

	public Node() {
		treasures = new ArrayList<>();
	}

	public int getV() {
		return this.value;
	}

	public int getW() {
		return this.weight;
	}

	public int getL() {
		return this.level;
	}

	public List<Treasure> getTreasures() {
		return treasures;
	}

	public void setV(int v) {
		this.value = v;
	}

	public void setW(int w) {
		this.weight = w;
	}

	public void setTreasures(List<Treasure> t) {
		this.treasures = t;
	}

	public void setL(int l) {
		this.level = l;
	}

	public int getBound() {
		return this.bound;
	}

	public void computeBound(int capacity, int numberOfItems, int[] v, int[] w) {
		int j, k;
		int totalWeight;

		//it was >=
		if (this.getW() > capacity) {
			bound =  0;
		} else {
			bound = this.getV();
			j = this.getL() + 1;
			totalWeight = this.getW();
			while (j <= numberOfItems && totalWeight + w[j - 1] <= capacity) {
				totalWeight += w[j - 1];
				bound += v[j - 1];
				j++;
			}
			k = j;
			if (k <= numberOfItems) {
				bound += (capacity - totalWeight) * (v[k - 1] / w[k - 1]);
			}

		}
	}
	
	public String toString() {
		return "value: " + this.value + " weight: " + this.weight + " bound " + this.bound;
	}
}
