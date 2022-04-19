package pt.ulusofona.aed.deisiRockstar2021;

public class ParseInfo { // Linhas ignoradas e as que estão bem.
    int numLinhasOk;
    int numLinhasIgnored;
    public ParseInfo(int linhasok, int linhasIgnoradas) {
        this.numLinhasOk = linhasok;
        this.numLinhasIgnored = linhasIgnoradas;
    }

    public String toString() {
        return "OK: " + numLinhasOk + ", " + "Ignored: " + numLinhasIgnored;
    }
}