# TimeTracker

Aplikacija za praćenje radnih aktivnosti zaposlenih, razvijena kao full-stack web aplikacija sa Spring Boot backendom i React frontendom.

---

## Tehnologije

### Backend
- **Java 17+** sa **Spring Boot 3.4.3**
- **Spring Data JPA** + **Hibernate** (MySQL)
- **Spring Validation** za validaciju podataka
- **SpringDoc OpenAPI 2.8.5** (Swagger UI)
- **SuperCSV 2.4.0** za CSV export
- **Maven** kao build alat

### Frontend
- **React 19** sa **Vite 7**
- **TailwindCSS 4** za stilizovanje
- **TanStack React Query 5** za server state management
- **React Router DOM 7** za rutiranje
- **React Hook Form** + **Yup** za forme i validaciju
- **Axios** za HTTP komunikaciju
- **jsPDF** + **jspdf-autotable** za PDF export
- **React Hot Toast** za notifikacije
- **Lucide React** za ikonice

---

## Arhitektura projekta

```
TimeTracker-dev/
├── back/               # Spring Boot aplikacija
│   └── src/main/java/app/
│       ├── timetracker_controller/     # REST kontroleri
│       ├── timetrack_service/          # Poslovna logika
│       ├── timetrack_repository/       # JPA repozitorijumi
│       ├── timetracker_entity_implemantion/  # JPA entiteti
│       ├── timetracker_dto_implementation/  # DTO klase
│       ├── timetracker_mapper/         # Maperi entitet <-> DTO
│       └── config/                     # CORS konfiguracija
└── front/              # React aplikacija
    └── src/
        ├── api/        # Axios API klijenti
        ├── components/ # Reusable komponente
        ├── hooks/      # Custom React hookovi
        ├── pages/      # Stranice aplikacije
        ├── services/   # Servisni sloj
        └── utils/      # Pomoćne funkcije
```

---

## Domenski model

Aplikacija upravljuje četiri osnovna entiteta:

- **Employee** – zaposleni (ime, prezime, odsek)
- **Department** – odsek/sektor unutar organizacije
- **Project** – projekat na kom zaposleni rade
- **Activity** – aktivnost koju je zaposleni obavio na određenom projektu, sa opisom i vremenskim pečatom

---

## REST API

Backend je dostupan na portu `8080`. Swagger UI dokumentacija: `http://localhost:8080/swagger-ui.html`

### Aktivnosti — `/activities`
| Metoda | Endpoint | Opis |
|--------|----------|------|
| GET | `/activities` | Sve aktivnosti |
| POST | `/activities/create` | Kreiranje aktivnosti |
| PUT | `/activities/{id}` | Izmena aktivnosti |
| DELETE | `/activities/{id}` | Brisanje aktivnosti |
| GET | `/activities/employee/{employeeId}` | Aktivnosti zaposlenog (opcioni filteri: `fromDate`, `toDate`) |
| POST | `/activities/bulk-insert` | Masovno dodavanje aktivnosti |
| GET | `/activities/export-csv` | Eksport u CSV format |
| GET | `/activities/from-last-five-seconds` | Aktivnosti u poslednjih 5 sekundi |

### Zaposleni — `/employees`
| Metoda | Endpoint | Opis |
|--------|----------|------|
| GET | `/employees` | Svi zaposleni |
| GET | `/employees/{id}` | Zaposleni po ID-u |
| POST | `/employees` | Kreiranje zaposlenog |
| PUT | `/employees/{id}` | Izmena zaposlenog |
| DELETE | `/employees/{id}` | Brisanje zaposlenog |
| POST | `/employees/bulk-insert` | Masovno dodavanje |
| GET | `/employees/export-csv` | Eksport u CSV |

### Projekti — `/projects`
| Metoda | Endpoint | Opis |
|--------|----------|------|
| GET | `/projects` | Svi projekti |
| POST | `/projects` | Kreiranje projekta |
| POST | `/projects/bulk-insert` | Masovno dodavanje projekata |

### Odseci — `/departments`
| Metoda | Endpoint | Opis |
|--------|----------|------|
| GET | `/departments` | Svi odseci |
| POST | `/departments` | Kreiranje odseka |

---

## Pokretanje projekta

### Preduslovi
- Java 17+
- Maven 3.8+
- MySQL 8+
- Node.js 18+ i npm

### 1. Baza podataka

Kreirajte MySQL bazu podataka:
```sql
CREATE DATABASE timetracker;
```

Podrazumevane konfiguracije (izmeniti u `back/src/main/resources/application.properties` po potrebi):
```
spring.datasource.url=jdbc:mysql://localhost:3306/timetracker
spring.datasource.username=root
spring.datasource.password=root
```

### 2. Backend

```bash
cd back
./mvnw spring-boot:run
```

Backend će biti dostupan na: `http://localhost:8080`

### 3. Frontend

```bash
cd front
npm install
npm run dev
```

Frontend će biti dostupan na: `http://localhost:5173`

---

## Frontend stranice

| Ruta | Opis |
|------|------|
| `/` | Pregled svih aktivnosti sa filterima |
| `/activities/create` | Forma za kreiranje nove aktivnosti |
| `/activities/:id` | Forma za izmenu postojeće aktivnosti |

---

## Funkcionalnosti

- Pregled, kreiranje, izmena i brisanje aktivnosti
- Filtriranje aktivnosti po zaposlenom i datumskom opsegu
- Masovni (bulk) uvoz podataka za aktivnosti, zaposlene i projekte
- Eksport podataka u CSV format
- Generisanje PDF izveštaja
- Validacija formulara na frontendu i backendu
- Swagger UI dokumentacija API-ja
- Toast notifikacije za povratne informacije korisniku
