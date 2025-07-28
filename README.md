# 🚨 Android Intent Hijacking Demo

This repository contains **three Android applications** designed to demonstrate **intent hijacking vulnerabilities** in Android.  
It is intended **for educational and training purposes** to help developers and security professionals understand how **implicit** and **explicit** intents can expose sensitive data.

---

## 📱 Applications Overview

### 1. `VulnerableSender` (Target App)

An Android app that simulates a vulnerable component by allowing users to input sensitive data and send it via:

- **Implicit Intent** – Sends data without specifying a target app, allowing **any matching app** to receive it.
- **Explicit Intent** – Sends data directly to a known receiver app **(hardcoded)**.

#### 🔹 Features:
- UI with an input field for sensitive data
- Two buttons:
  - **Implicit Intent Hijacking** – Triggers Android's chooser dialog
  - **Explicit Intent Hijacking** – Sends data directly to `IntentHijack1` or `IntentHijack2`

---

### 2. `IntentHijack1` (Malicious Receiver App #1)

A receiver app designed to **intercept and display** data sent via intents.

#### 🔹 Features:
- Displays received intent action and extras
- Handles both implicit and explicit intents
- Registers intent filter to match:
  - `android.intent.action.VIEW`
  - `text/plain` MIME type
- Can intercept **implicit intents** if user chooses it from the chooser dialog
- Can receive **explicit intent** if explicitly declared in the sender app

---

### 3. `IntentHijack2` (Malicious Receiver App #2)

Similar to `IntentHijack1`, but meant to demonstrate **another explicit hijacking scenario**.

#### 🔹 Features:
- Same functionality as Hijack1
- Different `packageName` and `Activity` to simulate alternate hijack path
- Demonstrates how **hardcoding an explicit intent** to the wrong component can expose data

---

## 🔍 Implicit vs Explicit Intent Hijacking

### ✅ Implicit Intent Hijacking (Common & Dangerous)
- Occurs when `startActivity(Intent)` is called with **no specific component set**
- Any app that matches the declared `IntentFilter` can register and intercept the intent
- Android shows a chooser dialog if multiple apps match

### ⚠️ Explicit Intent Hijacking (Less Common but Possible)
- Happens when the developer explicitly specifies a component (`setComponent(...)`) or package name
- **Hijack is possible** if:
  - The attacker app has the **same package and class name** as the intended one, and
  - The intent is sent before the legit app is installed (or if the attacker is installed later with higher priority)
- Can also occur if the explicit intent is **hardcoded to a non-secure third-party app**

---

## 🧪 How to Use

1. **Install all three APKs** on your Android device or emulator:
   - `VulnerableSender.apk`
   - `IntentHijack1.apk`
   - `IntentHijack2.apk`

2. Launch `VulnerableSender`.

3. Enter sensitive data (e.g., `MyPassword123`).

4. Click:
   - **Implicit Intent Hijacking** → Android will show a chooser dialog → Select either Hijack app
   - **Explicit Intent Hijacking** → App directly launches a hijack receiver

5. Observe how the receiver app displays the hijacked data.

---

## 📂 Repository Structure

```
intent-hijack-demo/
│
├── VulnerableSender/           # Android Studio project for vulnerable app
│   └── app/
│       └── src/main/java/com/tabsec/vulnerablesender/
│
├── IntentHijack1/             # Malicious Receiver App 1
│   └── app/
│       └── src/main/java/com/tabsec/intenthijack1/
│
├── IntentHijack2/             # Malicious Receiver App 2
│   └── app/
│       └── src/main/java/com/tabsec/intenthijack2/
│
├── apks/                      # Prebuilt APKs for quick testing
│   ├── VulnerableSender.apk
│   ├── IntentHijack1.apk
│   └── IntentHijack2.apk
│
├── README.md                  # This documentation file
└── .gitignore                 # Standard ignore rules
```

---

## ⚠️ Disclaimer

This project is intended **solely for educational and training purposes**.  
Do **not** use it for malicious activities or in production environments.  
Always follow ethical guidelines and responsible disclosure practices.

---
