# Projet Programmation Orientée Objet:
# Dukes Of Realm

### Lancement Du Jeu

Pour lancer le jeu, il faut lancer la class Main.java située dans ./basic_src/SampleGame/Main.java
Les différentes options de jeu disponibles sont disponibles dans ./basic_src/SampleGame/Settings.java

### Règle Du Jeu


Le but pour chaque Duc est de conquérir les chateaux de tous les autres joueurs présents sur la carte.

La conquète de chaque joueur n'implique pas la conquète de chaque chateau. En effet les chateaux neutres ne sont pas nécessaires à la conquète.

Chaque joueur est représenté par une couleur et les chateaux noirs sont des chateaux neutres, c'est à dire sans possibilité d'action de conquète.

Le joueur humain est représenté par un Chateau de couleur bleue. Les autres couleurs seront des joueurs IA.

Chaque joueur peut créer des unités, et les diriger sur un autre chateau, ennemi ou alié.



### Fonctionnalités implémentées et points à revoir

Le joueur peut alors récupérer les informations de chaque chateau avec un clique droit sur chacun. Il affiche alors:
	-Le propriétaire du chateau
	-Sa trésorerie
	-Son revenu (et son niveau)
	-Le nombre de soldats par catégorie


Si il possède le chateau, il peut également décider de former de nouvelles unités ou d'améliorer son chateau au niveau superieur.
Il peut alors choisir entre trois types d'unités:
	-Le piquier
	-Le chevalier
	-L'onagre

Les statistiques de chacun (vitesse, dégats, pv....) sont disponibles dans les classes ./basic_src/SampleGame/army/soldiers/<Nom_Unité>.java

Avec un clique gauche, le joueur peut choisir un chateau pour envoyer des unités depuis celui-ci. Le jeu se met alors en pause, et le joueur peut décider à quel chateau envoyer des troupes.
Lorsqu'il clique sur un autre chateau, le joueur choisit le nombre de troupes à envoyer, et de quel type.
Après avoir validé son choix, les soldats sortiront par la porte du chateau pour se diriger vers la cible choisie.
Le joueur ne peut annuler le mouvement des soldats, ni les rediriger, mais peut annuler son intention en cliquant à nouveau sur le chateau de base.

Le joueur peut à tout moment appuyer sur la barre espace pour mettre/enlever la pause.

La sauvegarde ne fonctionne pas.
Nous n'avons pas trouvé de moyen pour sauvegarder des objets javafx, et nous avions trop d'objets différents pour trouver une alternative.
Les boutons sont néanmoins affichés (mais non reliés)