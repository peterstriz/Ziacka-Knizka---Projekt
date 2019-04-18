# Žiacka Knižka <img src="../master/obrazky/logo.png" alt="logo" width="40"/>

## Ukážkové prihlásenie
* **Ziak:** striz98 heslo

* **Ucitel:** simova heslo

* **Riaditel:** pistek heslo

## Hlavné kritéria:
* **Dedenie:** V Pouzivatel -> Ziak, Ucitel, Riaditel ([tu](../master/src/pouzivatelia/Ziak.java#L20))

* **Polymorfizmus:** ScenaInterface -> nastav() - Každá jedna Scena si nastaví úplne iné hodnoty ([tu](../master/src/guiAplikacnaLogika/Scena.java#L26))

* **Agregácia:** Trieda obsahuje pole Ziak-ov ([tu](../master/src/udaje/Trieda.java#L21))

* **Oddelenie aplikačnej logiky:** Každá Scena má svojho Manazer-a, ktorý vykonáva jednotlivé metódy ([tu](../master/src/guiAplikacnaLogika/ManazerLogin.java))

* **Zapuzdrenie:** Všetky premenné sú private

* **Serializácia:** Pri prihlasovaní a odhlasovaní používateľov ([tu](../master/src/udaje/ZiackaKnizkaSingleton.java#L49-L89))

* **Použitie aspektovo-orientovaného programovania:** Pri počítaní priemeru žiakov ([tu](../master/src/aspekt/PocitaniePriemeru.java))

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

## Dokumentácia:
* [**Zámer projektu**](../master/Zamer%20PeterStriz%20Projekt%20ZiackaKnizka.pdf)

* [**Javadoc**](../master/doc/index.html)

* [**Diagram tried**](../master/Schema.uxf)
