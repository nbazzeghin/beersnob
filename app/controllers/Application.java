package controllers;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jongo.MongoCollection;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

import models.Beer;
import play.Logger;
import play.mvc.*;

import play.libs.Json;

import uk.co.panaxiom.playjongo.PlayJongo;
import views.html.*;

public class Application extends Controller {
	
	private static MongoCollection beers = PlayJongo.getCollection("beers");
	private static GridFS gfs = PlayJongo.gridfs();
  
    public static Result index() {
        return ok(index.render("Beer Snob Comming Soon!","BeerSnob"));
       
    }
    
    public static Result beers() {

    	Iterable<Beer> all = beers.find().as(Beer.class);
    	return ok(Json.toJson(all));
    }
}
