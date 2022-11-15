import { FetchHeader } from "../../interfaces/fetchHeader.mjs";
import { makeRequest } from "../../logic/fetchAPI.mjs";
import { isObjectEmpty, objectValid } from "../../logic/objectValid.mjs";
import { springDomain } from "../../main.mjs";


/**
 * Serves as getter method for all urls that don't have a request body or path variables.
 * Redirects the exact same url to spring with which this endpoint was called.
 * 
 * @param req 
 * @param res 
 * @param next 
 * @returns false if query input is invalid, http status if spring request was unsuccessful and the 
 *          requested car(s) if successful.
 */
async function getAllBy(req, res, next) {

    // case: request queries invalid
    if (!isObjectEmpty(req.query) && !objectValid(req.query)) {
        res.send("Failed to get car. Something wrong with query input.");
        return false;
    }

    const fetchHeaders: FetchHeader = {
        method: "get",
        headers: {
            "Content-Type": "application/json"
        }
    }

    // make request to spring
    const response = await makeRequest(springDomain + req.originalUrl, fetchHeaders);
    res.send(response);
    return response;
}


export { getAllBy };