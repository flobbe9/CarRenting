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

    // if car is invalid
    if (!carValid(req.body)) {
        res.send("Invalid car attribute.");
        return;
    }

    // creating fetchHeaders
    const fetchHeaders: FetchHeader = {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: req.body
    }

    // redirecting request to spring API
    const response = await makeRequest(springDomain + "/car/saveCar", fetchHeaders);
    res.send(response);
    return response;
};



export { saveCar };