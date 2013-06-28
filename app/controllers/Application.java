package controllers;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.jongo.MongoCollection;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

import models.Beer;
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
    
    public static Result newBeer() {
    	GridFSInputFile gfsImg = null;
    	Beer beer = new Beer();
    	beer.name = "Ultra Cool Bud";
    	beer.ac = 10.0;
    	
    	beers.save(beer);
    	
    	try {
			BufferedImage img = ImageIO.read(new URL("http://wwwimages.harpoonbrewery.com/SummerBeer-2013-Modal.jpg"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, "jpg", baos);
			baos.flush();
			gfsImg = gfs.createFile(baos.toByteArray());
			gfsImg.setFilename("m0arbeer.jpg");
			gfsImg.save();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return ok(Json.toJson(gfsImg.getId().toString()));
    }
    
    public static Result deleteBeer() {
    	
    	beers.remove();
    	return ok();
    }
    
    public static Result updateBeer() {
    	return TODO;
    }
  
}
