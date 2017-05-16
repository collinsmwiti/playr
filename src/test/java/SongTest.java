//imports
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

//class Song test
public class SongTest {

// rule added in order for tests to adhere to the added functinality before they occur
  @Rule
  public DatabaseRule database = new DatabaseRule();

  //test to instantiate the Song correctly
  @Test
  public void song_instantiatesCorrectly_true() {
    Song testSong = new Song("Believe", "www.youtube.com", "Youtube");
    assertEquals(true, testSong instanceof Song);
  }

  //test to get the SongName
  @Test
  public void getTrackName_songInstantiatesWithTrackName_Believe() {
    Song testSong = new Song("Believe", "www.youtube.com", "Youtube");
    assertEquals("Believe", testSong.getTrackName());
  }

  // test to get the trackLink
  @Test
  public void getTrackLink_songInstantiatesWithTrackLink_String() {
    Song testSong = new Song("Believe", "www.youtube.com", "Youtube");
    assertEquals("www.youtube.com", testSong.getTrackLink());
  }

  //test to get the host
  public void getHost_songInstantiatesWithHost_String() {
    Song testSong = new Song("Believe", "www.youtube.com", "Youtube");
    assertEquals("Youtube", testSong.getHost());
  }

  //test to return true if the song properties are same
  @Test
  public void equals_returnsTrueIfTrackNameTrackLinkAndHostAreSame_true() {
    Song firstSong = new Song("Believe", "www.youtube.com", "Youtube");
    Song anotherSong = new Song("Believe", "www.youtube.com", "Youtube");
    assertTrue(firstSong.equals(anotherSong));
  }

  //test to save data in the database
  @Test
  public void save_insertsObjectIntoDatabase_Song() {
    Song testSong = new Song("Believe", "www.youtube.com", "Youtube");
    testSong.save();
    assertTrue(Song.all().get(0).equals(testSong));
  }

  //test to return all instances of songs as true
  @Test
  public void all_returnsAllInstancesOfSong_true() {
    Song firstSong = new Song("Believe", "www.youtube.com", "Youtube");
    firstSong.save();
    Song secondSong = new Song("Believe", "www.youtube.com", "Youtube");
    secondSong.save();
    assertEquals(true, Song.all().get(0).equals(firstSong));
    assertEquals(true, Song.all().get(1).equals(secondSong));
  }

  //test used to asssign ids
  @Test
  public void save_assignsIdToObject() {
    Song testSong = new Song("Believe", "www.youtube.com", "Youtube");
    testSong.save();
    Song savedSong = Song.all().get(0);
    assertEquals(testSong.getId(), savedSong.getId());
  }

  //test used to find the songs according to their id
  @Test
  public void find_returnsSongWithSameId_secondSong() {
    Song firstSong = new Song("Believe", "www.youtube.com", "Youtube");
    firstSong.save();
    Song secondSong = new Song("Believe", "www.youtube.com", "Youtube");
    secondSong.save();
    assertEquals(Song.find(secondSong.getId()), secondSong);
  }
}
