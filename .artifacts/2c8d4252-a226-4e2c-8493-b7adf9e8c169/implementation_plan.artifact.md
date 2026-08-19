# Implementation Plan - Update Festival Events in DatabaseSeeder

The goal is to replace the current list of initial events in `DatabaseSeeder.kt` with a new, more comprehensive list for the 2026 festival.

## Proposed Changes

### [Data Layer]

#### [MODIFY] [DatabaseSeeder.kt](file:///Users/indioalba/Workspace/Festival/app/src/main/java/com/carbonbyte/sonfiestas/data/local/DatabaseSeeder.kt)
- Update the `initialEvents` list with the new events provided by the user.
- Ensure proper mapping of titles, dates, times, locations, and categories.
- Use `EventCategory` enums to categorize each event.

## Event Mapping Strategy
- **MUSIC**: Orquestas, DJs, Charangas, Tributos, Alba Fest.
- **BULLS**: Encierros, Capeas, Toro de fuego, Bolsín Taurino.
- **SPORTS**: Tenis, Pádel, Fútbol.
- **KIDS**: Gigantes, Cabezudos, Hinchables, Disco kids, Encierro infantil.
- **RELIGIOUS**: Misa, Procesión, Acto institucional.
- **GASTRONOMY**: Degustación, Huevos con chorizo, Chanfaina.
- **OTHER**: Chupinazo, Mercado Medieval, Pregón, Danzas de la Corte.

## Verification Plan

### Manual Verification
- Review the `DatabaseSeeder.kt` file to ensure all events are correctly listed and formatted.
- Ensure the project builds successfully after the changes.
