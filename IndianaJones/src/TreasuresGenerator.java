import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TreasuresGenerator {
	private final static int ITEM = 0;
	private final static int VALUE = 1;
	private final static int WEIGHT = 2;
	
	private List<Treasure> treasures;
	private String file;
	
	public TreasuresGenerator(String file) {
		this.file = file;
		this.treasures = new ArrayList<>();
		this.generateTreasures(file);
	}
	
	public void generateTreasures(String file){
		Stream<String> lines;
		try {
			lines = Files.lines(Paths.get("recources", file));
			lines.forEach(line -> addTreasure(line));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void addTreasure(String treasureInfo) {
		String[] tr = treasureInfo.split(" ");
		treasures.add(new Treasure(tr[ITEM], Integer.parseInt(tr[VALUE]), Integer.parseInt(tr[WEIGHT])));
	}
	
	public List<Treasure> getTreasures() {
		return treasures;
	}
}
