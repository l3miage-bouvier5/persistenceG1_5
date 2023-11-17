# Document de conception: Visiteur - Itération 2



## Introduction


Jusqu’à présent, notre projet consiste en une interface graphique qui permet d’ajouter des formes géométriques. Au niveau de la première itération, nous avons ajouté une fonctionnalité qui permet à l’utilisateur d’exporter sa figure en JSON (nous souhaitons pour la suite permettre également un export en XML). Actuellement, suite à la deuxième itération, l’utilisateur peut supprimer une forme qu’il vient d’ajouter en cliquant sur ‘CTRL Z’.
Ce document explique l’état de notre projet au niveau de cette deuxième itération.


Un dépot GIT a été créé, muni d’un README qui vous guidera pour installer et mettre en place le projet:  https://github.com/l3miage-bouvier5/persistenceG1_5
Nous décrirons ci-dessous notre modélisation en diagramme de classes et de séquences. Ces deux derniers représentent uniquement les classes et étapes nécessaires pour utiliser la fonctionnalité Undo (CTRL Z). Vous trouverez ces diagrammes sur GIT dans le dossier ‘diagrams’ à la racine du projet.

Finalement, notre projet est connecté sur Sonar et nous consultons au fur et à mesure la qualité de notre code.



## Modélisation

Diagramme de classes
Dans ce diagramme, nous avons appliqué le patron “command” pour implémenter la fonctionnalité Undo. Dans notre situation, le client est implémenté dans la classe GUIHelper parce que c'est là où toutes les commandes sont créées. La commande concrète est évidemment la classe UndoCommand où s’exécute la fonction provoquée par le ‘CTRL Z’. Le receiver est dans la classe JDrawingFrame qui reçoit l’action undo. Le rôle d’invoker est implémenté dans la classe Invoker qui prend la liste des commandes que le client souhaite éxécuter.

Diagramme de séquences
Au niveau de ce diagramme, nous avons schématisé le déroulement du programme dans le main. Ce diagramme de séquence ne représente qu’une interaction entre l’utilisateur et l’interface  : Ajouter une figure puis faire un “CTRL Z”.
Nous avons également ajouté en bleu un ‘utilisateur’ pour modéliser le cas où on a recours à la fonctionnalité Undo.


## Problématiques rencontrées

- Au niveau de l’implémentation de la fonctionnalité Undo, le premier problème survenu est celui des icônes des figures qui ne s’affichent pas sur le toolbar. Nous avons à la place des petits carrés gris.

- Bien que l'invocation du Undo supprime le dernier shape ajouté, celle-ci déplace les shapes qui restent dans le panel. La raison derrière ce problème est que nous supprimons le dernier élément de la liste des formes visibles et que nous peignons à nouveau le panel sans respect de l’échelle. Nous souhaitons dans l’idéal pouvoir uniquement effacer le dernier shape tracé sans toucher aux autres, mais le lien entre la liste et le panel reste artificiel.

## Conclusion

Nous avons réussi à intégrer deux fonctionnalités dans le projet jusqu’à présent. Nous aimerions dans la suite améliorer l’interface sans augmenter la dette technique et en gardant par la suite une bonne qualité de notre code. Nous sommes actuellement aussi à la recherche de plugin qui convertit du code Java en diagramme de séquence et qui pourrait être largement utile pour la suite.
