package ankurmgoyal.api;

public class Condition {
	Field field;
	String value;
	ComparisonOperator cOperator;
	String[] values;
	
	BooleanOperator bOperator;
	Condition[] conditions;
	
	public Condition(ComparisonOperator cOperator, Field field, String value) throws IllegalArgumentException{		
		
		if(cOperator == ComparisonOperator.IN){
			throw new IllegalArgumentException("IN operator requires use of alternate constructor in which values are given as String array");
		}
		
		this.value = value;
		this.field = field;
		this.cOperator = cOperator;
	}
	
	public Condition(ComparisonOperator cOperator, Field field, String[] values) throws IllegalArgumentException{
		if(cOperator != ComparisonOperator.IN){
			throw new IllegalArgumentException("This operator requires use of alternate constructor in which single value is given as a String");
		}
		
		this.values = values;
		this.field = field;
		this.cOperator = cOperator;
		
	}
	
	public Condition(BooleanOperator bOperator,Condition[] conditions){
		this.bOperator = bOperator;
		this.conditions = conditions;
	}
	
	public BooleanOperator getbOperator() {
		return bOperator;
	}

	public Condition[] getConditions() {
		return conditions;
	}

	public Condition getCondition() {
		try{
			if(value != null){
				return new Condition(cOperator,field,value);
			}
			return new Condition(cOperator,field,values);
		}
		catch(IllegalArgumentException e){
			return null;
		}
	}

	@Override
	public String toString() {
		if(cOperator != ComparisonOperator.IN){
			return cOperator+"("+field+",'"+value+"')";
		}
		else{
			String toReturn = cOperator+"("+field;
			for(int i = 0; i<values.length;i++){
				toReturn = toReturn+",'"+values[i]+"'";
			}
			toReturn = toReturn + ")";
			return toReturn;
		}
	}
	
	
	
}
