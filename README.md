# 📚 Study Buddy – College Study Companion App

**Study Buddy** is a collaborative Android application designed for college students to find study partners, share resources, track goals, join discussions, and schedule sessions effectively.  
Built with **Java**, **Firebase**, and **Android Studio**, it helps learners stay productive and connected.

---

## 🚀 Features

- 🔐 **Authentication**  
  Register/Login using Email (Google & Facebook coming soon!)

- 👥 **Study Partner Matching**  
  Connect with students by course/stream (BCA, BBA, MCA, etc.)

- 🗂️ **Resource Sharing**  
  Upload/download study materials securely via Firebase Storage

- 🎯 **Study Goals & Progress Tracker**  
  Set academic goals and visually track your progress

- 💬 **In-App Chat**  
  Real-time chat using Firebase Realtime Database

- 🧑‍💻 **Discussion Forums**  
  Participate in course-specific forums with peers

- 📅 **Schedule Study Sessions**  
  Pick topics, set dates/times, and view upcoming sessions

- 🔔 **Push Notifications** *(Coming Soon)*  
  Alerts for chats, sessions, deadlines

---

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| `Java` | Application logic |
| `XML` | UI Layouts |
| `Firebase Auth` | User Login/Register |
| `Firebase Database` | Real-time data |
| `Firebase Storage` | File uploads |
| `Gradle` | Dependency management |
| `Android Studio Meerkat` | Development IDE |

---

## 🔧 Installation & Setup

### 📥 Clone the Repository
```bash
git clone https://github.com/your-username/study-buddy-app.git
cd study-buddy-app
```

### ⚙ Open in Android Studio

1. Launch Android Studio
2. Select `Open an existing project`
3. Choose this project folder

### 🔗 Connect Firebase

1. Add your `google-services.json` file to `/app`
2. In Firebase Console, enable:
   - Firebase Authentication (Email/Password)
   - Firebase Realtime Database
   - Firebase Storage

### 🔐 Set Firebase Rules

```json
{
  "rules": {
    "Users": {
      ".read": "auth != null",
      "$uid": {
        ".write": "auth != null && auth.uid == $uid"
      }
    },
    "StudyGoals": {
      "$uid": {
        ".read": "auth != null && auth.uid == $uid",
        ".write": "auth != null && auth.uid == $uid"
      }
    },
    "inappchat": {
      ".read": "true",
      ".write": "true"
    },
    "study_partners": {
      ".read": "auth != null",
      "$uid": {
        ".write": "auth != null && auth.uid == $uid"
      }
    },
    "resources": {
      ".read": "auth != null",
      ".write": "auth != null"
    },
    "sessions": {
      "$uid": {
        ".read": "auth != null && auth.uid == $uid",
        ".write": "auth != null && auth.uid == $uid"
      }
    }
  }
}
```

---

## 📂 Project Structure

```
📦 study-buddy-app/
├── java/
│   └── com/example/studyapp/
│       ├── Activities/
│       │   ├── HomeActivity.java
│       │   ├── InAppChatActivity.java
│       │   ├── ScheduleActivity.java
│       │   ├── ForumChatActivity.java
│       └── ...
├── adapters/
├── models/
├── res/
│   ├── layout/
│   ├── drawable/
│   └── values/
├── utils/
└── google-services.json  ← (Add yours here)
```

---

## 👤 Author

**Ashith Fernandes**  
📧 [ashithfernandes319@gmail.com](mailto:ashithfernandes319@gmail.com)

---

## 🌟 Show Your Support

If you like this project:

- ⭐ Star it on GitHub  
- 🍴 Fork and build on top of it  
- 🐛 Report bugs or suggest features  

---

## 📜 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

---

_This project is open source. Feel free to contribute, enhance it, and push your improvements to this GitHub repo!_

