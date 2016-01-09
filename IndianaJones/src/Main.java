
public class Main {

	public static void main(String[] args) {
		KnapsackDSolver task1 = new KnapsackDSolver(13, "treasures.txt");
		task1.solve();
		System.out.println();
		KnapsackBBSolver task2 = new KnapsackBBSolver(13, "treasures.txt");
		task2.solve();
	}
	
}
