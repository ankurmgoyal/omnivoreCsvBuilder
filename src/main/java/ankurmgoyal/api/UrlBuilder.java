package ankurmgoyal.api;

public class UrlBuilder {
	
	/**Builds omnivore API call that returns list of given resources for a given location. 
	 * The API call is returned as a String that can then be passed to responseGetter in
	 * RequestFactory to get a Response.
	 * 
	 * @param locationID The id of the location we are querying
	 * @param resource The type of resource we are querying. See Resource enum for more details
	 * @return a String that contains the full URL of the API call
	 */
	public static String buildURL(String locationID,Resource resource){
		String toReturn = "https://api.omnivore.io/1.0/locations/"+locationID+"/"+resource.url()+"/";
		return toReturn;
	}
	
	/**Builds omnivore API call that returns list of given resources that meet certain conditions for a given location. 
	 * The API call is returned as a String that can then be passed to responseGetter in
	 * RequestFactory to get a Response.
	 * 
	 * @param locationID The id of the location we are querying
	 * @param resource The type of resource we are querying. See Resource enum for more details
	 * @param conditions The set of conditions applied to the query. See Condition class for more details
	 * @return a String that contains the full URL of the API call
	 */
	public static String buildURL(String locationID,Resource resource,Condition conditions){
		String toReturn = "https://api.omnivore.io/1.0/locations/"+locationID+"/"+resource.url()+"/";
		if(conditions == null){
			return toReturn;
		}
		toReturn=toReturn+"?where="+conditionBuilder(conditions);
		return toReturn;
	}
	
	public static String buildURL(String locationID,Resource resource,int id){
		if(resource == Resource.PRICE_LEVELS || resource == Resource.TICKET_ITEMS){
			String pId = new Integer(id).toString();
			String tempResource = resource.url().replace("REPLACE", pId);
			String toReturn = "https://api.omnivore.io/1.0/locations/"+locationID+"/"+tempResource+"/";
			return toReturn;
		}
		
		String toReturn = "https://api.omnivore.io/1.0/locations/"+locationID+"/"+resource.url()+"/"+id+"/";
		return toReturn;
	}
	
	public static String buildURL(String locationID,Resource resource,int primaryId, int subId) throws IllegalArgumentException{
		if(resource != Resource.PRICE_LEVELS && resource != Resource.TICKET_ITEMS){
			throw new IllegalArgumentException();
		}
		
		String pId = new Integer(primaryId).toString();
		String tempResource = resource.url().replace("REPLACE", pId);
		String toReturn = "https://api.omnivore.io/1.0/locations/"+locationID+"/"+tempResource+"/"+subId+"/";
		return toReturn;
	}
	
	private static String conditionBuilder(Condition conditions){
		String conditionString;
		if(conditions.getbOperator() == null){
			return conditions.getCondition().toString();
		}
		conditionString = conditions.getbOperator()+"(";
		Condition[] subConditions = conditions.getConditions();
		for(int i = 0; i<subConditions.length; i++){
			Condition c = subConditions[i];
			conditionString = conditionString + conditionBuilder(c);
			if(i<=subConditions.length-2){
				conditionString = conditionString+",";
			}
		}
		conditionString = conditionString+")";
		return conditionString;
	}
	
}
