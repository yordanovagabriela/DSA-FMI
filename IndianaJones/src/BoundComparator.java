import java.util.Comparator;

public class BoundComparator implements Comparator<Node>{

	@Override
	public int compare(Node node1, Node node2) {
		return node2.getBound() - node1.getBound();
	}

}
