import java.util.List;

public class KnapsackDSolver extends KnapsackSolver {

	private List<Treasure> treasures;
	private int[][] dp;
	private int[][] tracker;

	public KnapsackDSolver(int capacity, String file) {
		this.treasures = (new TreasuresGenerator(file)).getTreasures();
		this.numberOfItems = this.treasures.size();
		this.dp = new int[numberOfItems + 1][capacity + 1];
		this.tracker = new int[numberOfItems + 1][capacity + 1];
		this.optimalSolution = 0;
		this.capacity = capacity;
	}

	public void solve() {
		knapsack();
		printTracker();
	}

	public void knapsack() {
		for (int i = 1; i <= numberOfItems; i++) {
			for (int j = 1; j <= capacity; j++) {
				Treasure curr = treasures.get(i - 1);

				if (curr.getWeight() > j) {
					// the solution for this subproblem will be the value
					// without the current item
					dp[i][j] = dp[i - 1][j];
					tracker[i][j] = -1;
				} else {
					// the solution for this subproblem will be the max value
					// between
					// the value of the current item + the value of the item
					// that we could afford with the remaining weight
					// and the value without the current item itself
					dp[i][j] = Math.max(dp[i - 1][j], curr.getValue() + dp[i - 1][j - curr.getWeight()]);

					if (curr.getValue() + dp[i - 1][j - curr.getWeight()] > dp[i - 1][j]) {
						tracker[i][j] = 1;
					} else {
						tracker[i][j] = -1;
					}
				}
			}
		}

		optimalSolution = dp[numberOfItems][capacity];
	}

	public void printTracker() {
		System.out.printf("The optimal solution is %s.\n", optimalSolution);
		System.out.println("The most valuable items I can take are:");

		int i = numberOfItems;
		int w = capacity;

		while (i > 0) {
			if (tracker[i][w] == 1) {
				System.out.println("+" + treasures.get(i - 1));
				i--;
				w -= treasures.get(i).getWeight();
			} else {
				i--;
			}
		}

	}
}
