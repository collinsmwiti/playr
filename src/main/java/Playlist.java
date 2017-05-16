//imports
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;
//clas Playlist
public class Playlist {
  private int userId;
  private String userName;
  private int trackId;
  private String typeName;
  private String trackName;
  private String thumbNail;
  private String trackLink;
  private int securityId;
  private String host;
  private int id;

//constructor playlist
  public Playlist(int userId, String userName, int trackId, String typeName, String trackName, String thumbNail, String trackLink, int securityId, String host) {
    this.userId = userId;
    this.userName = userName;
    this.trackId = trackId;
    this.typeName = typeName;
    this.trackName = trackName;
    this.thumbNail = thumbNail;
    this.trackLink = trackLink;
    this.securityId = securityId;
    this.host = host;
  }

  //get methods
  public int getUserId() {
    return userId;
  }

  public String getUserName() {
    return userName;
  }

  public int getTrackId() {
    return trackId;
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

  public int getSecurityId() {
    return securityId;
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
     return this.getUserId() == newPlaylist.getUserId() &&
            this.getUserName().equals(newPlaylist.getUserName()) &&
            this.getTrackId() == newPlaylist.getTrackId() &&
            this.getTypeName().equals(newPlaylist.getTypeName()) &&
            this.getTrackName().equals(newPlaylist.getTrackName()) &&
            this.getThumbNail().equals(newPlaylist.getThumbNail()) &&
            this.getTrackLink().equals(newPlaylist.getTrackLink()) &&
            this.getSecurityId() == newPlaylist.getSecurityId() &&
            this.getHost().equals(newPlaylist.getHost());

   }
 }

 //save method
 public void save() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "INSERT INTO playlists (userId, userName, trackId, typeName, trackName, thumbNail, trackLink, securityId, host) VALUES (:userId, :userName, :trackId, :typeName, :trackName, :thumbNail, :trackLink, :securityId, :host)";
     this.id = (int) con.createQuery(sql, true)
     .addParameter("userId", this.userId)
     .addParameter("userName", this.userName)
     .addParameter("trackId", this.trackId)
     .addParameter("typeName", this.typeName)
     .addParameter("trackName", this.trackName)
     .addParameter("thumbNail", this.thumbNail)
     .addParameter("trackLink", this.trackLink)
     .addParameter("securityId", this.securityId)
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
}
