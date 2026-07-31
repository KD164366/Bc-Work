package com.example.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppRepository
    private val prefs = application.getSharedPreferences("bc_dilshan_prefs", Context.MODE_PRIVATE)

    private val _loggedInUserEmail = MutableStateFlow<String?>(null)
    val loggedInUserEmail: StateFlow<String?> = _loggedInUserEmail.asStateFlow()

    private val _loggedInUserName = MutableStateFlow<String?>(null)
    val loggedInUserName: StateFlow<String?> = _loggedInUserName.asStateFlow()

    init {
        val db = AppDatabase.getDatabase(application, viewModelScope)
        repository = AppRepository(db)

        val savedEmail = prefs.getString("user_google_email", null)
        val savedName = prefs.getString("user_google_name", null)
        if (!savedEmail.isNullOrBlank()) {
            _loggedInUserEmail.value = savedEmail
            _loggedInUserName.value = savedName ?: savedEmail.substringBefore("@")
        }
    }

    val userAccounts: StateFlow<List<UserAccount>> = repository.allUserAccounts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val userCount: StateFlow<Int> = repository.userCount
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val grade12Tutes: StateFlow<List<TuteItem>> = repository.grade12Tutes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val grade13Tutes: StateFlow<List<TuteItem>> = repository.grade13Tutes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allTutes: StateFlow<List<TuteItem>> = repository.allTutes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allQuestions: StateFlow<List<QuizQuestion>> = repository.allQuestions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val quizProgress: StateFlow<List<UserQuizProgress>> = repository.allQuizProgress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val adminConfig: StateFlow<AdminConfig?> = repository.adminConfig
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            AdminConfig(
                id = 1,
                announcementText = "සියලුම පන්ති රාත්රී 8.30 සිට 10.30 දක්වා පැවැත්වෙන බව එය සතිය සෑම දවසකමත් සෙනසුරාදා ඉරිදා හැර සියලුම දවස්වල පැවැත්වෙන බව කරුණාවෙන් සලකන්න",
                whatsappNumber = "0725197825",
                whatsappMessage = "Contact with Dilshan Nawarathna"
            )
        )

    fun loginUser(email: String, name: String) {
        val trimmedEmail = email.trim()
        val trimmedName = if (name.isBlank()) trimmedEmail.substringBefore("@") else name.trim()

        _loggedInUserEmail.value = trimmedEmail
        _loggedInUserName.value = trimmedName

        prefs.edit()
            .putString("user_google_email", trimmedEmail)
            .putString("user_google_name", trimmedName)
            .apply()

        viewModelScope.launch {
            repository.registerUserAccount(trimmedEmail, trimmedName)
        }
    }

    fun logoutUser() {
        _loggedInUserEmail.value = null
        _loggedInUserName.value = null
        prefs.edit().clear().apply()
    }

    fun getQuestionsForSet(setIndex: Int): Flow<List<QuizQuestion>> {
        return repository.getQuestionsBySet(setIndex)
    }

    fun saveQuizScore(setIndex: Int, score: Int) {
        viewModelScope.launch {
            repository.updateQuizProgress(setIndex, score)
        }
    }

    fun addTute(grade: String, lessonName: String, title: String, driveUrl: String) {
        viewModelScope.launch {
            val order = if (grade.contains("12")) 1 else 10
            repository.insertTute(
                TuteItem(
                    grade = grade,
                    lessonName = lessonName,
                    title = title,
                    driveUrl = driveUrl,
                    orderIndex = order
                )
            )
        }
    }

    fun deleteTute(id: Long) {
        viewModelScope.launch {
            repository.deleteTute(id)
        }
    }

    fun addQuestion(
        setIndex: Int,
        questionNumber: Int,
        questionText: String,
        opt1: String,
        opt2: String,
        opt3: String,
        opt4: String,
        opt5: String,
        correctIndex: Int
    ) {
        viewModelScope.launch {
            repository.insertQuestion(
                QuizQuestion(
                    setIndex = setIndex,
                    questionNumber = questionNumber,
                    questionText = questionText,
                    option1 = opt1,
                    option2 = opt2,
                    option3 = opt3,
                    option4 = opt4,
                    option5 = opt5,
                    correctOptionIndex = correctIndex
                )
            )
        }
    }

    fun deleteQuestion(id: Long) {
        viewModelScope.launch {
            repository.deleteQuestion(id)
        }
    }

    fun updateAdminConfig(announcementText: String, whatsappNumber: String, whatsappMessage: String) {
        viewModelScope.launch {
            repository.updateAdminConfig(announcementText, whatsappNumber, whatsappMessage)
        }
    }
}
