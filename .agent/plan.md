# Project Plan

App Name: Fiestas
Target: Village of Alba de Tormes.
Feature: Display festival events categorized by day and type.
Data Source: A .db file hosted at a public URL that overwrites the local database on update.
Event Data provided:
Sábado, 11 de octubre: Feria del Barro (All day), 16:00h Charanga, 17:00h Toro de Cajón, Capea, 22:00h Encierro nocturno, 22:15h Capea.
Domingo, 12 de octubre: Feria del Barro, 17:00h Tennis/Padel finals.
Lunes, 13 de octubre: 18:00h Play Back Infantil.
Martes, 14 de octubre: 11:00h Elderly recognition, 12:00h Santa Teresa departure, 12:30h Mass, 17:30h Flower offering, 18:00h Chupinazo/DJs, 19:30h Rosary, 20:00h Eucharist, 21:30h Pregón, 22:00h Toro de fuego, 22:15h Orquesta, 00:30h Toro de fuego, DJs.
Miércoles, 15 de octubre: 11:00-14:00 Pasacalles, 12:30h Mass, 13:00h Cabezudos, 14:00-17:00 Pasacalles, 17:00h Recognition, 18:00h Rosary, 18:30h Procession, 20:00h Mass, 21:00h Tribute concert, 22:00h Toro de fuego.
Jueves, 16 de octubre (Día del Mayor): 11:00h Meeting/Exhibition, 11:30h Charanga, 12:30h Mass, 13:45h Sweets/Dance, 14:30h Pasacalles, 15:00h Paella, 16:00h Golden Weddings, 16:30h Bingo, 18:00h DJ, 19:30h Chocolate/Rosary, 20:00h Mass.
Viernes, 17 de octubre: 10:00h Reading, 12:30h Mass, 13:30h Pasacalles, 17:00h Humor Amarillo, 19:30h Rosary, 20:00h Mass, 21:00h Play Back Adults, Toro de fuego, 23:45h Verbena, 02:30h Toro de fuego, DJs.
Sábado, 18 de octubre (Día de las Peñas): 07:30h Pasacalles, 07:45h Breakfast, 08:30h Encierro, 08:45h Capea, 10:30h Chess, 12:00h Football, 12:30h Mass, 13:00h Cabezudos, 14:00h Paella, 16:00h Football, 17:30h Cuts contest, 18:00h Futsal, 18:00h Museum visit, 19:30h Rosary, 20:00h Mass, 22:00h Correfoc, 00:00h Verbena, 02:00h Toro de fuego, DJs.
Domingo, 19 de octubre (Domingo de las mozas): 10:30h Pasacalles, 11:00h Encierro, 11:15h Capea, 12:00h Musical Mamma Mia, 12:30h Mini bueyes, 13:00h Mass, 13:30h Cabezudos, 14:00h Pasacalles, 18:00h Cancer manifesto, 19:30h Musical/Rosary, 20:00h Mass, 21:00h Toro de fuego, 21:30h Tribute concert.
Lunes, 20 de octubre (Día de la Mujer y el Deporte): 11:00h Meeting/Exhibition, 12:30h Mass, 13:45h Sweets/Dance, 14:30h Pasacalles, 15:00h Arroz a la Zamorana, 16:30h Bingo/Futsal, 18:00h DJ, 18:30h Botigol, 19:00h Basket, 19:30h Chocolate/Rosary, 20:00h Mass/Triples contest.
Martes, 21 de octubre (Día del Niño): 12:30h Mass, 15:30-18:30 Inflatables, 17:00-18:30 Workshops, 18:30h Snack, 19:00h Carretón run, 19:30h Kids music/Rosary, 20:00h Mass, 21:00h Toro de fuego.
Miércoles, 22 de octubre (Día de la Octava): 12:30h Mass, 18:00h Rosary, 18:30h Mass, 19:00h Procession, 21:30h Toro de fuego, 22:00h Performance, 23:45h Toro de fuego, 00:00h Quema de la capilla, Final bomb.

