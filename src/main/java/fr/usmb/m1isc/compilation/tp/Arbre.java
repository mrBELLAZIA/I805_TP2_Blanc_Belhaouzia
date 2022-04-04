package fr.usmb.m1isc.compilation.tp;

import static java.util.Objects.isNull;

public class Arbre {

    private NodeType type;
    private String racine;
    private Arbre fg,fd;

    public Arbre(NodeType type, String racine, Arbre fg, Arbre fd) {
        this.type = type;
        this.racine = racine;
        this.fg = fg;
        this.fd = fd;
    }
    public Arbre(NodeType type, String racine) {
        this.type = type;
        this.racine = racine;
    }
    public Arbre() {}

    public String getRacine() {
        return this.racine;
    }

    public String toString() {
        String resultat = "";
        if (this.type == NodeType.OPERATEUR) {
            resultat += "(" + this.racine;
        } else {
            resultat += " " + this.racine;
        }
        if (!isNull(this.fg)){
            resultat += this.fg.toString();
        }
        if (!isNull(this.fd)){
            resultat += this.fd.toString();
        }
        if (this.type == NodeType.OPERATEUR) {
            resultat += ")";
        }
        return resultat;
    }
}
