package fr.usmb.m1isc.compilation.tp;

import java_cup.runtime.Symbol;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception  {
		LexicalAnalyzer yy;
		if (args.length > 0)
			yy = new LexicalAnalyzer(new FileReader(args[0])) ;
		else
			yy = new LexicalAnalyzer(new InputStreamReader(System.in)) ;
		@SuppressWarnings("deprecation")
		parser p = new parser(yy);
		Symbol result = p.parse();
		Arbre a = (Arbre)result.value;
		//System.out.print(a.toString());
		try {
			FileWriter myWriter = new FileWriter("TP805.asm");
			myWriter.write(a.generation());
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		System.out.print(a.generation());
    }
}
