package ankurmgoyal.api;

public enum ComparisonOperator {

	EQ("eq"),
	NEQ("neq"),
	GT("gt"),
	GTE("gte"),
	LT("lt"),
	LTE("lte"),
	IN("in");
	
	
	String cOperator;
	
	ComparisonOperator(String cOperator){
		this.cOperator = cOperator;
	}
	
	public String get(){
		return cOperator;
	}

	@Override
	public String toString() {
		return cOperator;
	}
	
	
}
