** Import the project as maven project** </br>

```
To run project using cmd line : mvn spring-boot:run
IDE: Eclipse - Import project as existing maven project
JDK: 1.8
Database: H2

```

** Below it the curl to update the data in h2 database**

```
curl --location --request POST 'http://localhost:8080/trade' \
--header 'Content-Type: application/json' \
--data-raw '{
    "tradeId": "T1",
    "version": 1,
    "counterParty": "counterParty3",
    "bookId": "bookId",
    "maturityDate": "2022-05-22",
    "expiredFlag": "N"
}'
```