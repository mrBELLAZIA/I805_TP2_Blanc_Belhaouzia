# Juliette BLANC - Zohir BELHAOUZIA

# TP Compilation : Génération d'arbres abstraits

Les points qui ont été abordés et qui sont fonctionnels sont les suivants :
- la gestion des points virgules
- la gestion des entiers et des identificateur
- la gestion des opérateurs (+, -, *, /)
- la gestion des let
- la gestion des while et des if
- la gestion des input et des output
- la gestion des opérateurs de comparaison (<, <=, >, >=, =)
- la gestion des modulo
- la gestion des moins unaires
- la gestion des opérateurs binaires (and, or, not)

Il est cependant impossible d'utiliser plusieurs fois un while, un if, etc dans la même expression.

Fonctions pour tester les operateurs :

- pour tester le LT,IF:
```
  let a = input;
  let b = input;
  if (a<b) then (output a) else (output b).
```
- pour tester le GT,AND,GTE:
```
  let a = input;
  let b = input;
  let c = input;
  if ((a>b) and (b>=c)) then (output a) else (output c).
```
- pour tester le NOT,OR,LTE:
 ```
  let a = input;
  let b = input;
  let c = input;
  if (not(a>b) and (b<=c)) then (output a) else (output c).
 ```
- pour tester le WHILE:
```
let a = input;
let b = input;
while (0 < b)
do (let aux=(a mod b); let a=b; let b=aux );
output a.
```

