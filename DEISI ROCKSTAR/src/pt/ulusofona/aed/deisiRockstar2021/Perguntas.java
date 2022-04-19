package pt.ulusofona.aed.deisiRockstar2021;
import java.util.*;

public class Perguntas {
    static int countSongsYear(int ano , LinkedHashMap<String, Song> sons) {
        int count = 0;
        for (Song song : sons.values()){ // passa em todas as musicas
            if (song.lancamento == ano) { // compara o ano da musica com o utilizador intruziu
                count++; // se forem do mesmo ano incrementa o contador
            }
        }
        return count; // retorna o numero de musicas do ano que utilizador introduziu
    }

    static int countDuplicateSongsYear(int ano, LinkedHashMap<String, Song> sons) {
        int count = 0;
        HashMap<String, Song> duplicateSong = new HashMap<>();
        for (Song song : sons.values()){
            if (song.lancamento == ano && duplicateSong.containsKey(song.titulo)){ // Se tiver o mesmo ano e repetido
                count++; // E somar mais 1 ao count
            } else if (song.lancamento == ano){ // Se não
                duplicateSong.put(song.titulo, song); // Adicionar song ao map
            }
        } // o titulo pode estar duplicado mas o id nao pode
        return count;
    }

    static String getArtistForTag(String tag, HashMap<String, HashSet> artistTags, ArrayList<String> listaArtistas) {// recebe uma tag
        StringBuilder res = new StringBuilder();
        ArrayList<String> artistsForTag = new ArrayList<>();

        for(int i = 0; i < listaArtistas.size(); i++){ // percorre a lista dos artista que so tem uma musica
            if (artistTags.get(listaArtistas.get(i)) != null){ // se o artista tiver uma tag
                if (artistTags.get(listaArtistas.get(i)).contains(tag.toUpperCase())){ // e se tiver a tag que o utilizador introduziu
                    artistsForTag.add(listaArtistas.get(i)); // adiciona o artista a um ArrayList
                }
            }
        }
        for (int i = 0; i < artistsForTag.size(); i++) { // percorre todos os artistas que contem essa tag
            res.append(artistsForTag.get(i)); // adiciona o artista á string
            if (!(i == artistsForTag.size() - 1)){ // se nao for o ultimo artista da lista
                res.append(";"); // escreve ; para separar do artista seguinte
            }
        }

        if (res.length() == 0){ // se a tag nao existir
            res.append("No results");
        }
        return res.toString(); // retorna a string com o nome da tag e o nome de cada artista que contem essa tag
    }

    static String getMostDanceable(int anoInicio, int anoFim, int nResultados , LinkedHashMap<String, Song> sons){ // recebe dois anos e o numero de result que vai dar
        int count = 0;
        String string = "";
        ArrayList<Song> arrayResultados = new ArrayList<Song>();
        for (Song song : sons.values()){ // percorre as musicas todas
            if (song.lancamento >= anoInicio && song.lancamento <= anoFim) { // se a musica tiver sida lançada entre o ano de inicio e o ano fim
                arrayResultados.add(song); // adiciona essa musica a um ArrayList
                count++; // e incrementa o contador
            }
        }
        Collections.sort(arrayResultados, Comparator.comparingDouble((Song song) -> song.grauDanca).reversed()); // ordena o arraylist de acordo com o grau de dancibilidade das musicas em ordem decrescente
        for (int i = 0; i < nResultados; i++){ // percorre o numero de resultados que o utilizador quer retornar
            string += arrayResultados.get(i).titulo; // adiciona o nome da musica a uma string
            string += " : ";
            string += arrayResultados.get(i).lancamento; // adiciona o ano da musica a uma string
            string += " : ";
            string += arrayResultados.get(i).grauDanca; // adiciona o grau de dancibilidade da musica a uma string
            string += "\n";
        }
        return string; // retorna a string com os nomes das musicas com maior dancibilidade
    }

    static String getArtistOneSong(int anoInicio, int anoFim, HashMap<String, Song> artistSongs){
        ArrayList<String>artistsInYear = new ArrayList<>();
        String string = "";
        int count=0;
        if (anoFim <= anoInicio){
            return "Invalid period";
        }
        Collections.sort(Main.artistas);
        for (int i=0; i< Main.artistas.size();i++){
            if (artistSongs.containsKey(Main.artistas.get(i))){
                if (artistSongs.get(Main.artistas.get(i)).lancamento >= anoInicio && artistSongs.get(Main.artistas.get(i)).lancamento <= anoFim){
                    if (artistsInYear.contains(Main.artistas.get(i))){
                        artistsInYear.remove(Main.artistas.get(i));
                    }else{
                        artistsInYear.add(Main.artistas.get(i));
                    }
                }
            }
        }
        System.out.println(count);

        for (int i=0; i<artistsInYear.size();i++){
            string += artistsInYear.get(Integer.parseInt(Main.artistas.get(i)));
            string += " | ";
            string += artistsInYear.get(Integer.parseInt(artistSongs.get(Main.artistas.get(i)).titulo));
            string += " | ";
            string += artistSongs.get(Main.artistas.get(i)).lancamento;
            string += "\n";
            count++;
        }
        return string;
    }
    static String getTopArtistWithSongsBetween(int nArtistas, int min, int max){
        return "";
    }

