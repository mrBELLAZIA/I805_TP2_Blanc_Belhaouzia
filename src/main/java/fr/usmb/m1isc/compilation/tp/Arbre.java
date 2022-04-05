package fr.usmb.m1isc.compilation.tp;

import java.util.Stack;

import static fr.usmb.m1isc.compilation.tp.NodeType.*;
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
        if (this.type == OPERATEUR) {
            resultat += "(" + this.racine;
        } else {
            resultat += " " + this.racine;
        }
        if (!isNull(this.fg)) {
            resultat += this.fg.toString();
        }
        if (!isNull(this.fd)) {
            resultat += this.fd.toString();
        }
        if (this.type == OPERATEUR) {
            resultat += ")";
        }
        return resultat;
    }

    public String genCode() {
        String resultat = "";
        if (!isNull(this.fg)) {
            resultat += this.fg.genCode();
        }
        if (!isNull(this.fd)) {
            resultat += this.fd.genCode();
        }
        switch (this.type) {
            case ENTIER:
                resultat += "\tmov eax, " + this.racine + "\n";
                resultat += "\tpush eax\n";
            case OPERATEUR:
                if (this.racine.equals("+")) {
                    resultat += "\tpop eax\n";
                    resultat += "\tpop ebx\n";
                    resultat += "\tadd eax, ebx\n";
                } else if (this.racine.equals("-")) {
                    resultat += "\tpop eax\n";
                    resultat += "\tpop ebx\n";
                    resultat += "\tsub ebx, eax\n";
                } else if (this.racine.equals("*")) {
                    resultat += "\tpop eax\n";
                    resultat += "\tpop ebx\n";
                    resultat += "\tmul eax, ebx\n";
                } else if (this.racine.equals("/")) {
                    resultat += "\tpop eax\n";
                    resultat += "\tpop ebx\n";
                    resultat += "\tdiv ebx, eax";
                }
        }
        return resultat;
    }

    public String generation() {
        String resultat = "DATA SEGMENT\n";
        resultat += "DATA ENDS\n";
        resultat += "CODE SEGMENT\n";
        resultat += this.genCode();
        resultat += "CODE ENDS";
        return resultat;
    }
}
