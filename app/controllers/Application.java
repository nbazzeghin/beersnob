package controllers;

import org.jongo.MongoCollection;

import models.Beer;
import play.*;
import play.mvc.*;

import uk.co.panaxiom.playjongo.PlayJongo;
import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render("Beer Snob Comming Soon!","BeerSnob"));
       
    }
    
    public static Result beers() {
    	MongoCollection beers = PlayJongo.getCollection("beers");
    	Beer beer = beers.findOne().as(Beer.class);
    	return ok(index.render(beer.toString(), "Beers"));
    }
    
    public static Result newBeer() {
    	return TODO;
    }
    
    public static Result deleteBeer() {
    	return TODO;
    }
    
    public static Result updateBeer() {
    	return TODO;
    }
  
}
