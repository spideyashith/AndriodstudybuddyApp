######### 🎓✨ **Study Buddy – Your Ultimate College Study Companion**
**A collaborative, student-focused Android app to help you study smarter, not harder!**










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

* 💼 Ashith Fernandes
* 📧 ashithfernandes319@gmail.com

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
