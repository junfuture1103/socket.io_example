// import io from "socket.io-client";
const io = require("socket.io-client");

const socket = io("ws://localhost:443/client");
// const socket = io("/client");

// send a message to the server
socket.emit("hello from client", 5, "6", { 7: Uint8Array.from([8]) });

// receive a message from the server
socket.on("hello from server", (...args) => {
    // ...
});