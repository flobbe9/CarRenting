import express from "express";
import { redirect } from "./redirect.mjs";
import { update } from "./update.mjs";

export const carRouter = express.Router(); 


// path: /car/update
carRouter.put("/update", update);

// path: /car/**
carRouter.use("/**", redirect);