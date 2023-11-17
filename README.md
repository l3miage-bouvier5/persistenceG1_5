# persistenceG1_5
Repository Git du projet de Patrons et Composants

## Description

Ce projet a pour but d'apprendre et d'appliquer les différents design patterns 
en utilisant Java, Maven et Sonar. A chaque itération de ce projet sera ajoutée une nouvelle
fonctionnalité.

## Avancée

Dorénavent, chaque itération est documentée au niveau du dossier [documentation](./documentation) à la racine du projet.

- Itération 1 : L'ajout de la fonctionnalité 'export JSON' qui fonctionne correctement.
- [Itération 2](./documentation/iteration_2.md): L'ajout d'une deuxième fonctionnalité 'CTRL Z' qui pour  le moment ne fonctionne pas correctement. Bien que nous arrivons à supprimer le shape qu'on vient d'ajouter, le problème vient du fait que l'on redessine les figures qui restent par rapport à la frame et pas par rapport au Panel. Donc les formes se redessinent pas aux positions ou elles étaient avant.


## Mise en place du projet
### Prérequis

- Java 8
- Maven 3.6.3
- IDE (IntelliJ IDEA, Eclipse, VSCode, ...)
- StarUML (visualisation des diagrammes)

### Structure du projet
- Le dossier src comprend tous les fichiers source du projet
- Le dossier diagrams contiendra tous les diagrammes requis pour la modélisation du projet

### Installation

- Cloner le projet
- Importer le projet dans votre IDE
- Lancer la commande `mvn clean install` à la racine du projet

## Utilisation
- Lancer le main de la classe `App.java` dans le package `fr.uga.miage.m1`
- Une interface graphique va s'ouvrir
- Suivre les instructions qui s'affichent dans l'interface graphique (jouer avec les formes)

### Auteurs

- Seifeddine Farah
- Bouvier Léo

