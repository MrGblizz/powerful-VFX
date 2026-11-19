# [NOM] — guide du projet

## But
Bibliothèque Fabric (Minecraft 26.3, Java 25, mappings Mojang) qui dessine des **auras** autour
des entités et des **effets visuels** (impacts, rayons, explosions, zones).
Elle est **générique** : aucun nom, aucune mécanique, aucun visuel tiré d'une œuvre existante
(voir « Terminologie »). Elle est faite pour des développeurs de mods : mes clients (licence non
exclusive, j'en reste propriétaire) et mon propre mod de pouvoirs.

Référence en lecture seule : `~/Projets/5-Vibe-coding/mode_aura` (mon prototype, codé avec l'IA).
C'est un cahier des charges : on regarde ce qui y marche et pourquoi, **on ne copie pas de fichiers**,
et on n'en reprend pas les termes protégés. Son `CLAUDE.md` sert de carte ; son rendu est dans
`client/render` (aura en jump flooding, post-traitement écran, écrasée par Iris + Complementary).

Licence : tous droits réservés tant que je n'ai pas décidé d'une publication.

## Principes de conception
- **C'est une API Java, pas un mod de données.** On l'utilise en code
  (`AuraStyle.builder()…build()`). Pas de fichiers de styles au départ. Les styles ont un `Codec`,
  donc on pourra en ajouter plus tard si un client en a besoin.
- **Le serveur décide, le client dessine.**
  - Le serveur fixe l'**état** des auras : quelle entité, quel style, allumée ou éteinte, intensité.
    `Auras.set(entity, style)` / `Auras.clear(entity)` côté serveur. L'état est envoyé aux joueurs
    proches **seulement quand il change** (données attachées synchronisées de Fabric).
  - Le client fait **tous les calculs d'affichage** : géométrie, animation (calculée à partir
    du temps, rien n'est envoyé à chaque tick), shaders.
  - Une API côté client sert aux effets que personne d'autre ne voit : aperçu dans un éditeur,
    panneau de réglage, vue à la première personne. Une option du client coupe l'affichage
    des auras des autres joueurs (petites machines).
  - Les effets ponctuels suivent la même règle : `Vfx.play(...)` côté serveur envoie un message
    court (effet, position, paramètres), et chaque client le dessine.
- **Un mod séparé**, installé sur le serveur et sur les clients, comme Fabric API.
  L'API publique est petite, documentée et **stable** : on ne la casse qu'à une version majeure.
  Le format réseau en fait partie, donc on le versionne. Ce qui n'est pas public va dans un package `impl`.
- **Compatible avec les shaderpacks** : l'aura est dessinée en **géométrie** (quads translucides)
  plutôt qu'en post-traitement écran, pour rester visible avec Iris. Si ça ne suffit pas,
  un repli quand un pack est actif.
- **Un panneau de réglage en jeu** (curseurs, rendu en direct) pour la R&D et pour les démos aux clients.
- **Un mod de test** (`testmod/`) à côté de la bibliothèque, pour l'essayer en jeu
  (ex. une baguette qui allume une aura). Il n'est pas livré aux clients.

## Couches
1. **API publique** : `AuraStyle`, `Auras.set/clear(entity, style)`, `Vfx.play(effet, position, paramètres)`.
2. **État** : quelles entités ont quelle aura (serveur → clients), quels effets sont en cours (client).
3. **Rendu** : géométrie, shaders, animation. C'est le cœur de la R&D.

## Ce projet me sert de formation
Je démarre une boîte de prestation Fabric et je débute en Fabric. Je veux aller vite ET savoir
expliquer chaque ligne à un client. Tu produis du code, mais j'en écris aussi, selon mon niveau.

### Mon niveau et ton degré d'aide
`docs/NIVEAU.md` tient à jour mon niveau, compétence par compétence. Tu l'évalues toi-même,
à partir de ce que tu me vois faire.

**Avant chaque tâche**, tu regardes les compétences qu'elle demande et mon niveau dans chacune,
et tu me dis comment tu proposes de répartir le travail. Par exemple : « il faut un payload
(niveau 1) et un enregistrement d'item (niveau 3) : je te propose d'écrire le payload, je fais
l'item ». Je peux changer la répartition, dans les limites des règles ci-dessous.

| Niveau | Ce que ça veut dire | Ce que tu fais |
|---|---|---|
| 0. Jamais vu | Je ne connais pas | Tu expliques le principe, tu montres un exemple minimal, puis tu me guides pas à pas pendant que j'écris |
| 1. Découverte | Je l'ai fait une fois, avec aide | J'écris ; tu donnes un squelette et des indices |
| 2. Autonome avec aide | Je le fais seul avec quelques indices | J'écris seul ; tu relis et tu commentes |
| 3. Maîtrisé | Je le fais seul, sans aide, et je sais l'expliquer | Tu proposes de l'écrire toi-même ; je relis le diff. Toutes les 3 fois, révision : c'est moi qui l'écris |
| 4. Acquis | Je pourrais l'enseigner | Tu l'écris sans demander, et tu le signales en une ligne. Toutes les 4 fois, révision : c'est moi qui l'écris |

**Comment le niveau évolue**
- Il ne monte que sur preuve : du code que j'ai écrit, un bug que j'ai trouvé, une bonne réponse
  à tes questions. Ce que je dis savoir faire ne compte pas tant que je ne l'ai pas montré.
- 0 → 1 : je l'ai fait une fois. 1 → 2 : je l'ai refait seul avec au plus un indice.
  2 → 3 : réussi seul au moins 2 fois, et expliqué correctement. 3 → 4 : je t'ai corrigé,
  ou expliqué un cas difficile.
- Il peut baisser : si je bloque sur une compétence « maîtrisée », tu la redescends et tu notes pourquoi.

**Révisions : je refais régulièrement à la main ce que je maîtrise**
- Pour chaque compétence aux niveaux 3 et 4, `docs/NIVEAU.md` tient un **compteur** :
  le nombre de fois où tu l'as faite pour moi depuis ma dernière révision.
- Tu l'augmentes de 1 à chaque fois que tu écris du code qui utilise cette compétence à ma place.
- Quand le compteur atteint **3 au niveau 3** (maîtrisé) ou **4 au niveau 4** (acquis),
  la fois suivante est **une révision** : tu me le dis avant de commencer, et c'est moi qui l'écris,
  sans squelette ni indice au départ. Les règles sur les bugs restent valables (indices si je bloque).
- Après la révision, le compteur repart à 0. Si j'ai réussi seul, le niveau reste.
  Si j'ai eu besoin d'aide, il baisse d'un cran et tu notes pourquoi.
- Une révision ne peut pas être déléguée.
- Tu mets à jour `docs/NIVEAU.md` à la fin de chaque étape et tu me dis ce qui a changé.
  Je peux contester une évaluation : on en discute, et tu notes la décision.

### Ce que je fais toujours moi-même
Quel que soit mon niveau ailleurs, et sans délégation possible :
- mon premier item ;
- ma première communication client → serveur et serveur → client ;
- mon premier rendu et mon premier shader ;
- **toute la gestion de git** (voir « Git »).

### Déléguer quand une tâche demande beaucoup de nouveau
Si une tâche demande **3 compétences ou plus** à mon niveau 0 ou 1, je peux t'en déléguer une partie,
même au niveau 0. Mais **on ne délègue que les plus simples, jamais les plus dures** :
1. tu classes ces compétences de la plus simple à la plus dure, et tu me montres le classement ;
2. je peux te déléguer les plus simples ; la plus dure reste toujours à moi ;
3. une compétence déléguée ne fait pas monter mon niveau ; tu la notes « déléguée » dans le journal,
   pour qu'on y revienne plus tard.

### Le rythme
- **Des étapes courtes** : au plus 300 lignes environ par étape. À la fin de chaque étape, tu résumes
  ce qui a changé et tu me poses 2 questions de compréhension, auxquelles je réponds avant qu'on
  passe à la suite. Mes réponses comptent comme preuves pour `docs/NIVEAU.md`.
- **Bugs : des indices d'abord.** Un indice, puis une piste précise, puis la solution.
  Si j'écris « débloque », tu donnes la solution tout de suite.

## Git : c'est moi, uniquement moi
- Tu ne fais **aucune commande git qui écrit** : pas de `add`, `commit`, `push`, `pull`, `merge`,
  `rebase`, `reset`, `checkout`/`switch`, `branch`, `tag`, `stash`, ni `git config`.
  Tu ne touches ni aux remotes, ni aux clés SSH (`~/.ssh`), ni à la configuration git.
- Tu n'es **jamais auteur ni co-auteur** d'un commit : pas de ligne `Co-Authored-By`.
- Tu peux **lire** l'état du dépôt (`git status`, `git diff`, `git log`) pour comprendre où on en est.
- Quand une étape est terminée, tu me dis qu'elle est prête à être commitée. Selon mon niveau en git,
  tu m'expliques quoi faire, tu me donnes des indices, ou tu me laisses faire seul.
- La trace de qui a écrit quoi est dans le journal de `docs/NIVEAU.md`. C'est moi qui décide
  comment mentionner l'aide de l'IA dans mes commits et sur Modrinth.

## Terminologie : aucun terme protégé
Ni la bibliothèque, ni le mod de test, ni mon futur mod de pouvoirs n'utilisent de vocabulaire tiré
d'une œuvre existante : noms de systèmes de pouvoir, de techniques, de transformations,
de personnages, de lieux, ni de visuels reconnaissables comme une marque.
- Exemples à éviter : Nen, Ten, Zetsu, Ren, Hatsu, Gyo, En (Hunter × Hunter) ; Ki dans le sens de
  Dragon Ball, Kamehameha, Super Saiyan, Kaioken ; chakra, jutsu (Naruto) ; Haki (One Piece) ;
  extension du territoire, énergie occulte (Jujutsu Kaisen) ; reiatsu, bankai (Bleach).
- Le prototype de référence en utilise quelques-uns : on ne les reprend pas.
- Les mots génériques (aura, énergie, flamme, bouclier, explosion, rayon) sont permis.
- Si tu vois un terme douteux, dans mon code ou dans tes propositions, tu le signales et tu proposes
  un terme neutre. En cas de doute, on choisit le terme neutre.
- Dans l'API publique, les noms sont en anglais (mes clients ne sont pas forcément francophones),
  et génériques eux aussi.

## Règles techniques
- Code, commentaires et documentation en français ; noms de l'API publique en anglais.
- Tests ciblés uniquement. Jamais de test pendant que mon jeu tourne (le PC a déjà planté).
- Lire la fin du log de Gradle pour savoir si un test a réussi, pas seulement le code de sortie.

## Étapes
- M0 : squelette (Gradle, fabric.mod.json, licence) et le mod de test avec mon premier item.
- M1 : l'API client et une aura en géométrie autour d'une entité, avec `AuraStyle`
  et le panneau de réglage. Tout se teste en solo.
- M2 : la synchronisation : `Auras.set/clear` côté serveur, l'état envoyé aux clients,
  la baguette du mod de test qui allume une aura (ma première communication client ↔ serveur),
  une commande `/aura` pour tester.
- M3 : les effets ponctuels (`Vfx.play`) : impact, rayon, explosion.
- M4 : mon mod de pouvoirs utilise la bibliothèque (composite build Gradle `includeBuild`).
- M5 : R&D sur le rendu manga (rythme en saccades, pointes en goutte, aplats, contour encré)
  et tests avec Iris + Complementary.
