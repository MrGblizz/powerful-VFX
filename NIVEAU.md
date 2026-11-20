# Mon niveau en dev Fabric

Tenu par Claude, mis à jour à la fin de chaque étape (règles dans `CLAUDE.md`).
Échelle : 0 jamais vu · 1 découverte · 2 autonome avec aide · 3 maîtrisé · 4 acquis.
Tous les niveaux partent à 0 : ils montent sur preuve, dès le premier exercice.
Compteur : nombre de fois où Claude a fait la compétence pour moi depuis ma dernière révision
(niveaux 3 et 4 seulement). À 3 (niveau 3) ou 4 (niveau 4), la fois suivante, c'est moi qui l'écris.

## Grille

| Domaine | Compétence | Niveau | Compteur | Dernière révision | Preuves (date : ce que j'ai fait) |
|---|---|---|---|---|---|
| Outils | Gradle et Loom (build, lancer le jeu, dépendances) | 1 | – | – | 2026-11-19 : a écrit `gradle.properties` (après correction), `settings.gradle` (avec squelette) et les blocs plugins, loom et dependencies de `build.gradle` (avec squelette) ; a expliqué `processResources` correctement. Ne sait pas encore expliquer le bloc `loom` |
| Outils | Lire une erreur et une stacktrace, déboguer | 0 | – | – | |
| Outils | Git (commit, branche, lire un diff) | 1 | – | – | 2026-11-19 : concepts de branche et de conflit bien expliqués ; mais ne sait pas isoler un fichier à commiter (propose `.gitignore` au lieu de `git add`), n'a jamais commité |
| Bases | Java (POO, interfaces, records) | 1 | – | – | 2026-11-19 : interface (contrat, typage, méthodes à implémenter) et classe abstraite compris ; `==` vs `equals` compris en partie, mais ne connaît pas les `record` |
| Outils | Clés SSH et dépôt distant | 0 | – | – | |
| Bases | fabric.mod.json, points d'entrée (main, client) | 0 | – | – | |
| Bases | Enregistrer un item (registre, modèle, texture, traduction) | 0 | – | – | |
| Bases | Commande Brigadier | 0 | – | – | |
| Réseau | Payload client → serveur | 0 | – | – | |
| Réseau | Payload serveur → client | 0 | – | – | |
| Réseau | StreamCodec, threads client/serveur | 0 | – | – | |
| Données | Codec et DynamicOps | 0 | – | – | |
| Données | Attachments synchronisés | 0 | – | – | |
| Rendu | Événements de rendu, matrices, caméra | 0 | – | – | |
| Rendu | Dessiner de la géométrie (VertexConsumer, RenderType) | 0 | – | – | |
| Rendu | Shaders (GLSL, uniformes, intégration au pipeline) | 0 | – | – | |
| Avancé | Mixins | 0 | – | – | |
| Avancé | Gametests | 0 | – | – | |
| Métier | Concevoir une API publique stable | 0 | – | – | |
| Métier | Publier (versions, Modrinth, licence) | 0 | – | – | |

## Défi « 5 jours de suite » : monter un projet Fabric seul

Objectif métier : pouvoir démarrer un projet client de zéro, sans aide. À lancer quand M0 est terminée
(projet Powerful-VFX qui compile avec son premier item). Il sert de preuve pour les compétences
Gradle et Loom, fabric.mod.json, enregistrement d'item et Git.

**Règles**
- 5 jours consécutifs, **un projet neuf par jour**, dans un dossier vide hors de `powerful-VFX`
  (par exemple `~/Projets/exercices/jour-N`). Chaque jour : de zéro à un mod qui compile et ajoute un item.
- Interdit : Claude, le dossier `powerful-VFX`, `cours/`. Autorisé : Internet et la doc officielle (c'est la réalité d'un client).
- Je chronomètre et je note tout : durée, erreurs rencontrées, comment je les ai résolues, nombre de recherches Internet.
- Un jour manqué ou raté : on le note, la série reprend à 1 le lendemain.

**Grille d'évaluation de chaque jour** (Claude relit le résultat, sans l'avoir aidé)

| Critère | Réussi si |
|---|---|
| Squelette | `./gradlew build` donne `BUILD SUCCESSFUL` du premier coup ou après corrections trouvées seul |
| Cohérence | mod id identique dans `fabric.mod.json`, Loom et le code ; package et `group` cohérents |
| Item | enregistré avec modèle, texture et traduction ; il apparaît avec `/give` |
| Git | commits propres, `.gitignore` correct, wrapper ajouté, aucun fichier généré commité |
| Explication | je sais expliquer chaque fichier à voix haute (Claude pose 2 questions) |

