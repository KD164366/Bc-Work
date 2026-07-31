package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.MainViewModel
import com.example.ui.components.*
import com.example.ui.theme.Amber700
import com.example.ui.theme.Amber900
import com.example.ui.theme.BCTheme

sealed class Screen {
    object Grade12 : Screen()
    object Grade13 : Screen()
    object DailyMcq : Screen()
    object Contact : Screen()
    object Admin : Screen()
    data class PdfView(val title: String, val driveUrl: String) : Screen()
    data class ActiveQuiz(val setIndex: Int) : Screen()
}

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BCTheme {
                MainAppContent(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppContent(viewModel: MainViewModel) {
    val loggedInUserEmail by viewModel.loggedInUserEmail.collectAsStateWithLifecycle()
    val loggedInUserName by viewModel.loggedInUserName.collectAsStateWithLifecycle()

    if (loggedInUserEmail == null) {
        LoginScreen(
            onLoginSuccess = { email, name ->
                viewModel.loginUser(email, name)
            }
        )
    } else {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Grade12) }
        var isAdminUnlocked by remember { mutableStateOf(false) }

        val grade12Tutes by viewModel.grade12Tutes.collectAsStateWithLifecycle()
        val grade13Tutes by viewModel.grade13Tutes.collectAsStateWithLifecycle()
        val allTutes by viewModel.allTutes.collectAsStateWithLifecycle()
        val userAccounts by viewModel.userAccounts.collectAsStateWithLifecycle()
        val userCount by viewModel.userCount.collectAsStateWithLifecycle()
        val quizProgressList by viewModel.quizProgress.collectAsStateWithLifecycle()
        val adminConfig by viewModel.adminConfig.collectAsStateWithLifecycle()
        val allQuestions by viewModel.allQuestions.collectAsStateWithLifecycle()

        when (val screen = currentScreen) {
            is Screen.PdfView -> {
                PdfViewerScreen(
                    title = screen.title,
                    driveUrl = screen.driveUrl,
                    onBack = { currentScreen = Screen.Grade12 }
                )
            }
            is Screen.ActiveQuiz -> {
                QuizScreen(
                    setIndex = screen.setIndex,
                    questionsFlow = viewModel.getQuestionsForSet(screen.setIndex),
                    onFinishQuiz = { score ->
                        viewModel.saveQuizScore(screen.setIndex, score)
                        currentScreen = Screen.DailyMcq
                    },
                    onBack = { currentScreen = Screen.DailyMcq }
                )
            }
            else -> {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Column {
                                    Text(
                                        text = "BC with Dilshan",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        color = Amber900
                                    )
                                    Text(
                                        text = "බෞද්ධ ශිෂ්ටාචාරය - $loggedInUserName",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            },
                            actions = {
                                TextButton(
                                    onClick = { viewModel.logoutUser() },
                                    modifier = Modifier.testTag("logout_button")
                                ) {
                                    Text("ඉවත් වන්න", fontSize = 12.sp, color = Amber700)
                                }
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            tonalElevation = 8.dp,
                            modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
                        ) {
                            val items = listOf(
                                NavigationItem("Grade 12", Icons.Default.MenuBook, Screen.Grade12),
                                NavigationItem("Grade 13", Icons.Default.Class, Screen.Grade13),
                                NavigationItem("දෛනික MCQ", Icons.Default.Quiz, Screen.DailyMcq),
                                NavigationItem("විස්තර", Icons.Default.ContactPage, Screen.Contact),
                                NavigationItem("ඇඩ්මින්", Icons.Default.Lock, Screen.Admin)
                            )

                            items.forEach { item ->
                                NavigationBarItem(
                                    selected = currentScreen == item.screen,
                                    onClick = { currentScreen = item.screen },
                                    icon = {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = item.label
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = item.label,
                                            fontSize = 11.sp,
                                            fontWeight = if (currentScreen == item.screen) FontWeight.Bold else FontWeight.Normal
                                        )
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = Amber900,
                                        selectedTextColor = Amber900,
                                        indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                    )
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (currentScreen) {
                            Screen.Grade12 -> {
                                TutesTab(
                                    gradeTitle = "Grade 12 BC නිබන්ධන",
                                    announcementText = adminConfig?.announcementText ?: "",
                                    tuteList = grade12Tutes,
                                    onOpenPdf = { title, url ->
                                        currentScreen = Screen.PdfView(title, url)
                                    }
                                )
                            }
                            Screen.Grade13 -> {
                                TutesTab(
                                    gradeTitle = "Grade 13 BC නිබන්ධන",
                                    announcementText = adminConfig?.announcementText ?: "",
                                    tuteList = grade13Tutes,
                                    onOpenPdf = { title, url ->
                                        currentScreen = Screen.PdfView(title, url)
                                    }
                                )
                            }
                            Screen.DailyMcq -> {
                                DailyMcqTab(
                                    quizProgressList = quizProgressList,
                                    onSelectSet = { setIdx ->
                                        currentScreen = Screen.ActiveQuiz(setIdx)
                                    }
                                )
                            }
                            Screen.Contact -> {
                                ContactTab(
                                    whatsappNumber = adminConfig?.whatsappNumber ?: "0725197825",
                                    whatsappMessage = adminConfig?.whatsappMessage ?: "Contact with Dilshan Nawarathna",
                                    announcementText = adminConfig?.announcementText ?: ""
                                )
                            }
                            Screen.Admin -> {
                                AdminPanel(
                                    isAdminUnlocked = isAdminUnlocked,
                                    onUnlockAdmin = { pass ->
                                        if (pass == "164366") {
                                            isAdminUnlocked = true
                                            true
                                        } else {
                                            false
                                        }
                                    },
                                    userAccounts = userAccounts,
                                    userCount = userCount,
                                    allTutes = allTutes,
                                    allQuestions = allQuestions,
                                    adminConfig = adminConfig,
                                    onAddTute = { grade, lessonName, title, url ->
                                        viewModel.addTute(grade, lessonName, title, url)
                                    },
                                    onDeleteTute = { id ->
                                        viewModel.deleteTute(id)
                                    },
                                    onAddQuestion = { setIdx, qNum, qText, o1, o2, o3, o4, o5, cIdx ->
                                        viewModel.addQuestion(setIdx, qNum, qText, o1, o2, o3, o4, o5, cIdx)
                                    },
                                    onDeleteQuestion = { id ->
                                        viewModel.deleteQuestion(id)
                                    },
                                    onUpdateConfig = { announcement, phone, message ->
                                        viewModel.updateAdminConfig(announcement, phone, message)
                                    }
                                )
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }
}

data class NavigationItem(
    val label: String,
    val icon: ImageVector,
    val screen: Screen
)
