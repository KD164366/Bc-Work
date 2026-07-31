package com.example.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.QuizQuestion
import com.example.data.UserQuizProgress
import com.example.ui.theme.Amber700
import com.example.ui.theme.Amber900
import com.example.ui.theme.GreenSuccess
import com.example.ui.theme.RedError
import kotlinx.coroutines.flow.Flow

@Composable
fun DailyMcqTab(
    quizProgressList: List<UserQuizProgress>,
    onSelectSet: (setIndex: Int) -> Unit
) {
    val progressMap = remember(quizProgressList) {
        quizProgressList.associateBy { it.setIndex }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Quiz,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(
                            text = "දෛනික බහුවරණ ප්රශ්න",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "ප්රශ්න 10 න් 7 ක් හෝ ඊට වඩා නිවැරදි කර ඊළඟ කොටස අගුළු හරින්න!",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }

        items((1..5).toList()) { setIdx ->
            val progress = progressMap[setIdx]
            val isUnlocked = progress?.isUnlocked ?: (setIdx == 1)
            val bestScore = progress?.bestScore ?: 0
            val isCompleted = progress?.completed ?: false

            QuizSetCard(
                setIndex = setIdx,
                isUnlocked = isUnlocked,
                bestScore = bestScore,
                isCompleted = isCompleted,
                onClick = {
                    if (isUnlocked) {
                        onSelectSet(setIdx)
                    }
                }
            )
        }
    }
}

@Composable
fun QuizSetCard(
    setIndex: Int,
    isUnlocked: Boolean,
    bestScore: Int,
    isCompleted: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("quiz_set_card_$setIndex")
            .clickable(enabled = isUnlocked, onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isUnlocked) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isUnlocked) 3.dp else 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(
                        if (isUnlocked) Amber700 else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isUnlocked) Icons.Default.LockOpen else Icons.Default.Lock,
                    contentDescription = null,
                    tint = if (isUnlocked) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "0$setIndex කොටස (ප්රශ්න 10)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isUnlocked) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (isCompleted) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Amber700,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "ඉහළම ලකුණ: $bestScore / 10",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Amber900
                        )
                    }
                } else if (!isUnlocked) {
                    Text(
                        text = "කලින් කොටසින් ලකුණු 7ක් ලබාගෙන අගුළු අරින්න",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    Text(
                        text = "ආරම්භ කිරීමට ක්ලික් කරන්න",
                        fontSize = 12.sp,
                        color = Amber700
                    )
                }
            }

            if (isUnlocked) {
                Button(
                    onClick = onClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Amber700)
                ) {
                    Text("ආරම්භය", fontSize = 13.sp)
                }
            }
        }
    }
}

