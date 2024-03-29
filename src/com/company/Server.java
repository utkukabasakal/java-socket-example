import java.net.*;
import java.io.*;
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5858);
        } catch (IOException e) {
            System.err.println("I/O exception: " + e.getMessage());
            System.exit(1);
        }
        System.out.println("Sunucu baslatildi. Baglanti bekleniyor...");
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Basarısız.");
            System.exit(1);
        }
        //
        System.out.println(clientSocket.getLocalAddress() + " baglandi.");
           PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                clientSocket.getInputStream()));
        //
        String inputLine, outputLine;
        System.out.println("İstemciden girdi bekleniyor...");
        while ((inputLine = in.readLine()) != null) {
            System.out.println("istemciden gelen :" + inputLine);
            outputLine = inputLine.toUpperCase();
            out.println(outputLine);
            if (outputLine.equals("Exit"))
                break;
        }
        //
        System.out.println(clientSocket.getLocalSocketAddress()
                + " baglantisi kesildi.");
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}

