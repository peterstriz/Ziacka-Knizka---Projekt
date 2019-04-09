# Žiacka Knižka

## Hlavné kritéria:
* **Dedenie:** V Pouzivatel -> Ziak, Ucitel, Riaditel

* **Polymorfizmus:** ScenaInterface -> nastav() - Každá jedna Scena si nastaví úplne iné hodnoty

* **Agregácia:** Trieda obsahuje pole Ziak-ov

* **Oddelenie aplikačnej logiky:** Každá Scena má svojho Manazer-a, ktorý vykonáva jednotlivé metódy

* **Zapuzdrenie:** Všetky premenné sú private

## Ďalšie kritériá:
* **Použitie návrhových vzorov:** 
  * Strategy: Na prepínanie medzi jednotlivými scénami
  * Visitor: Pri login aby som vedel že na akú scénu sa mám prepnúť podľa typu používateľa
* **Multithreading:** Pri serializácii údajov
* **Vhniezdená trieda:** Pri multithreading-u
* **Explicitné použitie RTTI:** V RiaditelManazer pri vytváraní nových používateľov
<<<<<<< HEAD
* **Použitie lambda výrazov:** 
  * V gui pri ActionListeneroch v Button 
  * Pri vytváraní novej Nite pri serializacií
* **Použitie vlastnej výnimky:** V Manazer pre Ucitel na otestovanie či slovo nieje v neplatnom formáte
=======
* **Použitie lambda výrazov:** V gui pri ActionListeneroch v Button
>>>>>>> c217fbe10e203b238b75e4c27b37af25d7e7872d

## Dokumentácia:
* **Zámer projektu**
* **Javadoc**
<<<<<<< HEAD
* **Diagram tried**
=======
* **Diagram tried**
>>>>>>> c217fbe10e203b238b75e4c27b37af25d7e7872d
