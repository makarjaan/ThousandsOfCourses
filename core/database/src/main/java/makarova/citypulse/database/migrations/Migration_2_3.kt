package makarova.citypulse.database.migrations

import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import makarova.citypulse.database.AppDataBase

class Migration_2_3 : Migration(2, 3) {

    override fun migrate(db: SupportSQLiteDatabase) {
        try {
            db.execSQL("""
                CREATE TABLE IF NOT EXISTS `favorite_events` (
                    `event_id` TEXT NOT NULL,
                    `user_email` TEXT NOT NULL,
                    `title` TEXT NOT NULL,
                    `image_url` TEXT,
                    `place` TEXT,
                    `date_start` INTEGER NOT NULL,
                    `date_end` INTEGER,
                    `is_free` INTEGER NOT NULL,
                    `city` TEXT NOT NULL,
                    `category` TEXT NOT NULL,
                    `price` TEXT,
                    `added_at` INTEGER NOT NULL DEFAULT (strftime('%s', 'now') * 1000),
                    PRIMARY KEY(`event_id`, `user_email`)
                )
            """)

            db.execSQL("""
                CREATE INDEX IF NOT EXISTS `index_favorite_events_user_email` 
                ON `favorite_events` (`user_email`)
            """)

            db.execSQL("""
                CREATE INDEX IF NOT EXISTS `index_favorite_events_added_at` 
                ON `favorite_events` (`added_at`)
            """)


        } catch (ex: Exception) {
            Log.e(AppDataBase.DB_LOG_KEY, "Error while 2_3 migration: ${ex.message}", ex)
            throw ex
        }
    }
}