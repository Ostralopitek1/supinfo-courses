# 3JVA - SupFood

## Introduction
L'API REST et le webapp ont étés mis dans le même projet par soucis de simplicité de rendu et de tests. Les deux entitées sont bien distinctes dans le code (seulement les entitées sont partagées)
* 171994 - Adrien BEHADIR
* 213639 - Valentin LEROY
* 218295 - Benjamin MATHIAS
* 220921 - Romain PERRIN

## Pré-requis
#### Logiciels
* Tomcat 8.0.x
* mysql

#### Librairies
Dans le repertoire /lib

* hibernate 3
* gson 2.8.2
* jersey 1.19.1
* jstl 1.2
* mysql-connector-java-5.1.32

## Installation de l'environnement de développement
1. Lancer le script `sql/setup.sql` en tant qu'utilisateur `root` de la base de données MySql.
2. Ajouter l'ensemble des librairies du dossier `lib` à votre projet.
3. Editer les informations de connexion à la base de données dans le fichier `/src/META-INF/persistence.xml`
4. Se connecter sur http://localhost:8080(URL du serveur Tomcat) et créer un nouvel utilisateur
5. Lancer le script `sql/init.sql` pour charger des données de test
