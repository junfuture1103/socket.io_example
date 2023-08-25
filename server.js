const express = require("express");
const app = express();
const http = require("http");
const cors = require("cors");
app.use(cors());
const server = http.createServer(app);

const io = require("socket.io")(server, {
    // cors: {
    //     origin: ["http://localhost:3000"],
    //     methods: ["GET", "POST"],
    // },
});

const client_io = io.of('/client')

client_io.on("connection", (socket) => {
    console.log(`client/User connected: ${socket.id}`);
    socket.on("Disconnect", () => {
        console.log(`User disconnected: ${socket.id}`);
    });

    socket.on('Test', (jsonData) => {
        console.log('Received "Test" event from client');
        console.log('JSON Data:', jsonData);

        // Parse JSON data and send a response
        if (jsonData && jsonData.key1 && jsonData.key2) {
            const response = `Received JSON Data: key1=${jsonData.key1}, key2=${jsonData.key2}`;
            socket.emit('Test_OK', response);
        } else {
            socket.emit('Test_OK', 'Invalid JSON Data');
        }
    });

});


server.listen(3000, () => {
    console.log("SERVER WALKING");
});