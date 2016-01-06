import java.util.List;

public class KnapsackDSolver {
	
	private List<Treasure> treasures;
	private int[][] dp;
	private int[][] tracker;
	private int optimalSolution;
	private int capacity;
	private int numberOfItems;
	
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
			for (int j = 0; j <= capacity; j++) {
				Treasure curr = treasures.get(i - 1);
				if (curr.getWeight() > j) {
					dp[i][j] = dp[i - 1][j];
					tracker[i][j] = -1;
				} else {
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

	private void printTracker() {
		System.out.printf("The optimal solution is %s.\n", optimalSolution);
		System.out.println("The most valuable items I can take are:");

		while (numberOfItems > 0) {
			if (tracker[numberOfItems][capacity] == 1) {
				System.out.println("+" + treasures.get(numberOfItems - 1));
				numberOfItems--; 
				capacity -= treasures.get(numberOfItems).getWeight();
			} else {
				numberOfItems--;
			}
		}

	}


}
