import { FetchHeader } from "../../interfaces/fetchHeader.mjs";
import { makeRequest } from "../../logic/fetchAPI.mjs";
import { springDomain } from "../../main.mjs";
import { carValid } from "../../logic/carValid.mjs";


/**
 * Passes a car object to the spring api. If successful, the car will be saved in the db.
 * 
 * @param req 
 * @param res 
 * @param next 
 * @returns the saved car from the spring api or an error message if input data was invalid.
 */
async function saveCar(req, res, next) {

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
    const response = await makeRequest(springDomain + "/car/saveCar", fetchHeaders);
    res.send(response);
};



export { saveCar };