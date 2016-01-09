import java.util.ArrayList;
import java.util.List;

public class Node {

	private List<Treasure> treasures;
	private int value;
	private int weight;
	private int level;
	
	public Node(Node node) {
		this.treasures = node.treasures;
		this.value = node.value;
		this.weight = node.weight;
		this.level = node.level;
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
}
