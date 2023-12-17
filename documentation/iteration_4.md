# Itération 4

## Introduction
Au niveau de cette itération, nous avons réussi à ajouter la fonctionnalité suivante: l'utilisateur peut désormais importer un fichier XML contenant des shapes et/ou groupes de shapes.

## Principales réalisations
Tout d'abord, pour importer un fichier XML, il suffit de cliquer sur le bouton d'import dans la toolbar, ensuite :
- Sélectionner dans l'arborescence des fichiers le fichier .xml à importer.
- La figure sera visible dans le panel et toutes les opérations sont désormais reálisables.
- Les cas d'échec (par exemple si le format est invalide) sont gérés par un pop up.

Nous avons ensuite créé un fichier .jar contenant les classes qui gèrent l'import et l'export.

A travers `mvn clean install` vous pouvez l'ajouter dans les dépendances du projet.


## Problématiques rencontrées/résolues
Bien que le jar est bien dans nos dépendances,nous n'avons pas réussi à importer les classes concernées dans le projet. Il nous semble que nous avons mal construit le jar.

## Conclusion
En résumé, dans cette itération, nous avons ajouté la fonctionnalité demandée. Nous avons consulté au fur et à mesure l'analyse de Sonar pour garder un code de qualité. Cependant, le résultat actuel présente encore des opportunités d'amélioration qu'on espère atteindre dans la suite.
