import fetch from "node-fetch";
import { FetchHeader } from "../interfaces/fetchHeader.mjs";


/**
 * Making a request with any method to any url.
 * 
 * @param url complete entpoint.
 * @param fetchHeaders request headers with optional request body.
 * @returns json response(if 200_OK), the http status(if not 200_OK) or an error.
 */
async function makeRequest(url: string, fetchHeaders: FetchHeader) {
    
    try {
        // stringify request body if exists
        let body = fetchHeaders.body;
        if (body) fetchHeaders.body = JSON.stringify(body);

        const response = await fetch(url, fetchHeaders);

        // request successful
        if (response.ok) 
            return await response.json();

        // request not successful
        console.log(`An error occurred!\nStatus: ${response.status}\nMessage: ${await response.text()}`);
        return response.status;

    } catch (e) {
        console.log(e);
        return 500;
    }
} 


export { makeRequest };