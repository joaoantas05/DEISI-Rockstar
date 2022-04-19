package pt.ulusofona.aed.deisiRockstar2021;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class OutrasFuncoes {
    static String trimTrim(String string) { // retira os caracteres dos artistas
        string = string.replace("\"[", "").trim();
        string = string.replace("]\"", "").trim();
        string = string.replace("'[", "").trim();
        string = string.replace("]'", "").trim();
        string = string.replace("[", "").trim();
        string = string.replace("]", "").trim();
        string = string.replace("\"'", "").trim();
        string = string.replace("'\"", "").trim();
        string = string.replace("\"\"'", "").trim();
        string = string.replace("'\"\"", "").trim();
        string = string.replace("\"\"\"", "\"").trim();
        string = string.replace("\"\"", "").trim();
        string = string.replace("'", "").trim();
        return string;
    }

    static String executeGetArtistsOneSong(String command[]) {
        String[] palavra = (command[1].split(" "));
        int anoIni = Integer.parseInt(palavra[0].trim());
        int anoFim = Integer.parseInt(palavra[1].trim());
        String res = String.valueOf(Perguntas.getArtistOneSong(anoIni, anoFim, Main.artistSongs));
        return res;
    }

    public static String executeAddTags(String[] command) {
        ArrayList<String> tags = new ArrayList<>();
        String array[] = command[1].split(";"); // divide o resto da intrucao por ;
        String nome = array[0].trim(); // primeiro é o artista

        String info;
        for (int i = 0; i < array.length - 1; i++) { // percorrer as tags
            info = array[i + 1].trim();
            tags.add(i, info);
        }
        String res = Perguntas.addTags(nome, tags, Main.artistTags, Main.artistas);
        return res;
    }

    public static String executeRemoveTags(String[] command) {
        ArrayList<String> tags = new ArrayList<>();
        String array[] = command[1].split(";");
        String nome = array[0].trim();
        for (int i = 0; i < array.length - 1; i++) {
            tags.add(i, array[i + 1].trim());
        }
        String res = Perguntas.removeTags(nome, tags, Main.artistTags, Main.artistas);
        return res;
    }

    public static String executeGetArtistsForTag(String[] command) {
        String array[] = command[1].split(";");
        String tagNome = array[0].trim();
        String res = Perguntas.getArtistForTag(tagNome, Main.artistTags, Main.listaArtistas);
        return res;
    }

    public static String executeTopLongInYear(String[] command) {
        String[] palavra = (command[1].split(" "));
        int ano = Integer.parseInt(palavra[0].trim());
        int duracao = Integer.parseInt(palavra[1].trim());
        String res = String.valueOf(Perguntas.topLongInYear(ano, duracao, Main.sons));
        return res;
    }

    public static String executeGetMostDanceble(String[] command) {
        String[] palavra = (command[1].split(" "));
        int anoIni = Integer.parseInt(palavra[0].trim());
        int anoFim = Integer.parseInt(palavra[1].trim());
        int nrRes = Integer.parseInt(palavra[2].trim());
        String res = String.valueOf(Perguntas.getMostDanceable(anoIni, anoFim, nrRes, Main.sons));
        return res;
    }

    public static void loadSongs() {
        try {
            File fileSongs = new File("songs.txt");
            FileInputStream fis = new FileInputStream(fileSongs);
            Scanner leitorSongs = new Scanner(fis);
            Main.LinhasOKSons = 0;
            Main.LinhasIgnoradasSons = 0;
            HashSet<String> musicasIguaisSongs = new HashSet<String>(); // HashSet nao permite duplicados, logo se tiver um id duplicado vai ignorar
            while (leitorSongs.hasNextLine()) {
                String dados[] = leitorSongs.nextLine().split("@");
                if (dados.length != 3 ){
                    Main.LinhasIgnoradasSons++;
                } else {
                    if(musicasIguaisSongs.add(dados[0].trim())){ // hashset
                        Song song = new Song(dados[0].trim(), dados[1].trim(), Integer.parseInt(dados[2].trim()));
                        Main.sons.put(dados[0].trim(), song);
                        Main.LinhasOKSons++;
                        musicasIguaisSongs.add(dados[0].trim()); // hashset
                    }else{ Main.LinhasIgnoradasSons++; }
                }
            }
            leitorSongs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void loadArtists() {
        try {
            File fileArtists = new File("song_artists.txt");
            FileInputStream fis = new FileInputStream(fileArtists);
            Scanner leitorArtists = new Scanner(fis);
            Main.LinhasOKArtista = 0;
            Main.LinhasIgnoradasArtista = 0;
            Main.artistas = new ArrayList<String>();
            while (leitorArtists.hasNextLine()) {
                String dados[] = leitorArtists.nextLine().split("@");
                if (dados.length != 2 || !Main.sons.containsKey(dados[0].trim())) {
                    Main.LinhasIgnoradasArtista++;
                } else {
                    if (dados[0].trim().equals("") || !Main.sons.containsKey(dados[0].trim()) || dados[1].contains("''")){ // se no hashmap dos sons n達o contem o id dos artistas, se a musica com aquele id n達o existir ou um dos artistas estiver vazio (''): ignorar
                        Main.LinhasIgnoradasArtista++;
                    }else{
                        String[] artists = dados[1].split(",");
                        for(String artista : artists) {
                            artista = OutrasFuncoes.trimTrim(artista);
                            Main.sons.get(dados[0].trim()).artistas.add(artista);
                            Main.artistas.add(artista);
                            if (Main.artistSongs.containsKey(artista)){
                                Main.artistSongs.remove(artista);
                            }else{ Main.artistSongs.put(artista, Main.sons.get(dados[0].trim())); }
                            if (!Main.listaArtistas.contains(artista)) {
                                Main.listaArtistas.add(artista);
                            }
                        }
                        Main.LinhasOKArtista++;
                    }
                }
            }
            leitorArtists.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void loadDetails() {
        try {
            File fileDetails = new File("song_details.txt");
            FileInputStream fis = new FileInputStream(fileDetails);
            Scanner leitorDetails = new Scanner(fis);
            Main.LinhasOKDetalhes = 0;
            Main.LinhasIgnoradasDetalhes = 0;
            while (leitorDetails.hasNextLine()) {
                String dados[] = leitorDetails.nextLine().split("@");
                if (dados.length != 7) {
                    Main.LinhasIgnoradasDetalhes++;
                } else {
                    String id = dados[0].trim();
                    if(!id.equals("") && Main.sons.containsKey(dados[0].trim())) { // Se o id não for vazio e existir uma musica com esse id
                        if (Main.sons.get(dados[0].trim()).popular == -10) { // Se os details da musica ainda n達o tiverem sido alterados
                            Main.sons.get(dados[0].trim()).duracao = Integer.parseInt(dados[1].trim());
                            Main.sons.get(dados[0].trim()).letra = Integer.parseInt(dados[2].trim()) == 1;
                            Main.sons.get(dados[0].trim()).popular = Integer.parseInt(dados[3].trim());
                            Main.sons.get(dados[0].trim()).grauDanca = Double.parseDouble(dados[4].trim());
                            Main.sons.get(dados[0].trim()).grauViva = Float.parseFloat(dados[5].trim());
                            Main.sons.get(dados[0].trim()).volume = Float.parseFloat(dados[6].trim());
                            Main.LinhasOKDetalhes++;
                        } else { Main.LinhasIgnoradasDetalhes++; }
                    }else{ Main.LinhasIgnoradasDetalhes++; }
                }
            }
            leitorDetails.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}