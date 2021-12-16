# Enkel Kotlin+Java Spring Boot klient for Maskinporten autentisering

Basert på https://github.com/difi/jwt-grant-generator utgitt av Difi.

---

### Konfigurering

Konfigurering av virksomhetssertifikat / keypair og OAuth2/OpenId Connect tilbyder informasjon håndteres i "client.properties" filen.

Følgende variabler må bli satt:

| Variabelnavn            | Forklaring                                                                                                                                                                         | Eksempel                                                                      |
|-------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------|
| issuer                  | Din klient ID / integrasjons identifikator. Automatisk generert av maskinporten når du registrerer en ny integrasjon på https://selvbetjening-samarbeid-ver2.difi.no/integrations/ |                                                                               |
| audience                | Url til maskinporten-instansen du ønsker å bruke.                                                                                                                                  | Test: https://ver2.maskinporten.no/, prod: https://maskinporten.no/           |
| token.endpoint          | Token endepunkt for maskinporten tjenesten du bruker.                                                                                                                              | Test: https://ver2.maskinporten.no/token, prod: https://maskinporten.no/token |
| scope                   | Hvilke skop du ønsker tilgang til. Mellomroms separert liste, hvor hvert skop har (prefix:skop)                                                                                    | altinn:serviceowner altinn:consentrequests.read                               |
| keystore.file           | Path til keystore filen som inneholder ditt virksomhetssertifiket / keypair. Brukes for å autentisere din organisasjon mot maskinporten.                                           | C:/path/to/file.pfx                                                           |
| keystore.alias          | Alias for ditt virksomhetssertifikats nøkkel                                                                                                                                       | alias                                                                         |
| keystore.password       | Keystore passord                                                                                                                                                                   | passord                                                                       |
| keystore.alias.password | Alias passord, hvis ikke satt spesifikt bruk samme som keystore.password                                                                                                           | passord                                                                       |


Denne eksempel applikasjonen innholder kun ett enkelt kall som er satt mot http://localhost:8080/. Dette må byttes til ønsket api-endepunkt.
Håndtering av resultater er ikke implementert da det vil være avhengig av APIet man kaller på. Foreløpig kan resultat skrives til fil.

https://docs.digdir.no/oidc_sample_jwtgrant_postman.html Kan brukes som inspirasjon til å finne bl.a. keystore alias.