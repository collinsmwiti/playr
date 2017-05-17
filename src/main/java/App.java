//imports
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
    model.put("playlists", Playlist.all());
    model.put("template", "templates/playlists.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// relating user to the playlists
  get("/users/:id/playlists", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    User user = User.find(Integer.parseInt(request.params(":id")));
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

// posting users within the database
  post("/users/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    String image = request.queryParams("image");
    String userName = request.queryParams("userName");
    User newUser = new User(image, userName);
    newUser.save();
    request.session().attribute("user",newUser);
    model.put("user",request.session().attribute("user"));
    model.put("template", "templates/user_new_success.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// deleting old playlists
  post("/users/:userId/playlists/delete", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Playlist playlist = Playlist.find(Integer.parseInt(request.params("id")));
    User user = User.find(playlist.getUserId());
    playlist.delete();
    model.put("user", user);
    model.put("template", "templates/user_delete.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
