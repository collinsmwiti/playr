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
}
