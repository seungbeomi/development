package seungbeomi.web.springmvc.sample;

public enum Level {

	GOLD(3, "gold"), SILVER(2, "silver"), BASIC(1, "basic");

	private int code;
	private String value;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	Level(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public int intValue() {
		return code;
	}

	public static Level valueOf(int code) {
		switch(code) {
		case 1: return BASIC;
		case 2: return SILVER;
		case 3: return GOLD;
		default : throw new AssertionError("Unknown code: " + code );
		}
	}


}
