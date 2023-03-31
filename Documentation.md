# Serviciu de streaming pentru clipuri, cu planificator pe ore și gestionare useri
## Grupa 30237, Coșarcă Ioan-Cristian

Această documentație are rolul de a ghida navigarea prin utilizarea aplicației

- Funcționalitate / Scop
- Funcționalități Expuse
- Baza de date

## Funcționalitate / Scop

Acest proiect trebuie să descrie o aplicație care să ofere o interfață ce să-i permită unui utilizator să selecteze și să vizioneze clipurile dorite de el.

De asemenea, aplicația trebuie să se descurce în a gestiona mai mulți utilizatori conectați simultan, clipurile fiind disponibile doar între anumite ore și doar pentru un anumit utilizator, pe rând. Restul utilizatorilor trebuie să aștepte până când ora curentă devine ora la care se deblochează videoclipul sau până când utilizatorul curent își încheie vizionarea.

## Funcționalități Expuse

Aplicația trebuie să-i permită unui tip de utilizator să se înregistreze sau să se autentifice, fie că este vorba de un simplu utilizator / client, fie că e vorba de un administrator.

Un administrator ar trebui să poată gestiona clipurile care sunt încărcate / referențiate în aplicație. El poate viziona un videoclip și poate decide dacă va rămâne sau îl poate da jos / elimina.

Un utilizator ar trebui să poată vedea videoclipurile și să poată selecta unul pentru vizualizare. Un videoclip poate fi vizionat dacă ora curentă corespunde intervalului orar între care este disponibil sau dacă utilizatorul care l-a accesat înainte și-s terminat vizionarea. De asemenea, poate fi vizionat doar dacă în momentul curent nu este deja vizionat de un alt utilizator.

## Baza de Date

Baza de Date este împărțită în 5 tabele: User, Admin, Client, Video și History.

User reține toate înformațiile despre un utilizator, câmpuri de informații care ar fi comune între un Client și un Administrator: tip utilizator, nume, email și parolă.

Tabela Client va avea un Foreign Key către tabela User pentru a-și lua informațiile de acolo. În plus, fiecare Client are o vârstă și o țară din care provine (informații ce vor fi folosite pentru recomandări de videoclipuri).

Tabela Admin va avea un Foreign Key către tabela User, deoarece un Administrator este și el un utilizator.

Tabela Video reține toate datele despre un videoclip: nume, canal, tip videoclip, dacă conținutul său este restrâns unui anumit segment de vârstă, link-ul, ora la care începe să fie disponibil, ora la care devine indisponibil și statusul său.

Tabela History conține istoricul tuturor utilizatorilor. Aceasta are două Foreign Keys, id-ul unui utilizator și id-ul unui video. Aceasta va simboliza că utilizatorul (administrator sau client) cu id-ul userID a vizionat videoclipul cu id-ul videoID.

## Endpoints

