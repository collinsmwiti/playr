import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


//class App
public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    ProcessBuilder process = new ProcessBuilder();
    Integer port;
    if (process.environment().get("PORT") !=null) {
      port = Integer.parseInt(process.environment().get("PORT"));
    } else {
      port = 4567;
    }

    setPort(port);


 // creating a root route in App.java file that will render our home page
  get("/", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// route responsible for rendering the template with the new-playlist form
  get("/playlists/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("user",request.session().attribute("user"));
    model.put("template", "templates/playlist_form.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// route to retrieve all playlists in general
  get("/playlists", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("users", User.all());
    model.put("template", "templates/playlists.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// relating user to the playlists
  get("/users/:id/playlists", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    int id = Integer.parseInt(request.params(":id"));
    User user = User.find(id);
    model.put("user", user);
    model.put("template", "templates/user_playlist.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//  adding new users
  get("/users/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/user_form.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  // delete user's playlist
  get("/users/:userId/playlists/delete",(request,response)->{
    Map<String,Object> model = new HashMap<String,Object>();
    int id = Integer.parseInt(request.params(":userId"));
    User user = User.find(id);
    model.put("user",user);
    model.put("template","templates/user_delete.vtl");
    return new ModelAndView(model,layout);
  },new VelocityTemplateEngine());

  // adding single track in a saved playlist;
  get("/users/:userId/playlists/new",(request,response)->{
    Map<String,Object> model = new HashMap<String,Object>();
    int userId = Integer.parseInt(request.params(":userId"));
    User user = User.find(userId);
    request.session().attribute("user",user);
    model.put("template","templates/playlist_create.vtl");
    return new ModelAndView(model,layout);
  },new VelocityTemplateEngine());
    // posting users within the database
post("/users/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    String userImage = request.queryParams("userImage");
    String userName = request.queryParams("userName");
    User newUser = new User(userImage, userName);
    newUser.save();
    request.session().attribute("user",newUser);
    model.put("user",request.session().attribute("user"));
    model.put("template", "templates/user_new_success.vtl");
    return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

// showing playlists already included in the database and displaying them to the user
 post("/playlists/new", (request, response) -> {
   Map<String, Object> model = new HashMap<String, Object>();
   String typeName = request.queryParams("typeName");
   String trackName = request.queryParams("trackName");
   String thumbNail = request.queryParams("thumbNail");
   String trackLink = request.queryParams("trackLink");
   String host = request.queryParams("host");
   User user = request.session().attribute("user");
   Playlist newPlaylist = new Playlist(user.getId(), user.getUserImage(), user.getUserName(), typeName, trackName, thumbNail, trackLink, host);
   newPlaylist.save();
   model.put("template", "templates/playlist_new_success.vtl");
   return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

// deleting old playlists
  post("/users/:userId/playlists/delete", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    int id = Integer.parseInt(request.params(":userId"));
    User user = User.find(id);
    Playlist.delete(user.getId());
    User.delete(user.getId());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// deleting single track in a playlist;
    post("/users/:userId/playlists/:playlistsId/delete",(request,response)-> {
      Map<String,Object> model = new HashMap<String,Object>();
      int userId = Integer.parseInt(request.params(":userId"));
      int playlistsId = Integer.parseInt(request.params(":playlistsId"));
      Playlist.deleteTrack(playlistsId);
      model.put("template","templates/index.vtl");
      return new ModelAndView(model,layout);
    },new VelocityTemplateEngine());
  }
}
