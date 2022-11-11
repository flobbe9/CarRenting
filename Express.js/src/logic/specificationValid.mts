import { Specification } from "../interfaces/specification.mjs";


/**
 * Checks Specification object.
 * 
 * @param specification to check.
 * @returns true if all checks were successful.
 */
function specificationValid(specification: Specification): boolean {

    // checking for negative values
    if (!Object.values(specification).some(value => value < 0))
        return false;
                            
    return true;
}


export { specificationValid };