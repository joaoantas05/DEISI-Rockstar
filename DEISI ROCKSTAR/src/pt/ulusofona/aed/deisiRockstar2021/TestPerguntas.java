package pt.ulusofona.aed.deisiRockstar2021;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestPerguntas {
    @BeforeClass
    public static void leitura() throws IOException {
        LoadFilesForTestes.loadFiles();
    }

    @Test
    public void testSong(){
        String expected = "73694387 | Aspire | 2013 | 0:0 | -10.0 |  | ()";
        Song obtained = new Song("73694387","Aspire",2013);
        assertEquals("Expected '73694387 | Aspire | 2013'", expected, obtained.toString());
    }

    @Test
    public void testSong2(){
        String expected = "63857184 | Animus | 0 | 0:0 | -10.0 |  | ()";
        Song obtained = new Song("63857184","Animus",0);
        assertEquals("Expected '63857184 | Animus | 2020'", expected, obtained.toString());
    }

    @Test
    public void testCreativeQuery(){
        String expected = "";
        String obtained = LoadFilesForTestes.execute("TOP_LONG_IN_YEAR 2020 500000");
        assertEquals(expected, obtained);
    }

    @Test
    public void testPergunta1() throws IOException {
        String expected = "1164";
        String obtained = LoadFilesForTestes.execute("COUNT_SONGS_YEAR 2000");
        assertEquals(expected, obtained);
    }
    @Test
    public void testPergunta2() throws IOException {
        String expected = "26";
        String obtained = LoadFilesForTestes.execute("COUNT_DUPLICATE_SONGS_YEAR 2001");
        assertEquals(expected, obtained);
    }
    @Test
    public void testPergunta3() throws IOException {
        String expected = "No results";
        String obtained = LoadFilesForTestes.execute("GET_ARTISTS_FOR_TAG 2002");
        assertEquals(expected, obtained);
    }
    @Test
    public void testPergunta4() throws IOException {
        String expected = "Go Girl : 2012 : 0.986";
        String obtained = LoadFilesForTestes.execute("GET_MOST_DANCEABLE 2003 2020 1");
        assertEquals(expected, obtained);
    }
    @Test
    public void testPergunta5() throws IOException {
        String expected = "";
        String obtained = LoadFilesForTestes.execute("GET_ARTISTS_ONE_SONG 2004 2010");
        assertEquals(expected, obtained);
    }
    @Test
    public void testPergunta6() throws IOException {
        String expected = "";
        String obtained = LoadFilesForTestes.execute("GET_TOP_ARTISTS_WITH_SONGS_BETWEEN 2005");
        assertEquals(expected, obtained);
    }
    @Test
    public void testPergunta7() throws IOException {
        String expected = "";
        String obtained = LoadFilesForTestes.execute("MOST_FREQUENT_WORDS_IN_ARTIST_NAME 2006");
        assertEquals(expected, obtained);
    }
    @Test
    public void testPergunta8() throws IOException {
        String expected = "";
        String obtained = LoadFilesForTestes.execute("GET_UNIQUE_TAGS 2007");
        assertEquals( expected, obtained);
    }
    @Test
    public void testPergunta9() throws IOException {
        String expected = "";
        String obtained = LoadFilesForTestes.execute("GET_UNIQUE_TAGS_IN_BETWEEN_YEARS 2008");
        assertEquals(expected, obtained);
    }
    @Test
    public void testPergunta10() throws IOException {
        String expected = "";
        String obtained = LoadFilesForTestes.execute("GET_RISING_STARS 2009");
        assertEquals(expected, obtained);
    }
    @Test
    public void testPergunta11() throws IOException {
        ArrayList<String> tags = new ArrayList<>();
        String in = "ADD_TAGS Maria Leal;ze;joao;andre";
        String[] command = in.split(" ", 2);
        String array[] = command[1].split(";");
        String nome = array[0].trim();

        for(int i = 0; i < array.length - 1; i++){
            tags.add(i, array[i + 1].trim());
        }
        String expected = "Maria Leal | JOAO,ANDRE,ZE";
        String obtained = Perguntas.addTags(nome, tags, Main.artistTags, LoadFilesForTestes.artistas);
        assertEquals(expected, obtained);
    }
    @Test
    public void testPergunta12() throws IOException {
        ArrayList<String> tags = new ArrayList<>();
        String in = "ADD_TAGS Maria Leal;ze;joao;andre";
        String[] command = in.split(" ", 2);
        String array[] = command[1].split(";");
        String nome = array[0].trim();

        for(int i = 0; i < array.length - 1; i++){
            tags.add(i, array[i + 1].trim());
        }
        Perguntas.addTags(nome, tags, Main.artistTags, LoadFilesForTestes.artistas);
        String expected = "Madonna | No tags";
        String obtained = LoadFilesForTestes.execute("REMOVE_TAGS Madonna;andre");
        assertEquals(expected, obtained);
    }
    @Test
    public void testPergunta13() throws IOException {
        String expected = "";
        String obtained = LoadFilesForTestes.execute("CLEANUP 2012");
        assertEquals(expected, obtained);
    }
    @Test
    public void testPergunta14() throws IOException {
        String expected = "";
        String obtained = LoadFilesForTestes.execute("TOPLONG 2013");
        assertEquals(expected, obtained);
    }
}