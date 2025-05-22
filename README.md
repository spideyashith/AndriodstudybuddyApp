
---

````markdown
# 📚 Study Buddy – College Study Companion App

Study Buddy is a collaborative Android application designed for college students to find study partners, share resources, track goals, join discussions, and schedule study sessions effectively. Built using **Java**, **Firebase**, and **Android Studio**, it offers a suite of features to help learners stay productive and connected.

---

## 🚀 Features

- 🔐 **Authentication**
  - Register/Login using Email (Google & Facebook coming soon!)
  
- 👥 **Study Partner Matching**
  - Find and connect with study partners by course.

- 🗂️ **Resource Sharing**
  - Upload and download PDF resources securely via Firebase Storage.

- 🎯 **Study Goals & Progress Tracker**
  - Set, track, and manage academic goals with visual progress indicators.

- 💬 **In-App Chat**
  - Real-time messaging with study buddies using Firebase Realtime Database.

- 🧑‍💻 **Discussion Forums**
  - Participate in course-specific discussions (e.g., BCA, BBA, MCA).

- 📅 **Schedule Study Sessions**
  - Pick date/time, enter topics, and view scheduled sessions.

- 🔔 **Push Notifications** *(Upcoming)*
  - Get notified of new messages, upcoming sessions, and deadlines.

---

## 🛠️ Tech Stack

- **Java** – Core programming language
- **XML** – UI Layouts
- **Firebase**
  - Authentication
  - Realtime Database
  - Firebase Storage
- **Gradle** – Dependency Management
- **Android Studio Meerkat (Giraffe compatible)**

---

## 🔧 Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/study-buddy-app.git
   cd study-buddy-app
````

2. **Open in Android Studio**

3. **Connect Firebase**

   * Add your own `google-services.json` to the `app/` directory.
   * Enable:

     * Authentication (Email/Password)
     * Realtime Database
     * Firebase Storage

4. **Set Firebase Rules** (in Firebase console)

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
├── Activities/
│   ├── HomeActivity.java
│   ├── InAppChatActivity.java
│   ├── ScheduleActivity.java
│   ├── ForumChatActivity.java
│   └── ...
├── Adapters/
├── Models/
├── res/
│   ├── layout/
│   ├── drawable/
│   └── values/
└── utils/
```

---

## 🙋‍♂️ Author

* 💼 [Your Name](https://github.com/your-username)
* 📧 [your.email@example.com](mailto:your.email@example.com)

---

## 🌟 Show Your Support

If you like this project:

* ⭐ Star it on GitHub
* 🍴 Fork it
* 🐛 Report issues or contribute enhancements!

---

## 📜 License

This project is licensed under the **MIT License** – see the [LICENSE](LICENSE) file for details.

```

---

And This Project is open source u can Contribute towards this project and push back to this github account.
```
