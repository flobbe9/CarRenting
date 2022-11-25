import express from "express";
import { carRouter } from "./routers/car/router.mjs";

// using localhost as default or any other if specified (e.g. in docker)
export const springDomain = (process.argv[2]) ? `http://${process.argv[2]}:4002` : "http://localhost:4002";


const app = express();
const port = 4001;


app.use(express.json());


app.use("/car", carRouter);


// handling unknown URLs
app.use("*", (req, res) => {

    res.send(`Something wrong with this url.`);
});


app.listen(port, () => console.log(`Listening on port ${port}.`));