**Passage de niveau**
- 3 jours réussis sur 5, dont les 2 derniers : Gradle et Loom passe à **2**.
- 5 jours réussis, avec un temps en baisse et au plus 1 consultation d'aide par jour : passe à **3**.
- Le niveau ne monte pas pour un jour fait avec l'aide de Claude.

**Suivi**

| Jour | Date | Durée | Build OK | Item OK | Git OK | Explications OK | Notes |
|---|---|---|---|---|---|---|---|
| 1 | | | | | | | |
| 2 | | | | | | | |
| 3 | | | | | | | |
| 4 | | | | | | | |
| 5 | | | | | | | |

## Compétences déléguées (à reprendre plus tard)

| Date | Compétence | Tâche | Pourquoi déléguée |
|---|---|---|---|

## Journal
<!-- Une entrée par étape : date, ce qu'on a fait, qui a écrit quoi, niveaux changés et pourquoi. -->

### 2026-11-19 : diagnostic de départ
- Fait : 5 questions (Java, Git, Gradle) + vérification de l'environnement. Aucun code écrit.
- Java : ajout d'une ligne « Java (POO, interfaces, records) », niveau 1. Interface et classe abstraite
  bien décrites ; manque : champs et état d'une classe abstraite, héritage simple vs interfaces multiples,
  `record` (`equals` généré, comparaison par valeur). Pas de niveau 2 : aucun code écrit.
- Git : reste à 0. Bons concepts (branches, conflits), mais la réponse sur « commiter un seul fichier »
  est fausse (`.gitignore` n'est pas fait pour ça).
- Gradle : 0, jamais utilisé consciemment.
- Note : `NIVEAU.md` est à la racine alors que `CLAUDE.md` cite `docs/NIVEAU.md`. À trancher.

### 2026-11-19 : M0, squelette Gradle de la racine
- Écrit par l'utilisateur : `gradle.properties`, `settings.gradle`, `build.gradle` (blocs 1 à 3), avec squelettes et corrections.
  Blocs 4 et 5 recopiés depuis mes exemples (code standard). Commentaires ajoutés par Claude dans `settings.gradle`.
- Écrit par Claude (délégué, simple) : `LICENSE`, `.gitignore`, les `fabric.mod.json`, les 3 classes d'entrée,
  package renommé en `io.github.mrgblizz.powerfulvfx`, cours `cours/01-gradle-squelette.md`.
- Gradle : 0 → 1 (écrit une fois avec aide). Compétence « fabric.mod.json et points d'entrée » : toujours 0, déléguée.
- Questions de compréhension : `processResources` bien expliqué (précision : seule la copie dans le jar est modifiée,
  le fichier source reste intact) ; le mod id a été cité dans `fabric.mod.json` et le bloc `loom`, mais pas dans le code Java
  (`MOD_ID`) ; il a signalé ne pas comprendre le bloc `loom` : à revoir.

### 2026-11-19 : fin du squelette Gradle (M0, partie 1)
- Fait : `testmod/build.gradle` écrit par l'utilisateur (avec corrections sur `plugins` et `project(':')`), wrapper généré,
  premier `./gradlew build` réussi (vérifié par Claude : 2 jars, version injectée dans `fabric.mod.json`).
- Questions : `processResources` bien identifiée ; origine de la valeur (clé `version` de `gradle.properties`) non citée ;
  utilité du `-sources.jar` devinée à moitié (intuition correcte : il sert aux développeurs qui utilisent l'API,
  mais pas pour que le mod fonctionne). Gradle reste à 1.

### 2026-11-20 : quiz de 12 questions (Gradle, Java, Git)
- Résultat : 8 réponses justes (Gradle 1, 2, 4 ; Java 5, 6, 7, 9 ; Git 10), 3 partielles (mod id, ligne `package`, `.gitignore`), 1 « je ne sais pas » (sortir un dossier du suivi).
- Gradle reste à 1 : bon raisonnement (plugin chargé par la racine, chemin de projet, hypothèses de débogage), mais il faut le refaire seul (défi 5 jours).
- Java reste à 1 : concepts solides, pas encore de code écrit seul.
- Git : 0 → 1 (a utilisé `git add`, poussé vers GitHub, lit `AM`) ; reste à voir : `.gitignore`, `git rm --cached`.
- Points à revoir : le mod id apparaît aussi dans le bloc `loom` et dans la constante `MOD_ID` ; la ligne `package` s'écrit avec des points ; différence `cours/` (partout) et `/cours/` (racine seulement).
