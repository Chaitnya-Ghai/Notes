# 📝 Notes App (IPC with Content Provider & Resolver)

This project demonstrates **Inter-Process Communication (IPC)** in Android using **Room Database, Content Provider, and Content Resolver**.  

---

## 📖 Overview
The system is divided into **two apps**:

1. **Notes Provider App**  
   - Uses **Room Database** to store notes.  
   - Exposes its database through a **custom ContentProvider**.  
   - Defines the contract (URIs, MIME types, permissions).  

2. **Notes Consumer App**  
   - Accesses the provider app’s database using a **ContentResolver**.  
   - Can perform full **CRUD operations** (Create, Read, Update, Delete) on the shared notes.  

---

## ✨ Key Concepts
- **Room Database** → Local data persistence.  
- **ContentProvider** → IPC mechanism to expose data securely across apps.  
- **ContentResolver** → Client-side API to query & modify provider data.  
- **IPC (Inter-Process Communication)** → Enables two apps to interact with the same dataset.  

---

## ⚡ Features
- Add, edit, delete, and fetch notes across apps.  
- Centralized database in **Provider App**.  
- Secure contract-based access using URIs.  
- Demonstrates **real-world IPC architecture** in Android.  

---

## 📷 Screenshots
<div style="display: flex; gap: 10px;">
  
</div>

---

## 🔑 Learning
This project is useful if you want to learn:  
- How Android IPC works with **Content Providers**.  
- How to connect **two independent apps** via contracts & resolvers.  
- Advanced usage of **Room + Provider + Resolver** combo.

---
