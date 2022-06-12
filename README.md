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
1. -e         Create excel report.
2. -f <arg>   Date from which we will filter your data
3. -h         help
4. -i <arg>   input directory
5. -o <arg>   output directory
6. -r <arg>   report type [1-5]
7. -t <arg>   Date till which we will filter your data



#Przykład 
