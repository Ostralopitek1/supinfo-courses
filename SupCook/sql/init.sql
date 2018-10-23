/* First, launch the server then run this script */

USE supfood;

INSERT INTO users VALUES
  (1,
   'user@user.com',
   'firstName',
   'lastName',
   'MTIzNDU2Nzg5', /*Password = 123456789*/
   'supinfo',
   'user1');


INSERT INTO categories VALUES
  (NULL,
   'Recettes du chef'),
 (NULL,
  'Coup de coeur'),
 (NULL,
  'Sauces'),
 (NULL,
  'Original'),
 (NULL,
  'Pizza'),
 (NULL,
  'Pates')
;

INSERT INTO marks VALUES
(1, 3, 7, NULL),
(2, 4, 1, 1),
(8, 3, 2, 1),
(17, 2, 5, 1),
(18, 1, 4, 1);

   INSERT INTO recipes VALUES
(NULL , 540, 'A base d''une délicieuse pâte à pizza maison avec une sauce tomate minute, de la mozza di buffala et des tranches de pepperoni.', 1, 'Pizza Pepperoni, tomate- mozza', 600, 5, 1, b'1'),
(NULL , 15, 'Le polpettone est à base de boeuf haché, parmesan et mie de pain aux aromates et servi avec des champignons de saison sautés aux pousses d''épinard.', 2, 'Pain de viande à l''italienne', 30, 4, 1, b'1'),
(NULL , 20, 'Un beau gratin de courge butternut monté comme une lasagne. Une crème de parmesan onctueuse remplace la béchamel classique et des graines de courges torréfiées pour donner du croquant; vous allez adorer ce beau plat aux premiers senteurs d''automne.', 3, 'Gratin de courge d''automne', 30, 2, 1, b'1'),
(NULL, 20, 'En 45 minutes vous allez réaliser une moussaka grecque traditionnelle à l''agneau et aux aubergines. Un plat unique aux saveurs méditerranéens pour vos belles tablées en famille ou entre amis.', 3, 'Moussaka traditionnelle', 30, 1, 1, b'1'),
(NULL, 20, 'Voici LA recette traditionnelle des lasagne à la bolognaise que vous recherchiez depuis toujours. En plus d''être irrésistiblement délicieuse, cette recette vous permettra d''apprendre à réaliser une sauce béchamel, cuire des feuilles de lasagne, déglacer avec du vin rouge. Mamma Mia vous allez vous régaler !', 0, 'Les lasagnes', 30, 6, 1, b'1'),
(NULL, 20, 'Pour que la recette soit encore plus légère, faire une béchamel avec de la Maïzena', 1, 'Crêpes jambon béchamel', 30, 3, 1, b'1'),
(NULL, 30, 'Voilà une recette de brioche que je fais depuis une bonne quinzaine d''années et qui est la base des pains au lait chez les boulangers (apprise au CAP Boulanger-Pâtissier). Sa mie est moelleuse et aérée et sa texture fond dans la bouche.', 2, 'Brioche tressée de mon enfance', 45, 4, 1, b'0'),
(NULL, 15, 'Découvrez la recette de Bretzel, spécialité allemande salée qui fait tomber les petits comme les grands. Très facile à faire pour épater vos convives.', 0, 'Bretzel', 30, 4, 1, b'1'),
(NULL, 0, 'Pour les apéritifs entre amis !', 0, 'Toast provençal', 20, 4, 1, b'1'),
(NULL, 45, 'Découvrez la recette de la Quiche lorraine facile et délicieuse qui ravira toute la famille.', 1, 'Quiche lorraine', 15, 2, 1, b'0'),
(NULL, 45, 'Découvrez la bonne recette du Poulet au curry et lait de coco, un mets en sauce ultra savoureux à déguster avec un bon riz parfumé.', 3, 'Poulet au curry et lait de coco', 15, 1, 1, b'1'),
(NULL, 15, 'Idéal lors d''une invitation de dernière minute.', 0, 'Pâtes aux petits lardons', 5, 6, 1, b'0'),
(NULL, 30, 'Une pâte à pizza facile à réaliser.', 2, 'Pâte à pizza fine', 30, 5, 1, b'1');


