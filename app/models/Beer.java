package models;

import org.jongo.marshall.jackson.oid.Id;

public class Beer {
	
	@Id
	private String id;
	public String name;
	public Double ac;
	public String brewery;
	public String tags;
	public String notes;
	public Double rating;
	public String imgId;

	
	public String getId(){
		return this.id;
	}
	
}
