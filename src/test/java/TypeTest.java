// imports
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

//class Type test
public class TypeTest {

  //rule placed in order for tests to adhere to
  @Rule
  public DatabaseRule database = new DatabaseRule();

  //test to instantiates the Type correctly
  @Test
  public void type_instantiatesCorrectly_true() {
    Type testType = new Type("Oldschool");
    assertEquals(true, testType instanceof Type);
  }

  //test used to get the TypeName
  @Test
  public void getTypeName_typeInstantiatesWithTypeName_Oldschool() {
    Type testType = new Type("Oldschool");
    assertEquals("Oldschool", testType.getTypeName());
  }

  //test to return true if the TypeName is same
  @Test
  public void equals_returnsTrueIfTypeNameIsSame_true() {
    Type firstType = new Type("Oldschool");
    Type anotherType = new Type("Oldschool");
    assertTrue(firstType.equals(anotherType));
  }

  //test to save data in the database
  @Test
  public void save_insertsObjectIntoDatabase_Type() {
    Type testType = new Type("Oldschool");
    testType.save();
    assertTrue(Type.all().get(0).equals(testType));
  }

  //test to return all instance of Type true
  @Test
  public void all_returnsAllInstancesOfType_true() {
    Type firstType = new Type("Oldschool");
    firstType.save();
    Type secondType = new Type("Worship");
    secondType.save();
    assertEquals(true, Type.all().get(0).equals(firstType));
    assertEquals(true, Type.all().get(1).equals(secondType));
  }

  //test to assign id to a Type
  @Test
  public void save_assignsIdToObject() {
    Type testType = new Type("Oldschool");
    testType.save();
    Type savedType = Type.all().get(0);
    assertEquals(testType.getId(), savedType.getId());
  }

  //test used to find the Type by id
  @Test
  public void find_returnsTypeWithSameId_secondType() {
    Type firstType = new Type("Oldschool");
    firstType.save();
    Type secondType = new Type("Worship");
    secondType.save();
    assertEquals(Type.find(secondType.getId()), secondType);
  }
}
