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
        if ((this.type == OPERATEUR)||(this.type == LET)||(this.type == SEMI)) {
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

        // gère les points virgules
        if (this.type == SEMI) {
            return this.fg.genCode() + this.fd.genCode();
        }

        // gère les entiers
        else if ((this.type == ENTIER)||(this.type == IDENT)) {
            //resultat += "\tpush eax\n";
            return "\tmov eax, "+this.racine+"\n";
        }

        // gère les opérateurs (+, -, *, /)
        else if (this.type == OPERATEUR) {
            resultat += this.fg.genCode();
            resultat += "\tpush eax\n";
            resultat += this.fd.genCode();
            resultat += "\tpop ebx\n";
            if (this.racine.equals("+")) {
                resultat += "\tadd eax, ebx\n";
            } else if (this.racine.equals("-")) {
                resultat += "\tsub ebx, eax\n";
                resultat += "\tmov eax, ebx\n";
            } else if (this.racine.equals("*")) {
                resultat += "\tmul eax, ebx\n";
            } else if (this.racine.equals("/")) {
                resultat += "\tdiv ebx, eax\n";
                resultat += "\tmov eax, ebx\n";
            }
            return resultat;
        }

        // gère les let
        else if (this.type == LET) {
            resultat += this.fd.genCode();
            resultat += "\tmov "+this.fg.racine+", eax\n";
            return resultat;
        }

        // gère les while
        else if(this.type == WHILE){
            resultat += "debut_while_1:\n";
            resultat += this.fg.genCode();
            resultat += "\tjz sortie_while_1\n";
            resultat += this.fd.genCode();
            resultat += "\tjmp debut_while_1\n";
            resultat += "sortie_while_1:\n";
            return resultat;
        }

        // gère les input
        else if (this.type == INPUT) {
            return"\tin eax\n";
        }

        // gère les output
        else if(this.type == OUTPUT) {
            resultat += "\tmov eax, "+this.racine+"\n";
            resultat += "\tout eax\n";
            return resultat;
        }

        // gère les strictement inférieurs
        else if (this.type == LT) {
            resultat += this.fg.genCode();
            resultat += "\tpush eax\n";
            resultat += this.fd.genCode();
            resultat += "\tpop ebx\n";
            resultat += "\tsub eax, ebx\n";
            resultat += "\tjle faux_lt_1\n";
            resultat += "\tmov eax, 1\n";
            resultat += "\tjmp sortie_lt_1\n";
            resultat += "faux_lt_1 :\n";
            resultat += "\tmov eax, 0\n";
            resultat += "sortie_lt_1 :\n";
            return resultat;
        }

        // gère les inférieurs ou égal
        else if (this.type == LTE) {
            resultat += this.fg.genCode();
            resultat += "\tpush eax\n";
            resultat += this.fd.genCode();
            resultat += "\tpop ebx\n";
            resultat += "\tsub eax, ebx\n";
            resultat += "\tjl faux_lte_1\n";
            resultat += "\tmov eax, 1\n";
            resultat += "\tjmp sortie_lte_1\n";
            resultat += "faux_lte_1 :\n";
            resultat += "\tmov eax, 0\n";
            resultat += "sortie_lte_1 :\n";
            return resultat;
        }

        // gère les strictements supérieurs
        else if (this.type == GT) {
            resultat += this.fg.genCode();
            resultat += "\tpush eax\n";
            resultat += this.fd.genCode();
            resultat += "\tpop ebx\n";
            resultat += "\tsub eax, ebx\n";
            resultat += "\tjge faux_gt_1\n";
            resultat += "\tmov eax, 1\n";
            resultat += "\tjmp sortie_gt_1\n";
            resultat += "faux_gt_1 :\n";
            resultat += "\tmov eax, 0\n";
            resultat += "sortie_gt_1 :\n";
            return resultat;
        }

        // gère les supérieurs ou égal
        else if (this.type == GTE) {
            resultat += this.fg.genCode();
            resultat += "\tpush eax\n";
            resultat += this.fd.genCode();
            resultat += "\tpop ebx\n";
            resultat += "\tsub eax, ebx\n";
            resultat += "\tjg faux_gte_1\n";
            resultat += "\tmov eax, 1\n";
            resultat += "\tjmp sortie_gte_1\n";
            resultat += "faux_gte_1 :\n";
            resultat += "\tmov eax, 0\n";
            resultat += "sortie_gte_1 :\n";
            return resultat;
        }

        // gère les égal
        else if (this.type == EGAL) {
            resultat += this.fg.genCode();
            resultat += "\tpush eax\n";
            resultat += this.fd.genCode();
            resultat += "\tpop ebx\n";
            resultat += "\tsub eax, ebx\n";
            resultat += "\tjnz faux_egal_1\n";
            resultat += "\tmov eax, 1\n";
            resultat += "\tjmp sortie_egal_1\n";
            resultat += "faux_egal_1 :\n";
            resultat += "\tmov eax, 0\n";
            resultat += "sortie_egal_1 :\n";
            return resultat;
        }

        // gère les modulos
        else if (this.type == MOD) {
            resultat += this.fd.genCode();
            resultat += "\tpush eax\n";
            resultat += this.fg.genCode();
            resultat += "\tpop ebx\n";
            resultat += "\tmov ecx, eax\n";
            resultat += "\tdiv ecx, ebx\n";
            resultat += "\tmul ecx, ebx\n";
            resultat += "\tsub eax, ecx\n";
            return resultat;
        }

        else if(this.type == MOINS_UNAIRE) {
            resultat += this.fg.genCode();
            resultat += "\tmul eax, -1\n";
            return resultat;
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
