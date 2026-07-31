package com.example.data

object InitialData {
    val initialTutes = listOf(
        // Grade 12 BC
        TuteItem(
            grade = "Grade 12 BC",
            lessonName = "පලමු පාඩම",
            title = "01 වන පාඩම - බෞද්ධ ශිෂ්ටාචාරයේ ආරම්භය",
            driveUrl = "https://drive.google.com/file/d/1xkVh2-49KHpg-fdZvVvYYH1mua7JYFh1/view?usp=sharing",
            orderIndex = 1
        ),
        TuteItem(
            grade = "Grade 12 BC",
            lessonName = "දෙවන පාඩම",
            title = "02 වන පාඩම - බෞද්ධ ශිෂ්ටාචාරයේ මූලික ලක්ෂණ",
            driveUrl = "https://drive.google.com/file/d/1zxu5NPniOJOxGc2w5xR1twFGGBBlV5Ql/view?usp=sharing",
            orderIndex = 2
        ),
        TuteItem(
            grade = "Grade 12 BC",
            lessonName = "තෙවන පාඩම",
            title = "03 වන පාඩම - බෞද්ධ සංස්කෘතිය හා සමාජය",
            driveUrl = "https://drive.google.com/file/d/1IG8jkm-c2Lvmxb5OhQIoV1Q7OZTP-12C/view?usp=sharing",
            orderIndex = 3
        ),
        TuteItem(
            grade = "Grade 12 BC",
            lessonName = "සිව්වන පාඩම",
            title = "04 වන පාඩම - බෞද්ධ කලා හා වාස්තුවිද්යාව",
            driveUrl = "https://drive.google.com/file/d/1ZN1GVIB1S05iQ2pl5owQ1ZEvmFf_r8xZ/view?usp=sharing",
            orderIndex = 4
        ),
        TuteItem(
            grade = "Grade 12 BC",
            lessonName = "පස්වන පාඩම",
            title = "05 වන පාඩම - බෞද්ධ සාහිත්යය හා අධ්යාපනය",
            driveUrl = "https://drive.google.com/file/d/1xvChelAc-RmCkmVtfTjoC3IvEDEc4abH/view?usp=sharing",
            orderIndex = 5
        ),
        TuteItem(
            grade = "Grade 12 BC",
            lessonName = "හයවන පාඩම",
            title = "06 වන පාඩම - බෞද්ධ දර්ශනය හා ආචාරධර්ම",
            driveUrl = "https://drive.google.com/file/d/1xvChelAc-RmCkmVtfTjoC3IvEDEc4abH/view?usp=sharing",
            orderIndex = 6
        ),
        TuteItem(
            grade = "Grade 12 BC",
            lessonName = "හත්වන පාඩම",
            title = "07 වන පාඩම - ලංකා බෞද්ධ ඉතිහාසය",
            driveUrl = "https://drive.google.com/file/d/1yaGGiIR_eQWuaNswgSiqDu9nKTV0k4L-/view?usp=sharing",
            orderIndex = 7
        ),
        TuteItem(
            grade = "Grade 12 BC",
            lessonName = "අටවන පාඩම",
            title = "08 වන පාඩම - බෞද්ධ රාජ්ය පාලනය",
            driveUrl = "https://drive.google.com/file/d/1EgJ1qj-Gzq23ivkPtPGqa-H1z-dyYEIy/view?usp=sharing",
            orderIndex = 8
        ),
        TuteItem(
            grade = "Grade 12 BC",
            lessonName = "නවවන පාඩම",
            title = "09 වන පාඩම - බෞද්ධ ආර්ථික රටාව",
            driveUrl = "https://drive.google.com/file/d/1ZXNQmu65qd6o3xSNMGvcTifo7InUW6NP/view?usp=sharing",
            orderIndex = 9
        ),

        // Grade 13 BC
        TuteItem(
            grade = "Grade 13 BC",
            lessonName = "දසවන පාඩම",
            title = "10 වන පාඩම - නූතන සමාජය හා බුදුදහම",
            driveUrl = "https://drive.google.com/file/d/1t6pVUPRVPThyhtmYiNXVUa6Q-KrdEwXW/view?usp=sharing",
            orderIndex = 10
        ),
        TuteItem(
            grade = "Grade 13 BC",
            lessonName = "එකොලොස්වන පාඩම",
            title = "11 වන පාඩම - ගෝලීයකරණය හා බෞද්ධ ශිෂ්ටාචාරය",
            driveUrl = "https://drive.google.com/file/d/1Bp4BdqXh9G0Ubc0MnlTlravA_JIP5QzF/view?usp=sharing",
            orderIndex = 11
        ),
        TuteItem(
            grade = "Grade 13 BC",
            lessonName = "දොලොස්වන පාඩම",
            title = "12 වන පාඩම - බෞද්ධ පරිසර විද්යාව හා මානව අයිතිවාසිකම්",
            driveUrl = "https://drive.google.com/file/d/1TnuArIdGbOYQeIfmNILX1ejyjAqORqau/view?usp=sharing",
            orderIndex = 12
        )
    )

