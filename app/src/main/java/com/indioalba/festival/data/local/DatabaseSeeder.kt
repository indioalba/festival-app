package com.indioalba.festival.data.local

import com.indioalba.festival.data.model.Event
import com.indioalba.festival.data.model.EventCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DatabaseSeeder {
    suspend fun seedIfEmpty(eventDao: EventDao) = withContext(Dispatchers.IO) {
        if (eventDao.getAnyEvent() == null) {
            eventDao.insertAll(initialEvents)
        }
    }

    private val initialEvents = listOf(
        // Sábado, 11 de octubre
        Event(title = "Feria del Barro", date = "2025-10-11", time = "Todo el día", location= "Plaza de Toros", category = EventCategory.OTHER.name),
        Event(title = "Charanga", date = "2025-10-11", time = "16:00h", category = EventCategory.MUSIC.name),
        Event(title = "Toro de Cajón, Capea", date = "2025-10-11", time = "17:00h", category = EventCategory.BULLS.name, location = "Plaza de Toro", imageUrl = "https://albadetormes.com/opencms/export/sites/albadetormes/.Archivos/Imagenes/2025/fiestasagosto/cabeceratransverberacion2025.png"),
        Event(title = "Encierro nocturno", date = "2025-10-11", time = "22:00h", category = EventCategory.BULLS.name, imageUrl = "https://s1.elespanol.com/2021/10/16/actualidad/619948594_211821965_1706x960.jpg"),
        Event(title = "Capea", date = "2025-10-11", time = "22:15h", category = EventCategory.BULLS.name, imageUrl = "https://s1.elespanol.com/2021/10/16/actualidad/619948594_211821965_1706x960.jpg"),
        
        // Domingo, 12 de octubre
        Event(title = "Feria del Barro", date = "2025-10-12", time = "Todo el día", category = EventCategory.OTHER.name),
        Event(title = "Finales de Tenis/Padel", date = "2025-10-12", time = "17:00h", category = EventCategory.SPORTS.name),
        
        // Lunes, 13 de octubre
        Event(title = "Play Back Infantil", date = "2025-10-13", time = "18:00h", category = EventCategory.KIDS.name),
        
        // Martes, 14 de octubre
        Event(title = "Reconocimiento a mayores", date = "2025-10-14", time = "11:00h", category = EventCategory.OTHER.name),
        Event(title = "Salida de Santa Teresa", date = "2025-10-14", time = "12:00h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Misa", date = "2025-10-14", time = "12:30h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Ofrenda floral", date = "2025-10-14", time = "17:30h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Chupinazo/DJs", date = "2025-10-14", time = "18:00h", category = EventCategory.MUSIC.name),
        Event(title = "Santo Rosario", date = "2025-10-14", time = "19:30h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Eucaristía", date = "2025-10-14", time = "20:00h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Pregón", date = "2025-10-14", time = "21:30h", category = EventCategory.OTHER.name),
        Event(title = "Toro de fuego", date = "2025-10-14", time = "22:00h", category = EventCategory.BULLS.name, imageUrl = "https://s1.elespanol.com/2021/10/16/actualidad/619948594_211821965_1706x960.jpg"),
        Event(title = "Orquesta", date = "2025-10-14", time = "22:15h", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego", date = "2025-10-14", time = "00:30h", category = EventCategory.BULLS.name, imageUrl = "https://s1.elespanol.com/2021/10/16/actualidad/619948594_211821965_1706x960.jpg"),
        Event(title = "DJs", date = "2025-10-14", time = "00:45h", category = EventCategory.MUSIC.name),
        
        // Miércoles, 15 de octubre
        Event(title = "Pasacalles", date = "2025-10-15", time = "11:00-14:00", category = EventCategory.MUSIC.name),
        Event(title = "Misa", date = "2025-10-15", time = "12:30h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Cabezudos", date = "2025-10-15", time = "13:00h", category = EventCategory.KIDS.name),
        Event(title = "Pasacalles", date = "2025-10-15", time = "14:00-17:00", category = EventCategory.MUSIC.name),
        Event(title = "Reconocimiento", date = "2025-10-15", time = "17:00h", category = EventCategory.OTHER.name),
        Event(title = "Santo Rosario", date = "2025-10-15", time = "18:00h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Procesión", date = "2025-10-15", time = "18:30h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Misa", date = "2025-10-15", time = "20:00h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Concierto tributo", date = "2025-10-15", time = "21:00h", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego", date = "2025-10-15", time = "22:00h", category = EventCategory.BULLS.name),

        // Jueves, 16 de octubre (Día del Mayor)
        Event(title = "Encuentro/Exposición", date = "2025-10-16", time = "11:00h", category = EventCategory.OTHER.name),
        Event(title = "Charanga", date = "2025-10-16", time = "11:30h", category = EventCategory.MUSIC.name),
        Event(title = "Misa", date = "2025-10-16", time = "12:30h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Dulces/Baile", date = "2025-10-16", time = "13:45h", category = EventCategory.GASTRONOMY.name),
        Event(title = "Pasacalles", date = "2025-10-16", time = "14:30h", category = EventCategory.MUSIC.name),
        Event(title = "Paella", date = "2025-10-16", time = "15:00h", category = EventCategory.GASTRONOMY.name),
        Event(title = "Bodas de Oro", date = "2025-10-16", time = "16:00h", category = EventCategory.OTHER.name),
        Event(title = "Bingo", date = "2025-10-16", time = "16:30h", category = EventCategory.OTHER.name),
        Event(title = "DJ", date = "2025-10-16", time = "18:00h", category = EventCategory.MUSIC.name),
        Event(title = "Chocolate/Rosario", date = "2025-10-16", time = "19:30h", category = EventCategory.GASTRONOMY.name),
        Event(title = "Misa", date = "2025-10-16", time = "20:00h", category = EventCategory.RELIGIOUS.name),

        // Viernes, 17 de octubre
        Event(title = "Lectura", date = "2025-10-17", time = "10:00h", category = EventCategory.OTHER.name),
        Event(title = "Misa", date = "2025-10-17", time = "12:30h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Pasacalles", date = "2025-10-17", time = "13:30h", category = EventCategory.MUSIC.name),
        Event(title = "Humor Amarillo", date = "2025-10-17", time = "17:00h", category = EventCategory.OTHER.name),
        Event(title = "Rosario", date = "2025-10-17", time = "19:30h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Misa", date = "2025-10-17", time = "20:00h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Play Back Adultos", date = "2025-10-17", time = "21:00h", category = EventCategory.OTHER.name),
        Event(title = "Toro de fuego", date = "2025-10-17", time = "22:00h", category = EventCategory.BULLS.name),
        Event(title = "Verbena", date = "2025-10-17", time = "23:45h", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego", date = "2025-10-17", time = "02:30h", category = EventCategory.BULLS.name),
        Event(title = "DJs", date = "2025-10-17", time = "02:45h", category = EventCategory.MUSIC.name),

        // Sábado, 18 de octubre (Día de las Peñas)
        Event(title = "Pasacalles", date = "2025-10-18", time = "07:30h", category = EventCategory.MUSIC.name),
        Event(title = "Desayuno", date = "2025-10-18", time = "07:45h", category = EventCategory.GASTRONOMY.name),
        Event(title = "Encierro", date = "2025-10-18", time = "08:30h", category = EventCategory.BULLS.name),
        Event(title = "Capea", date = "2025-10-18", time = "08:45h", category = EventCategory.BULLS.name),
        Event(title = "Ajedrez", date = "2025-10-18", time = "10:30h", category = EventCategory.SPORTS.name),
        Event(title = "Fútbol", date = "2025-10-18", time = "12:00h", category = EventCategory.SPORTS.name),
        Event(title = "Misa", date = "2025-10-18", time = "12:30h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Cabezudos", date = "2025-10-18", time = "13:00h", category = EventCategory.KIDS.name),
        Event(title = "Paella", date = "2025-10-18", time = "14:00h", category = EventCategory.GASTRONOMY.name),
        Event(title = "Fútbol", date = "2025-10-18", time = "16:00h", category = EventCategory.SPORTS.name),
        Event(title = "Concurso de cortes", date = "2025-10-18", time = "17:30h", category = EventCategory.BULLS.name),
        Event(title = "Fútbol Sala", date = "2025-10-18", time = "18:00h", category = EventCategory.SPORTS.name),
        Event(title = "Visita Museo", date = "2025-10-18", time = "18:00h", category = EventCategory.OTHER.name),
        Event(title = "Rosario", date = "2025-10-18", time = "19:30h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Misa", date = "2025-10-18", time = "20:00h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Correfoc", date = "2025-10-18", time = "22:00h", category = EventCategory.OTHER.name),
        Event(title = "Verbena", date = "2025-10-18", time = "00:00h", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego", date = "2025-10-18", time = "02:00h", category = EventCategory.BULLS.name),
        Event(title = "DJs", date = "2025-10-18", time = "02:15h", category = EventCategory.MUSIC.name),

        // Domingo, 19 de octubre (Domingo de las mozas)
        Event(title = "Pasacalles", date = "2025-10-19", time = "10:30h", category = EventCategory.MUSIC.name),
        Event(title = "Encierro", date = "2025-10-19", time = "11:00h", category = EventCategory.BULLS.name),
        Event(title = "Capea", date = "2025-10-19", time = "11:15h", category = EventCategory.BULLS.name),
        Event(title = "Musical Mamma Mia", date = "2025-10-19", time = "12:00h", category = EventCategory.MUSIC.name),
        Event(title = "Mini bueyes", date = "2025-10-19", time = "12:30h", category = EventCategory.KIDS.name),
        Event(title = "Misa", date = "2025-10-19", time = "13:00h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Cabezudos", date = "2025-10-19", time = "13:30h", category = EventCategory.KIDS.name),
        Event(title = "Pasacalles", date = "2025-10-19", time = "14:00h", category = EventCategory.MUSIC.name),
        Event(title = "Manifiesto Cáncer", date = "2025-10-19", time = "18:00h", category = EventCategory.OTHER.name),
        Event(title = "Musical/Rosario", date = "2025-10-19", time = "19:30h", category = EventCategory.MUSIC.name),
        Event(title = "Misa", date = "2025-10-19", time = "20:00h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Toro de fuego", date = "2025-10-19", time = "21:00h", category = EventCategory.BULLS.name),
        Event(title = "Concierto Tributo", date = "2025-10-19", time = "21:30h", category = EventCategory.MUSIC.name),

        // Lunes, 20 de octubre (Día de la Mujer y el Deporte)
        Event(title = "Encuentro/Exposición", date = "2025-10-20", time = "11:00h", category = EventCategory.OTHER.name),
        Event(title = "Misa", date = "2025-10-20", time = "12:30h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Dulces/Baile", date = "2025-10-20", time = "13:45h", category = EventCategory.GASTRONOMY.name),
        Event(title = "Pasacalles", date = "2025-10-20", time = "14:30h", category = EventCategory.MUSIC.name),
        Event(title = "Arroz a la Zamorana", date = "2025-10-20", time = "15:00h", category = EventCategory.GASTRONOMY.name),
        Event(title = "Bingo/Fútbol Sala", date = "2025-10-20", time = "16:30h", category = EventCategory.SPORTS.name),
        Event(title = "DJ", date = "2025-10-20", time = "18:00h", category = EventCategory.MUSIC.name),
        Event(title = "Botigol", date = "2025-10-20", time = "18:30h", category = EventCategory.SPORTS.name),
        Event(title = "Basket", date = "2025-10-20", time = "19:00h", category = EventCategory.SPORTS.name),
        Event(title = "Chocolate/Rosario", date = "2025-10-20", time = "19:30h", category = EventCategory.GASTRONOMY.name),
        Event(title = "Misa/Concurso Triples", date = "2025-10-20", time = "20:00h", category = EventCategory.SPORTS.name),

        // Martes, 21 de octubre (Día del Niño)
        Event(title = "Misa", date = "2025-10-21", time = "12:30h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Hinchables", date = "2025-10-21", time = "15:30-18:30", category = EventCategory.KIDS.name),
        Event(title = "Talleres", date = "2025-10-21", time = "17:00-18:30", category = EventCategory.KIDS.name),
        Event(title = "Merienda", date = "2025-10-21", time = "18:30h", category = EventCategory.GASTRONOMY.name),
        Event(title = "Encierro carretón", date = "2025-10-21", time = "19:00h", category = EventCategory.KIDS.name),
        Event(title = "Música infantil/Rosario", date = "2025-10-21", time = "19:30h", category = EventCategory.KIDS.name),
        Event(title = "Misa", date = "2025-10-21", time = "20:00h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Toro de fuego", date = "2025-10-21", time = "21:00h", category = EventCategory.BULLS.name),

        // Miércoles, 22 de octubre (Día de la Octava)
        Event(title = "Misa", date = "2025-10-22", time = "12:30h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Rosario", date = "2025-10-22", time = "18:00h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Misa", date = "2025-10-22", time = "18:30h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Procesión", date = "2025-10-22", time = "19:00h", category = EventCategory.RELIGIOUS.name),
        Event(title = "Toro de fuego", date = "2025-10-22", time = "21:30h", category = EventCategory.BULLS.name),
        Event(title = "Actuación", date = "2025-10-22", time = "22:00h", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego", date = "2025-10-22", time = "23:45h", category = EventCategory.BULLS.name),
        Event(title = "Quema de la capilla", date = "2025-10-22", time = "00:00h", category = EventCategory.OTHER.name),
        Event(title = "Bomba final", date = "2025-10-22", time = "00:15h", category = EventCategory.OTHER.name)
    )
}
