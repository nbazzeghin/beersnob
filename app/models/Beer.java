package models;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

//{
//    "name": "Test Beer 1",
//    "ac": 5,
//    "brewery": "Walking Bones",
//    "tags": "amber,dark,nutty",
//    "notes": "This dark amber beer has a hint of nuts.",
//    "rating": 7.3
// }

public class Beer {
	
	@Id @ObjectId
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
