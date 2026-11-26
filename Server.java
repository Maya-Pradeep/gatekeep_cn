import java.io.*;
import java.net.*;
import java.nio.file.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(6789);
        Socket s = ss.accept();

        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        String user = in.readUTF();
        String pass = in.readUTF();
        String result = "Invalid username or password";

        for (String line : Files.readAllLines(Paths.get("users.txt"))) {
            String[] parts = line.split(" ");
            if (parts[0].equals(user) && parts[1].equals(pass))
                result = "Login successful. Welcome, " + user + "!";
        }

        out.writeUTF(result);

        s.close();
        ss.close();
    }
}
