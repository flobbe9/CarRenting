import express from "express";
import { saveCarRouter } from "./saveCar.mjs";

// path: /car/**
export const carRouter = express.Router(); 


// path: /car/saveCar
carRouter.use("/saveCar", saveCarRouter);


// path: /car/getCar


// ...