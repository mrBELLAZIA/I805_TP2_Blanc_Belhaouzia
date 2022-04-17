# Juliette BLANC - Zohir BELHAOUZIA

# TP Compilation : Génération d'arbres abstraits

Les points qui ont été abordés et qui sont fonctionnels sont les suivants :
[x] la gestion des points virgules
[x] la gestion des entiers et des identificateur
[x] la gestion des opérateurs (+, -, *, /)
[x] la gestion des let
[x] la gestion des while et des if
[x] la gestion des input et des output
[x] la gestion des opérateurs de comparaison (<, <=, >, >=, =)
[x] la gestion des modulo
[x] la gestion des moins unaires
[x] la gestion des opérateurs binaires (and, or, not)

Il est cependant impossible d'utiliser plusieurs fois un while, un if, etc dans la même expression.

Fonctions pour tester les operateurs :

- pour tester le LT, IF:
```
  let a = input;
  let b = input;
  if (a<b) then (output a) else (output b).
```
- pour tester le GT, AND, GTE:
```
  let a = input;
  let b = input;
  let c = input;
  if ((a>b) and (b>=c)) then (output a) else (output c).
```
- pour tester le NOT, OR, LTE:
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

