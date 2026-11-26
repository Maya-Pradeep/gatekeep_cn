# GateKeeper

**Client-Server User Authentication System Using Java Sockets**

Computer Networks (24CSE153) activity, Dept. of CSE, BNMIT.

A client sends a username and a password to a server over a TCP socket. The server checks them against `users.txt` and replies `Login successful. Welcome, <username>!` or `Invalid username or password`.

## Files

| File | What it is |
| --- | --- |
| `Server.java` | Listens on port 6789, reads the username and password, checks them against `users.txt`, sends the result |
| `Client.java` | Connects to the server, sends the username and password typed by the user, prints the reply |
| `users.txt` | Registered accounts, one per line: `username password` |

Java standard library only (`java.net`, `java.io`, `java.nio.file`). Works on JDK 8 or later.

## Run it

```
javac Server.java Client.java
java Server        # terminal 1 (keep users.txt in the same folder)
java Client        # terminal 2
```

Start the server first. It handles one login attempt and then exits, so run `java Server` again before each attempt.

## Sample output

```
Username: faisal
Password: pass123
Login successful. Welcome, faisal!
```

```
Username: faisal
Password: wrong99
Invalid username or password
```

## How it works

1. `ServerSocket(6789)` listens, and `accept()` waits for the client's TCP connection.
2. The client sends the username, then the password, with two `writeUTF()` calls. The server reads them in the same order with two `readUTF()` calls.
3. The server starts with "Invalid username or password" (deny by default), splits each line of `users.txt` into `parts[0]` (username) and `parts[1]` (password), and changes the result to success only if **both** match on the same line.
4. The server sends the result with `writeUTF()`, and both sides close the connection.

## Limitations

This is a learning project: passwords travel as plain text and are stored as plain text in `users.txt`. A real system would use TLS (`SSLServerSocket`) and store salted password hashes (bcrypt or PBKDF2).
