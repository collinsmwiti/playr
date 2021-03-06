//imports
import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource{
  @Override
  protected void before(){
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/playr_test","Kenneth","Ee7BoiRVET");
  }

  @Override
  protected void after(){
    try(Connection con = DB.sql2o.open()) {
      String deleteUsersQuery = "DELETE FROM users *;";
      String deletePlaylistsQuery = "DELETE FROM playlists *;";
      con.createQuery(deleteUsersQuery).executeUpdate();
      con.createQuery(deletePlaylistsQuery).executeUpdate();
    }
  }
}
