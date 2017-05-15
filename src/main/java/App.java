// created class App.java
public class App{
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    // Heroku deployment configurations
    ProcessBuilder process = new ProcessBuilder();
    Integer port;
    if (process.environment().get("PORT") != null) {
      port = Integer.parseInt(process.environment().get("PORT"));
    } else {
      port = 4567;
    }

    setPort(port);
}

// creating a root route in App.java file that will render our home page
 get("/", (request, response) -> {
   Map<String, Object> model = new HashMap<String, Object>();
   model.put("playlists", Playlist.all());
   model.put("template", "templates/index.vtl");
   return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

// route responsible for rendering the template with the new-playlist form
 get("/playlists/new", (request, response) -> {
   Map<String, Object> model = new HashMap<String, Object>();
   model.put("playlists", Playlist.all());
   model.put("template", "templates/playlist-form.vtl");
   return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

 // route to display all playlists
 get("/playlists", (request, response) -> {
   Map<String, Object> model = new HashMap<String, Object>();
   model.put("playlists", Playlist.all());
   model.put("template", "templates/playlists.vtl");
   return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

 // routing and a basic template setup for playlist
 get("/playlists/:id", (request, response) -> {
   Map<String, Object> model = new HashMap<String, Object>();
   Playlist playlist = Playlist.find(Integer.parseInt(request.params(":id")));
   model.put("playlist", playlist);
   model.put("template", "templates/playlist.vtl");
   return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

 // showing playlists already included in the database and displaying them to the user
 post("/playlists", (request, response) -> {
   Map<String, Object> model = new HashMap<String, Object>();
   int userId = request.queryParams("userId");
   String userName = request.queryParams("userName");
   int trackId = request.queryParams("trackId");
   String typeName = request.queryParams("typeName");
   String trackName = request.queryParams("trackName");
   String thumbNail = request.queryParams("thumbNail");
   String trackLink = request.queryParams("trackLink");
   int securityId = request.queryParams("securityId");
   String host = request.queryParams("host");
   Timestamp dateCreated = request.queryParams("dateCreated");
   Playlist newPlaylist = new Playlist(userId, userName, trackId, typeName, trackName, thumbNail, trackLink, securityId, host, dateCreated);
   newPlaylist.save();
   model.put("template", "templates/playlist-success.vtl");
   return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

 // deleting old playlists
 post("/playlists/:id/delete", (request, response) -> {
   HashMap<String, Object> model = new HashMap<String, Object>();
   Playlist playlist = Playlist.find(Integer.parseInt(request.params("id")));
   playlist.delete();
   model.put("playlist", stylist);
   model.put("template", "templates/playlist.vtl");
   return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());
 }