INSERT INTO pictures VALUES
  (NULL,
   'https://img.aws.livestrongcdn.com/ls-article-image-673/ds-photo/getty/article/115/213/179267699.jpg',
   1, b'1'),
  (NULL,
   'https://www.atelierdeschefs.com/media/courslive3-b1472',
   2, b'1'),
  (NULL,
   'https://www.atelierdeschefs.com/media/courslive3-b987',
   3, b'1'),
  (NULL,
   'https://www.atelierdeschefs.com/media/courslive3-b931',
   4, b'1'),
  (NULL,
   'https://www.atelierdeschefs.com/media/courslive3-b843',
   5, b'1'),
  (NULL,
   'http://static.cuisineaz.com/610x610/i135035-crepes-au-jambon-et-a-la-bechamel.jpeg',
   6, b'1'),
  (NULL,
   'http://img-3.journaldesfemmes.fr/ux276DsgJ9DovoSfuIS0li67PKw=/748x499/smart/9c079f88cd284089ac1decb54a50fdfa/recipe-jdf/355517.jpg',
   7, b'1'),
  (NULL,
   'http://img-3.journaldesfemmes.fr/UkKyjIIZDn7Rr4SwYsjXJSrTatQ=/748x499/smart/acdb9d5fed0a4a36a82b1b4113202f0e/recipe-jdf/10027649.jpg',
   8, b'1'),
  (NULL,
   'http://img-3.journaldesfemmes.fr/qRV4-x-ipckXsh31gyFNzDWK9zY=/748x499/smart/009cd68ebd754cffbf9ebedcb5805f70/recipe-jdf/301654.jpg',
   9, b'1'),
  (NULL,
   'http://img-3.journaldesfemmes.fr/dpDbw8ms85e_fPvZUmyPtZ1hozg=/748x499/smart/5a1c637d7ef0426784dad14c29aaff55/recipe-jdf/10025089.jpg',
   10, b'1'),
  (NULL,
   'http://img-3.journaldesfemmes.fr/lF7ltlSR0HqgZl4j4TvmBFdVhUs=/748x499/smart/1caa08eee72d47549def35eb70035ae0/recipe-jdf/320303.jpg',
   11, b'1'),
  (NULL,
   'http://img-3.journaldesfemmes.fr/FdyX41KAR6yieYZMbNKNxSQEL-M=/748x499/smart/963b974e4ad54d3e916af050c079a4d5/recipe-jdf/338694.jpg',
   12, b'1'),
  (NULL,
   'https://image.afcdn.com/recipe/20171206/75873_w420h344c1cx2456cy1632cxt0cyt0cxb4912cyb3264.jpg',
   13, b'1');

   INSERT INTO products VALUES
(NULL , 'farine', 1, b'1'),
(NULL, 'chorizo', 1, b'1'),
(NULL, 'fromage', 1, b'1'),
(NULL, 'tomate', 1, b'1'),
(NULL, 'farine', 2, b'1'),
(NULL, 'oeuf', 2, b'1'),
(NULL, 'lardons', 2, b'1'),
(NULL, 'salade', 3, b'1'),
(NULL, 'farine', 3, b'1'),
(NULL, 'boeuf', 4, b'1'),
(NULL, 'fromage', 4, b'1'),
(NULL, 'pates', 4, b'1'),
(NULL, 'farine', 5, b'1'),
(NULL, 'jambon', 5, b'1'),
(NULL, 'fromage', 5, b'1'),
(NULL, 'farine', 6, b'1'),
(NULL, 'sel', 6, b'1'),
(NULL, 'boeuf', 7, b'1'),
(NULL, 'tomates', 7, b'1'),
(NULL, 'fromage', 7, b'1'),
(NULL, 'poulet', 8, b'1'),
(NULL, 'curry', 8, b'1');
