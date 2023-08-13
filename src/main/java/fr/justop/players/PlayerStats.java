package fr.justop.players;

import java.util.*;

public class PlayerStats {
	private Map<UUID, Integer> nbTour;
	private Map<UUID, Integer> seg1;
	private Map<UUID, Integer> seg2;
	private Map<UUID, Integer> seg3;
	private Map<UUID, Integer> seg4;
	private Map<UUID, Integer> seg5;
	private Map<UUID, Integer> seg6;
	private Map<UUID, Integer> seg7;
	private Map<UUID, Integer> seg8;
	private Map<UUID, Integer> seg9;
	private Map<UUID, Integer> currentAge;
	private Map<UUID, Integer> finalTime;
	private static Map<UUID, Integer> global = new HashMap<>();
	private static Map<UUID, Integer> sortedGlobal = new LinkedHashMap<>();

	public void initialize() {
		this.nbTour = new HashMap<>();
		this.seg1 = new HashMap<>();
		this.seg2 = new HashMap<>();
		this.seg3 = new HashMap<>();
		this.seg4 = new HashMap<>();
		this.seg5 = new HashMap<>();
		this.seg6 = new HashMap<>();
		this.seg7 = new HashMap<>();
		this.seg8 = new HashMap<>();
		this.seg9 = new HashMap<>();
		this.currentAge = new HashMap<>();
		this.finalTime = new HashMap<>();

	}

	public static void sortByValues() {

		sortedGlobal.clear();
		List<Integer> list = new ArrayList<>();

		for (Map.Entry<UUID, Integer> entry : global.entrySet()) {
			list.add(entry.getValue());
		}

		list.sort(Comparator.naturalOrder());

		for (int value : list) {
			for (Map.Entry<UUID, Integer> entry : global.entrySet()) {
				if (entry.getValue().equals(value)) {
					sortedGlobal.put(entry.getKey(), value);
				}
			}
		}

	}


	public static Map<UUID, Integer> getSortedGlobal() {
		return sortedGlobal;
	}

	public static Map<UUID, Integer> getGlobal() {
		return global;
	}

	public Map<UUID, Integer> getNbTour() {
		return nbTour;
	}

	/**
	 * @return the seg1
	 */
	public Map<UUID, Integer> getSeg1() {
		return seg1;
	}

	/**
	 * @return the seg2
	 */
	public Map<UUID, Integer> getSeg2() {
		return seg2;
	}

	/**
	 * @return the seg3
	 */
	public Map<UUID, Integer> getSeg3() {
		return seg3;
	}

	/**
	 * @return the seg4
	 */
	public Map<UUID, Integer> getSeg4() {
		return seg4;
	}

	/**
	 * @return the seg5
	 */
	public Map<UUID, Integer> getSeg5() {
		return seg5;
	}

	/**
	 * @return the seg6
	 */
	public Map<UUID, Integer> getSeg6() {
		return seg6;
	}

	/**
	 * @return the seg7
	 */
	public Map<UUID, Integer> getSeg7() {
		return seg7;
	}

	/**
	 * @return the seg8
	 */
	public Map<UUID, Integer> getSeg8() {
		return seg8;
	}

	/**
	 * @return the seg9
	 */
	public Map<UUID, Integer> getSeg9() {
		return seg9;
	}

	/**
	 * @return the finalTime
	 */
	public Map<UUID, Integer> getFinalTime() {
		return finalTime;
	}

	/**
	 * @return the currentAge
	 */
	public Map<UUID, Integer> getCurrentAge() {
		return currentAge;
	}

}
