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

/**
 * checks if an array is a subset of another array
 *
 * @param {any[]} subset - the given subset to be found in the superset
 * @param {any[]} superset - the given superset which contains the subset
 *
 * @returns {boolean} 'true' is the given subset is found in the given superset, otherwise 'false'
 * */
export function isSubset(subset, superset) {
    const trueSubset = new Set(subset);
    const trueSuperset = new Set(superset);

    for (const element of trueSubset) {
        if (!trueSuperset.has(element)) {
            return false;
        }
    }

    return true;
}

/**
 * find the longest common prefix in an array of strings
 *
 * @param {string[]} array - the given array of strings
 *
 * @returns {string} the longest common prefix in the given array of strings
 * */
export function longestCommonPrefix(array) {
    if (array.length === 0) {
        return "";
    }

    array.sort();

    const first = array[0];
    const last = array[array.length - 1];

    let prefix = "";

    for (let i = 0; i < first.length; i++) {
        if (first.charAt(i) === last.charAt(i)) {
            prefix += first.charAt(i);
        } else {
            break;
        }
    }

    return prefix;
}

/**
 * calculates the nth fibonacci number
 *
 * @param {number} n - the index of the fibonacci number to calculate
 *
 * @returns {number} the nth fibonacci number
 * */
export function fibonacciNumber(n) {
    if (n === 0) {
        return 0;
    }

    if (n === 1) {
        return 1;
    }

    let firstPrevious = 1;  // F(n-1)
    let secondPrevious = 0; // F(n-2)

    for (let i = 2; i <= n; i++) {
        const currentFibonacci = firstPrevious + secondPrevious;

        secondPrevious = firstPrevious;
        firstPrevious = currentFibonacci;
    }

    return firstPrevious;
}

/**
 * converts a decimal to hexadecimal
 *
 * @param {number} decimal - the given decimal number
 *
 * @returns {string} the hexadecimal form of the given decimal number
 * */
export function decimalToHex(decimal) {
    if (decimal === 0) {
        return "0";
    }

    const hex = "0123456789ABCDEF";

    let result = "";
    let num = Math.abs(decimal);

    while (num > 0) {
        const remainder = num % 16;

        result = hex.charAt(remainder) + result;
        num = Math.floor(num / 16);
    }

    if (decimal < 0) {
        result = "-" + result;
    }

    return result;
}

/**
 * check if a given string contains only digits
 *
 * @param {string} word - the given string
 *
 * @returns {boolean} 'true' if the given string contains only digits, 'false' otherwise
 * */
export function containOnlyDigits(word) {
    for (let i = 0; i < word.length; i++) {
        if (isNaN(parseInt(word[i], 10))) {
            return false;
        }
    }

    return true;
}

/**
 * check if a given string contains only digits using regex
 *
 * @param {string} word - the given string
 *
 * @returns {boolean} 'true' if the given string contains only digits, 'false' otherwise
 * */
export function containOnlyDigitsRegex(word) {
    const regex = /^\d+$/;

    return regex.test(word);
}

/**
 * calculate the area of a rectangle given its length and width
 *
 * @param {number} length - the length of the rectangle
 * @param {number} width - the width of the rectangle
 *
 * @returns {number} the area of the rectangle
 * */
export function calculateRectangleArea(length, width) {
    if (typeof length !== "number" || typeof width !== "number" || length <= 0 || width <= 0) {
        throw new Error("Length and width must be positive integers");
    }

    return length * width;
}

/**
 * finds the second-smallest number in an array of numbers
 *
 * @param {number[]} numbers - the array of numbers
 *
 * @returns {number} the second-smallest number in the given array
 * */
export function findSecondSmallest(numbers) {
    if (numbers.length < 2) {
        throw new Error("Array must contain more than 2 numbers");
    }

    let smallest = Infinity;
    let secondSmallest = Infinity;

    for (let i = 0; i < numbers.length; i++) {
        if (numbers[i] < smallest) {
            secondSmallest = smallest;
            smallest = numbers[i];
        } else if (numbers[i] < secondSmallest && numbers[i] !== smallest) {
            secondSmallest = numbers[i];
        }
    }

    return secondSmallest;
}

