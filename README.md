Report Monster 5000

ReadMe.txt

Ver. 1.0.
11062022

#Opis projektu
Celem projektu jest przygotowanie prostego narzędzia do raportowania czasu pracy
spędzonego w projektach przez zaspół programistyczny.
Dane wejściowe zawierające zarejsetrowane godziny są zaczytywane z plików .xls
znajdujących się w katalogu sieciowym.
Program jest uruchamiany z konsoli, użytkownik wpisuje do linii komend wywołanie
progamu oraz listę parametrów.
Program generuje syntetyczne raporty, które są wyświetlane w konsoli, moga być także
eksportowane do pliku excel.
Program posiada bazowa obsługe błędów.

#Technologie
Java 11
Apache Commons CLI
Apache POI

#Lista raportów
1. Liczba godzin wg pracowników za dany okres.
2. Liczba godzin przepracowanych wg projektów za dany okres.
3. Liczba godzin w poszczególnych projektach wg pracowników za dany okres.
4. Liczba godzin wg tasków za dany okres.
5. Wykresy poswięconego czasu wg projektów.

#Parametry wywołania
-




#Przykład 



# Obsługiwane wyjątki plików wejściowych
- Sprawdzenie pustych wierszy,
- Sprawdzenie czy czas trawnia zadania nie jest większy niż 24h
- Sprawdzenie czy czas trwania zadania jest dodatni
- Sprawdzenie czy opis zadania jest wpisany, jeżeli nie przyjmuje się domyślną wartośc NO DATA
- Błędy związane z wpisaniem tekstu w polu data