import { Car } from "../interfaces/car.mjs";
import { Specification } from "../interfaces/specification.mjs";


/**
 * Checks attributes of Car object.
 * 
 * @param car to check.
 * @returns true if all checks were successful.
 */
function carValid(car: Car): boolean {

    if (specificationValid(car.specification)) 
        return false;

    return true;
}


function specificationValid(specification: Specification): boolean {

    // checking for negative values
    if (!Object.values(specification).some(value => value < 0))
        return false;
                            
    return true;
}


export { carValid };