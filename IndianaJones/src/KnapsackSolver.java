
abstract class KnapsackSolver {
	protected int optimalSolution;
	protected int capacity;
	protected int numberOfItems;
	
	public void solve() {
		knapsack();
		printTracker();
	}
	
	public abstract void knapsack();
	public abstract void printTracker();
}
