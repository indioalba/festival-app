package com.carbonbyte.sonfiestas.data.local

import com.carbonbyte.sonfiestas.data.model.Event
import com.carbonbyte.sonfiestas.data.model.EventCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DatabaseSeeder {
    suspend fun seedIfEmpty(eventDao: EventDao) = withContext(Dispatchers.IO) {
        if (eventDao.getAnyEvent() == null) {
            eventDao.insertAll(initialEvents)
        }
    }
    val initialEvents = listOf(
        // Viernes 21/08/2026
        Event(title = "Chupinazo y Fiesta del agua", date = "2026-08-21", time = "18:00h", location = "Parking Hospital", category = EventCategory.OTHER.name),
        Event(title = "Pasacalles de Gigantes con Saltinpunki", date = "2026-08-21", time = "19:00h", location = "Calles de la Villa. Plaza Mayor", category = EventCategory.KIDS.name),
        Event(title = "Pasacalles de Gigantes con Saltinpunki", date = "2026-08-21", time = "20:00h", location = "Plaza Mayor", category = EventCategory.KIDS.name),
        Event(title = "Salamenco", date = "2026-08-21", time = "21:30h", location = "Plaza Mayor", category = EventCategory.MUSIC.name),
        Event(title = "Fiesta con D.J.s", date = "2026-08-21", time = "23:00h", location = "Calle Padre Cámara", category = EventCategory.MUSIC.name),
        Event(title = "Cencenrrada Decrépita con Saltinpunki", date = "2026-08-21", time = "23:00h", location = "Plaza Mayor", category = EventCategory.OTHER.name),

        // Sábado 22/08/2026
        Event(title = "Mercado Medieval y Feria Agroalimentaria Alva", date = "2026-08-22", time = "11-14:30h 17-22h", location = "Castillo de los Duques de Alba", category = EventCategory.OTHER.name),
        Event(title = "Encuentro Amistoso Piensos Durán Albense-Club Atlético Benavente", date = "2026-08-22", time = "12:30h", location = "Pabellón Municipal", category = EventCategory.SPORTS.name),
        Event(title = "Danzas de la Corte", date = "2026-08-22", time = "13:30h", location = "Desde la Plaza Mayor al Castillo", category = EventCategory.OTHER.name),
        Event(title = "Degustación gratuita de Chanfaina", date = "2026-08-22", time = "13:30h", location = "Mercado Medieval", category = EventCategory.GASTRONOMY.name),
        Event(title = "Charanga el Bombazo", date = "2026-08-22", time = "13:30h - 16:30h", location = "Salida desde el castillo", category = EventCategory.MUSIC.name),
        Event(title = "Fiesta con D.J.s", date = "2026-08-22", time = "16:00h - 00:00h", location = "Calles Bulevar y Don Alejandro", category = EventCategory.MUSIC.name),
        Event(title = "Apertura Mercado Medieval y Feria Agroalimentaria Alva", date = "2026-08-22", time = "17:00h", location = "Castillo de los Duques de Alba", category = EventCategory.OTHER.name),
        Event(title = "Pregón", date = "2026-08-22", time = "22:00h", location = "Plaza Mayor", category = EventCategory.OTHER.name),
        Event(title = "Tres de Picas", date = "2026-08-22", time = "22:30h", location = "Plaza Mayor", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego sin buscapiés", date = "2026-08-22", time = "23:59h", location = "Plaza Mayor", category = EventCategory.BULLS.name),
        Event(title = "Orquesta Embrujo", date = "2026-08-22", time = "00:30h", location = "Calle Parada", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego con buscapiés", date = "2026-08-22", time = "03:10h", location = "Plaza Mayor", category = EventCategory.BULLS.name),
        Event(title = "Continúa Orquesta Embrujo", date = "2026-08-22", time = "03:20h", location = "Calle Parada", category = EventCategory.MUSIC.name),

        // Domingo 23/08/2026
        Event(title = "Preencierro con la Charanga Que lo que’s", date = "2026-08-23", time = "07:45h", location = "Curva Sur", category = EventCategory.MUSIC.name),
        Event(title = "Huevos con chorizo", date = "2026-08-23", time = "08:00h", location = "Curva Sur", category = EventCategory.GASTRONOMY.name),
        Event(title = "Encierro", date = "2026-08-23", time = "09:00h", location = "Recorrido habitual", category = EventCategory.BULLS.name),
        Event(title = "Capea", date = "2026-08-23", time = "09:15h", location = "Plaza de Toros", category = EventCategory.BULLS.name),
        Event(title = "Finales Torneo Tenis y Pádel", date = "2026-08-23", time = "10:00h", location = "Pistas de Tenis La Dehesa", category = EventCategory.SPORTS.name),
        Event(title = "Pasacalles con música y gigantes", date = "2026-08-23", time = "10:30h", location = "Desde la Plaza Mayor al Castillo", category = EventCategory.KIDS.name),
        Event(title = "Apertura Mercado Medieval y Feria Agroalimentaria Alva", date = "2026-08-23", time = "11:00h", location = "Castillo de los Duques de Alba", category = EventCategory.OTHER.name),
        Event(title = "Encierro infantil con carretones", date = "2026-08-23", time = "11:30h", location = "Salida desde Mercado Medieval", category = EventCategory.KIDS.name),
        Event(title = "Cabezudos con Charanga el Chupinazo", date = "2026-08-23", time = "13:30h", location = "Desde el Castillo hasta la Plaza Mayor", category = EventCategory.KIDS.name),
        Event(title = "Teatro de calle 'Soldados a caballo'", date = "2026-08-23", time = "17:00h", location = "Mercado Medieval", category = EventCategory.OTHER.name),
        Event(title = "Final del IX Bolsín Taurino", date = "2026-08-23", time = "18:00h", location = "Plaza de Toros Ducal", category = EventCategory.BULLS.name),
        Event(title = "Teatro de calle 'Soldados a caballo'", date = "2026-08-23", time = "19:00h", location = "Mercado Medieval", category = EventCategory.OTHER.name),
        Event(title = "Cetrería", date = "2026-08-23", time = "20:00h", location = "Mercado Medieval", category = EventCategory.OTHER.name),
        Event(title = "Alba Fest", date = "2026-08-23", time = "22:00h", location = "Plaza Mayor", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego sin buscapiés", date = "2026-08-23", time = "00:30h", location = "Plaza Mayor", category = EventCategory.BULLS.name),
        Event(title = "Continúa Alba Fest", date = "2026-08-23", time = "00:45h", location = "Plaza Mayor", category = EventCategory.MUSIC.name),

        // Lunes 24/08/2026
        Event(title = "Hinchables infantiles", date = "2026-08-24", time = "12:00h - 14:00h", location = "Piscinas municipales", category = EventCategory.KIDS.name),
        Event(title = "Hinchables infantiles", date = "2026-08-24", time = "16:30h - 19:30h", location = "Piscinas municipales", category = EventCategory.KIDS.name),
        Event(title = "Acto institucional y Coro Kyria", date = "2026-08-24", time = "20:30h", location = "Basílica de Santa Teresa", category = EventCategory.RELIGIOUS.name),
        Event(title = "Disco móvil", date = "2026-08-24", time = "22:00h", location = "Plaza de Toros", category = EventCategory.MUSIC.name),
        Event(title = "Capea", date = "2026-08-24", time = "23:00h", location = "Plaza de Toros", category = EventCategory.BULLS.name),

        // Martes 25/08/2026
        Event(title = "Salida de Santa Teresa", date = "2026-08-25", time = "12:00h", location = "Plaza de Santa Teresa", category = EventCategory.RELIGIOUS.name),
        Event(title = "Santa Misa", date = "2026-08-25", time = "12:30h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Desfile de Cabezudos", date = "2026-08-25", time = "13:30h", location = "Salida Plaza Mayor", category = EventCategory.KIDS.name),
        Event(title = "Disco kids y fiesta del agua", date = "2026-08-25", time = "17:00h", location = "Parking del Hospital", category = EventCategory.KIDS.name),
        Event(title = "Charanga el Chupinazo", date = "2026-08-25", time = "18:00h", location = "Parque del Espolón", category = EventCategory.MUSIC.name),
        Event(title = "Tributo a Estopa 'Destrangis'", date = "2026-08-25", time = "21:30h", location = "Plaza Mayor", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego sin buscapiés", date = "2026-08-25", time = "23:00h", location = "Plaza Mayor", category = EventCategory.BULLS.name),
        Event(title = "Macrodiscomóvil", date = "2026-08-25", time = "23:30h", location = "Calle Parada", category = EventCategory.MUSIC.name),

        // Miércoles 26/08/2026
        Event(title = "Fiesta temática de Reguetón", date = "2026-08-26", time = "17:00h", location = "Plaza Mayor", category = EventCategory.MUSIC.name),
        Event(title = "Charanga", date = "2026-08-26", time = "21:00h", location = "Curva Sur", category = EventCategory.MUSIC.name),
        Event(title = "Encierro", date = "2026-08-26", time = "22:00h", location = "Recorrido habitual", category = EventCategory.BULLS.name),
        Event(title = "Capea con Charanga el Chupinazo", date = "2026-08-26", time = "22:15h", location = "Plaza de Toros", category = EventCategory.BULLS.name),
        Event(title = "Toro de fuego sin buscapiés", date = "2026-08-26", time = "23:30h", location = "Plaza Mayor", category = EventCategory.BULLS.name),
        Event(title = "Orquesta Princesa", date = "2026-08-26", time = "00:30h", location = "Calle Parada", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego con buscapiés", date = "2026-08-26", time = "02:40h", location = "Plaza Mayor", category = EventCategory.BULLS.name),
        Event(title = "Continúa Orquesta Princesa", date = "2026-08-26", time = "03:00h", location = "Calle Parada", category = EventCategory.MUSIC.name),

        // Jueves 27/08/2026
        Event(title = "Santa Misa", date = "2026-08-27", time = "12:30h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Cabezudos con Charanga el Bombazo", date = "2026-08-27", time = "13:30h", location = "Plaza Mayor", category = EventCategory.KIDS.name),
        Event(title = "En Jazz. Victoria Mesonero Sextet", date = "2026-08-27", time = "18:30h", location = "Basílica Neogótica", category = EventCategory.MUSIC.name),
        Event(title = "Procesión solemne", date = "2026-08-27", time = "20:30h", location = "Plaza del Peregrino", category = EventCategory.RELIGIOUS.name),
        Event(title = "Toro de fuego sin buscapiés", date = "2026-08-27", time = "21:30h", location = "Plaza Mayor", category = EventCategory.BULLS.name),
        Event(title = "Orquesta Madelon", date = "2026-08-27", time = "21:45h", location = "Plaza Mayor", category = EventCategory.MUSIC.name),
        Event(title = "Bomba final", date = "2026-08-27", time = "00:30h", location = "Plaza Mayor", category = EventCategory.OTHER.name),
    )
}
