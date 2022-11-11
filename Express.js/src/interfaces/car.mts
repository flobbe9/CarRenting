import { Specification } from "./specification.mjs";

export interface Car {
    brand: string, 
    model: string, 
    color: string, 
    fuelType: string, 
    specification: Specification
}