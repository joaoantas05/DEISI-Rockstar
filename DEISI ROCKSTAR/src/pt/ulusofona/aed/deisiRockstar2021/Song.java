package pt.ulusofona.aed.deisiRockstar2021;

import java.util.ArrayList;

public class Song {
    String iD;
    String titulo;
    int lancamento;
    int duracao;
    boolean letra;
    int popular;
    double grauDanca;
    float grauViva;
    float volume;
    ArrayList<String> artistas = new ArrayList<>();

    public Song(String ID, String titulo, int anoLancamento) {
        this.iD = ID;
        this.titulo = titulo;
        this.lancamento = anoLancamento;
        this.popular = -10;
    }

    public Song (String ID, int duracao, boolean letra, int popular, double grauDanca, float grauViva, float volume, ArrayList<String> artistas){
        this.iD = ID;
        this.duracao = duracao;
        this.letra = letra;
        this.popular = popular;
        this.grauDanca = grauDanca;
        this.grauViva = grauViva;
        this.volume = volume;
        this.artistas = artistas;
    }

    public String toString() {
        int count = 0,count2 = 0;
        long minutes = (duracao / 1000) / 60; // converte a duracao para minutos e segundos
        long seconds = (duracao / 1000) % 60;
        String rs, nSongs="";

        nSongs = "(";
        // adiciona os dados da musica a uma string
        rs = iD + " | " + titulo + " | " + lancamento + " | " + minutes + ":" + seconds + " | " + popular + ".0 | ";

        for (String artista : this.artistas) { // percorre a lista de artistas que pertence a musica
            if (count > 0) { // separa os artistas
                rs += " / ";
                nSongs += " / ";
            }
            rs += artista; // adiciona o artista a string

            for (Song song : Main.sons.values()) { // percorre a lista de musicas
                for (String artist : song.artistas) { // percorre a lista de artistas com essa musica
                    if (artist.equals(artista)) { // se o artista pertencer a lista de artistas
                        count2++; // incrementa o contador
                        break;
                    }
                }
            }
            nSongs += count2; // adiciona a string o numero de musicas que esse artista tem
            count2 = 0;
            count++;
        }
        nSongs += ")";
        rs += " | " + nSongs; // se forem varios artistas separa-os

        return rs; // retorna os dados da musica
    }
}