    val initialQuestions = listOf(
        // Set 01 (1 - 10)
        QuizQuestion(
            setIndex = 1, questionNumber = 1,
            questionText = "1. බුදුදහමේ මූලික අරමුණ කුමක්ද?",
            option1 = "i. ධනය රැස් කිරීම", option2 = "ii. නිවන සාක්ෂාත් කිරීම", option3 = "iii. රාජ්ය පාලනය", option4 = "iv. වෙළඳාම", option5 = "v. විනෝදාස්වාදය",
            correctOptionIndex = 1 // ii
        ),
        QuizQuestion(
            setIndex = 1, questionNumber = 2,
            questionText = "2. බෞද්ධ ශිෂ්ටාචාරයේ පදනම ලෙස සැලකෙන්නේ කුමක්ද?",
            option1 = "i. ත්රිපිටකය", option2 = "ii. වේද", option3 = "iii. බයිබලය", option4 = "iv. කුරානය", option5 = "v. මහාවංශය",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 1, questionNumber = 3,
            questionText = "3. ත්රිවිධ රත්නයට අයත් නොවන්නේ කුමක්ද?",
            option1 = "i. බුද්ධ", option2 = "ii. ධර්ම", option3 = "iii. සංඝ", option4 = "iv. දෙවියෝ", option5 = "v. කිසිවක් නොවේ",
            correctOptionIndex = 3 // iv
        ),
        QuizQuestion(
            setIndex = 1, questionNumber = 4,
            questionText = "4. බෞද්ධයන්ගේ ප්රධාන ආගමික ස්ථානය කුමක්ද?",
            option1 = "i. පල්ලිය", option2 = "ii. කෝවිල", option3 = "iii. විහාරය", option4 = "iv. පන්සල", option5 = "v. iii හා iv",
            correctOptionIndex = 4 // v
        ),
        QuizQuestion(
            setIndex = 1, questionNumber = 5,
            questionText = "5. බෞද්ධ ශිෂ්ටාචාරයේ ප්රධාන ගුණාංගයක් වන්නේ",
            option1 = "i. කරුණාව", option2 = "ii. වෛරය", option3 = "iii. ලෝභය", option4 = "iv. ඊර්ෂ්යාව", option5 = "v. කෝපය",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 1, questionNumber = 6,
            questionText = "6. සිල් රැකීමේ ප්රධාන අරමුණ වන්නේ",
            option1 = "i. ධනය වැඩි කිරීම", option2 = "ii. සදාචාරය වර්ධනය කිරීම", option3 = "iii. බලය ලබා ගැනීම", option4 = "iv. ප්රසිද්ධිය ලබා ගැනීම", option5 = "v. විනෝදය",
            correctOptionIndex = 1 // ii
        ),
        QuizQuestion(
            setIndex = 1, questionNumber = 7,
            questionText = "7. ශ්රී මහා බෝධිය ශ්රී ලංකාවට වැඩම කළේ",
            option1 = "i. සංඝමිත්තා තෙරණිය විසිනි", option2 = "ii. මහින්ද හිමියන් විසිනි", option3 = "iii. දුටුගැමුණු රජු විසිනි", option4 = "iv. පණ්ඩුකාභය රජු විසිනි", option5 = "v. වළගම්බා රජු විසිනි",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 1, questionNumber = 8,
            questionText = "8. බෞද්ධ ශිෂ්ටාචාරයේ දාන පින්කමෙන් වර්ධනය වන්නේ",
            option1 = "i. ත්යාගශීලීත්වය", option2 = "ii. වෛරය", option3 = "iii. ලෝභය", option4 = "iv. කෝපය", option5 = "v. ඊර්ෂ්යාව",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 1, questionNumber = 9,
            questionText = "9. බෞද්ධ සංස්කෘතියේ ප්රධාන උත්සවය වන්නේ",
            option1 = "i. වෙසක්", option2 = "ii. නත්තල්", option3 = "iii. රාමසාන්", option4 = "iv. දීපාවලී", option5 = "v. පාස්කු",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 1, questionNumber = 10,
            questionText = "10. බෞද්ධ ශිෂ්ටාචාරයේ මූලික සදාචාරය ලෙස සැලකෙන්නේ",
            option1 = "i. පංචශීලය", option2 = "ii. අෂ්ටාංගික මාර්ගය", option3 = "iii. සප්ත බෝජ්ඣංග", option4 = "iv. පාරමිතා", option5 = "v. සතර සතිපට්ඨාන",
            correctOptionIndex = 0 // i
        ),

        // Set 02 (11 - 20)
        QuizQuestion(
            setIndex = 2, questionNumber = 11,
            questionText = "11. බුදුන් වහන්සේ උපත ලැබූ ස්ථානය වන්නේ",
            option1 = "i. ලුම්බිණිය", option2 = "ii. බුද්ධගයාව", option3 = "iii. සාරානාත්", option4 = "iv. කුසිනාරාව", option5 = "v. අනුරාධපුරය",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 2, questionNumber = 12,
            questionText = "12. බුදුන් වහන්සේ බුද්ධත්වයට පත් වූයේ",
            option1 = "i. බෝධි වෘක්ෂය යටදී", option2 = "ii. ජේතවනාරාමයේදී", option3 = "iii. මිහින්තලයේදී", option4 = "iv. ඉසිපතනයේදී", option5 = "v. ලුම්බිණියේදී",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 2, questionNumber = 13,
            questionText = "13. ප්රථම ධර්ම දේශනාව පැවැත්වූයේ",
            option1 = "i. ඉසිපතන මිගදායෙහි", option2 = "ii. ලුම්බිණියේ", option3 = "iii. ජේතවනාරාමයේ", option4 = "iv. අනුරාධපුරයේ", option5 = "v. පොළොන්නරුවේ",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 2, questionNumber = 14,
            questionText = "14. පිරිනිවන් පෑවේ",
            option1 = "i. කුසිනාරාවේදී", option2 = "ii. සාරානාත්වලදී", option3 = "iii. බුද්ධගයාවේදී", option4 = "iv. ලුම්බිණියේදී", option5 = "v. ශ්රාවස්තියේදී",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 2, questionNumber = 15,
            questionText = "15. බෞද්ධ ශිෂ්ටාචාරයේ අහිංසාව යන්නෙන් අදහස් වන්නේ",
            option1 = "i. හිංසා නොකිරීම", option2 = "ii. යුද්ධ කිරීම", option3 = "iii. සොරකම් කිරීම", option4 = "iv. බොරු කීම", option5 = "v. මත්පැන් පානය",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 2, questionNumber = 16,
            questionText = "16. පංචශීලයේ පළමු ශික්ෂාපදය වන්නේ",
            option1 = "i. ප්රාණඝාතයෙන් වැළකීම", option2 = "ii. සොරකමින් වැළකීම", option3 = "iii. බොරු කීමෙන් වැළකීම", option4 = "iv. මත්ද්රව්යයෙන් වැළකීම", option5 = "v. කාමයෙහි වරදවා හැසිරීමෙන් වැළකීම",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 2, questionNumber = 17,
            questionText = "17. බෞද්ධ විහාරස්ථානයක ප්රධාන වන්දනා ස්ථානය වන්නේ",
            option1 = "i. චෛත්යය", option2 = "ii. ගබඩාව", option3 = "iii. මුළුතැන්ගෙය", option4 = "iv. උද්යානය", option5 = "v. පොකුණ",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 2, questionNumber = 18,
            questionText = "18. බෞද්ධ ශිෂ්ටාචාරයේ භාවනාවෙන් වර්ධනය වන්නේ",
            option1 = "i. සිතේ සංයමය", option2 = "ii. ලෝභය", option3 = "iii. වෛරය", option4 = "iv. කෝපය", option5 = "v. අලසකම",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 2, questionNumber = 19,
            questionText = "19. ශ්රී ලංකාවට බුදුදහම හඳුන්වා දුන්නේ",
            option1 = "i. මහින්ද මහරහතන් වහන්සේ", option2 = "ii. සංඝමිත්තා තෙරණිය", option3 = "iii. අශෝක රජු", option4 = "iv. දේවානම්පියතිස්ස රජු", option5 = "v. වළගම්බා රජු",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 2, questionNumber = 20,
            questionText = "20. බෞද්ධ ශිෂ්ටාචාරයේ ප්රධාන අගය වන්නේ",
            option1 = "i. මෛත්රිය", option2 = "ii. වෛරය", option3 = "iii. ඊර්ෂ්යාව", option4 = "iv. අහංකාරය", option5 = "v. ලෝභය",
            correctOptionIndex = 0 // i
        ),

        // Set 03 (21 - 30)
        QuizQuestion(
            setIndex = 3, questionNumber = 21,
            questionText = "21. බුදුන් වහන්සේගේ පළමු ශ්රාවකයන් පස්දෙනා හැඳින්වෙන්නේ කුමන නාමයෙන්ද?",
            option1 = "i. දසමහ යෝධයෝ", option2 = "ii. පංචවග්ගිය භික්ෂූන්", option3 = "iii. අටමහා ශ්රාවකයෝ", option4 = "iv. සතරවරම් දෙවිවරුන්", option5 = "v. දසබලධාරීන්",
            correctOptionIndex = 1 // ii
        ),
        QuizQuestion(
            setIndex = 3, questionNumber = 22,
            questionText = "22. ශ්රී ලංකාවේ ප්රථම බෞද්ධ විහාරය ලෙස සැලකෙන්නේ",
            option1 = "i. ජේතවනාරාමය", option2 = "ii. අභයගිරිය", option3 = "iii. මහාවිහාරය", option4 = "iv. මිරිසවැටිය", option5 = "v. රුවන්වැලිසෑය",
            correctOptionIndex = 2 // iii
        ),
        QuizQuestion(
            setIndex = 3, questionNumber = 23,
            questionText = "23. බුදුදහම ශ්රී ලංකාවට හඳුන්වා දුන් කාලයේ රජ වූයේ",
            option1 = "i. දුටුගැමුණු රජු", option2 = "ii. දේවානම්පියතිස්ස රජු", option3 = "iii. පණ්ඩුකාභය රජු", option4 = "iv. විජය රජු", option5 = "v. වළගම්බා රජු",
            correctOptionIndex = 1 // ii
        ),
        QuizQuestion(
            setIndex = 3, questionNumber = 24,
            questionText = "24. බෞද්ධ ශිෂ්ටාචාරයේ දානයේ ප්රධාන අරමුණ වන්නේ",
            option1 = "i. ප්රසිද්ධිය ලබා ගැනීම", option2 = "ii. ධනය රැස් කිරීම", option3 = "iii. බලය ලබා ගැනීම", option4 = "iv. පුණ්ය රැස් කිරීම හා පරිත්යාගශීලී බව වර්ධනය කිරීම", option5 = "v. විනෝදාස්වාදය",
            correctOptionIndex = 3 // iv
        ),
        QuizQuestion(
            setIndex = 3, questionNumber = 25,
            questionText = "25. බෞද්ධ ශිෂ්ටාචාරයට අනුව දෙමාපියන් සැලකෙන්නේ",
            option1 = "i. සංඝයා ලෙස", option2 = "ii. රජවරුන් ලෙස", option3 = "iii. බ්රහ්මයන් ලෙස", option4 = "iv. දෙවිවරුන් ලෙස", option5 = "v. ගුරුවරුන් ලෙස",
            correctOptionIndex = 2 // iii
        ),
        QuizQuestion(
            setIndex = 3, questionNumber = 26,
            questionText = "26. බුද්ධ ප්රතිමාව ඉදිරිපිට මල් පූජා කිරීමෙන් සිහිපත් කරන්නේ",
            option1 = "i. ජයග්රහණය", option2 = "ii. ආයුෂ වැඩිවීම", option3 = "iii. අනිත්යතාවය", option4 = "iv. ධනය", option5 = "v. බලය",
            correctOptionIndex = 2 // iii
        ),
        QuizQuestion(
            setIndex = 3, questionNumber = 27,
            questionText = "27. බුදුන් වහන්සේ දේශනා කළ උතුම්ම පූජාව වන්නේ",
            option1 = "i. මල් පූජාව", option2 = "ii. පහන් පූජාව", option3 = "iii. ගිලන්පස පූජාව", option4 = "iv. සුවඳ දුම් පූජාව", option5 = "v. ධර්මානුධර්ම ප්රතිපත්තිය",
            correctOptionIndex = 4 // v
        ),
        QuizQuestion(
            setIndex = 3, questionNumber = 28,
            questionText = "28. බෞද්ධ ශිෂ්ටාචාරයේ ගුරුවරයාගේ ප්රධාන කාර්යය වන්නේ",
            option1 = "i. ශිෂ්යයාට යහපත් දැනුම ලබාදීම", option2 = "ii. දඬුවම් කිරීම", option3 = "iii. වෙළඳාම් කිරීම", option4 = "iv. මුදල් එකතු කිරීම", option5 = "v. දේශපාලනය කිරීම",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 3, questionNumber = 29,
            questionText = "29. බෞද්ධ ශිෂ්ටාචාරයට අනුව ශ්රේෂ්ඨතම ජයග්රහණය වන්නේ",
            option1 = "i. සතුරන් ජය ගැනීම", option2 = "ii. රටක් ජය ගැනීම", option3 = "iii. තම සිත ජය ගැනීම", option4 = "iv. ධනය උපයා ගැනීම", option5 = "v. බලය ලබා ගැනීම",
            correctOptionIndex = 2 // iii
        ),
        QuizQuestion(
            setIndex = 3, questionNumber = 30,
            questionText = "30. බෞද්ධ ශිෂ්ටාචාරයේ මූලික සමාජ වටිනාකමක් වන්නේ",
            option1 = "i. ආත්මාර්ථකාමීත්වය", option2 = "ii. වෛරය", option3 = "iii. තරඟකාරීත්වය", option4 = "iv. මෛත්රී සහ සහයෝගීතාව", option5 = "v. අසත්යය",
            correctOptionIndex = 3 // iv
        ),

        // Set 04 (31 - 40)
        QuizQuestion(
            setIndex = 4, questionNumber = 31,
            questionText = "31. බුදුන් වහන්සේගේ මෑණියන්ගේ නම කුමක්ද?",
            option1 = "i. යශෝධරා දේවිය", option2 = "ii. මහාමායා දේවිය", option3 = "iii. ප්රජාපතී ගෝතමී", option4 = "iv. සුජාතා", option5 = "v. විශාඛා",
            correctOptionIndex = 1 // ii
        ),
        QuizQuestion(
            setIndex = 4, questionNumber = 32,
            questionText = "32. බුදුන් වහන්සේගේ පියාණන් වූයේ",
            option1 = "i. බිම්බිසාර රජු", option2 = "ii. අශෝක රජු", option3 = "iii. ශුද්ධෝදන රජු", option4 = "iv. අජාසත් රජු", option5 = "v. දේවානම්පියතිස්ස රජු",
            correctOptionIndex = 2 // iii
        ),
        QuizQuestion(
            setIndex = 4, questionNumber = 33,
            questionText = "33. පංචශීලයේ පස්වන ශික්ෂාපදය වන්නේ",
            option1 = "i. ප්රාණඝාතයෙන් වැළකීම", option2 = "ii. බොරු කීමෙන් වැළකීම", option3 = "iii. සොරකමින් වැළකීම", option4 = "iv. මත්පැන් හා මත්ද්රව්ය භාවිතයෙන් වැළකීම", option5 = "v. කාමයෙහි වරදවා හැසිරීමෙන් වැළකීම",
            correctOptionIndex = 3 // iv
        ),
        QuizQuestion(
            setIndex = 4, questionNumber = 34,
            questionText = "34. ශ්රී ලංකාවේ විශාලතම දාගැබ ලෙස සැලකෙන්නේ",
            option1 = "i. ජේතවනාරාම දාගැබ", option2 = "ii. රුවන්වැලිසෑය", option3 = "iii. අභයගිරි දාගැබ", option4 = "iv. මිරිසවැටිය", option5 = "v. ලංකාරාමය",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 4, questionNumber = 35,
            questionText = "35. සත්ය වචනය කථා කිරීම අයත් වන්නේ",
            option1 = "i. දානයට", option2 = "ii. භාවනාවට", option3 = "iii. ශීලයට", option4 = "iv. ප්රඥාවට", option5 = "v. පූජාවට",
            correctOptionIndex = 2 // iii
        ),
        QuizQuestion(
            setIndex = 4, questionNumber = 36,
            questionText = "36. කරුණාව දැක්විය යුත්තේ",
            option1 = "i. මිතුරන්ට පමණි", option2 = "ii. සියලු සත්ත්වයන්ට", option3 = "iii. ඥාතීන්ට පමණි", option4 = "iv. භික්ෂූන්ට පමණි", option5 = "v. මිනිසුන්ට පමණි",
            correctOptionIndex = 1 // ii
        ),
        QuizQuestion(
            setIndex = 4, questionNumber = 37,
            questionText = "37. බෞද්ධයන්ගේ මූලික ධර්ම ග්රන්ථය වන්නේ",
            option1 = "i. මහාවංශය", option2 = "ii. වේදය", option3 = "iii. ත්රිපිටකය", option4 = "iv. රාමායණය", option5 = "v. භගවත් ගීතාව",
            correctOptionIndex = 2 // iii
        ),
        QuizQuestion(
            setIndex = 4, questionNumber = 38,
            questionText = "38. \"මෙත්තා\" යන්නෙන් අදහස් වන්නේ",
            option1 = "i. වෛරය", option2 = "ii. කෝපය", option3 = "iii. සැමට හිතවත් බව", option4 = "iv. ලෝභය", option5 = "v. බිය",
            correctOptionIndex = 2 // iii
        ),
        QuizQuestion(
            setIndex = 4, questionNumber = 39,
            questionText = "39. බෞද්ධයන්ගේ උසස්ම පරමාර්ථය වන්නේ",
            option1 = "i. නිර්වාණය", option2 = "ii. ධනය රැස් කිරීම", option3 = "iii. කීර්තිය ලබා ගැනීම", option4 = "iv. බලය ලබා ගැනීම", option5 = "v. දීර්ඝායුෂ ලැබීම",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 4, questionNumber = 40,
            questionText = "40. යහපත් මිතුරා හැඳින්වෙන්නේ",
            option1 = "i. සත්පුරුෂයා", option2 = "ii. කල්යාණ මිත්රයා", option3 = "iii. සඟයා", option4 = "iv. ආචාර්යවරයා", option5 = "v. ගුරුවරයා",
            correctOptionIndex = 1 // ii
        ),

        // Set 05 (41 - 50)
        QuizQuestion(
            setIndex = 5, questionNumber = 41,
            questionText = "41. බෞද්ධයන්ගේ ප්රධාන වන්දනා ක්රියාවක් වන්නේ",
            option1 = "i. බෝධි වන්දනාව", option2 = "ii. ක්රීඩා උළෙල", option3 = "iii. වෙළඳ ප්රදර්ශනය", option4 = "iv. සංගීත ප්රසංගය", option5 = "v. නාට්ය උළෙල",
            correctOptionIndex = 0 // i
        ),
        QuizQuestion(
            setIndex = 5, questionNumber = 42,
            questionText = "42. පහන් පූජාවෙන් සංකේතවත් වන්නේ",
            option1 = "i. ධනය", option2 = "ii. ප්රඥාවේ ආලෝකය", option3 = "iii. ජයග්රහණය", option4 = "iv. බලය", option5 = "v. ආයුෂ",
            correctOptionIndex = 1 // ii
        ),
        QuizQuestion(
            setIndex = 5, questionNumber = 43,
            questionText = "43. බෞද්ධයන්ට අනුව උතුම්ම ධනය වන්නේ",
            option1 = "i. රන්", option2 = "ii. මුදල්", option3 = "iii. ධර්මය", option4 = "iv. ඉඩම්", option5 = "v. මැණික්",
            correctOptionIndex = 2 // iii
        ),
        QuizQuestion(
            setIndex = 5, questionNumber = 44,
            questionText = "44. බුදුන් වහන්සේගේ පුත්රයාගේ නම වන්නේ",
            option1 = "i. නන්ද", option2 = "ii. ආනන්ද", option3 = "iii. උපාලි", option4 = "iv. රාහුල", option5 = "v. මහාකාශ්යප",
            correctOptionIndex = 3 // iv
        ),
        QuizQuestion(
            setIndex = 5, questionNumber = 45,
            questionText = "45. බෞද්ධයෙකු දිනපතා කළ යුතු යහපත් ක්රියාවක් වන්නේ",
            option1 = "i. සූදුව", option2 = "ii. මත්පැන් පානය", option3 = "iii. බුද්ධ වන්දනාව", option4 = "iv. සොරකම් කිරීම", option5 = "v. බොරු කීම",
            correctOptionIndex = 2 // iii
        ),
        QuizQuestion(
            setIndex = 5, questionNumber = 46,
            questionText = "46. ශ්රේෂ්ඨතම ගුරුතුමා ලෙස සැලකෙන්නේ",
            option1 = "i. අශෝක රජු", option2 = "ii. මහින්ද මහරහතන් වහන්සේ", option3 = "iii. බුදුන් වහන්සේ", option4 = "iv. දේවානම්පියතිස්ස රජු", option5 = "v. සංඝමිත්තා තෙරණිය",
            correctOptionIndex = 2 // iii
        ),
        QuizQuestion(
            setIndex = 5, questionNumber = 47,
            questionText = "47. සැමට සමානව සැලකීම අයත් වන්නේ",
            option1 = "i. වෛරයට", option2 = "ii. අහංකාරයට", option3 = "iii. මෛත්රියට", option4 = "iv. ලෝභයට", option5 = "v. ක්රෝධයට",
            correctOptionIndex = 2 // iii
        ),
        QuizQuestion(
            setIndex = 5, questionNumber = 48,
            questionText = "48. සදාචාරාත්මක ජීවිතයක පදනම වන්නේ",
            option1 = "i. ධනය", option2 = "ii. ශීලය", option3 = "iii. බලය", option4 = "iv. ප්රසිද්ධිය", option5 = "v. වෙළඳාම",
            correctOptionIndex = 1 // ii
        ),
        QuizQuestion(
            setIndex = 5, questionNumber = 49,
            questionText = "49. බෞද්ධ සංඝයාට අයත් ප්රධාන සාමාජිකයා වන්නේ",
            option1 = "i. ගොවියා", option2 = "ii. වෛද්යවරයා", option3 = "iii. භික්ෂුව", option4 = "iv. වෙළෙන්දා", option5 = "v. ශිල්පියා",
            correctOptionIndex = 2 // iii
        ),
        QuizQuestion(
            setIndex = 5, questionNumber = 50,
            questionText = "50. බුදුදහමේ අවසාන අරමුණ වන්නේ",
            option1 = "i. ධනය රැස් කිරීම", option2 = "ii. සමාජ තත්ත්වය ඉහළ නැංවීම", option3 = "iii. බලය ලබා ගැනීම", option4 = "iv. දුකින් මිදී නිර්වාණය සාක්ෂාත් කිරීම", option5 = "v. ප්රසිද්ධිය ලබා ගැනීම",
            correctOptionIndex = 3 // iv
        )
    )
}
