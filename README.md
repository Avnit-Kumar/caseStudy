# 🐬 Running MySQL in Docker (Beginner Guide)

This guide explains how to run **MySQL using Docker** on port **3306** and automatically create a database with predefined credentials.

## 📋 Database Details

* **Database name:** `casedb`
* **Username:** `user`
* **Password:** `Passw0rd`
* **Port:** `3306`

---

## 📦 Pull the MySQL Docker Image

```bash
docker pull mysql:8.0
```

---

## 🚀 Start MySQL Container

Run the following command to start MySQL in a Docker container:

```bash
docker run --name mysql-casedb -e MYSQL_ROOT_PASSWORD=Passw0rd -e MYSQL_DATABASE=casedb -e MYSQL_USER=user -e MYSQL_PASSWORD=Passw0rd -p 3306:3306 -d mysql:8.0
```

### What this does:

* Creates a database named `casedb`
* Creates a user named `user`
* Sets the password to `Passw0rd`
* Exposes MySQL on `localhost:3306`

---

## ✅ Verify MySQL Is Running

Check running containers:

```bash
docker ps
```

If the container is **not running**, check the logs:

```bash
docker logs mysql-casedb
```

---

Happy coding 🚀