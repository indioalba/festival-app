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
        // --- Sábado 10/10/2026 ---
        Event(title = "Fútbol Alevines: Alba CF vx CD Hergar", date = "2026-10-10", time = "Desde las 10:00h", location = "Campo de Fútbol Municipal La Dehesa", category = EventCategory.SPORTS.name),
        Event(title = "Fútbol Infantil: Alba CF vs CD Cristo Rey", date = "2026-10-10", time = "Desde las 10:00h", location = "Campo de Fútbol Municipal La Dehesa", category = EventCategory.SPORTS.name),
        Event(title = "Fútbol Cadete: Alba CF B vs CD Hergar D", date = "2026-10-10", time = "Desde las 10:00h", location = "Campo de Fútbol Municipal La Dehesa", category = EventCategory.SPORTS.name),
        Event(title = "Apertura de la Feria del Barro", date = "2026-10-10", time = "11:00h", location = "Plaza Mayor", category = EventCategory.OTHER.name),
        Event(title = "Demostración de Torno Tradicional", date = "2026-10-10", time = "12:30h", location = "Plaza Mayor", category = EventCategory.OTHER.name),
        Event(title = "Inauguración Feria del Barro con Grupo Charro Albense", date = "2026-10-10", time = "13:00h", location = "Plaza Mayor", category = EventCategory.MUSIC.name),
        Event(title = "Pasacalles con charanga", date = "2026-10-10", time = "13h a 15h", location = "Calles de la Villa", category = EventCategory.MUSIC.name),
        Event(title = "Fútbol Sala: CD Albense FS vs Vilalba FS", date = "2026-10-10", time = "16:00h", location = "Pabellón Municipal", category = EventCategory.SPORTS.name),
        Event(title = "Toro del Cajón (2 toros)", date = "2026-10-10", time = "17:00h", location = "C/ Peñaranda, C/ Beltrana, Curva Sur, C/ Norte y Plaza de Toros", category = EventCategory.BULLS.name),
        Event(title = "Capea popular de toros con charanga", date = "2026-10-10", time = "18:00h", location = "Plaza de Toros", category = EventCategory.BULLS.name),
        Event(title = "Demostración de pintura creativa (técnica persa)", date = "2026-10-10", time = "18:00h", location = "Plaza Mayor", category = EventCategory.OTHER.name),
        Event(title = "Talleres de torno simultáneos", date = "2026-10-10", time = "20:30h", location = "Plaza Mayor", category = EventCategory.OTHER.name),
        Event(title = "Discoteca Móvil (ATC 15 de Octubre)", date = "2026-10-10", time = "19:00h", location = "Calle Castillo", category = EventCategory.MUSIC.name),
        Event(title = "Encierro nocturno de vaquillas", date = "2026-10-10", time = "22:00h", location = "C/ San Francisco, C/ Peñaranda, C/ Beltrana, Curva Sur, C/ Norte y Plaza de Toros", category = EventCategory.BULLS.name),
        Event(title = "Capea popular de vaquillas", date = "2026-10-10", time = "22:15h", location = "Plaza de Toros", category = EventCategory.BULLS.name),

        // --- Domingo 11/10/2026 ---
        Event(title = "Apertura de la Feria del Barro", date = "2026-10-11", time = "11:00h", location = "Plaza Mayor", category = EventCategory.OTHER.name),
        Event(title = "Concurso profesional '1 kg. de barro'", date = "2026-10-11", time = "11:30h", location = "Plaza Mayor", category = EventCategory.OTHER.name),
        Event(title = "Nombramiento Hijo Adoptivo a D. Julián Moreiro Prieto", date = "2026-10-11", time = "13:00h", location = "Teatro de La Villa Ducal", category = EventCategory.OTHER.name),
        Event(title = "Taller participativo de torno", date = "2026-10-11", time = "13:00h", location = "Plaza Mayor", category = EventCategory.OTHER.name),
        Event(title = "Demostración de pintura artesanal", date = "2026-10-11", time = "17:30h", location = "Plaza Mayor", category = EventCategory.OTHER.name),
        Event(title = "Corte de jamón solidario en plato de barro", date = "2026-10-11", time = "18:30h", location = "Plaza Mayor", category = EventCategory.GASTRONOMY.name),
        Event(title = "Fallo y entrega de premios Feria del Barro", date = "2026-10-11", time = "20:00h", location = "Plaza Mayor", category = EventCategory.OTHER.name),

        // --- Miércoles 14/10/2026 ---
        Event(title = "Reconocimiento a la persona de mayor edad", date = "2026-10-14", time = "11:00h", location = "Alba de Tormes", category = EventCategory.OTHER.name),
        Event(title = "Salida de clausura de la imagen de Santa Teresa", date = "2026-10-14", time = "12:00h", location = "Plaza de Santa Teresa", category = EventCategory.RELIGIOUS.name),
        Event(title = "Santa Misa", date = "2026-10-14", time = "12:30h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Ofrenda floral a Santa Teresa de Jesús", date = "2026-10-14", time = "17:30h", location = "Plaza del Peregrino", category = EventCategory.RELIGIOUS.name),
        Event(title = "Pasacalles con charanga", date = "2026-10-14", time = "18:30h", location = "Calles de la Villa", category = EventCategory.MUSIC.name),
        Event(title = "Eucaristía de la Novena", date = "2026-10-14", time = "20:00h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Pregón de Fiestas por el Duque de Huéscar", date = "2026-10-14", time = "21:00h", location = "Plaza Mayor", category = EventCategory.OTHER.name),
        Event(title = "Toro de fuego sin buscapiés", date = "2026-10-14", time = "21:30h", location = "Plaza Mayor", category = EventCategory.BULLS.name),
        Event(title = "Orquesta Diamante Show Band", date = "2026-10-14", time = "22:00h", location = "Plaza Mayor", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego sin buscapiés", date = "2026-10-14", time = "00:30h", location = "Plaza Mayor", category = EventCategory.BULLS.name),

        // --- Jueves 15/10/2026 (Solemnidad de Santa Teresa) ---
        Event(title = "Pasacalles con charanga", date = "2026-10-15", time = "11h a 13h", location = "Calles de la Villa", category = EventCategory.MUSIC.name),
        Event(title = "Santa Misa Presidida por el Obispo de Salamanca", date = "2026-10-15", time = "12:30h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Cabezudos acompañados por charanga", date = "2026-10-15", time = "13:30h", location = "Calles de la Villa", category = EventCategory.KIDS.name),
        Event(title = "Encierro de minibueyes con charanga", date = "2026-10-15", time = "16:30h", location = "Curva Sur", category = EventCategory.BULLS.name),
        Event(title = "Santo Rosario", date = "2026-10-15", time = "18:00h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Procesión de Santa Teresa de Jesús y el Santo Brazo", date = "2026-10-15", time = "18:30h", location = "Calles de la Villa", category = EventCategory.RELIGIOUS.name),
        Event(title = "Santa Misa de la Novena", date = "2026-10-15", time = "20:00h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Toro de fuego sin buscapiés", date = "2026-10-15", time = "20:45h", location = "Plaza Mayor", category = EventCategory.BULLS.name),
        Event(title = "Actuación: Charros y Gitanos", date = "2026-10-15", time = "21:00h", location = "Plaza Mayor", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego con buscapiés", date = "2026-10-15", time = "23:00h", location = "Plaza Mayor", category = EventCategory.BULLS.name),

        // --- Viernes 16/10/2026 ---
        Event(title = "Lectura continuada 'Las Moradas'", date = "2026-10-16", time = "10:00h", location = "Iglesia de San Juan de la Cruz", category = EventCategory.RELIGIOUS.name),
        Event(title = "Santa Misa", date = "2026-10-16", time = "12:30h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Pasacalles amenizado por charanga", date = "2026-10-16", time = "13:30h", location = "Calles de la Villa", category = EventCategory.MUSIC.name),
        Event(title = "Charanga con dulces y pastas", date = "2026-10-16", time = "14:00h", location = "Plaza Mayor", category = EventCategory.GASTRONOMY.name),
        Event(title = "Fiesta tardeo con Sergio Lucas", date = "2026-10-16", time = "18:00h", location = "Plaza de Toros", category = EventCategory.MUSIC.name),
        Event(title = "Santa Misa", date = "2026-10-16", time = "20:00h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Play Backs Adultos", date = "2026-10-16", time = "21:00h", location = "Plaza de Toros", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego sin buscapiés", date = "2026-10-16", time = "A continuación", location = "Plaza Mayor", category = EventCategory.BULLS.name),
        Event(title = "Orquesta Panorama City", date = "2026-10-16", time = "23:45h", location = "Calle Parada", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego con buscapiés carga especial", date = "2026-10-16", time = "02:45h", location = "Plaza Mayor", category = EventCategory.BULLS.name),
        Event(title = "Discoteca Móvil", date = "2026-10-16", time = "03:00h", location = "Plaza Mayor", category = EventCategory.MUSIC.name),

        // --- Sábado 17/10/2026 (Día de las Peñas) ---
        Event(title = "Charanga de mañaneo", date = "2026-10-17", time = "07:30h", location = "Curva Sur", category = EventCategory.MUSIC.name),
        Event(title = "Huevos fritos con chorizo", date = "2026-10-17", time = "07:30h", location = "Curva Sur", category = EventCategory.GASTRONOMY.name),
        Event(title = "Encierro de vaquillas", date = "2026-10-17", time = "08:15h", location = "C/ San Francisco, C/ Peñaranda, C/ Beltrana, Curva Sur, C/ Norte y Plaza de Toros", category = EventCategory.BULLS.name),
        Event(title = "Capea amenizada por charanga", date = "2026-10-17", time = "08:30h", location = "Plaza de Toros", category = EventCategory.BULLS.name),
        Event(title = "V Torneo Tenis y Pádel", date = "2026-10-17", time = "10:00h", location = "Pistas Deportivas", category = EventCategory.SPORTS.name),
        Event(title = "Partidos Fútbol Base", date = "2026-10-17", time = "10:00h", location = "Campo de Fútbol Municipal La Dehesa", category = EventCategory.SPORTS.name),
        Event(title = "Santa Misa", date = "2026-10-17", time = "12:30h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Cabezudos acompañados por charanga", date = "2026-10-17", time = "13:00h", location = "Calles de la Villa", category = EventCategory.KIDS.name),
        Event(title = "Comida popular de peñas: Paella", date = "2026-10-17", time = "15:00h", location = "Plaza de Toros", category = EventCategory.GASTRONOMY.name),
        Event(title = "Concurso numérico con regalos", date = "2026-10-17", time = "A continuación", location = "Plaza de Toros", category = EventCategory.OTHER.name),
        Event(title = "Visita guiada gratuita al Museo CARMUS", date = "2026-10-17", time = "15:00h", location = "Museo CARMUS", category = EventCategory.OTHER.name),
        Event(title = "Santa Misa", date = "2026-10-17", time = "20:00h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Correfoc", date = "2026-10-17", time = "22:00h", location = "C/ Salitre, C/ Benitas, C/ Carlos III, C/ Espolón, C/ Edades del Hombre", category = EventCategory.OTHER.name),
        Event(title = "Verbena Vulkano", date = "2026-10-17", time = "00:00h", location = "Plaza Mayor", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego sin buscapiés", date = "2026-10-17", time = "02:00h", location = "Plaza Mayor", category = EventCategory.BULLS.name),
        Event(title = "Verbena Vulkano (continuación)", date = "2026-10-17", time = "A continuación", location = "Plaza Mayor", category = EventCategory.MUSIC.name),

        // --- Domingo 18/10/2026 (Domingo de las Mozas) ---
        Event(title = "V Torneo Tenis y Pádel", date = "2026-10-18", time = "10:00h", location = "Pistas Deportivas", category = EventCategory.SPORTS.name),
        Event(title = "XI Torneo de Ajedrez", date = "2026-10-18", time = "10:30h", location = "Casa Molino / Salón Multiusos", category = EventCategory.SPORTS.name),
        Event(title = "Pasacalles con charanga", date = "2026-10-18", time = "10:30h", location = "Calles de la Villa", category = EventCategory.MUSIC.name),
        Event(title = "Encierro de novillos", date = "2026-10-18", time = "11:30h", location = "C/ San Francisco, C/ Peñaranda, C/ Beltrana, Curva Sur, C/ Norte y Plaza de Toros", category = EventCategory.BULLS.name),
        Event(title = "Capea popular de novillos", date = "2026-10-18", time = "11:45h", location = "Plaza de Toros", category = EventCategory.BULLS.name),
        Event(title = "Visita guiada Exposición 'San Juan de la Cruz'", date = "2026-10-18", time = "12:00h", location = "Convento de San Juan de la Cruz", category = EventCategory.OTHER.name),
        Event(title = "Santa Misa", date = "2026-10-18", time = "13:00h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Cabezudos acompañados por charanga", date = "2026-10-18", time = "13:30h", location = "Calles de la Villa", category = EventCategory.KIDS.name),
        Event(title = "Partidos Fútbol Base y Aficionados", date = "2026-10-18", time = "16:00h", location = "Campo de Fútbol Municipal La Dehesa", category = EventCategory.SPORTS.name),
        Event(title = "Clase Magistral Taurina", date = "2026-10-18", time = "18:00h", location = "Plaza de Toros", category = EventCategory.BULLS.name),
        Event(title = "Santa Misa", date = "2026-10-18", time = "20:00h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Concierto de Órgano 'Los Sonidos Centenarios 2026'", date = "2026-10-18", time = "21:00h", location = "Basílica de la Anunciación", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego sin buscapiés", date = "2026-10-18", time = "21:15h", location = "Plaza Mayor", category = EventCategory.BULLS.name),
        Event(title = "Concierto Malas Compañías", date = "2026-10-18", time = "21:30h", location = "Plaza Mayor", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego con buscapiés", date = "2026-10-18", time = "23:15h", location = "Plaza Mayor", category = EventCategory.BULLS.name),

        // --- Lunes 19/10/2026 (Día del Niño) ---
        Event(title = "Santa Misa", date = "2026-10-19", time = "12:30h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Parque de hinchables", date = "2026-10-19", time = "15:30h a 18:30h", location = "Plaza Mayor y Plaza del Grano", category = EventCategory.KIDS.name),
        Event(title = "Merienda infantil", date = "2026-10-19", time = "18:30h", location = "Plaza Mayor", category = EventCategory.KIDS.name),
        Event(title = "Encierro de carretones", date = "2026-10-19", time = "19:00h", location = "Plaza Mayor a Plaza de Toros", category = EventCategory.KIDS.name),
        Event(title = "Play Back Infantil", date = "2026-10-19", time = "19:45h", location = "Plaza de Toros", category = EventCategory.KIDS.name),
        Event(title = "Santa Misa de la Novena", date = "2026-10-19", time = "20:00h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Toro de fuego sin buscapiés", date = "2026-10-19", time = "21:30h", location = "Plaza Mayor", category = EventCategory.BULLS.name),

        // --- Martes 20/10/2026 (Día de la Mujer) ---
        Event(title = "Santa Misa cantada por Grupo Charro Albense", date = "2026-10-20", time = "12:30h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Actuación Grupo Charro Albense", date = "2026-10-20", time = "A continuación", location = "Plaza Santa Teresa", category = EventCategory.MUSIC.name),
        Event(title = "Dulces, pastas y baile con charanga", date = "2026-10-20", time = "13:45h", location = "Plaza Mayor", category = EventCategory.GASTRONOMY.name),
        Event(title = "Pasacalles con charanga", date = "2026-10-20", time = "14:30h", location = "Calles de la Villa", category = EventCategory.MUSIC.name),
        Event(title = "Comida: Paella de la Mujer", date = "2026-10-20", time = "15:00h", location = "Plaza de Toros", category = EventCategory.GASTRONOMY.name),
        Event(title = "Concurso numérico con regalos", date = "2026-10-20", time = "16:30h", location = "Plaza de Toros", category = EventCategory.OTHER.name),
        Event(title = "Actuación Teresa Blanco", date = "2026-10-20", time = "18:00h", location = "Plaza de Toros", category = EventCategory.MUSIC.name),
        Event(title = "Chocolate con bizcochos", date = "2026-10-20", time = "19:30h", location = "Plaza de Toros", category = EventCategory.GASTRONOMY.name),
        Event(title = "Santa Misa de la Novena", date = "2026-10-20", time = "20:00h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),

        // --- Miércoles 21/10/2026 (Día del Mayor y el Deporte) ---
        Event(title = "Charanga: visita a nuestros mayores", date = "2026-10-21", time = "11:30h", location = "Residencias de las Villas", category = EventCategory.MUSIC.name),
        Event(title = "Santa Misa cantada por Grupo Charro Albense", date = "2026-10-21", time = "12:30h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Actuación Grupo Charro Albense", date = "2026-10-21", time = "A continuación", location = "Plaza Santa Teresa", category = EventCategory.MUSIC.name),
        Event(title = "Dulces, pastas y baile con charanga", date = "2026-10-21", time = "13:45h", location = "Plaza Mayor", category = EventCategory.GASTRONOMY.name),
        Event(title = "Pasacalles con charanga", date = "2026-10-21", time = "14:30h", location = "Calles de la Villa", category = EventCategory.MUSIC.name),
        Event(title = "Comida: Arroz Negro del Mayor", date = "2026-10-21", time = "15:00h", location = "Plaza de Toros", category = EventCategory.GASTRONOMY.name),
        Event(title = "Concurso numérico con regalos", date = "2026-10-21", time = "16:30h", location = "Plaza de Toros", category = EventCategory.OTHER.name),
        Event(title = "Campeonato Fútbol Sala Botigol", date = "2026-10-21", time = "16:30h", location = "Pabellón Municipal", category = EventCategory.SPORTS.name),
        Event(title = "Actuación Salamenco", date = "2026-10-21", time = "18:00h", location = "Plaza de Toros", category = EventCategory.MUSIC.name),
        Event(title = "Campeonato Basket 3x3 y Triples", date = "2026-10-21", time = "18:30h", location = "Pabellón Municipal", category = EventCategory.SPORTS.name),
        Event(title = "Chocolate con bizcochos", date = "2026-10-21", time = "19:30h", location = "Plaza de Toros", category = EventCategory.GASTRONOMY.name),
        Event(title = "Santa Misa de la Novena", date = "2026-10-21", time = "20:00h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),

        // --- Jueves 22/10/2026 (Día de la Octava) ---
        Event(title = "Santa Misa de la Novena", date = "2026-10-22", time = "12:30h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Santo Rosario", date = "2026-10-22", time = "18:00h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Santa Misa", date = "2026-10-22", time = "18:30h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Procesión de regreso a clausura", date = "2026-10-22", time = "19:00h", location = "Basílica de la Anunciación", category = EventCategory.RELIGIOUS.name),
        Event(title = "Encierro de 2 toros de fuego sin buscapiés", date = "2026-10-22", time = "21:30h", location = "Plaza Mayor", category = EventCategory.BULLS.name),
        Event(title = "Marmaol Tributo Pop Rock", date = "2026-10-22", time = "21:45h", location = "Plaza Mayor", category = EventCategory.MUSIC.name),
        Event(title = "Toro de fuego con buscapiés", date = "2026-10-22", time = "23:30h", location = "Plaza Mayor", category = EventCategory.BULLS.name),
        Event(title = "Quema de la capilla", date = "2026-10-22", time = "23:45h", location = "Plaza Mayor", category = EventCategory.OTHER.name),
        Event(title = "Bomba final", date = "2026-10-22", time = "00:00h", location = "Plaza Mayor", category = EventCategory.OTHER.name)
    )
    /*val initialEvents = listOf(
        // Viernes 21/08/2026
        Event(title = "Chupinazo y Fiesta del agua", date = "2026-08-21", time = "18:00h", location = "Parking Hospital", category = EventCategory.OTHER.name),
        Event(title = "Pasacalles de Gigantes con Saltinpunki", date = "2026-08-21", time = "19:00h", location = "Calles de la Villa. Plaza Mayor", category = EventCategory.KIDS.name),


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
    )*/
}
