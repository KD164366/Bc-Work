package com.example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserAccountDao {
    @Query("SELECT * FROM user_accounts ORDER BY loginTimestamp DESC")
    fun getAllUserAccounts(): Flow<List<UserAccount>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserAccount(userAccount: UserAccount)

    @Query("SELECT COUNT(*) FROM user_accounts")
    fun getUserCount(): Flow<Int>
}

@Dao
interface TuteItemDao {
    @Query("SELECT * FROM tute_items WHERE grade = :grade ORDER BY orderIndex ASC")
    fun getTutesByGrade(grade: String): Flow<List<TuteItem>>

    @Query("SELECT * FROM tute_items ORDER BY grade ASC, orderIndex ASC")
    fun getAllTutes(): Flow<List<TuteItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTute(tuteItem: TuteItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTutes(tuteItems: List<TuteItem>)

    @Delete
    suspend fun deleteTute(tuteItem: TuteItem)

    @Query("DELETE FROM tute_items WHERE id = :id")
    suspend fun deleteTuteById(id: Long)
}

@Dao
interface QuizQuestionDao {
    @Query("SELECT * FROM quiz_questions WHERE setIndex = :setIndex ORDER BY questionNumber ASC")
    fun getQuestionsBySet(setIndex: Int): Flow<List<QuizQuestion>>

    @Query("SELECT * FROM quiz_questions ORDER BY setIndex ASC, questionNumber ASC")
    fun getAllQuestions(): Flow<List<QuizQuestion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: QuizQuestion)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuizQuestion>)

    @Delete
    suspend fun deleteQuestion(question: QuizQuestion)

    @Query("DELETE FROM quiz_questions WHERE id = :id")
    suspend fun deleteQuestionById(id: Long)
}

@Dao
interface QuizProgressDao {
    @Query("SELECT * FROM quiz_progress ORDER BY setIndex ASC")
    fun getAllProgress(): Flow<List<UserQuizProgress>>

    @Query("SELECT * FROM quiz_progress WHERE setIndex = :setIndex")
    suspend fun getProgressForSet(setIndex: Int): UserQuizProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgress(progress: UserQuizProgress)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgressList(progressList: List<UserQuizProgress>)
}

@Dao
interface AdminConfigDao {
    @Query("SELECT * FROM admin_config WHERE id = 1")
    fun getAdminConfig(): Flow<AdminConfig?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAdminConfig(config: AdminConfig)
}
