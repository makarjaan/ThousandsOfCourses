package makarova.thousandsofcourses.database.migrations

import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import makarova.thousandsofcourses.database.AppDataBase

class Migration_1_2: Migration(1, 2) {

    override fun migrate(db: SupportSQLiteDatabase) {
        try {
            db.execSQL("""
                CREATE TABLE `courses` (
                    `id` INTEGER NOT NULL, 
                    `title` TEXT NOT NULL, 
                    `text` TEXT NOT NULL, 
                    `price` TEXT NOT NULL, 
                    `rate` REAL NOT NULL, 
                    `startDate` TEXT NOT NULL, 
                    `publishDate` TEXT NOT NULL, 
                    PRIMARY KEY(`id`)
                )
            """)

            db.execSQL("""
                CREATE TABLE `user_favorites_courses` (
                    `user_id` INTEGER NOT NULL,
                    `course_id` INTEGER NOT NULL,
                    `added_date` INTEGER NOT NULL DEFAULT (strftime('%s','now')),
                    PRIMARY KEY(`user_id`, `course_id`),
                    FOREIGN KEY(`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
                    FOREIGN KEY(`course_id`) REFERENCES `courses`(`id`) ON DELETE CASCADE
                )
            """)

            db.execSQL("""
                CREATE INDEX `index_user_favorites_courses_user_id_course_id` 
                ON `user_favorites_courses` (`user_id`, `course_id`)
            """)

            db.execSQL("""
                CREATE INDEX `index_user_favorites_courses_course_id` 
                ON `user_favorites_courses` (`course_id`)
            """)
        } catch (ex: Exception) {
            Log.e(AppDataBase.DB_LOG_KEY, "Error while 1_2 migration: ${ex.message}")
        }
    }
}