package com.hgw.homework.example.eg2;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 追风同学
 * @date 2020/05/28 11:48
 * @description
 */
public class Server {


    private static ServerSocket server;
    private static Socket socket;
    private static final int port = 8082;

    public static void main(String[] args) throws IOException {
        //create server with port
        server = new ServerSocket(port);
        while(true) {
            //listen server and bind socket when accept a connection
            socket = server.accept();
            listenRequest(socket);
        }
    }

    /**
     * create a new thread for socket.<br>
     * listen input from socket<br>
     * process requests and give responses to client.
     * @param socket the specific socket that connects to the server
     */
    private static void listenRequest(Socket socket) {
        /*
         * create a new thread for this socket so that it
         * can be responsible for this socket only
         */
        new Thread(new Runnable(){
            DataInputStream in;
            DataOutputStream out;
            boolean status;

            @Override
            public void run() {
                try {
                    /*
                     * create DataInputStream and DataOutputStream objects to get
                     * the stream of socket
                     */
                    in = new DataInputStream(socket.getInputStream());
                    out = new DataOutputStream(socket.getOutputStream());
                    status = true;
                    while(status) {
                        processInput();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            /**
             * process requests from client and give response respectively
             * @throws IOException
             */
            private void processInput() throws IOException {
                String content = in.readUTF();
                String[] command = content.split(":");
                String fileName = null;
                switch(command[0]) {
                    case "FILE_TRANSFER_REQUEST":
                        /*
                         * structure of FILE_REQUEST should be:
                         * @COMMAND:@FILENAME
                         */
                        fileName = command[1];
                        fileTransferResponse(fileName);
                        break;
                    case "FILE_BYTES_REQUEST":
                        /*
                         * structure of FILE_BYTES_REQUEST
                         * @Command:@FileName
                         */
                        fileName = command[1];
                        fileBytesResponse(fileName);
                        break;
                    case "DISCONNECT":
                        stop();
                        break;
                    default:
                        break;
                }

            }

            /**
             * close relevant resources and change status to
             * false to end this thread
             * @throws IOException
             */
            private void stop() throws IOException {
                in.close();
                out.close();
                socket.close();
                status = false;
            }

            /**
             * send file bytes(raw data) to client
             * @param fileName name of file
             * @throws IOException
             */
            private void fileBytesResponse(String fileName) throws IOException {
                File file = new File(fileName);
                FileInputStream fileInput = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int length = 0;
                while((length = fileInput.read(bytes)) != -1) {
                    out.write(bytes, 0, length);
                    out.flush();
                }
                fileInput.close();
            }

            private void fileTransferResponse(String fileName) throws IOException {
                File file = new File(fileName);
                StringBuffer response = new StringBuffer();
                /*
                 * if file does not exist, response to the client
                 * with error message
                 */
                if(!file.exists()) {
                    //make sure there is nothing in response
                    response.setLength(0);
                    /*
                     * structure of FILE_NOT_EXISTS should be:
                     * @COMMAND:@MESSAGE
                     */
                    response.append("FILE_NOT_EXISTS:");
                    response.append("The file \"" + fileName+ "\" your request does not exist.");
                    out.writeUTF(response.toString());
                    out.flush();
                }else {
                    /*
                     * structure of FILE_EXISTS should be;
                     * @Command:@FileName:@FileLength
                     */
                    response.setLength(0);
                    response.append("FILE_EXISTS:");
                    response.append(fileName+":");
                    response.append(file.length());
                    out.writeUTF(response.toString());
                    out.flush();
                }
            }

        }).start();
    }



}