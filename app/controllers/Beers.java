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

public class Beers extends Controller {
	
	private static MongoCollection beers = PlayJongo.getCollection("beers");
	private static GridFS gfs = PlayJongo.gridfs();
  
    public static Result listBeers() {

    	Iterable<Beer> all = beers.find().as(Beer.class);
    	return ok(Json.toJson(all));
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public static Result newBeer() {
    	GridFSInputFile gfsImg = null;
    	ObjectMapper mapper = new ObjectMapper();
    	Beer beer = null;
		try {
			beer = mapper.readValue(request().body().asJson(),Beer.class);
			beers.save(beer);
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	try {
			BufferedImage img = ImageIO.read(new URL("http://wwwimages.harpoonbrewery.com/SummerBeer-2013-Modal.jpg"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, "jpg", baos);
			baos.flush();
			gfsImg = gfs.createFile(baos.toByteArray());
			gfsImg.setFilename("bestbeer.jpg");
			gfsImg.save();
			beer.imgId = gfsImg.getId().toString();
	    	beers.save(beer);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return ok(Json.parse("{\"beerId\":\"" + beer.getId() +"\"}"));
    }
    
    public static Result deleteBeer(String id) {
    	
    	Logger.info("Deleting beer ID: " + new ObjectId(id).toString());
    	beers.remove(new ObjectId(id));
    	return ok();
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public static Result updateBeer(String id) {
    	//TODO: Fix imgId so we can updated image w/ this as well.
    	beers.update(new ObjectId(id)).with(request().body().asJson().toString());    	
    	return ok();
    }
  
}
