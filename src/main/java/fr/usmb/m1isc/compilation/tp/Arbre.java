package fr.usmb.m1isc.compilation.tp;

import static java.util.Objects.isNull;

public class Arbre {

    private String op;
    private Arbre fg,fd;
    private int racine;

    public Arbre(String op,Arbre fg,Arbre fd) {
        this.op = op;
        this.fg = fg;
        this.fd = fd;
    }
    public Arbre(String op,int racine) {
        this.op = op;
        this.racine = racine;

    }
    public Arbre(String op) {
        this.op = op;
    }
    public Arbre() {}

    public int getRacine() {
        return this.racine;
    }

    public String afficheFeuille() {
        if (this.op.equals("int")) {
            return Integer.toString(this.racine);
        }
        else {
            return this.op;
        }
    }

    public void afficheArbre() {
        System.out.print("(" + this.afficheFeuille());
        if (!isNull(this.fd)){
            this.fd.afficheArbre();
        }
        if (!isNull(this.fg)){
            this.fg.afficheArbre();
        }
        System.out.print(")");
    }
}
