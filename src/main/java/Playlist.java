//imports
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

//clas Playlist
public class Playlist {
  private int userId;
  private String userImage;
  private String userName;
  private String typeName;
  private String trackName;
  private String thumbNail;
  private String trackLink;
  private String host;
  private int id;

//constructor playlist
  public Playlist(int userId, String userImage, String userName, String typeName, String trackName, String thumbNail, String trackLink, String host) {
    this.userId = userId;
    this.userImage = userImage;
    this.userName = userName;
    this.typeName = typeName;
    this.trackName = trackName;
    this.thumbNail = thumbNail;
    this.trackLink = trackLink;
    this.host = host;
  }

  //get methods
  public int getUserId() {
    return userId;
  }

  public String getUserImage() {
    return userImage;
  }

  public String getUserName() {
    return userName;
  }

  public String getTypeName() {
    return typeName;
  }

  public String getTrackName() {
    return trackName;
  }

  public String getThumbNail() {
    return thumbNail;
  }

  public String getTrackLink() {
    return trackLink;
  }

  public String getHost() {
    return host;
  }

  public int getId() {
    return id;
  }

  //override method
  @Override
 public boolean equals(Object otherPlaylist){
   if (!(otherPlaylist instanceof Playlist)) {
     return false;
   } else {
     Playlist newPlaylist = (Playlist) otherPlaylist;
     return this.getUserId() == newPlaylist.getUserId() &&this.getUserImage().equals(newPlaylist.getUserImage()) &&this.getUserName().equals(newPlaylist.getUserName()) &&this.getTypeName().equals(newPlaylist.getTypeName()) &&this.getTrackName().equals(newPlaylist.getTrackName()) &&this.getThumbNail().equals(newPlaylist.getThumbNail()) &&this.getTrackLink().equals(newPlaylist.getTrackLink()) &&this.getHost().equals(newPlaylist.getHost())&&this.getId()==newPlaylist.getId();

   }
 }

 //save method
 public void save() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "INSERT INTO playlists (userId, userImage, userName, typeName, trackName, thumbNail, trackLink, host) VALUES (:userId, :userImage, :userName, :typeName, :trackName, :thumbNail, :trackLink, :host)";
     this.id = (int) con.createQuery(sql, true)
     .addParameter("userId", this.userId)
     .addParameter("userImage", this.userImage)
     .addParameter("userName", this.userName)
     .addParameter("typeName", this.typeName)
     .addParameter("trackName", this.trackName)
     .addParameter("thumbNail", this.thumbNail)
     .addParameter("trackLink", this.trackLink)
     .addParameter("host", this.host)
     .executeUpdate()
     .getKey();
   }
 }

 //method to display playlist as a List
 public static List<Playlist> all() {
   String sql = "SELECT * FROM playlists";
   try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Playlist.class);
   }
 }

 //find method
 public static Playlist find(int id) {
   try(Connection con = DB.sql2o.open()) {
     String sql = "SELECT * FROM playlists where id=:id";
     Playlist playlist = con.createQuery(sql)
       .addParameter("id", id)
       .executeAndFetchFirst(Playlist.class);
     return playlist;
   }
 }

 public static void delete(int id) {
   try(Connection con = DB.sql2o.open()) {
     String sql = "DELETE FROM playlists WHERE userId = :id;";
     con.createQuery(sql)
     .addParameter("id", id)
     .executeUpdate();
   }
 }

 public static void deleteTrack(int id){
   try(Connection con = DB.sql2o.open()) {
     String sql = "DELETE FROM playlists WHERE id = :id;";
     con.createQuery(sql)
     .addParameter("id",id)
     .executeUpdate();
   }
 }
}
