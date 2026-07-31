package com.example.data

import kotlinx.coroutines.flow.Flow

class AppRepository(private val database: AppDatabase) {

    val allUserAccounts: Flow<List<UserAccount>> = database.userAccountDao().getAllUserAccounts()
    val userCount: Flow<Int> = database.userAccountDao().getUserCount()

    val grade12Tutes: Flow<List<TuteItem>> = database.tuteItemDao().getTutesByGrade("Grade 12 BC")
    val grade13Tutes: Flow<List<TuteItem>> = database.tuteItemDao().getTutesByGrade("Grade 13 BC")
    val allTutes: Flow<List<TuteItem>> = database.tuteItemDao().getAllTutes()

    val allQuizProgress: Flow<List<UserQuizProgress>> = database.quizProgressDao().getAllProgress()
    val adminConfig: Flow<AdminConfig?> = database.adminConfigDao().getAdminConfig()

    suspend fun registerUserAccount(email: String, name: String) {
        val user = UserAccount(googleEmail = email.trim(), userName = name.trim())
        database.userAccountDao().insertUserAccount(user)
    }

    suspend fun insertTute(tuteItem: TuteItem) {
        database.tuteItemDao().insertTute(tuteItem)
    }

    suspend fun deleteTute(id: Long) {
        database.tuteItemDao().deleteTuteById(id)
    }

    fun getQuestionsBySet(setIndex: Int): Flow<List<QuizQuestion>> {
        return database.quizQuestionDao().getQuestionsBySet(setIndex)
    }

    val allQuestions: Flow<List<QuizQuestion>> = database.quizQuestionDao().getAllQuestions()

    suspend fun insertQuestion(question: QuizQuestion) {
        database.quizQuestionDao().insertQuestion(question)
    }

    suspend fun deleteQuestion(id: Long) {
        database.quizQuestionDao().deleteQuestionById(id)
    }

    suspend fun updateQuizProgress(setIndex: Int, score: Int) {
        val currentProgress = database.quizProgressDao().getProgressForSet(setIndex)
        val newBestScore = maxOf(score, currentProgress?.bestScore ?: 0)
        
        database.quizProgressDao().saveProgress(
            UserQuizProgress(
                setIndex = setIndex,
                isUnlocked = true,
                bestScore = newBestScore,
                completed = true
            )
        )

        // If score >= 7 out of 10, unlock the next set automatically!
        if (score >= 7 && setIndex < 5) {
            val nextSetIndex = setIndex + 1
            val nextProgress = database.quizProgressDao().getProgressForSet(nextSetIndex)
            if (nextProgress == null || !nextProgress.isUnlocked) {
                database.quizProgressDao().saveProgress(
                    UserQuizProgress(
                        setIndex = nextSetIndex,
                        isUnlocked = true,
                        bestScore = nextProgress?.bestScore ?: 0,
                        completed = nextProgress?.completed ?: false
                    )
                )
            }
        }
    }

    suspend fun updateAdminConfig(announcement: String, phone: String, message: String) {
        database.adminConfigDao().updateAdminConfig(
            AdminConfig(
                id = 1,
                announcementText = announcement,
                whatsappNumber = phone,
                whatsappMessage = message
            )
        )
    }
}
