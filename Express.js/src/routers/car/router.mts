import express from "express";
import { deleteCar } from "./deleteCar.mjs";
import { getAllBy } from "./getAllBy.mjs";
import { getCar } from "./getCar.mjs";
import { saveCar } from "./saveCar.mjs";
import { update } from "./update.mjs";

// path: /car/**
export const carRouter = express.Router(); 


// path: /car/saveCar
carRouter.post("/saveCar", saveCar);


// path /car/update
carRouter.put("/update", update);


// path: /car/getCar
carRouter.post("/getCar", getCar);


// path: /car/getById
carRouter.get("/getById", getAllBy);


// path: /car/getAll
carRouter.get("/getAll", getAllBy);


// path: /car/getAllByBrandAndModel
carRouter.get("/getAllByBrandAndModel", getAllBy);


// path: /car/getAllByIsAvailable
carRouter.get("/getAllByIsAvailable", getAllBy);


// path: /car/getAllByFuelType
carRouter.get("/getAllByFuelType", getAllBy);


// path: /car/getAllByColor
carRouter.get("/getAllByColor", getAllBy);


// path: /car/existsByModel
carRouter.get("/existsByModel", getAllBy);


// path: /car/delete
carRouter.delete("/delete", deleteCar);