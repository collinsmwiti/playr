// imports
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

// class Playlist Test
public class PlaylistTest {

  //rule taken in order for the tests to adhere to
  @Rule
  public DatabaseRule database = new DatabaseRule();

  //test to instantiates the playlist correctly
  @Test
  public void playlist_instantiatesCorrectly_true() {
    Playlist testPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    assertEquals(true, testPlaylist instanceof Playlist);
  }

  //test to instantiate playlist with user id
  @Test
  public void Playlist_instantiatesWithUserId_int() {
    Playlist testPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    assertEquals(1, testPlaylist.getUserId());
  }

// test to get the user name within the playlist
  @Test
  public void Playlist_instantiatesWithUserName_String() {
    Playlist testPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    assertEquals("John", testPlaylist.getUserName());
  }

  //test to get the type name in playlist
  @Test
  public void Playlist_instantiatesWithTypeName_String() {
    Playlist testPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    assertEquals("Worship", testPlaylist.getTypeName());
  }

  //test to get the track name within the playlist
  @Test
  public void Playlist_instantiatesWithTrackName_String() {
    Playlist testPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    assertEquals("Imela", testPlaylist.getTrackName());
  }

  //test to get thumbnail in the playlist
  @Test
  public void Playlist_instantiatesWithThumbNail_String() {
    Playlist testPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    assertEquals("Picture", testPlaylist.getThumbNail());
  }

  //test to get the track link within the playlist
  @Test
  public void Playlist_instantiatesWithTrackLink_String() {
    Playlist testPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    assertEquals("www.youtube.com", testPlaylist.getTrackLink());
  }

  //test to get security id
  @Test
  public void Playlist_instantiatesWithSecurityId_int() {
    Playlist testPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    assertEquals(1, testPlaylist.getSecurityId());
  }

  //test to get host within the playlist
  @Test
  public void Playlist_instantiatesWithHost_String() {
    Playlist testPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    assertEquals("Youtube", testPlaylist.getHost());
  }

  //test to override if the playlist properties are true
  @Test
  public void equals_returnsTrueIfPlaylistPropertiesAreSame_true() {
    Playlist testPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    Playlist anotherPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    assertTrue(testPlaylist.equals(anotherPlaylist));
  }

  //test to save details in the database
  @Test
  public void save_returnsTrueIfDescriptionsAreTheSame() {
    Playlist testPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    testPlaylist.save();
    assertTrue(Playlist.all().get(0).equals(testPlaylist));
  }

  //test to assign ids to playlist properties
  @Test
  public void save_assignsIdToPlaylist() {
    Playlist testPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    testPlaylist.save();
    Playlist savedPlaylist = Playlist.all().get(0);
    assertEquals(savedPlaylist.getId(), testPlaylist.getId());
  }

  @Test
  public void getId_playlistInstantiateWithAnID() {
    Playlist testPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    testPlaylist.save();
    assertTrue(testPlaylist.getId() > 0);
  }

  //test to return all instances of playlists
  public void all_returnsAllInstancesOfPlaylist_true() {
    Playlist firstPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    firstPlaylist.save();
    Playlist secondPlaylist = new Playlist(2, "Johnny", "Riddim", "Black", "Pics", "www.vimeo.com", 2, "Vimeo");
    secondPlaylist.save();
    assertEquals(true, Playlist.all().get(0).equals(firstPlaylist));
    assertEquals(true, Playlist.all().get(1).equals(secondPlaylist));
  }

  //test to find playlist based on id
  @Test
  public void find_returnsPlaylistWithSameId_secondPlaylist() {
    Playlist firstPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    firstPlaylist.save();
    Playlist secondPlaylist = new Playlist(2, "Johnny", "Riddim", "Black", "Pics", "www.vimeo.com", 2, "Vimeo");
    secondPlaylist.save();
    assertEquals(Playlist.find(secondPlaylist.getId()), secondPlaylist);
  }

  //test to forge relationship between playlist and users
  @Test
  public void save_savesUserIdIntoDB_true() {
    User testUser = new User("image", "Dre");
    testUser.save();
    Playlist testPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    testPlaylist.save();
    Playlist savedPlaylist = Playlist.find(testPlaylist.getId());
    assertTrue(savedPlaylist.getUserId() > 0);
  }


//deleting the playlists
  @Test
  public void delete_deletesPlaylist_true() {
    Playlist testPlaylist = new Playlist(1, "John", "Worship", "Imela", "Picture", "www.youtube.com", 1, "Youtube");
    testPlaylist.save();
    int testPlaylistId = testPlaylist.getId();
    testPlaylist.delete();
    assertEquals(null, Playlist.find(testPlaylistId));
  }
}
