import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KnapsackBBSolver extends KnapsackSolver {

	private List<Treasure> treasures;
	private List<Node> nodes;
	private int[] v;
	private int[] w;

	public KnapsackBBSolver(int capacity, String file) {
		this.treasures = (new TreasuresGenerator(file)).getTreasures();
		this.v = treasures.stream().mapToInt(Treasure::getValue).toArray();
		this.w = treasures.stream().mapToInt(Treasure::getWeight).toArray();
		this.optimalSolution = 0;
		this.capacity = capacity;
		this.numberOfItems = this.treasures.size();
	}

	public void solve() {
		knapsack();
		printTracker();
	}

	public void knapsack() {

		// nodes are sorted by their bound in descending order
		PriorityQueue<Node> queue = new PriorityQueue<>(new BoundComparator());
		nodes = new ArrayList<>();

		Node root = new Node();
		Node includeOrNot;

		root.computeBound(capacity, numberOfItems, v, w);

		queue.add(root);

		while (!queue.isEmpty()) {

			// remove node with best bound
			Node current = queue.poll();
			includeOrNot = new Node();

			// check if node is still promising
			if (current.getBound() > optimalSolution) {
				// set includeOrNot to the child that includes the next item
				includeOrNot.setL(current.getL() + 1);
				includeOrNot.setW(current.getW() + w[includeOrNot.getL() - 1]);
				includeOrNot.setV(current.getV() + v[includeOrNot.getL() - 1]);

				Treasure currentTreasure = treasures.get(includeOrNot.getL() - 1);

				if (includeOrNot.getW() <= capacity && includeOrNot.getV() > optimalSolution) {
					optimalSolution = includeOrNot.getV();
				}

				includeOrNot.computeBound(capacity, numberOfItems, v, w);

				if (includeOrNot.getBound() > optimalSolution) {
					includeOrNot.getTreasures().addAll(current.getTreasures());
					includeOrNot.getTreasures().add(currentTreasure);

					nodes.add(new Node(includeOrNot));
					queue.add(new Node(includeOrNot));
				}

				if (includeOrNot.getBound() == optimalSolution) {
					includeOrNot.getTreasures().addAll(current.getTreasures());
					includeOrNot.getTreasures().add(currentTreasure);
					nodes.add(new Node(includeOrNot));
				}

				// set includeOrNot to the child that does NOT include the next
				// item
				includeOrNot.setW(current.getW());
				includeOrNot.setV(current.getV());
				includeOrNot.computeBound(capacity, numberOfItems, v, w);

				if (includeOrNot.getBound() > optimalSolution) {
					includeOrNot.setTreasures(current.getTreasures());

					nodes.add(new Node(includeOrNot));
					queue.add(new Node(includeOrNot));
				}

				if (includeOrNot.getBound() == optimalSolution) {
					includeOrNot.setTreasures(current.getTreasures());
					nodes.add(new Node(includeOrNot));
				}

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

}
