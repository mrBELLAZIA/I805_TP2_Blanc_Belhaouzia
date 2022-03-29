package fr.usmb.m1isc.compilation.tp;

public class Arbre {

    private NodeType type;
    private Arbre fg,fd;
    private int nb;

    public Arbre(NodeType type,Arbre fg,Arbre fd) {
        this.type = type;
        this.fg = fg;
        this.fd = fd;
    }
    public Arbre(NodeType type,int nb) {
        this.type = type;
        this.nb = nb;

    }
    public Arbre(NodeType type) {
        this.type = type;
    }

}
