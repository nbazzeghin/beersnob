package models;

import java.util.ArrayList;
import java.util.List;

import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.oid.Id;

import com.mongodb.WriteResult;

import de.undercouch.bson4jackson.types.ObjectId;

import uk.co.panaxiom.playjongo.PlayJongo;

public class Beer {
	
	@Id
	public ObjectId id;
	public String name;
	public int ac;
	
}