    static String mostFrequentWordsInArtistName(){
        return "";
    }

    static String getUniqueTags(HashMap<String, HashSet> artistTags, ArrayList<String> artistas){ //
        StringBuilder res = new StringBuilder();
        String test="";

        for (int i = 0; i < artistTags.get(artistas).size(); i++){

            if(artistTags.containsValue(test)){
                artistTags.get(artistas).add(test);
            }
        }
        test = artistTags.get(artistas).toString();
        test = test.replace("[", "").trim();
        test = test.replace(" ", "").trim();
        test = test.replace("]", "").trim();
        res.append(test);
        return res.toString();
    }

    static String getUniqueTagsInBetweenYear(int anoInicio, int anoFinal){
        return "";
    }

    static String[] getRisingStars(int anoInicio, int anoFinal, String ordenacao){
        String[] artistas = new String[0];
        return artistas;
    }

    static String addTags(String artista, ArrayList<String> tag, HashMap<String, HashSet> artistTags, ArrayList<String> artistas) { // recebe um artista e uma lista de tags
        StringBuilder res = new StringBuilder();
        String test="", temp="";
        res.append(artista);
        res.append(" | ");

        for (int i = 0; i < tag.size() ; i++){ // percorre a lista de tags que o ultilizador introduziu e mete todas em uppercase
            temp = tag.get(i).toUpperCase();
            tag.remove(i);
            tag.add(temp);
        }

        if (!artistas.contains(artista)) { // se o artista que o utilizador intruduziu nao existir no ficheiro de artista
            return "Inexistent artist";
        }
        if(!artistTags.containsKey(artista)){ // se o hashmap nao contem o artista que o ultilizador introduziu
            artistTags.put(artista, new HashSet()); // adiciona-o
        }
        for (int i = 0; i < tag.size(); i++){ // percorre a lista de tags que o ultilizador introduziu
            test = tag.get(i).toUpperCase(); // se o hashmap nao contem uma das tags
            if(!artistTags.containsValue(test)){ // adiciona essa tag ao hashmap associado ao artista
                artistTags.get(artista).add(test);
                Main.hashTags.add(artista);
            }
        }
        // retirar os caracteres da lista das tags do hashmap
        test = artistTags.get(artista).toString();
        test = test.replace("[", "").trim();
        test = test.replace(" ", "").trim();
        test = test.replace("]", "").trim();
        res.append(test);
        return res.toString(); // retorna a string com o nome do artista e com todas as tags que esse artista tem
    }

    static String removeTags(String artista, ArrayList<String> tag, HashMap<String, HashSet> artistTags, ArrayList<String> artistas) { // recebe artista e a lisat de tags
        StringBuilder res = new StringBuilder();
        String test = "", getTags = "";
        res.append(artista);
        res.append(" | ");

        if (!artistas.contains(artista)) { // se o artista que o utilizador intruduziu nao existir no ficheiro de artista
            return "Inexistent artist";
        }
        if(!artistTags.containsKey(artista)){ // se o hashmap nao contem o artista que o ultilizador introduziu
            artistTags.put(artista, new HashSet()); // adiciona-o
        }
        for (int i = 0; i < tag.size(); i++){ // percorre a lista de tags que o utilizador introduziu
            if(!artistTags.containsValue(tag)){ // se essa tag nao estiver no hashmap
                getTags = tag.get(i).toUpperCase();
                artistTags.get(artista).remove(getTags); // remove a tag ao artista do hashmap
            }
        }
        for (int i = 0; i < artistTags.get(artista).size(); i++){ // percorre todas as tags desse artista
            if (!(test == artistTags.get(artista).toString())){ // se a tag nao for repetida ( nao for igual a tag anterior)
                getTags = artistTags.get(artista).toString(); // adiciona-a a lista
            }
            test = getTags;
            // remove os caracteres
            getTags = getTags.replace("[","").trim();
            getTags = getTags.replace("]","").trim();


            res.append(getTags); // adiciona a tag á string
            if (!(i == artistTags.get(artista).size()-1)){ // se a tag nao for a ultima da lista
                res.append(","); // adiciona , para separar as tags
            }
        }

        if (artistTags.get(artista).isEmpty()){ // se o artista ja nao tiver tags
            test = "No tags";
            res.append(test); // adiciona no tags a string
        }


        return res.toString(); // retorna o nome do artista e todas as tags que esse artista tem
    }

    static String cleanup(){
        return "Musicas removidas: XX;\nArtistas removidos: YY";
    }

    static String topLongInYear(int ano, int duracao, LinkedHashMap<String, Song> sons){  // Se quisermos fazer uma playlist de musicas daquele ano mais longas (depende do que o utilizador introduzir)
        String string = "";

        for (Song song : sons.values()){
            long songMinutes = 0;
            long songSeconds = 0;

            if (song.lancamento == ano && song.duracao >= duracao) {
                songMinutes = (song.duracao / 1000) / 60;
                songSeconds = (song.duracao / 1000) % 60;
                string += song.titulo;
                string += " - ";
                string += songMinutes;
                string += ".";
                string += songSeconds;
                string += ";\n";
            }
        }
        return string;
    }
}