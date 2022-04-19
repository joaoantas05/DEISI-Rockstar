package pt.ulusofona.aed.deisiRockstar2021;
import java.util.*;
import java.io.*;
public class Main {
    public static LinkedHashMap<String, Song> sons = new LinkedHashMap<>(); // ser ordenado
    public static HashSet<String> hashTags = new HashSet<>();
    public static ArrayList<String> artistas;
    public static HashMap<String, HashSet> artistTags = new LinkedHashMap<>();
    public static HashMap<String, Song> artistSongs = new LinkedHashMap<>();
    public static ArrayList<String> listaArtistas = new ArrayList<>();
    public static int LinhasOKArtista = 0;
    public static int LinhasOKSons = 0;
    public static int LinhasOKDetalhes = 0;
    public static int LinhasIgnoradasArtista = 0;
    public static int LinhasIgnoradasSons = 0;
    public static int LinhasIgnoradasDetalhes = 0;
    public static void loadFiles() throws IOException {
        sons.clear();
        OutrasFuncoes.loadSongs();
        OutrasFuncoes.loadArtists();
        OutrasFuncoes.loadDetails();
    }
    public static ArrayList<Song> getSongs(){ return new ArrayList<>(sons.values()); }
    public static ParseInfo getParseInfo(String fileName){
        switch (fileName) {
            case "songs.txt":
                return new ParseInfo(LinhasOKSons,LinhasIgnoradasSons);
            case "song_artists.txt":
                return new ParseInfo(LinhasOKArtista,LinhasIgnoradasArtista);
            case "song_details.txt":
                return new ParseInfo(LinhasOKDetalhes,LinhasIgnoradasDetalhes);
            default:
                return null;
        }
    }

    public static String execute(String in){
        String command[] = in.split(" ", 2);
        String res = "";
        switch (command[0].trim()){
            case("COUNT_SONGS_YEAR"): {
                int ano = Integer.parseInt(command[1].trim());
                res = String.valueOf(Perguntas.countSongsYear(ano,Main.sons));
                break;
            }
            case("COUNT_DUPLICATE_SONGS_YEAR"): {
                int ano = Integer.parseInt(command[1].trim());
                res = String.valueOf(Perguntas.countDuplicateSongsYear(ano,Main.sons));
                break;
            }
            case("GET_ARTISTS_FOR_TAG"): {
                res = OutrasFuncoes.executeGetArtistsForTag(command);
                break;
            }
            case("GET_MOST_DANCEABLE"): {
                res = OutrasFuncoes.executeGetMostDanceble(command);
                break;
            }

            case("GET_ARTISTS_ONE_SONG"): {
                res = OutrasFuncoes.executeGetArtistsOneSong(command);
                break;
            }

            case("GET_UNIQUE_TAGS"): {
                res = Perguntas.getUniqueTags(artistTags, artistas);
                break;
            }

            case("ADD_TAGS"): { // se o utilizador escrever addtags vai para outras funcoes
                res = OutrasFuncoes.executeAddTags(command);
                break;
            }
            case("REMOVE_TAGS"): {
                res = OutrasFuncoes.executeRemoveTags(command);
                break;
            }
            case ("TOP_LONG_IN_YEAR"): {
                res = OutrasFuncoes.executeTopLongInYear(command);
                break;
            }
            default:
                System.out.println("Illegal command. Try again\n");
                break;
        }
        return res;
    }

    public static String getCreativeQuery(){ return "TOP_LONG_IN_YEAR"; }
    public static int getTypeOfSecondParameter(){ return 1; }
    public static String getVideoUrl(){
        return "https://youtu.be/iCt-7xbgG7A";
    }
    public static void main(String[] args) throws IOException {
        loadFiles();
        System.out.println(getParseInfo("songs.txt"));
        System.out.println("Welcome to DEISI Rockstar!"); Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (line != null && !line.equals("KTHXBYE")) {
            long start = System.currentTimeMillis();
            String result = execute(line);
            long end = System.currentTimeMillis(); System.out.println(result);
            System.out.println("(took " + (end - start) + " ms)");
            line = in.nextLine(); }
    }
}