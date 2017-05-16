//imports
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

// class Track
public class Song {
  private String trackName;
  private String trackLink;
  private String host;
  private int id;

  //constructor Track
  public Song(String trackName, String trackLink, String host) {
    this.trackName = trackName;
    this.trackLink = trackLink;
    this.host = host;
  }

  //get methods
  public String getTrackName() {
    return trackName;
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

  //override method for class Song
  @Override
  public boolean equals(Object otherSong){
    if (!(otherSong instanceof Song)) {
      return false;
    } else {
      Song newSong = (Song) otherSong;
      return this.getTrackName().equals(newSong.getTrackName()) &&
            this.getTrackLink().equals(newSong.getTrackLink()) &&
             this.getHost().equals(newSong.getHost());
    }
  }

  // save method
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO songs (trackName, trackLink, host) VALUES (:trackName, :trackLink, :host)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("trackName", this.trackName)
      .addParameter("trackLink", this.trackLink)
      .addParameter("host", this.host)
      .executeUpdate()
      .getKey();
    }
  }

  // method to display data as a List
  public static List<Song> all() {
    String sql = "SELECT * FROM songs";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Song.class);
    }
  }

  // find method
  public static Song find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM songs where id=:id";
      Song song = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Song.class);
      return song;
    }
  }
}
