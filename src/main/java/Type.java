//imports
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

// class Type
public class Type {
  private String typeName;
  private int id;

  //constructor Type
  public Type(String typeName) {
    this.typeName = typeName;
  }

  //get method
  public String getTypeName() {
    return typeName;
  }

  public int getId() {
    return id;
  }

  //override method
  @Override
  public boolean equals(Object otherType) {
    if (!(otherType instanceof Type)) {
      return false;
    } else {
      Type newType = (Type) otherType;
      return this.getTypeName().equals(newType.getTypeName());
    }
  }

  //save method
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO types (typeName) VALUES (:typeName)";
      this.id= (int) con.createQuery(sql, true)
      .addParameter("typeName", this.typeName)
      .executeUpdate()
      .getKey();
    }
  }

  //method used to list data within the database
  public static List<Type> all() {
    String sql = "SELECT * FROM types";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Type.class);
    }
  }

  //find method
  public static Type find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM types where id=:id";
      Type type = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Type.class);
      return type;
    }
  }
}
