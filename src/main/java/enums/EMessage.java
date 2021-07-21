package enums;

public enum EMessage {

	MESSAGE (1, "message"),
	ERROR (2, "error"),
	WARNING (3, "warning");

	private int value;
	private String name;

	private EMessage(final int value, final String name) {
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

}
