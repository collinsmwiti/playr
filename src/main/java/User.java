//imports
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

// class user
public class User {
  private String userName;
  private int id;

  //constructor user
  public User(String userName) {
    this.userName = userName;
  }

  //get method
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
      return this.getUserName().equals(newUser.getUserName());
    }
  }

  //save method
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO users (userName) VALUES (:userName)";
      this.id= (int) con.createQuery(sql, true)
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
}
