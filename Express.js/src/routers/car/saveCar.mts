import express from "express";
import { FetchHeader } from "../../interfaces/fetchHeader.mjs";
import { makeRequest } from "../../logic/fetchAPI.mjs";
import { springDomain } from "../../main.mjs";
import { carValid } from "../../logic/carValid.mjs";

// path: /car/saveCar
export const saveCarRouter = express.Router(); 


saveCarRouter.post("/", async (req, res, next) => {

    const body = req.body;

    // if request body misses
    if (!body) {
        res.send("Bad request. Missing request body.");
        return;
    }

    // if car is invalid
    if (!carValid(body)) {
        res.send("Invalid car attribute.");
        return;
    }

    // creating fetchHeaders
    const fetchHeaders: FetchHeader = {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: body
    }

    // redirecting request to spring API
    res.send(await makeRequest(springDomain + "/car/saveCar", fetchHeaders));
});