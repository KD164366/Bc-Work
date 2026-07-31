package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        UserAccount::class,
        TuteItem::class,
        QuizQuestion::class,
        UserQuizProgress::class,
        AdminConfig::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userAccountDao(): UserAccountDao
    abstract fun tuteItemDao(): TuteItemDao
    abstract fun quizQuestionDao(): QuizQuestionDao
    abstract fun quizProgressDao(): QuizProgressDao
    abstract fun adminConfigDao(): AdminConfigDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bc_dilshan_database"
                )
                .addCallback(DatabaseCallback(scope))
                .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database)
                    }
                }
            }
        }

        suspend fun populateDatabase(database: AppDatabase) {
            // Populate Initial Admin Config
            val adminConfigDao = database.adminConfigDao()
            adminConfigDao.updateAdminConfig(
                AdminConfig(
                    id = 1,
                    announcementText = "සියලුම පන්ති රාත්රී 8.30 සිට 10.30 දක්වා පැවැත්වෙන බව එය සතිය සෑම දවසකමත් සෙනසුරාදා ඉරිදා හැර සියලුම දවස්වල පැවැත්වෙන බව කරුණාවෙන් සලකන්න",
                    whatsappNumber = "0725197825",
                    whatsappMessage = "Contact with Dilshan Nawarathna"
                )
            )

            // Populate Initial Grade 12 & Grade 13 Tutes
            val tuteDao = database.tuteItemDao()
            tuteDao.insertTutes(InitialData.initialTutes)

            // Populate Initial Quiz Progress (Set 1 unlocked, others locked)
            val progressDao = database.quizProgressDao()
            val initialProgress = (1..5).map { setIdx ->
                UserQuizProgress(
                    setIndex = setIdx,
                    isUnlocked = (setIdx == 1),
                    bestScore = 0,
                    completed = false
                )
            }
            progressDao.saveProgressList(initialProgress)

            // Populate Initial 50 Questions
            val questionDao = database.quizQuestionDao()
            questionDao.insertQuestions(InitialData.initialQuestions)
        }
    }
}
