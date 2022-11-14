/**
 * Checks any object or iterable for falsy values.
 *      
 * @param obj to check.
 * @returns false if:
 * - 'obj' is null,                                                                      
 * - 'obj' is undefined,                                                                      
 * - 'obj' is NaN,                                                                      
 * - 'obj' is a string with length 0                                                                      
 * - 'obj' is an iterable and any of the above applies to one element in the iterable.
 * - 'obj' is an object but has no keys or any of the above applies to one element in the object.
 */
 function objectValid(obj: any): boolean {
    
    if (isNullOrUndefined(obj)) 
        return false;
    
    if (isNotANumber(obj)) 
        return false;
        
    if (isEmptyString(obj))
        return false;
    
    if (isIterable(obj))
        return !obj.some(el => !objectValid(el));

    if (isObject(obj)) {
        if (isObjectEmpty(obj)) 
            return false;

        return !Object.entries(obj).some((key, value) => !objectValid(key) || !objectValid(value));
    }

    return true;
}


/**
 * Removes all space characters in front of a string and returns the modified version.
 * Leaves any other space character in the string as it is.         
 *  
 * @param str to remove spaces from.
 * @returns the modified string.
 */
function removeSpacesInFront(str: string): string {

    let idx = -1;

    // iterating array of single characters of string
    [...str].some((char, index) => {
        
        // setting current index
        idx = index;

        // break if the first non space character is found
        return char !== " ";
    });

    return str.substring(idx);
}


export { objectValid, removeSpacesInFront };


///// helper methods


function isNullOrUndefined(obj: any): boolean {

    return obj === null || obj === undefined;
}


function isNotANumber(obj: any): boolean {

    return typeof obj === "number" && isNaN(obj);
}


function isEmptyString(obj: any): boolean {

    return typeof obj === "string" && obj === "";
}


function isIterable(obj: any): boolean {

    return typeof obj[Symbol.iterator] === "function" && typeof obj !== "string";
}


function isObject(obj: any): boolean {

    return typeof obj === "object";
}

function isObjectEmpty(obj: any): boolean {

    return Object.keys(obj).length === 0;
}