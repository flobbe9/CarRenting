import { FetchHeader } from "../../interfaces/fetchHeader.mjs";
import { makeRequest } from "../../logic/fetchAPI.mjs";
import { springDomain } from "../../main.mjs";


/**
 * Redirects any request that is not processed before to spring boot without 
 * doing anything with it.
 * 
 * @param req 
 * @param res 
 * @param next 
 * @returns The response object from the spring api.
 */
async function redirect(req, res, next) {

    // incomplete fetch header
    let fetchHeaders: FetchHeader = {
        method: req.method,
        headers: {
            "Content-Type": req.get("Content-Type")
        }
    }

    // set body if exists
    if (req.body && Object.keys(req.body).length !== 0)
        fetchHeaders.body = req.body;

    // redirecting request to spring API
    const response = await makeRequest(springDomain + req.originalUrl, fetchHeaders);
    res.send(response);
    return response;
};


export { redirect };