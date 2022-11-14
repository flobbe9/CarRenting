import { FetchHeader } from "../../interfaces/fetchHeader.mjs";
import { makeRequest } from "../../logic/fetchAPI.mjs";
import { springDomain } from "../../main.mjs";


async function getById(id: number) {

    const fetchHeaders: FetchHeader = {
        method: "get",
        headers: {
            "Content-Type": "application/json"
        }
    }
    
    const response = await makeRequest(springDomain + `/car/getById?id=${id}`, fetchHeaders);

    return response;
}


export { getById };