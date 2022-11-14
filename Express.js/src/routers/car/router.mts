import express from "express";
import { saveCar } from "./saveCar.mjs";
import { update } from "./update.mjs";

// path: /car/**
export const carRouter = express.Router(); 


// path: /car/saveCar
carRouter.post("/saveCar", saveCar);


// path /car/update
carRouter.put("/update", update);


// path: /car/getCar