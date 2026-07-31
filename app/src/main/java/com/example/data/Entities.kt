package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_accounts")
data class UserAccount(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val googleEmail: String,
    val userName: String,
    val loginTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "tute_items")
data class TuteItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val grade: String, // "Grade 12" or "Grade 13"
    val lessonName: String, // e.g., "පලමු පාඩම"
    val title: String, // e.g., "බෞද්ධ ශිෂ්ටාචාරය - පාඩම 01"
    val driveUrl: String,
    val orderIndex: Int
)

@Entity(tableName = "quiz_questions")
data class QuizQuestion(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val setIndex: Int, // 1 for Part 01, 2 for Part 02, etc.
    val questionNumber: Int,
    val questionText: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val option5: String,
    val correctOptionIndex: Int // 0 for i, 1 for ii, 2 for iii, 3 for iv, 4 for v
)

@Entity(tableName = "quiz_progress")
data class UserQuizProgress(
    @PrimaryKey val setIndex: Int,
    val isUnlocked: Boolean,
    val bestScore: Int,
    val completed: Boolean
)

@Entity(tableName = "admin_config")
data class AdminConfig(
    @PrimaryKey val id: Int = 1,
    val announcementText: String,
    val whatsappNumber: String,
    val whatsappMessage: String
)
