package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AdminConfig
import com.example.data.QuizQuestion
import com.example.data.TuteItem
import com.example.data.UserAccount
import com.example.ui.theme.Amber700
import com.example.ui.theme.Amber900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPanel(
    isAdminUnlocked: Boolean,
    onUnlockAdmin: (password: String) -> Boolean,
    userAccounts: List<UserAccount>,
    userCount: Int,
    allTutes: List<TuteItem>,
    allQuestions: List<QuizQuestion>,
    adminConfig: AdminConfig?,
    onAddTute: (grade: String, lessonName: String, title: String, url: String) -> Unit,
    onDeleteTute: (id: Long) -> Unit,
    onAddQuestion: (setIndex: Int, qNum: Int, qText: String, o1: String, o2: String, o3: String, o4: String, o5: String, correctIdx: Int) -> Unit,
    onDeleteQuestion: (id: Long) -> Unit,
    onUpdateConfig: (announcement: String, phone: String, message: String) -> Unit
) {
    var passwordInput by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf<String?>(null) }

    if (!isAdminUnlocked) {
        // Password Protection Screen
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("admin_lock_card"),
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
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(Amber700),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Lock",
                            tint = Color.White,
                            modifier = Modifier.size(38.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "ඇඩ්මින් කොටස",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Amber900
                    )

                    Text(
                        text = "අගුළු ඇරීමට මුරපදය ඇතුළත් කරන්න",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
                    )

                    // Secret Password Field (Masked visual transformation)
                    OutlinedTextField(
                        value = passwordInput,
                        onValueChange = {
                            passwordInput = it
                            passwordError = null
                        },
                        label = { Text("මුරපදය") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                                tint = Amber700
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("admin_password_input")
                    )

                    if (passwordError != null) {
                        Text(
                            text = passwordError!!,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            val success = onUnlockAdmin(passwordInput)
                            if (!success) {
                                passwordError = "වැරදි මුරපදයකි! නැවත උත්සාහ කරන්න."
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("admin_unlock_button"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Amber700)
                    ) {
                        Text(
                            text = "අගුළු අරින්න",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    } else {
        // Unlocked Admin Interface with Tabs
        var selectedTab by remember { mutableStateOf(0) }
        val tabs = listOf("පරිශීලකයින්", "PDF කළමනාකරණය", "නිවේදන", "MCQ කළමනාකරණය")

        Column(modifier = Modifier.fillMaxSize()) {
            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                edgePadding = 16.dp,
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title, fontWeight = FontWeight.Bold) }
                    )
                }
            }

            when (selectedTab) {
                0 -> AdminUsersTab(userAccounts, userCount)
                1 -> AdminTutesTab(allTutes, onAddTute, onDeleteTute)
                2 -> AdminConfigTab(adminConfig, onUpdateConfig)
                3 -> AdminMcqTab(allQuestions, onAddQuestion, onDeleteQuestion)
            }
        }
    }
}

@Composable
fun AdminUsersTab(userAccounts: List<UserAccount>, userCount: Int) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Group,
                        contentDescription = null,
                        tint = Amber900,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "ලොග් වූ මුළු Google Accounts ගණන",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "$userCount පරිශීලකයින්",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Amber900
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = "ලියාපදිංචි Google Accounts ලැයිස්තුව",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Amber900,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        if (userAccounts.isEmpty()) {
            item {
                Text(
                    text = "තවමත් පරිශීලකයින් ලොග් වී නොමැත.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            items(userAccounts) { user ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = user.googleEmail,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (user.userName.isNotBlank() && user.userName != user.googleEmail) {
                            Text(
                                text = "නම: ${user.userName}",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AdminTutesTab(
    allTutes: List<TuteItem>,
    onAddTute: (grade: String, lessonName: String, title: String, url: String) -> Unit,
    onDeleteTute: (id: Long) -> Unit
) {
    var showAddDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Button(
                    onClick = { showAddDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Amber700)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("අලුත් PDF එකතු කරන්න", fontWeight = FontWeight.Bold)
                }
            }

            items(allTutes, key = { it.id }) { tute ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${tute.grade} - ${tute.lessonName}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Amber700
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = tute.title,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        IconButton(onClick = { onDeleteTute(tute.id) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }

        if (showAddDialog) {
            var grade by remember { mutableStateOf("Grade 12 BC") }
            var lessonName by remember { mutableStateOf("") }
            var title by remember { mutableStateOf("") }
            var url by remember { mutableStateOf("") }

            AlertDialog(
                onDismissRequest = { showAddDialog = false },
                title = { Text("අලුත් PDF Tute එකක් එක් කරන්න") },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row {
                            FilterChip(
                                selected = grade == "Grade 12 BC",
                                onClick = { grade = "Grade 12 BC" },
                                label = { Text("Grade 12") }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            FilterChip(
                                selected = grade == "Grade 13 BC",
                                onClick = { grade = "Grade 13 BC" },
                                label = { Text("Grade 13") }
                            )
                        }
                        OutlinedTextField(
                            value = lessonName,
                            onValueChange = { lessonName = it },
                            label = { Text("පාඩමේ නම (උදා: පලමු පාඩම)") }
                        )
                        OutlinedTextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text("මාතෘකාව") }
                        )
                        OutlinedTextField(
                            value = url,
                            onValueChange = { url = it },
                            label = { Text("Google Drive URL එක") }
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (lessonName.isNotBlank() && title.isNotBlank() && url.isNotBlank()) {
                                onAddTute(grade, lessonName, title, url)
                                showAddDialog = false
                            }
                        }
                    ) {
                        Text("එක් කරන්න")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddDialog = false }) {
                        Text("අවලංගු කරන්න")
                    }
                }
            )
        }
    }
}

