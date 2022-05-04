#!/usr/bin/env node

const express = require("express");
const app = new express();
app.use((require("body-parser")).json());
const cleverbot = require("cleverbot-free");

app.post(
    "/bot",
    async (req, res) => {
        let message = req.body.message, history = req.body.history;
        if (!history) {
            cleverbot(message).then(response => res.send({"response": response}));
            console.log("Type 1")
        } else {
            cleverbot(message, history).then(response => res.send({"response": response}));
            console.log("Type 2")
        }
    }
)

app.listen(8090);