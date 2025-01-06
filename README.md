# Name: Prajapati Uday Mukesh
# Company: CODTECH IT SOLUTIONS 
# Id: CT6WDS2794
# Domain: Java Programming
# Duration: DECEMBER 12th, 2024 to JANUARY 27th, 2025.
# Mentor: Muzammil Ahmed

# Overview:
This project implements a multi-client chat application using Java. It consists of two main components:
ChatServer: A server that manages multiple client connections, broadcasts messages, and handles user authentication by ensuring unique usernames.
ChatClient: A client application that connects to the server, enables users to send and receive messages, and provides a real-time chat experience.

# Key Activities:
# 1. ChatServer
Accepting Client Connections:
The server continuously listens for incoming client connections on a specific port (12345). Each client connection is handled by a dedicated thread using the ClientHandler class.

# 2. Managing Clients:
A synchronized list (clientWriters) stores output streams for all connected clients to facilitate message broadcasting.
A synchronized set (clientNames) ensures unique usernames by prompting a client to choose another name if the provided one is already in use.

# 3. Broadcasting Messages:
The server broadcasts messages to all connected clients, including notifications when a user joins or leaves the chat.

2. ChatClient
# 1. Connecting to the Server:
The client establishes a connection with the server using a socket. It sends the username and messages to the server and receives broadcasts from other users.

# 2. Real-Time Communication:
A dedicated thread (ServerListener) listens for messages from the server and displays them in the console.
The main thread handles user input and sends it to the server.

# Technology Used:
# 1. Socket Programming:
ServerSocket for accepting incoming connections on the server.
Socket for client-server communication.

# 2. Multi-Threading:
The server uses threads (ClientHandler) to handle multiple client connections simultaneously.
The client employs a thread (ServerListener) for listening to server broadcasts while the main thread sends messages.

# 3. Synchronized Data Structures:
ArrayList and HashSet are synchronized to ensure thread-safe operations while managing shared resources like client output streams and usernames.

# 4. I/O Streams:
BufferedReader and PrintWriter for efficient reading and writing of data between the server and clients.

# Key Insights:
# 1. Concurrent Client Management:
The server's multi-threaded architecture allows it to handle multiple clients simultaneously, ensuring scalability.

# 2. User Authentication:
Unique username enforcement prevents ambiguity during communication and improves user experience.

# 3. Real-Time Communication:
The application demonstrates full-duplex communication where clients can send and receive messages concurrently.
Notifications like "User joined" and "User left" enhance interactivity.

# Final Output:
# ChatServer:
![image](https://github.com/user-attachments/assets/a52bb233-a876-49d5-8259-7d46fd862bce)
# ChatClient:
![image](https://github.com/user-attachments/assets/f238b442-cb55-4e22-bc8c-fb46f6624677)
![image](https://github.com/user-attachments/assets/fe44a42c-ac6b-43ea-80ea-68ba1ea63f76)

