package makarova.citypulse.database.migrations

import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import makarova.citypulse.database.AppDataBase

class Migration_1_2: Migration(1, 2) {

    override fun migrate(db: SupportSQLiteDatabase) {
        try {
            db.execSQL("""
            CREATE TABLE IF NOT EXISTS `category_interest` (
                `email` TEXT NOT NULL,
                `category` TEXT NOT NULL,
                `score` INTEGER NOT NULL DEFAULT 0,
                PRIMARY KEY(`email`, `category`)
            )
        """)

            db.execSQL("""
            CREATE INDEX IF NOT EXISTS `index_category_interest_email` 
            ON `category_interests` (`email`)
        """)

            db.execSQL("""
            CREATE INDEX IF NOT EXISTS `index_category_interest_category_slug` 
            ON `category_interests` (`category_slug`)
        """)
        } catch (ex: Exception) {
            Log.e(AppDataBase.DB_LOG_KEY, "Error while 1_2 migration: ${ex.message}")
        }
    }
}