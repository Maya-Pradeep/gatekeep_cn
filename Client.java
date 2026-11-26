import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 6789);

        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        Scanner sc = new Scanner(System.in);
        System.out.print("Username: ");
        out.writeUTF(sc.nextLine());
        System.out.print("Password: ");
        out.writeUTF(sc.nextLine());

        System.out.println(in.readUTF());

        s.close();
    }
}
