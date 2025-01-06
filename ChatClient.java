import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Read initial message from server (e.g., "Enter your name:")
            String serverMessage = serverInput.readLine();
            System.out.println(serverMessage);

            // Get the client's name from user input
            String clientName = input.readLine();
            output.println(clientName);

            // Start a thread to listen for messages from the server
            new Thread(new ServerListener(serverInput)).start();

            // Main thread to handle sending messages
            String message;
            while ((message = input.readLine()) != null) {
                output.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Runnable to listen for messages from the server
    private static class ServerListener implements Runnable {
        private BufferedReader serverInput;

        public ServerListener(BufferedReader serverInput) {
            this.serverInput = serverInput;
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = serverInput.readLine()) != null) {
                    System.out.println(message);  // Print server messages (chat messages)
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
