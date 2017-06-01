package ankurmgoyal.api;

public enum BooleanOperator {

	AND("and"),
	OR("or"),
	NOT("not");
	
	
	String bOperator;
	
	BooleanOperator(String bOperator){
		this.bOperator = bOperator;
	}
	
	public String get(){
		return bOperator;
	}

	@Override
	public String toString() {
		return bOperator;
	}
	
	
}
