import express from "express";
import { objectValid } from "./logic/objectValid.mjs";
import { carRouter } from "./routers/car/router.mjs";

export const springDomain = "http://localhost:4002";


const app = express();
const port = 4001;


app.use(express.json());


// checking every requestbody or parameter with objectValid()
app.use((req, res, next) => {
    
    // body
    if (req.body && !objectValid(req.body)) 
        throw "Invalid input.";
    
    // parameter
    if (req.params && !objectValid(req.query)) 
        throw "Invalid input.";

    next();
});


app.use("/car", carRouter);


// handling unknown URLs
app.use("*", (req, res, next) => {

    res.send(`Something wrong with this url.`);
});


app.listen(port, () => console.log(`Listening to port ${port}.`));