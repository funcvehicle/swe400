package gateway;

public enum SQLEnum {
	FAILED_SQL_ERROR (1), 
	FAILED_BAD_PARAMS (2),
	DOES_NOT_EXIST (3),
	EXISTS (4),
	SUCCESS(5);
	private final int value;
	SQLEnum(int value){
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
