import { FetchHeader } from "../../interfaces/fetchHeader.mjs";
import { makeRequest } from "../../logic/fetchAPI.mjs";
import { objectValid } from "../../logic/objectValid.mjs";
import { springDomain } from "../../main.mjs";


async function getCar(req, res, next) {

    // case: one query is invalid
    if (!objectValid(req.query)) {
        res.send("Failed to get car. Something wrong with query input.")
        return false;
    }

    const fetchHeaders: FetchHeader = {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: req.body
    }

    // make request to spring
    const response = await makeRequest(springDomain + req.originalUrl, fetchHeaders);
    res.send(response);
    return response;
}


export { getCar };