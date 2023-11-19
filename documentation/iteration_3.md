# Itération 3

## Introduction
Au niveau de cette itération, nous avons pris en compte les retours ainsi que les bugs/problèmes de l'itération précédente, ensuite nous avons réussi à implémenter les nouvelles fonctionnalités demandées. 
En effet, l'utilisateur peut désormais déplacer un shape, il peut aussi sélectionner des shapes pour les regrouper, et ensuite déplacer ce groupe. Les déplacements peuvent être reversés avec le CTRL+Z.

## Modélisation
Suite au retour de l'itération 2, nous avons mis à jour le diagramme de classe pour qu'il corresponde à la phase de refactoring qui avait pour but de correctement implémenter le patron commande.
Les diagrammes sont désormais en format PNG sur GIT

## Actions qui répondent aux retours
- Une classe Client qui gère les interactions de l'utilisateur est implémentée
- Le client réalise l'ajout et la suppression de shape (undo) à travers la commande AddShape
- La documentation de chaque itération est ajoutée au repository GIT
- Une branche est créée pour tout travail en cours (wip = work in progress) et ensuite mergée dès que ce travail est validé.

## Principales réalisations
Au niveau de cette itération, nous avons introduit les fonctionnalités suivantes:
- Déplacement d'un shape et annulation du déplacement avec un CTRL Z
- Création d'un groupe
  - Sélection (ou désélection) d'un shape avec un clic, le shape sélectionné a des bordures vertes
  - Appui du bouton "Group" qui a été ajouté au panel
- Déplacement de groupes et annulation du déplacement avec un CTRL Z

## Problématiques rencontrées/résolues
Par rapport à l'itération 2
- Nous avons pu faire réapparaître les icônes des boutons
- Nous avons pu résoudre le bug du CTRL Z, et dorénavant, la suppression d'un shape n'entraine pas le déplacement des shapes qui restent sur la figure.

Au niveau de l'itération courante
- Lors du déplacement d'un shape ou d'un groupe de shapes, la frame semble clignoter. Ceci est dû au fait que nous retraçons les shapes tout au long du déplacement, en retraçant la frame complète au lieu de retracer uniquement ce qu'on déplace.
- Si on clique sur un shape dans la figure sans le déplacer, il nous faut deux CTRL-Z consécutifs pour revenir en arrière sur les ajouts/déplacements réalisés sur la frame. Ceci est dû au fait qu'un move est pris en compte alors qu'il n'y en avait pas un visiblement.

## Conclusion
En résumé, dans cette itération, nous avons commencé par appliquer les retours à l'itération précédente et ensuite nous avons ajouté les fonctionnalités demandées. Nous avons consulté au fur et à mesure l'analyse de Sonar pour garder un code de qualité.
Nous aimerons pour la suite pouvoir, en plus de résoudre les bugs indiqués plus haut, de pouvoir déterminer chaque groupe avec une couleur différente. Pour le moment, tous les shapes de tous les groupes ont des bordures vertes, ce qui empêche de distinguer des shapes qui appartiennent à des groupes différents.
