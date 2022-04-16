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
            resultat += "("+this.racine;
        } else {
            resultat += " "+this.racine;
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

    // génère la partie data
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

    // génère la partie code
    public String genCode() {

        String resultat = "";
        if (!isNull(this.fg)) {
            resultat += this.fg.genCode();
        }
        if (!isNull(this.fd)) {
            resultat += this.fd.genCode();
        }

        // gère les entiers
        if (this.type == ENTIER) {
            resultat += "\tpush eax\n";
            resultat += "\tmov eax, "+this.racine+"\n";
        }

        // gère les opérateurs (+, -, *, /)
        else if (this.type == OPERATEUR) {
            if (this.racine.equals("+")) {
                resultat += "\tpop ebx\n";
                resultat += "\tadd eax, ebx\n";
            } else if (this.racine.equals("-")) {
                resultat += "\tpop ebx\n";
                resultat += "\tsub ebx, eax\n";
                resultat += "\tmov eax, ebx\n";
            } else if (this.racine.equals("*")) {
                resultat += "\tpop ebx\n";
                resultat += "\tmul eax, ebx\n";
            } else if (this.racine.equals("/")) {
                resultat += "\tpop ebx\n";
                resultat += "\tdiv ebx, eax\n";
                resultat += "\tmov eax, ebx\n";
            }
        }

        // gère les let
        else if (this.type == LET) {
            resultat += "\tmov "+this.fg.racine+",eax\n";
            resultat += "\tmov eax, "+this.fg.racine+"\n";
        }

        // gère les while
        else if(this.type == WHILE){
            resultat += "debut_while_1:\n";

            resultat += "sortie_while_1:\n";
        }

        // gère les input
        else if (this.type == INPUT) {
            resultat += "\tin eax\n";
        }

        // gère les output
        else if(this.type == OUTPUT) {
            resultat += "\tmov eax, "+this.racine+"\n";
            resultat += "\tout eax\n";
        }

        // gère les strictement inférieurs
        else if (this.type == GT) {
            resultat += "\tmov eax,"+this.fg.racine+"\n";
            resultat += "\tpush eax\n";
            resultat += "\tmov eax, "+this.fd.racine +"\n";
            resultat += "\tpop ebx\n";
            resultat += "\tsub eax, ebx\n";
            resultat += "\tjle faux_gt_1\n";
            resultat += "\tmov eax, 1\n";
            resultat += "\tjmp sortie_gt_1\n";
            resultat += "faux_gt_1 :\n";
            resultat += "\tmov eax, 0\n";
            resultat += "sortie_gt_1 :\n";
        }

        // gère les modulos
        else if (this.type == MOD) {
            resultat += "\tmov eax,"+this.fd.racine+"\n";
            resultat += "\tpush eax\n";
            resultat += "\tmov eax, "+this.fg.racine +"\n";
            resultat += "\tpop ebx\n";
            resultat += "\tmov ecx, eax\n";
            resultat += "\tdiv ecx, ebx\n";
            resultat += "\tmul ecx, ebx\n";
            resultat += "\tsub eax, ecx\t";
        }

        return resultat;
    }

    // fonction principale
    public String generation() {
        String resultat = "DATA SEGMENT\n";
        ArrayList<String> listData = new ArrayList<>();
        this.genData(listData);
        for(int i = 0; i < listData.size(); i++){
            resultat += "\t "+listData.get(i)+" DD\n";
        }
        resultat += "DATA ENDS\n";
        resultat += "CODE SEGMENT\n";
        resultat += this.genCode();
        resultat += "CODE ENDS";
        return resultat;
    }
}
