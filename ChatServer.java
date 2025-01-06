import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    // List to store client output streams for broadcasting messages
    private static final List<PrintWriter> clientWriters = new ArrayList<>();
    private static final Set<String> clientNames = new HashSet<>();

    public static void main(String[] args) {
        int port = 12345;  // Server listening port
        System.out.println("Chat server started...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                // Accept a new client connection and start a handler thread
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ClientHandler will manage communication with a specific client
    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // Set up input and output streams for the client
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Add the client to the list of writers
                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                // Prompt for client name
                out.println("Enter your name: ");
                clientName = in.readLine();
                
                // Ensure that no other client has the same name
                synchronized (clientNames) {
                    while (clientNames.contains(clientName)) {
                        out.println("Name already taken. Enter a new name: ");
                        clientName = in.readLine();
                    }
                    clientNames.add(clientName);
                }

                // Welcome message
                broadcastMessage(clientName + " has joined the chat.");

                String message;
                while ((message = in.readLine()) != null) {
                    // Broadcast the message to all clients
                    broadcastMessage(clientName + ": " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Remove client from the list when they disconnect
                try {
                    if (clientName != null) {
                        broadcastMessage(clientName + " has left the chat.");
                    }
                    synchronized (clientWriters) {
                        clientWriters.remove(out);
                    }
                    synchronized (clientNames) {
                        clientNames.remove(clientName);
                    }
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Broadcast a message to all connected clients
        private void broadcastMessage(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }
    }
}
