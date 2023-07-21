/**
 * calculate the area of a circle given its radius.
 *
 * @param {number} radius - the radius of the circle.
 *
 * @returns {number} the area of the circle.
 *
 * @throws {Error} if the provided radius is not a non-negative number.
 */
export function areaOfCircle(radius) {
	if (isNaN(radius) || radius < 0) {
		throw new Error("Invalid radius!!");
	}

	const pi = 3.14159;
	const area = pi * (radius * radius);

	return area;
}

/**
 * convert celsius to fahrenheit.
 *
 * @param {number} celsius - The temperature in celsius.
 *
 * @returns {number} the equivalent temperature in fahrenheit.
 *
 * @throws {Error} if the provided celsius is not a number.
 */
export function celsiusToFahrenheit(celsius) {
	if (isNaN(celsius)) {
		throw new Error("Invalid value!!");
	}

	return celsius * (9 / 5) + 32;
}

/**
 * calculate the factorial of a number using recursion
 *
 * @param {number} num - the number for which factorial needs to be calculated.
 *
 * @returns {number} the factorial of the given number.
 *
 * @throws {Error} if the provided num is not a number.
 */
export function recursiveFactorial(num) {
	if (isNaN(num)) {
		throw new Error("Invalid value!!");
	}

	if (num === 0 || num === 1) {
		return 1;
	} else {
		return num * recursiveFactorial(num - 1);
	}
}

/**
 * calculate the factorial of a number using iteration
 *
 * @param {number} num - the number for which factorial needs to be calculated.
 *
 * @returns {number} the factorial of the given number.
 *
 * @throws {Error} if the provided num is not a number
 */
export function iterativeFactorial(num) {
	if (isNaN(num)) {
		throw new Error("Invalid value!!");
	}

	let result = 1;

	for (let i = 2; i <= num; i++) {
		result *= i;
	}

	return result;
}

/**
 * calculate the factorial of a number using tail recursion
 *
 * @param {number} num - the number for which factorial needs to be calculated.
 * @param {number} accumulator - the current accumulator value (default: 1).
 *
 * @returns {number} the factorial of the given number.
 *
 * @throws {Error} if the provided num is not a number
 */
export function tailRecursiveFactorial(num, accumulator = 1) {
	if (isNaN(num) || isNaN(accumulator)) {
		throw new Error("Invalid input value!!");
	}

	if (num === 0 || num === 1) {
		return accumulator;
	} else {
		return tailRecursiveFactorial(num - 1, num * accumulator);
	}
}

/**
 * checks if a given number is even or odd
 *
 * an even number is divisible by 2 with no remainder, while an odd number
 * has a remainder of 1 when divided by 2
 *
 * @param {number} num - the number to check
 *
 * @returns {string} returns 'even' if the number is even else 'odd'
 *
 * @throws {Error} if the input is not a number
 */
export function evenOrOdd(num) {
	if (typeof num !== "number" || isNaN(number)) {
		throw new Error("Invalid input!!");
	}

	if (num % 2 === 0) {
		return "even";
	} else {
		return "odd";
	}
}

/**
 *
 */
export function reverseString(word) {
	if (typeof word !== "string") {
		throw new Error("Invalid input!!");
	}

	// split the string into an array of characters
	let arrayOfCharacters = word.split("");

	// reverse the array of characters
	let reverse = arrayOfCharacters.reverse();

	// join the reversed array and return it
	return reverse.join("");
}
