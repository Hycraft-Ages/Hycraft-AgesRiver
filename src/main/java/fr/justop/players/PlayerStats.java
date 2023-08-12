package fr.justop.players;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

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
	private Map<UUID, String> finalTime;
	private static Map<UUID, Integer> global = new TreeMap<>();

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
	public Map<UUID, String> getFinalTime() {
		return finalTime;
	}

	/**
	 * @return the currentAge
	 */
	public Map<UUID, Integer> getCurrentAge() {
		return currentAge;
	}

}
