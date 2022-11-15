import express from "express";
import { objectValid } from "./logic/objectValid.mjs";
import { carRouter } from "./routers/car/router.mjs";

export const springDomain = "http://localhost:4002";


const app = express();
const port = 4001;


app.use(express.json());


// assuming that every post request has a request body
app.post("/**", (req, res, next) => {

    if (!objectValid(req.body))
        throw "Invalid request body.";

    next();
});


app.use("/car", carRouter);


// handling unknown URLs
app.use("*", (req, res, next) => {

    res.send(`Something wrong with this url.`);
});


app.listen(port, () => console.log(`Listening to port ${port}.`));