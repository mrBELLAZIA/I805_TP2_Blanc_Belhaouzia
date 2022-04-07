package fr.usmb.m1isc.compilation.tp;

import java.util.ArrayList;

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
        if ((this.type == OPERATEUR) || (this.type == LET)) {
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
        if ((this.type == OPERATEUR) || (this.type == LET)) {
            resultat += ")";
        }
        return resultat;
    }

    public void genData(ArrayList<String> listData) {
        if (!isNull(this.fg)) {
            this.fg.genData(listData);
        }
        if (!isNull(this.fd)) {
            this.fd.genData(listData);
        }
        if ((this.type == IDENT) && (!listData.contains(this.racine))) {
            listData.add(this.racine);
        }
    }

    public String genCode() {
        String resultat = "";
        if (!isNull(this.fg)) {
            resultat += this.fg.genCode();
        }
        if (!isNull(this.fd)) {
            resultat += this.fd.genCode();
        }
        if (this.type == ENTIER) {
            resultat += "\tmov eax, " + this.racine + "\n";
            resultat += "\tpush eax\n";
        } else if (this.type == OPERATEUR) {
            resultat += "\tpop eax\n";
            resultat += "\tpop ebx\n";
            if (this.racine.equals("+")) {
                resultat += "\tadd eax, ebx\n";
            } else if (this.racine.equals("-")) {
                resultat += "\tsub ebx, eax\n";
            } else if (this.racine.equals("*")) {
                resultat += "\tmul eax, ebx\n";
            } else if (this.racine.equals("/")) {
                resultat += "\tdiv ebx, eax\n";
            }
        } else if (this.type == LET) {
            resultat += "\tmov eax,"+ "jsp encore"+"\n";
            resultat += "\tmov "+this.fg.racine+",eax \n";
            resultat += this.fd.genCode();
        }
        return resultat;
    }

    public String generation() {
        String resultat = "DATA SEGMENT\n";
        ArrayList<String> listData = new ArrayList<>();
        this.genData(listData);
        for(int i = 0; i < listData.size(); i++){
            resultat += "\t " + listData.get(i) + " DD\n";
        }
        resultat += "DATA ENDS";
        resultat += "CODE SEGMENT\n";
        resultat += this.genCode();
        resultat += "CODE ENDS";
        return resultat;
    }
}
