//imports
import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource{
  @Override
  protected void before(){
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/playr_test","collins","password");
  }

  @Override
  protected void after(){
    try(Connection con = DB.sql2o.open()) {
      String deletePlaylistQuery = "DELETE FROM playlists *;";
      String deleteUsersQuery = "DELETE FROM users *;";
      String deleteSongsQuery = "DELETE FROM songs *;";
      String deleteTypesQuery = "DELETE FROM types *;";
      con.createQuery(deletePlaylistQuery).executeUpdate();
      con.createQuery(deleteUsersQuery).executeUpdate();
      con.createQuery(deleteSongsQuery).executeUpdate();
      con.createQuery(deleteTypesQuery).executeUpdate();
    }
  }
}
