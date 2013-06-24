package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render("Beer Snob Comming Soon!","BeerSnob"));
       
    }
    
    public static Result beers() {
    	return TODO;
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
