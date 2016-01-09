public class Treasure {

	private String name;
	private int weight;
	private int value;

	public Treasure(String name, int value, int weight) {
		this.name = name;
		this.value = value;
		this.weight = weight;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String toString() {
		return this.name + " " + this.value + " " + this.weight;
	}


}
