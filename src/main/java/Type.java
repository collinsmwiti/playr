// import org.sql2o.*;
// import java.util.List;
// import java.util.ArrayList;
//
//
// public class Type {
//   private int id;
//   private String typeName;
// 
//   public Type( String typeName){
//     this.id =id;
//     this.typeName = typeName;
//   }
// // Method to get id
//   public int getId() {
//     return id;
//   }
// //Method to get typeName
//   public String getTypeName() {
//     return typeName;
//   }
// // save Method
//   // public void save() {
//   //   try(Connection con = DB.sql2o.open()) {
//   //     String sql = "INSERT INTO types (typeName) VALUES (:name);";
//   //     this.id = (int) con.createQuery(sql, true)
//   //     .addParameter("typeName", this.typeName)
//   //     .executeUpdate()
//   //     .getKey();
//   //   }
//   // }
//   // public static List<Type> all(){
//   //   try(Connection con = DB.sql2o.open()){
//   //     String sql = "SELECT * FROM types;";
//   //     return con.createQuery(sql)
//   //     .executeAndFetch(Type.class);
//   //   }
//   // }
//
//
//
//
//
// }
