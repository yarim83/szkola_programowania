## O Projekcie

Projekt prezetuje obiektową, bazodanową warstwę aplikacji dla szkoły programowania. Aplikacja 
zawiera część potencjalnych funkcjonalności - przechowania rozwiązań do zadań, wykonywanych przez kursantów.

#### DAO - Wzorzec projektowy

Wzorzec projektowy opisujący sposób dostępu do danych. Umieszczanie kodu odpowiedzialnego za dostęp do bazy danych razem z logiką biznesową
programu nie jest dobrą praktyką. Zaleca się wprowadzenie odrębnej warstwy, która zajmuje się wszystkimi aspektami związanymi z 
dostępem do bazy danych.

Zalety:
* Oddzielenie warstwy danych od reszty programu.
* Uniezależnienie warstwy logiki biznesowej od zastosowanego sposobu dostępu do danych.
* Umożliwienie łatwej podmiany sposobu dostępu do danych.
* Ukrycie szczegółów implementacji dostępu do danych.

![Screenshot](https://github.com/yarim83/szkola_programowania/blob/master/src/pl/coderslab/screens/DAO.png)

### Model bazy danych oparty o MySQL

* [MySQL Model](https://github.com/yarim83/szkola_programowania/tree/master/resources) 

### Zastosowane technologie
Ta sekcja przedstawia główne technologie wykorzystane w projekcie.
* [JAVA](https://jquery.com/)
* [DAO](https://www.baeldung.com/java-dao-pattern)
* [JBCrypt](https://mvnrepository.com/artifact/org.mindrot/jbcrypt)
