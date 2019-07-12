import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args) throws IOException {
        //
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        //
        try {
            socket = new Socket("localhost", 5858);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Sunucu bulunamadı");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("I/O exception:" + e.getMessage());
            System.exit(1);
        }
        //
        System.out.println("Sunucuya baglanildi.");
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        System.out.println("Buyuk harflere cevrilmesi icin girdi bekleniyor (baglantiyi kesmek icin: 'exit') ...");
        while (!(userInput = stdInput.readLine()).equals("exit")) {
            out.println(userInput);
            System.out.println("Sunucudan gelen: " + in.readLine());
        }
        System.out.println("Baglanti kesiliyor...");
        //
        out.close();
        in.close();
        stdInput.close();
        socket.close();
    }
}