
public class Main {

	public static void main(String[] args) {
		KnapsackDSolver task1 = new KnapsackDSolver(10, "treasures.txt");
		task1.solve();
		
		KnapsackBBSolver task2 = new KnapsackBBSolver(10, "treasures.txt");
		task2.solve();
	}
	
}
