import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FrontBookkeeper61864 implements IFrontBookkeeper {

	private Map<String, LinkedList<Integer>> soldiers = new HashMap<>();
	private Map<String, LinkedList<Integer>> units = new HashMap<>();

	@Override
	public void updateFront(String[] news) {
		for (String command : news) {
			if (command.contains("=")) {
				this.assign(command);
			}
		}

	}

	private boolean isSoldier(String name) {
		return soldiers.containsKey(name);
	}
	private void attach(String command) {
		int unit1Ind = command.indexOf(' ');
		int unit2Ind = command.lastIndexOf(' ') + 1;

		String unit1 = command.substring(0, unit1Ind);
		String unit2 = command.substring(unit2Ind);
		
		if(isSoldier(unit1)) {
			units.get(unit2).addAll(soldiers.get(unit1));
		} else {
			units.get(unit2).addAll(units.get(unit1));
		}

	}

	private void attachAfter(String command) {

	}

	private void showSoldier(String command) {
		int idBegin = command.lastIndexOf(' ') + 1;
		int soldierID = Integer.parseInt(command.substring(idBegin));

		for (Map.Entry<String, LinkedList<Integer>> entry : soldiers.entrySet()) {
			if (entry.getValue().contains(soldierID)) {
				System.out.println(entry.getKey());
			}
		}
	}

	private void show(String command) {
		int idBegin = command.indexOf(' ') + 1;
		String name = command.substring(idBegin);

		System.out.println(units.get(name));
	}

	private void assign(String command) {
		int arrayBegin = command.indexOf('[') + 1;
		int arrayEnd = command.indexOf(']');
		int nameIndex = command.indexOf(' ');
		String name = command.substring(0, nameIndex);

		LinkedList<Integer> soldiersList = new LinkedList<>();

		if (arrayBegin == arrayEnd) {
			units.put(name, soldiersList);
		} else {
			String[] soldiersArray = command.substring(arrayBegin, arrayEnd).split("[ ,]+");

			for (int i = 0; i < soldiersArray.length; i++) {
				soldiersList.add(Integer.parseInt(soldiersArray[i]));
			}

		}

		if (soldiersList.size() == 1) {
			soldiers.put(name, soldiersList);
		} else {
			units.put(name, soldiersList);
		}
	}

	public static void main(String[] args) {
		String[] news = { "gabi = []", "moni = [1]", "reni = [2, 3, 4, 5]" };
		FrontBookkeeper61864 f = new FrontBookkeeper61864();
		f.updateFront(news);
		f.attach("moni attached to reni");
		System.out.println("Soldiers listed below:");
		for (String key : f.soldiers.keySet()) {
			System.out.println(key + " - " + f.soldiers.get(key));
		}
		System.out.println("Units listed below:");
		for (String key : f.units.keySet()) {
			System.out.println(key + " - " + f.units.get(key));
		}
		f.showSoldier("show soldier 1");
		f.show("show reni");

	}

}