UI Requirement: Icons for each event category (Religious, Bulls, Music, Sports, Kids, Gastronomy, etc.). Vibrant Material 3 design. Full Edge-to-Edge. Adaptive icon.
Technical: Room database, public URL for DB update.

## Project Brief

# Project Brief: Fiestas (Alba de Tormes)

## Features
1. **Categorized Event Schedule**: Browse the full festival program organized by day, featuring distinct Material 3 icons for categories such as Religious, Bulls,
 Music, Sports, Kids, and Gastronomy.
2. **Remote Data Synchronization**: Automatically fetch and update the local event database from a public URL to ensure users always have the most current schedule.
3. **Smart Filtering**: Quickly navigate through the extensive program by filtering events based on their type or the specific day
 of the festival.
4. **Vibrant Material 3 Experience**: A high-energy, edge-to-edge interface designed with a bold color palette and an adaptive icon tailored for the Alba de Tormes identity.

## High-Level Tech Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material 3
- **Local Persistence**: Room Database (via **KSP**)
- **Asynchronous Logic**: Kotlin Coroutines & Flow
- **Networking**: Retrofit & OkHttp (for database file synchronization)
- **Image/Icon Loading**: Coil

## Implementation Steps
**Total Duration:** 16m 50s

### Task_1_DataPersistenceAndSync: Setup Room database schema and synchronization logic. Define the Event entity with fields for title, date, time, and category. Implement a Repository to handle downloading a remote .db file and replacing the local database file.
- **Status:** COMPLETED
- **Updates:** I have successfully completed Task 1: Data Persistence and Sync.
- **Acceptance Criteria:**
  - Room database schema defined
  - Repository implements DB download and replacement logic
  - Project builds successfully
  - Initial data structure matches provided event data
- **Duration:** 9m 20s

### Task_2_UIFoundationAndTheme: Implement the application's visual foundation using Material 3. Create a vibrant color scheme (light and dark modes) and enable full Edge-to-Edge display. Define domain models and a mapping utility to associate event categories (e.g., Religious, Bulls, Music) with appropriate Material icons.
- **Status:** COMPLETED
- **Updates:** I have successfully completed Task 2: UI Foundation and Theme.
- **Acceptance Criteria:**
  - Vibrant Material 3 theme configured
  - Edge-to-Edge display implemented
  - Category-to-icon mapping utility created
  - UI theme previews look correct
- **Duration:** 4m 59s

### Task_3_MainEventsScreen: Build the main festival schedule screen using Jetpack Compose. Display events grouped by day in a scrollable list. Each event item should show the time, title, and category icon. Implement a filtering mechanism to allow users to filter events by their type (category).
- **Status:** COMPLETED
- **Updates:** I have successfully completed Task 3: Main Events Screen.
- **Acceptance Criteria:**
  - Events displayed correctly grouped by date
  - Category icons visible for each event
  - Filtering by event type is functional
  - UI matches Material 3 guidelines
- **Duration:** 1m 35s

### Task_4_AppIconAndFinalVerification: Create an adaptive app icon reflecting the festival theme. Perform a final review of the application to ensure stability, verify all requirements are met (especially the data sync and display for Alba de Tormes), and ensure no crashes occur.
- **Status:** COMPLETED
- **Updates:** I have successfully completed Task 4: App Icon and Final Verification.
- Adaptive app icon implemented.
- Application runs without crashes and is stable.
- UI alignment with user requirements verified: event grouping by day, category filtering, and Material 3 guidelines.
- Final build passes.
- Critic agent has evaluated the application and confirmed its stability and functionality.
- All acceptance criteria for the task are met.
- Project is complete.
- **Acceptance Criteria:**
  - Adaptive app icon implemented
  - Application runs without crashes
  - UI alignment with user requirements verified
  - Final build passes
- **Duration:** 56s

