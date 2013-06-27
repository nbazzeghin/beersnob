import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "beersnob"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    
    "uk.co.panaxiom" %% "play-jongo" % "0.4"
  )

	// Only compile the bootstrap bootstrap.less file and any other *.less file in the stylesheets directory 
	def customLessEntryPoints(base: File): PathFinder = ( 
	    (base / "app" / "assets" / "stylesheets" / "bootstrap" * "bootstrap.less") +++
	    (base / "app" / "assets" / "stylesheets" / "bootstrap" * "responsive.less") +++ 
	    (base / "app" / "assets" / "stylesheets" * "*.less")
	)

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
    lessEntryPoints <<= baseDirectory(customLessEntryPoints),
    resolvers += Resolver.url("My GitHub Play Repository", url("http://alexanderjarvis.github.com/releases/"))(Resolver.ivyStylePatterns) 
  )
  
  object Resolvers {
      	val githubRepository = "LeoDagDag repository" at "http://leodagdag.github.com/repository/"
     	val dropboxRepository = "Dropbox repository" at "http://dl.dropbox.com/u/18533645/repository/"
    	val morphiaRepository = "Morphia repository" at "http://morphia.googlecode.com/svn/mavenrepo/"
	}

}
