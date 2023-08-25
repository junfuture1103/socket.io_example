package testjun;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class socket_jun {
	public static Socket mSocket;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello, world!!!!");
		
		try {
            IO.Options options = new IO.Options();
            options.reconnection = true;
            options.reconnectionAttempts = 3;
            options.reconnectionDelay = 1000;
            options.forceNew = true; // Similar to rejectUnauthorized: false in JavaScript

//            mSocket = IO.socket("http://192.168.1.231:443/client", options);
            mSocket = IO.socket("http://127.0.0.1:3000/client", options);
            mSocket.connect();
        } catch (URISyntaxException e) {}
		
		
		// socket on-emit
		mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Socket connected");
                
                JsonObject jsonData = new JsonObject();
                jsonData.addProperty("key1", "value1 by jun");
                jsonData.addProperty("key2", "value2 by jun");

                mSocket.emit("Test", jsonData);         
            }
        }).on("Test_OK", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Received 'Test_OK' event from server");
                // Here, you can perform any action you want upon receiving the "Test" event
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Socket disconnected");
            }
        });
		

	}

}
