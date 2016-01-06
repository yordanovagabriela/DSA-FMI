import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KnapsackBBSolver {

	private List<Treasure> treasures;
	private Queue<Treasure> queue;
	private int[] v;
	private int[] w;
	private int optimalSolution;
	private int capacity;
	private int numberOfItems;

	public KnapsackBBSolver(int capacity,String file) {
		this.treasures = (new TreasuresGenerator(file)).getTreasures();
		this.v = treasures.stream().mapToInt(Treasure::getValue).toArray();
		this.w = treasures.stream().mapToInt(Treasure::getWeight).toArray();
		this.optimalSolution = 0;
		this.capacity = capacity;
		this.numberOfItems = this.treasures.size();
		queue = new LinkedList<>();
	}

	public void solve() {
		knapsackBB();
		System.out.printf("The optimal solution is %s.", optimalSolution);
	}
	
	public void knapsackBB() {

		List<String> lst = new ArrayList<>();
		Treasure p = new Treasure(null, 0, 0, 0);
		Treasure q;
		queue.add(p);

		while (!queue.isEmpty()) {
			Treasure curr = queue.poll();

			int level = curr.getLevel() + 1;
			int weight = curr.getWeight() + w[level - 1];
			int value = curr.getValue() + v[level - 1];
			String name = treasures.get(level - 1).getName();

			q = new Treasure(name, value, weight, level);

			if (q.getWeight() <= capacity && q.getValue() > optimalSolution) {
				optimalSolution = q.getValue();
			}

			if (bound(q) > optimalSolution) {
				queue.add(new Treasure(q));
			}
			
			q.setWeight(curr.getWeight());
			q.setValue(curr.getValue());
			
			if (bound(q) > optimalSolution) {
				queue.add(new Treasure(q));
			}

		}
		 //System.out.println(lst);
	}

	public float bound(Treasure q) {
		int j, k;
		int totalWeight;
		float result;

		if (q.getWeight() >= capacity) {
			return 0;
		} else {
			result = q.getValue();
			j = q.getLevel() + 1;
			totalWeight = q.getWeight();
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