/**
 * checks if a given string is a valid email address
 *
 * @param {string} email - the given string
 *
 * @returns {boolean} 'true' if the given string is an email address, 'false' otherwise
 * */
export function isValidEmail(email) {
    const pattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    return pattern.test(email);
}

/**
 * checks if a given string is a valid email address
 *
 * @param {string} email - the given string
 *
 * @returns {boolean} 'true' if the given string is an email address, 'false' otherwise
 * */
export function isValidEmail2(email) {
    const at = email.indexOf("@");

    if (at === 1 || email.indexOf("@", at + 1) !== 1) {
        return false;
    }

    const dot = email.indexOf(".", at);

    if (dot === -1 || dot === at + 1 || dot === email.length - 1) {
        return false;
    }

    if (at === 0) {
        return false;
    }

    if (dot === at + 1) {
        return false;
    }

    const characters = /^[a-zA-Z0-9._-]+$/;

    if (!characters.test(email)) {
        return false;
    }

    const domain = email.slice(at + 1);
    const domainDot = domain.indexOf(".");

    return !(domainDot === -1 || domainDot === 0 || domainDot === domain.length - 1);
}

/**
 * find the factors of a given number
 *
 * @param {number} num - the given number
 *
 * @returns {number[]} an array of the factors of the given number
 * */
export function findFactors(num) {
    const factors = [];

    for (let i = 1; i <= Math.sqrt(num); i++) {
        if (num % i === 0) {
            factors.push(i);
        }

        if (i !== num / i) {
            factors.push(num / i);
        }
    }

    return factors;
}

/**
 * removes all spaces from a given string
 *
 * @param {string} words - the given string
 *
 * @returns {string} a string without spaces
 * */
export function removeSpaces(words) {
    let spaceless = "";

    for (let i = 0; i < words.length; i++) {
        if (words[i] !== "") {
            spaceless += words[i];
        }
    }

    return spaceless;
}

/**
 * removes all spaces from a given string
 *
 * @param {string} words - the given string
 *
 * @returns {string} a string without spaces
 * */
export function removeSpaces2(words) {
    return words.replace(/\s+/g, "");
}

/**
 * determines if a number is a power of two.
 *
 * @param {number} num - the given number
 *
 * @returns {boolean} 'true' if the given number is a power of two, 'false' otherwise
 * */
export function isPowerOfTwo(num) {
    if (num <= 0) {
        return false;
    }

    return (num & (num - 1)) === 0;
}

/**
 * calculate the sum of squares of numbers from 1 to num
 *
 * @param {number} num - the given number
 *
 * @returns {number} the sum of squares from 1 to the given num
 * */
export function sumOfSquares(num) {
    let sum = 0;

    for (let i = 1; i <= num; i++) {
        const square = i * i;

        sum += square;
    }

    return sum;
}

/**
 * calculates the area of a parallelogram
 *
 * @param {number} base - the base of the parallelogram
 * @param {number} height - the height of the parallelogram
 *
 * @returns {number} the area of the parallelogram
 * */
export function calculateParallelogramArea(base, height) {
    if (base <= 0 || height <= 0) {
        throw new Error("base and height must be positive numbers");
    }

    return base * height;
}

/**
 * function to find the middle element of an array
 *
 * @param {any[]} array - the given array
 *
 * @returns {any} the middle element of the given array
 * */
export function findMiddleElement(array) {
    if (array.length === 0) {
        throw new Error("array cannot be empty");
    }

    const middle = (array.length - 1) / 2;

    return array[middle];
}

/**
 * function to reverse the words in a string without changing their order
 *
 * @param {string} sentence - the given string
 *
 * @returns {string} a string whose words are the reversed version of the given string
 * */
export function reverseWordsInSentence(sentence) {
    const words = sentence.split("");

    const reverse = words.map(word => {
        return word.split("").reverse().join("");
    });

    return reverse.join("");
}