@Composable
fun QuizScreen(
    setIndex: Int,
    questionsFlow: Flow<List<QuizQuestion>>,
    onFinishQuiz: (score: Int) -> Unit,
    onBack: () -> Unit
) {
    val questions by questionsFlow.collectAsState(initial = emptyList())
    val selectedAnswers = remember { mutableStateMapOf<Int, Int>() } // questionIndex -> selectedOptionIndex
    var quizFinished by remember { mutableStateOf(false) }

    val totalQuestions = questions.size
    val answeredCount = selectedAnswers.size

    val score = remember(quizFinished, selectedAnswers.size) {
        if (!quizFinished) 0
        else {
            questions.indices.count { idx ->
                selectedAnswers[idx] == questions[idx].correctOptionIndex
            }
        }
    }

    Scaffold(
        topBar = {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                tonalElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.Quiz,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Text(
                        text = "0$setIndex කොටස - බහුවරණ ප්රශ්න",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "$answeredCount / $totalQuestions",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Amber900
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (questions.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (quizFinished) {
                QuizResultView(
                    score = score,
                    total = totalQuestions,
                    onRetry = {
                        selectedAnswers.clear()
                        quizFinished = false
                    },
                    onContinue = {
                        onFinishQuiz(score)
                    }
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(questions.size) { idx ->
                        val q = questions[idx]
                        QuestionItemCard(
                            question = q,
                            selectedIndex = selectedAnswers[idx],
                            onOptionSelected = { optionIdx ->
                                selectedAnswers[idx] = optionIdx
                            }
                        )
                    }
                }

                // Finish Quiz Footer Button
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 8.dp
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Button(
                            onClick = {
                                quizFinished = true
                            },
                            enabled = answeredCount > 0,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("submit_quiz_button"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Amber700)
                        ) {
                            Text(
                                text = "ලකුණු පරීක්ෂා කරන්න ($answeredCount / $totalQuestions)",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionItemCard(
    question: QuizQuestion,
    selectedIndex: Int?,
    onOptionSelected: (Int) -> Unit
) {
    val options = listOf(
        question.option1,
        question.option2,
        question.option3,
        question.option4,
        question.option5
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = question.questionText,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            options.forEachIndexed { optIdx, optText ->
                val isSelected = (selectedIndex == optIdx)
                val isCorrect = (question.correctOptionIndex == optIdx)

                val backgroundColor = when {
                    selectedIndex != null && isCorrect -> GreenSuccess.copy(alpha = 0.15f)
                    selectedIndex != null && isSelected && !isCorrect -> RedError.copy(alpha = 0.15f)
                    isSelected -> MaterialTheme.colorScheme.primaryContainer
                    else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                }

                val borderColor = when {
                    selectedIndex != null && isCorrect -> GreenSuccess
                    selectedIndex != null && isSelected && !isCorrect -> RedError
                    isSelected -> Amber700
                    else -> Color.Transparent
                }

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { onOptionSelected(optIdx) },
                    shape = RoundedCornerShape(10.dp),
                    color = backgroundColor,
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, borderColor)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = optText,
                            fontSize = 14.sp,
                            fontWeight = if (isSelected || (selectedIndex != null && isCorrect)) FontWeight.Bold else FontWeight.Normal,
                            color = when {
                                selectedIndex != null && isCorrect -> GreenSuccess
                                selectedIndex != null && isSelected && !isCorrect -> RedError
                                else -> MaterialTheme.colorScheme.onSurface
                            },
                            modifier = Modifier.weight(1f)
                        )

                        if (selectedIndex != null && isCorrect) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Correct",
                                tint = GreenSuccess,
                                modifier = Modifier.size(20.dp)
                            )
                        } else if (selectedIndex != null && isSelected && !isCorrect) {
                            Icon(
                                imageVector = Icons.Default.Cancel,
                                contentDescription = "Incorrect",
                                tint = RedError,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }

            // Interactive Congratulations message when selecting correct answer
            AnimatedVisibility(visible = selectedIndex == question.correctOptionIndex) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .background(GreenSuccess.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = GreenSuccess,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Congratulations! නිවැරදි පිළිතුර!",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = GreenSuccess
                    )
                }
            }
        }
    }
}

@Composable
fun QuizResultView(
    score: Int,
    total: Int,
    onRetry: () -> Unit,
    onContinue: () -> Unit
) {
    val isGreat = score >= 7
    val isTryAgain = score < 4

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isGreat) "🎉 Congratulations! 🎉" else if (isTryAgain) "නැවත උත්සාහ කරන්න" else "ප්රතිඵල",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isGreat) GreenSuccess else Amber900,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .background(
                            if (isGreat) GreenSuccess.copy(alpha = 0.15f) else Amber700.copy(alpha = 0.15f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "$score / $total",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isGreat) GreenSuccess else Amber900
                        )
                        Text(
                            text = "ලකුණු",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = when {
                        isGreat -> "ඉතා විශිෂ්ටයි!"
                        isTryAgain -> "නැවත උත්සාහ කරන්න"
                        else -> "හොඳයි! තවත් වැඩිදියුණු කරගන්න."
                    },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isGreat) GreenSuccess else Amber900,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (isGreat) "ඔබ සාර්ථකව ඊළඟ කොටස අගුළු හැර ඇත!" else "ඊළඟ කොටස අගුළු හැරීමට ලකුණු 7 ක් ලබාගන්න.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(28.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onRetry,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("නැවත කරන්න")
                    }

                    Button(
                        onClick = onContinue,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Amber700)
                    ) {
                        Text("ඉදිරියට")
                    }
                }
            }
        }
    }
}
