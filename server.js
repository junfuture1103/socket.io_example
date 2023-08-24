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
});

io.on("connection", (socket) => {
    console.log(`User connected: ${socket.id}`);
    socket.on("Disconnect", () => {
        console.log(`User disconnected: ${socket.id}`);
    });
});

server.listen(3000, () => {
    console.log("SERVER WALKING");
});