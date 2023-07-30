/**
 * function to convert a binary number to a decimal number
 *
 * @param {string} binary - the given binary number
 *
 * @returns {number} the decimal form of the given binary input
 * */
export function binaryToDecimal(binary) {
    if (!/^[01]+$/.test(binary)) {
        throw new Error("Invalid binary input");
    }

    // reverse binary number to facilitate conversion
    const reversed = binary.split("").reverse().join("");

    let decimal = 0;

    for (let i = 0; i < reversed.length; i++) {
        const bit = parseInt(reversed.charAt(i), 10);

        decimal += bit * Math.pow(2, i);
    }

    return decimal;
}

/**
 * counts the number of digits in an integer
 *
 * @param {number} num - the given integer value
 *
 * @returns {number} the number of digits in the given integer
 * */
export function countDigits(num) {
    const absolute = Math.abs(num);

    if (absolute === 0) {
        return 1;
    }

    const stringNum = absolute.toString();

    return stringNum.length;
}

/**
 * function that reverses the order of elements in a given array
 *
 * @param {any[]} array - the given array
 *
 * @returns {any[]} the reversed version of the given array
 * */
export function reverseArray(array) {
    const reversed = [];

    for (let i = array.length - 1; i >= 0; i--) {
        reversed.push(array[i]);
    }

    return reversed;
}