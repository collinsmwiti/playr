// imports
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

//class user test
public class UserTest {

  //rule placed in order for tests to adhere to
  @Rule
  public DatabaseRule database = new DatabaseRule();

  //test to instantiates the user correctly
  @Test
  public void user_instantiatesCorrectly_true() {
    User testUser = new User("Dre");
    assertEquals(true, testUser instanceof User);
  }

  //test used to get the userName
  @Test
  public void getUserName_userInstantiatesWithUserName_Dre() {
    User testUser = new User("Dre");
    assertEquals("Dre", testUser.getUserName());
  }

  //test to return true if the userName is same
  @Test
  public void equals_returnsTrueIfUserNameIsSame_true() {
    User firstUser = new User("Dre");
    User anotherUser = new User("Dre");
    assertTrue(firstUser.equals(anotherUser));
  }

  //test to save data in the database
  @Test
  public void save_insertsObjectIntoDatabase_User() {
    User testUser = new User("Dre");
    testUser.save();
    assertTrue(User.all().get(0).equals(testUser));
  }

  //test to return all instance of user true
  @Test
  public void all_returnsAllInstancesOfUser_true() {
    User firstUser = new User("Dre");
    firstUser.save();
    User secondUser = new User("Snoop");
    secondUser.save();
    assertEquals(true, User.all().get(0).equals(firstUser));
    assertEquals(true, User.all().get(1).equals(secondUser));
  }

  //test to assign id to a user
  @Test
  public void save_assignsIdToObject() {
    User testUser = new User("Dre");
    testUser.save();
    User savedUser = User.all().get(0);
    assertEquals(testUser.getId(), savedUser.getId());
  }

  //test used to find the user by id
  @Test
  public void find_returnsUserWithSameId_secondUser() {
    User firstUser = new User("Dre");
    firstUser.save();
    User secondUser = new User("Snoop");
    secondUser.save();
    assertEquals(User.find(secondUser.getId()), secondUser);
  }
}
