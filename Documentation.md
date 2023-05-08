# Serviciu de streaming pentru clipuri, cu planificator pe ore și gestionare useri
## Grupa 30237, Coșarcă Ioan-Cristian

Această documentație are rolul de a ghida navigarea prin utilizarea aplicației

- Funcționalitate / Scop
- Funcționalități Expuse
- Baza de date
- Endpoints
- Observer Pattern
- Testarea

## Funcționalitate / Scop

Acest proiect trebuie să descrie o aplicație care să ofere o interfață ce să-i permită unui utilizator să selecteze și să vizioneze clipurile dorite de el.

De asemenea, aplicația trebuie să se descurce în a gestiona mai mulți utilizatori conectați simultan, clipurile fiind disponibile doar între anumite ore și doar pentru un anumit utilizator, pe rând. Restul utilizatorilor trebuie să aștepte până când ora curentă devine ora la care se deblochează videoclipul sau până când utilizatorul curent își încheie vizionarea.

## Funcționalități Expuse

Aplicația trebuie să-i permită unui tip de utilizator să se înregistreze sau să se autentifice, fie că este vorba de un simplu utilizator / client, fie că e vorba de un administrator.

Un administrator ar trebui să poată gestiona clipurile care sunt încărcate / referențiate în aplicație. El poate viziona un videoclip și poate decide dacă va rămâne sau îl poate da jos / elimina.

Un utilizator ar trebui să poată vedea videoclipurile și să poată selecta unul pentru vizualizare. Un videoclip poate fi vizionat dacă ora curentă corespunde intervalului orar între care este disponibil sau dacă utilizatorul care l-a accesat înainte și-s terminat vizionarea. De asemenea, poate fi vizionat doar dacă în momentul curent nu este deja vizionat de un alt utilizator.

Când un videoclip devine disponibil pentru vizionare, utilizatorii care așteaptă vor fi notificați.

## Baza de Date

Baza de Date este împărțită în 5 tabele: User, Admin, Client, Video și History.

User reține toate înformațiile despre un utilizator, câmpuri de informații care ar fi comune între un Client și un Administrator: tip utilizator, nume, email și parolă.

Tabela Client va avea un Foreign Key către tabela User pentru a-și lua informațiile de acolo. În plus, fiecare Client are o vârstă și o țară din care provine (informații ce vor fi folosite pentru recomandări de videoclipuri).

Tabela Admin va avea un Foreign Key către tabela User, deoarece un Administrator este și el un utilizator.

Tabela Video reține toate datele despre un videoclip: nume, canal, tip videoclip, dacă conținutul său este restrâns unui anumit segment de vârstă, link-ul, ora la care începe să fie disponibil, ora la care devine indisponibil și statusul său.

Tabela History conține istoricul tuturor utilizatorilor. Aceasta are două Foreign Keys, id-ul unui utilizator și id-ul unui video. Aceasta va simboliza că utilizatorul (administrator sau client) cu id-ul userID a vizionat videoclipul cu id-ul videoID.

## Endpoints

### GET
Admin
- /getAdmins - returnează o listă cu toți administratorii
- /getAdminByID/{id} - returnează un administrator după id-ul specificat
- /getAdminByEmail/{email} - returnează un administrator după emailul specificat

Client
- /getClients - returnează o listă cu toți clienții
- /getClientByID/{id} - returnează un client după id-ul specificat
- /getClientByEmail/{email} - returnează un client după emailul specificat
- /getClientsByAge/{age} - returnează o listă cu toți clienții care au vârsta specificată
- /getClientsByCountry/{country} - returnează o listă cu toți clienții din țara specificată

History
- /getHistory - returnează o listă cu întregul istoric ("global")
- /allVideosWatchedByThisUser/{userID} - returnează o listă cu întregul istoric al utilizatorului cu id-ul specificat
- /allUsersWatchingThisVideo/{videoID} - returnează o listă cu toți utilizatorii care au vizionat videoclipul cu id-ul specificat

Video
- /getVideos - returnează o listă cu toate videoclipurile
- /getVideoByName/{name} - returnează un video după numele specificat
- /getVideosByChannel/{channel} - returnează o listă cu toate videoclipurile care provin de la un canal specificat
- /getVideosByGenre/{genre} - returnează o listă cu toate videoclipurile care sunt de genul specificat
- /getVideosByHour/{startHour} - returnează o listă cu toate videoclipurile care încep la ora specificată

### POST
Admin
- /addAdmin - adaugă un administrator în baza de date

Client
- /addClient - adaugă un client în baza de date

History
- /addHistory - adaugă un videoclip în istoricul unui utilizator

Video
- /addVideo - adaugă un video în baza de date

### PUT
Admin
- /updateAdmin - cu informațiile nou primite, actualizează un administrator unde coincide id-ul

Client
- /updateClient - cu informațiile nou primite, actualizează un client unde coincide id-ul

History
- /updateHistory - schimbă informațiile unei instanțe de istoric

Video
- /updateVideos - cu informațiile nou primite, actualizează un video unde coincide id-ul

### DELETE
Admin
- /deleteAdmin/{id} - șterge un administrator din baza de date cu id-ul specificat

Client
- /deleteClient/{id} - șterge un client din baza de date cu id-ul specificat

History
- /deleteHistoryByID/{id} - șterge o instanță de istoric din baza de date cu id-ul specificat
- /deleteUserHistory/{userID} - șterge întreg istoricul al unui utilizator cu id-ul specificat

Video
- /deleteVideoByID/{id} - șterge un video din baza de date cu id-ul specificat
- /deleteVideoByName/{name} - șterge un video din baza de date cu numele specificat

## Observer Pattern

Această metodă de proiectare este utilă pentru a ilustra relația dintre Clienți și Videoclipuri.

Un Client trebuie să observe starea unui Videoclip pentru a i se permite apoi să îl acceseze sau nu. Dacă un videoclip nu este disponibil dintr-un anumit motiv, nimeni nu îl poate vedea și clienții vor fi anunțați. De asemenea, când un videoclip devine disponibil, toți clienții vor fi notificați.

## Testarea

Pentru testarea funcționalității proiectului s-a folosit JUnit Testing și frameworkul Mokito pentru verificarea că se apelează metodele din clasele care trebuie.
