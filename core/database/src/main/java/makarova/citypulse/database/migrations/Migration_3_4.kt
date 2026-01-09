package makarova.citypulse.database.migrations

import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import makarova.citypulse.database.AppDataBase


class Migration_3_4 : Migration(3, 4) {

    override fun migrate(db: SupportSQLiteDatabase) {
        try {
            db.execSQL("ALTER TABLE users ADD COLUMN avatar_url TEXT")

            db.execSQL("ALTER TABLE users ADD COLUMN updated_at INTEGER NOT NULL DEFAULT 0")

            Log.d(AppDataBase.DB_LOG_KEY, "Migration 3_4 completed successfully")

        } catch (ex: Exception) {
            Log.e(AppDataBase.DB_LOG_KEY, "Error while 3_4 migration: ${ex.message}", ex)
            throw ex
        }
    }
}