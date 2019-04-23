# Žiacka Knižka <img src="../master/obrazky/logo.png" alt="logo" width="40"/> - Peter Stríž

## Ako program používať?
- Po spustení programu zadá daný používateľ svoje prihlasovacie údaje a bude presmerovaný na jemu prislúchajúce okno. Všetci používatelia budú mať rovnaké menu v ktorom budú vidieť svoje meno a rôzne nastavenia. ![hlavne_menu](../master/obrazky/Hlavne_menu.png)

- Žiak si môže prezerať svoje známky v predmete ktorý si vyberie. ![ziak_okno](../master/obrazky/ziak_okno.png)

- Učiteľ môže sledovať, upravovať a pridávať známky, len žiakom ktorých vyučuje. ![ucitel_okno](../master/obrazky/ucitel_okno.PNG)

- Riaditeľ môže vytvárať nových používateľov a nové triedy, ktorým môže neskôr priradiť žiakov a učiteľa. ![riaditel_okno](../master/obrazky/riaditel_okno.PNG)

## Ukážkové prihlasovacie údaje
* **Žiak:** striz98 heslo

* **Učiteľ:** simova heslo

* **Riaditeľ:** pistek heslo

## Hlavné kritéria:
* **Dedenie:** V Pouzivatel -> Ziak, Ucitel, Riaditel ([tu](../master/src/pouzivatelia/Ziak.java#L20))

* **Polymorfizmus:** ScenaInterface -> nastav() - Každá jedna Scena si nastaví úplne iné hodnoty ([tu](../master/src/guiAplikacnaLogika/Scena.java#L26))

* **Agregácia:** Trieda obsahuje pole Ziak-ov ([tu](../master/src/udaje/Trieda.java#L21))

* **Oddelenie aplikačnej logiky:** Každá Scena má svojho Manazer-a, ktorý vykonáva jednotlivé metódy ([tu](../master/src/guiAplikacnaLogika/ManazerLogin.java))

* **Zapuzdrenie:** Všetky premenné sú private

* **Serializácia:** Pri prihlasovaní a odhlasovaní používateľov ([tu](../master/src/udaje/ZiackaKnizkaSingleton.java#L49-L89))

## Ďalšie kritériá:
* **Použitie návrhových vzorov:** 
  * Strategy: Na prepínanie medzi jednotlivými scénami ([tu](../master/src/guiAplikacnaLogika/ManazerLogin.java#L38-L40), [tu](../master/src/guiAplikacnaLogika/Scena.java#L13-L29) a [tu](../master/src/gui/ScenaUcitelHlavna.java#L49-L60))
  * Visitor: Pri login aby som vedel že na akú scénu sa mám prepnúť podľa typu používateľa ([tu](../master/src/guiAplikacnaLogika/ManazerLogin.java#L27-L54) a [tu](../master/src/pouzivatelia/Ziak.java#L103-L105))

* **Multithreading:** Pri serializácii údajov ([tu](../master/src/udaje/ZiackaKnizkaSingleton.java#L50-L61))

* **Vhniezdená trieda:** Pri vlastnej výnimke ([tu](../master/src/guiAplikacnaLogika/ManazerUcitel.java#L28-L32))

* **Explicitné použitie RTTI:** V RiaditelManazer pri vytváraní nových používateľov ([tu](../master/src/guiAplikacnaLogika/ManazerRiaditel.java#L48-L62))

* **Použitie lambda výrazov:** 
  * V gui pri ActionListeneroch v Button 
  * Pri vytváraní novej Nite pri serializacií  ([tu](../master/src/udaje/ZiackaKnizkaSingleton.java#L50-L61))

* **Použitie vlastnej výnimky:** V Manazer pre Ucitel na otestovanie či slovo nieje v neplatnom formáte ([tu](../master/src/guiAplikacnaLogika/ManazerUcitel.java#L28-#L44))

* **Použitie aspektovo-orientovaného programovania:** Pri počítaní priemeru žiakov ([tu](../master/src/aspekt/PocitaniePriemeru.java))
 
## Dokumentácia:
* [**Zámer projektu**](../master/Zamer%20PeterStriz%20Projekt%20ZiackaKnizka.pdf)

* [**Javadoc**](../master/doc/index.html)

* [**Diagram tried**](../master/Schema.uxf) <details> <summary>Klik!</summary> <img src="../master/obrazky/schema.png" alt="schema"/> </details>

## História commitov
1. Initial commit
2. prvotne pridavanie classov a packageov
3. pridana zakladna struktura 'znamky', a constructor, upravene 'predmet' a pridany constructor
4. pridany gitignore
5. pridana class 'Trieda', premenovane nazvy class-ov, pridany zakladny tes
t vypisu mena 
6. pridane zakladne GUI, a pridana class na 'userlogin'
7. pridany userlogin, ktory vrati objekt prihlaseneho pouzivatela
8. pridany zamer projektu
9. zmeneny sposob zapamatavania si pouzivatelov
10. login okno a normalne okno sa meni podla toho ci je niekto prihlaseny, p
ridane construcory v 'ziak' pre 'predmet'
11. pridany druhy 'scene', zmemene vsetky 'array' -> 'arrayList'
12. 'znamky' su vypisane po prihlaseni v tabulke, 'ucitel' a 'ziak' su odter
az pod interface 'pouzivatel', pridane pridavanie predmetov a znamok ku konkretn
ym 'ziakom'
13. pridane vykreslovanie 'tabulky' pre 'ucitela' ked sa prihlasi podla toho
 ktorych studentov vyucuje; pokus o serializaciu (neuspesne)
14. refaktor v sposobe interakcie medzi 'ziak', 'ucitel', 'trieda'; v 'znamk
a' pridany konvertor 'String -> Date'; lahsi pristup v 'ziackaKnizka' na nacitav
anie 'pouzivatelov'
15. 'tabulka' sa da editovat 'ucitelom', ktory vie taktiez pridat novu 'znma
ku' 'ziakovi'
16. sceny su teraz rozdelene vo viacerych suboroch, a pouzivaju navrhovy vzo
r Strategy
17. pridany navrhovy vzor Singleton na pamatanie si Stage, ZiackaKnizka; odd
elenie GUI od Aplikacknej casti; pridany uplne zakladny MenuBar a obrazky ku nem
u
18. zmenene nazvy clssov; pridany Riaditel ktory vie vytvorit noveho Pouziva
tela, a vie ho pridat do Tried
19. Riaditel vie priradit k Triede Ucitela; UMLET pridany, ale este prazdny
20. pridana serializacia, pri odhlasovani Pouzivatelov
21. Riaditel vie pridavat Predmety do Triedy
22. pridane Menu a refaktor vo vykreslovani ZiakScena a UcitelScena
23. pridany navrhovy vzor Visitor na vytvaranie novych scen v ManazerLogin; 
pridany diagram tried
24. kazdy Pouzivatel uz moze pouzivat Menu, v ktorom si vie vyhladat ineho P
ouzivatela a jeho email
25. udaje z UserLogin su odteraz v OsobneUdaje; pridany javadoc pre package 
Pouzivatel a ZiackaKnizka
26. oddelenie aplikacnej logiky od user interface; pridany javadoc aj do gui
; oddelenie class-ov do package-ov pre lepsiu prehladnost
27. quick fix pri nacitavani novych pouzivatelov
28. Update README.md
29. vlastna vynimka pri testovanie slova v ManazerUcitel; novy Thread je ter
az vytvoreny pomocou lambda vyrazu
30. updatovane README.md
31. Pouzivatelia si vedia menit svoj email a heslo
32. pridane logo pre ZackuKnizku; pridane okomentovanie pre zvysok kodu; upr
aveny diagram tried pre lepsiu prehladnost
33. updatovane Readme; pridany aspekt na pocitanie priemeru znamok
34. opraveny nespravny nazov priecinka s pouzivatelmi
35. pridane obrazky na dokumentaciu
36. updatovane Readme