@Composable
fun AdminConfigTab(
    adminConfig: AdminConfig?,
    onUpdateConfig: (announcement: String, phone: String, message: String) -> Unit
) {
    var announcement by remember(adminConfig) { mutableStateOf(adminConfig?.announcementText ?: "") }
    var phone by remember(adminConfig) { mutableStateOf(adminConfig?.whatsappNumber ?: "0725197825") }
    var message by remember(adminConfig) { mutableStateOf(adminConfig?.whatsappMessage ?: "Contact with Dilshan Nawarathna") }
    var isSaved by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "පන්ති නිවේදන සහ WhatsApp අංක වෙනස් කිරීම",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Amber900
            )
        }

        item {
            OutlinedTextField(
                value = announcement,
                onValueChange = {
                    announcement = it
                    isSaved = false
                },
                label = { Text("මුදුනෙහි පෙන්වන පන්ති නිවේදනය") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
        }

        item {
            OutlinedTextField(
                value = phone,
                onValueChange = {
                    phone = it
                    isSaved = false
                },
                label = { Text("WhatsApp අංකය") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = message,
                onValueChange = {
                    message = it
                    isSaved = false
                },
                label = { Text("WhatsApp පණිවිඩය") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            Button(
                onClick = {
                    onUpdateConfig(announcement, phone, message)
                    isSaved = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Amber700)
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("වෙනස්කම් සුරකින්න", fontWeight = FontWeight.Bold)
            }

            if (isSaved) {
                Text(
                    text = "වෙනස්කම් සාර්ථකව සුරකින ලදී!",
                    color = Amber700,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun AdminMcqTab(
    allQuestions: List<QuizQuestion>,
    onAddQuestion: (setIndex: Int, qNum: Int, qText: String, o1: String, o2: String, o3: String, o4: String, o5: String, correctIdx: Int) -> Unit,
    onDeleteQuestion: (id: Long) -> Unit
) {
    var showAddDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Button(
                    onClick = { showAddDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Amber700)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("අලුත් MCQ ප්රශ්නයක් එක් කරන්න", fontWeight = FontWeight.Bold)
                }
            }

            items(allQuestions, key = { it.id }) { q ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "0${q.setIndex} කොටස - ප්රශ්න ${q.questionNumber}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Amber700
                            )
                            Text(
                                text = q.questionText,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        IconButton(onClick = { onDeleteQuestion(q.id) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }

        if (showAddDialog) {
            var setIndex by remember { mutableStateOf(1) }
            var qNum by remember { mutableStateOf(1) }
            var qText by remember { mutableStateOf("") }
            var o1 by remember { mutableStateOf("") }
            var o2 by remember { mutableStateOf("") }
            var o3 by remember { mutableStateOf("") }
            var o4 by remember { mutableStateOf("") }
            var o5 by remember { mutableStateOf("") }
            var correctIdx by remember { mutableStateOf(0) }

            AlertDialog(
                onDismissRequest = { showAddDialog = false },
                title = { Text("අලුත් MCQ ප්රශ්නයක් එක් කරන්න") },
                text = {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.height(350.dp)
                    ) {
                        item {
                            OutlinedTextField(
                                value = setIndex.toString(),
                                onValueChange = { setIndex = it.toIntOrNull() ?: 1 },
                                label = { Text("කොටස (1 - 5)") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                        item {
                            OutlinedTextField(
                                value = qNum.toString(),
                                onValueChange = { qNum = it.toIntOrNull() ?: 1 },
                                label = { Text("ප්රශ්න අංකය") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                        item {
                            OutlinedTextField(
                                value = qText,
                                onValueChange = { qText = it },
                                label = { Text("ප්රශ්නය") }
                            )
                        }
                        item { OutlinedTextField(value = o1, onValueChange = { o1 = it }, label = { Text("i. පිළිතුර") }) }
                        item { OutlinedTextField(value = o2, onValueChange = { o2 = it }, label = { Text("ii. පිළිතුර") }) }
                        item { OutlinedTextField(value = o3, onValueChange = { o3 = it }, label = { Text("iii. පිළිතුර") }) }
                        item { OutlinedTextField(value = o4, onValueChange = { o4 = it }, label = { Text("iv. පිළිතුර") }) }
                        item { OutlinedTextField(value = o5, onValueChange = { o5 = it }, label = { Text("v. පිළිතුර") }) }
                        item {
                            OutlinedTextField(
                                value = (correctIdx + 1).toString(),
                                onValueChange = { correctIdx = ((it.toIntOrNull() ?: 1) - 1).coerceIn(0, 4) },
                                label = { Text("නිවැරදි පිළිතුරු අංකය (1, 2, 3, 4, හෝ 5)") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (qText.isNotBlank() && o1.isNotBlank() && o2.isNotBlank()) {
                                onAddQuestion(setIndex, qNum, qText, o1, o2, o3, o4, o5, correctIdx)
                                showAddDialog = false
                            }
                        }
                    ) {
                        Text("එක් කරන්න")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddDialog = false }) {
                        Text("අවලංගු කරන්න")
                    }
                }
            )
        }
    }
}
