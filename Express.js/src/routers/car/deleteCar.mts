import { FetchHeader } from "../../interfaces/fetchHeader.mjs";
import { makeRequest } from "../../logic/fetchAPI.mjs";
import { objectValid } from "../../logic/objectValid.mjs";
import { springDomain } from "../../main.mjs";


async function deleteCar(req, res, next) {

    const body = req.body;
    
    // case: request body invalid 
    if (!objectValid(body)) {
        res.send("Invalid request body.");
        return false;
    }

    const fetchHeaders: FetchHeader = {
        method: "delete",
        headers: {
            "Content-Type": "application/json"
        },
        body: req.body
    }

    // redirecting request to spring API
    const response = await makeRequest(springDomain + req.originalUrl, fetchHeaders);
    console.log(response);
    res.send(response);
    return response;
}


export { deleteCar };