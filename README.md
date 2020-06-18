Replica Management and Consistency

Objectives:
1. Consistency across replicas.
2. Reordering operations.
3. Using an Integrated Development Environment (IDE)

 a system consisting of a server and three client processes. Each client
process will connect to the server over a socket connection and register a user name at
the server. The server should be able to handle all three clients concurrently and display
the names of the connected clients in real time.
Each client will implement a simple four-function calculator. The calculator should
handle:
• Addition
• Subtraction
• Multiplication
• Division
• Negative numbers
• Decimals to four digits, rounding up
The calculator need not support grouping operations. The user should be able to delete
any commands prior to their execution.
Each client will keep a local copy of a shared value and will execute all operations on
that local copy. Each calculator should accept an unlimited number of operations prior
to executing them and commands should be executed according to algebraic order of
operations. Any operation resulting in NaNs should be rejected by the client and that
sequence of operations should be cleared.
When instructed by the user via a GUI input, the server will poll clients for their executed
sequence of operations. Clients will then upload the sequence of operations (not just
the final computed value) they have executed locally. The server will order all
operations received from clients according to algebraic order of operations and apply
those operations to the value stored on the server.


For example, each client may perform the following sequence of operations on its local
value.
Client 1 Client 2 Client 3
Initial Value: 1 Initial Value: 1 Initial Value: 1
+ 1 / 2 * 3
- 11 * 22 / 33
* 111 - 222 + 333
/ 1111 + 2222 - 3333
= 0.9010 = 2011.0000 = -2999.9091
The server would then execute the following sequence of operations on its copy of the
value:
<Initial Value: 1> + 1 - 11 * 111 / 1111 / 2 * 22 - 222 + 2222 * 3 / 33 + 333 - 3333
= -3030.0891.
After the server completes its calculations, it should push the new value to each of the
clients. The clients should then overwrite their local copy with the value received from
the server.
Clients should keep a persistent log of all instructions entered by the user (e.g., the log
should survive a client process being killed and restarted). If a client logs user-input
operations, but misses a server poll due to being shut down, it should upload the logged
operations during the next poll. No user-input should be uploaded more than once.
The server and the clients should each be managed with a simple GUI. The GUI should
provide a way kill the process without using the ‘exit’ button on the window.
The required actions are summarized as follows:
Client
The client will execute the following sequence of steps:
1. Initialize a local copy of the shared value.
2. Connect to the server via a socket.
3. Provide the server with a unique user name.
a. May be a string provided by the user; or,
b. Some value associated with the process.
4. Wait to be polled by server. While waiting:
a. Allow users to input and execute operations on the four-function
calculator.
b. Log user-input in persistent storage.
5. When polled by the server, upload all user-input logged since previous poll.
6. Overwrite local copy of shared value with server copy.
7. Notify user local copy has been updated.
8. Repeat at step 4 until the process is killed by the user.

Server
The server should support three concurrently connected clients and display a list of
which clients are connected in real-time. The server will execute the following sequence
of steps:
1. Initialize server copy of shared value.
2. Startup and listen for incoming connections.
3. Print that a client has connected and fork a thread to handle that client.
4. When instructed by the user, poll clients for user-input sequence.
5. Display received input sequences from clients on GUI.
6. Apply user-input sequence to server copy of shared value.
7. Push updated copy of shared value to clients.
8. Begin at step 4 until server is closed by the user.
The server must correctly handle an unexpected client disconnection without crashing.
When a client disconnects, the user must be notified in real-time. The server should
reject any sequence of operations that results in NaNs and reset the shared value to 1.



