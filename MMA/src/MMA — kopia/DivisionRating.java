package MMA;

public enum DivisionRating {
	Champion("Champion of Divison"), TOP_1("#1"), TOP_2("#2"), TOP_3("#3"), TOP_4("#4"), TOP_5("#5"), TOP_6("#6"),
	TOP_7("#7"), TOP_8("#8"), TOP_9("#9"), TOP_10("#10");

	public final String description;

	private DivisionRating(String desc) {
		description = desc;
	}
}
