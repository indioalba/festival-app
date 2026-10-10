# Room Database Keep Rules
-keep class * extends androidx.room.RoomDatabase {
    <init>();
    *;
}
-keep class **.*_Impl extends androidx.room.RoomDatabase {
    <init>();
    *;
}
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao interface * { *; }
-keep @androidx.room.Dao abstract class * { *; }
