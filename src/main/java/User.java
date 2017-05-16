//imports
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

// class user
public class User {
  private String userImage;
  private String userName;
  private int id;

  //constructor user
  public User(String userImage, String userName) {
    this.userImage = userImage;
    this.userName = userName;
  }

  //get method
  public String getUserImage() {
    return userImage;
  }

  public String getUserName() {
    return userName;
  }

  public int getId() {
    return id;
  }

  //override method
  @Override
  public boolean equals(Object otherUser) {
    if (!(otherUser instanceof User)) {
      return false;
    } else {
      User newUser = (User) otherUser;
      return this.getUserImage().equals(newUser.getUserImage()) &&
            this.getUserName().equals(newUser.getUserName());
    }
  }

  //save method
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO users (userImage, userName) VALUES (:userImage, :userName)";
      this.id= (int) con.createQuery(sql, true)
      .addParameter("userImage", this.userImage)
      .addParameter("userName", this.userName)
      .executeUpdate()
      .getKey();
    }
  }

  //method used to list data within the database
  public static List<User> all() {
    String sql = "SELECT * FROM users";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(User.class);
    }
  }

  //find method
  public static User find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM users where id=:id";
      User user = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(User.class);
      return user;
    }
  }

  //method to add playlists to User
  public List<Playlist> getPlaylists() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM playlists where userId=:id";
      return con.createQuery(sql)
      .addParameter("id", this.id)
      .executeAndFetch(Playlist.class);
    }
  }
}
