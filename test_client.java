package testjun;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;
import org.json.JSONObject;


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
//		mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//                System.out.println("Socket connected");
//        
//            }
//        })
		
		mSocket.on("connection-success", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("===== connection success ======");
                // Here, you can perform any action you want upon receiving the "Test" event
                
                JSONObject receivedData = (JSONObject) args[0];
                
                String key1Value = receivedData.getString("socketId");
                
                // socket id get
                System.out.println(receivedData);
                System.out.println(key1Value);
                
                //getRTPcap
                getRTPcap();
                
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Socket disconnected");
            }
        });
		

	}

    // Function to emit getRTPcap
    public static void getRTPcap() {
        JSONObject jsonData = new JSONObject();
        jsonData.put("roomNumber", "101");
        jsonData.put("url", "wallpad");

        mSocket.emit("getRTPcap", jsonData, new Ack() {
            @Override
            public void call(Object... args) {
                JSONObject receivedData = (JSONObject) args[0];
                System.out.println(receivedData);
            }
        });
    }
	
}
