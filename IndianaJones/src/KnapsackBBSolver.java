import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KnapsackBBSolver extends KnapsackSolver {

	private List<Treasure> treasures;
	private List<Node> nodes;
	private Queue<Node> queue;
	private int[] v;
	private int[] w;

	public KnapsackBBSolver(int capacity, String file) {
		this.treasures = (new TreasuresGenerator(file)).getTreasures();
		this.v = treasures.stream().mapToInt(Treasure::getValue).toArray();
		this.w = treasures.stream().mapToInt(Treasure::getWeight).toArray();
		this.optimalSolution = 0;
		this.capacity = capacity;
		this.numberOfItems = this.treasures.size();
		queue = new LinkedList<>();
	}

	public void solve() {
		knapsack();
		printTracker();
	}

	public void knapsack() {
		nodes = new ArrayList<>();
		Node initial = new Node();
		Node childOrNot;

		nodes.add(initial);
		queue.add(initial);

		while (!queue.isEmpty()) {
			Node current = queue.poll();

			childOrNot = new Node();
			childOrNot.setL(current.getL() + 1);
			childOrNot.setW(current.getW() + w[childOrNot.getL() - 1]);
			childOrNot.setV(current.getV() + v[childOrNot.getL() - 1]);

			Treasure currentTreasure = treasures.get(childOrNot.getL() - 1);

			if (childOrNot.getW() <= capacity && childOrNot.getV() > optimalSolution) {
				optimalSolution = childOrNot.getV();
			}

			if (bound(childOrNot) > optimalSolution) {
				childOrNot.getTreasures().addAll(current.getTreasures());
				childOrNot.getTreasures().add(currentTreasure);

				queue.add(new Node(childOrNot));
				nodes.add(new Node(childOrNot));
			}

			childOrNot.setW(current.getW());
			childOrNot.setV(current.getV());

			if (bound(childOrNot) > optimalSolution) {
				childOrNot.setTreasures(current.getTreasures());
				queue.add(new Node(childOrNot));
				nodes.add(new Node(childOrNot));
			}

		}
	}

	public void printTracker() {
		System.out.printf("The optimal solution is %s.\n", optimalSolution);
		System.out.println("The most valuable items I can take are:");
		for (Node n : nodes) {
			if (n.getV() == optimalSolution) {
				for (Treasure t : n.getTreasures()) {
					System.out.println("+" + t);
				}
				break;
			}
		}
	}
	
	public float bound(Node node) {
		int j, k;
		int totalWeight;
		float result;

		if (node.getW() >= capacity) {
			return 0;
		} else {
			result = node.getV();
			j = node.getL() + 1;
			totalWeight = node.getW();
			while (j <= numberOfItems && totalWeight + w[j - 1] <= capacity) {
				totalWeight += w[j - 1];
				result += v[j - 1];
				j++;
			}
			k = j;
			if (k <= numberOfItems) {
				result += (capacity - totalWeight) * (v[k - 1] / w[k - 1]);
			}
			return result;
		}
	}


}
