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

	return pi * (radius * radius);
}

/**
 * convert Celsius to Fahrenheit.
 *
 * @param {number} celsius - The temperature in Celsius.
 *
 * @returns {number} the equivalent temperature in Fahrenheit.
 *
 * @throws {Error} if the provided Celsius is not a number.
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
	if (typeof num !== "number" || isNaN(num)) {
		throw new Error("Invalid input!!");
	}

	if (num % 2 === 0) {
		return "even";
	} else {
		return "odd";
	}
}

/**
 * reverses a given string
 *
 * @param {string} word - the string to reverse
 *
 * @returns {string} the reversed string
 *
 * @throws {Error} if the input is not a string
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

/**
 * checks if a given string is a palindrome.
 *
 * A palindrome is a word, phrase, number, or another sequence of characters that reads the
 * same forward and backward
 *
 * Spaces, punctuation, and letter casing are usually ignored
 *
 * @param {string} word - the string to check for palindrome
 *
 * @returns {boolean} returns true if the string is a palindrome otherwise false
 *
 * @throws {Error} if the input is not a string
 */
export function isPalindrome(word) {
	if (typeof word !== "string") {
		throw new Error("Invalid input!!");
	}

	//make the string case-insensitive by removing spaces and converting
	// to lowercase
	const comparableWord = word.replace(/\s/g, "").toLowerCase();

	return comparableWord === comparableWord.split("").reverse().join("");
}

/**
 * find the largest number in an array of numbers
 *
 * @param {number[]} numbers - the array of numbers
 *
 * @returns {number|null} - returns the largest number or null for an empty array
 *
 * @throws {Error} if the input is not an array
 */
export function findLargestElement(numbers) {
	if (!Array.isArray(numbers)) {
		throw new Error("Invalid input!!");
	}

	if (numbers.length === 0) {
		return null;
	}

	let largestNumber = numbers[0];

	for (let i = 0; i < numbers.length; i++) {
		if (numbers[i] > largestNumber) {
			largestNumber = numbers[i];
		}
	}

	return largestNumber;
}

/**
 * finds the second-largest element in an array of numbers
 *
 * @param {number[]} numbers - the array of numbers
 *
 * @returns {number|null} - returns the second-largest number or null if the array elements are less than 2
 *
 * @throws {Error} if the input is not an array
 */
export function findSecondLargestElement(numbers) {
	if (!Array.isArray(numbers)) {
		throw new Error("Invalid input!!");
	}

	if (numbers.length < 2) {
		return null;
	}

	let largestNumber = Math.max(numbers[0], numbers[1]);
	let secondLargestNumber = Math.max(numbers[0], numbers[1]);

	for (let i = 2; i < numbers.length; i++) {
		const currentNumber = numbers[i];

		if (currentNumber > largestNumber) {
			secondLargestNumber = largestNumber;
			largestNumber = currentNumber;
		} else if (
			currentNumber > secondLargestNumber &&
			currentNumber !== largestNumber
		) {
			secondLargestNumber = currentNumber;
		}
	}

	return secondLargestNumber;
}

/**
 * function to count the number of vowels in a string using regular expression
 *
 * @param {string} word - the input string
 *
 * @returns {number} the number of vowels in the input string
 * */
export function countVowelsUsingRegex(word) {
	const regex = /[aeiou]/gi;

	const matches = word.match(regex);

	return matches ? matches.length : 0;
}

/**
 * function to count the number of vowels in a string
 *
 * @param {string} word - the input string
 *
 * @returns {number} the number of vowels in the input string
 * */
export function countVowels(word) {
	const vowels = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'];
	let count = 0;

	for (let i = 0; i < word.length; i++) {
		if (vowels.includes(word[i])) {
			count++;
		}
	}

	return count;
}

/**
 * function to calculate the average of numbers in an array
 *
 * @param {number[]} numbers - the array of numbers
 *
 * @return {number} the average of the input array
 * */
export function calculateAverage(numbers) {
	if (numbers.length === 0) {
		return 0;
	}

	let sum = 0;

	for (let i = 0; i < numbers.length; i++) {
		sum += numbers[i];
	}

	return sum / numbers.length;
}

/**
 * function to check if an array is sorted in ascending order
 *
 * @param {number[]} numbers - the array of numbers
 *
 * @returns {boolean} - true if the array is sorted in ascending order, false otherwise
 * */
export function isAscending(numbers) {
	for (let i = 0; i < numbers.length; i++) {
		if (numbers[i] > numbers[i + 1]) {
			return false;
		}
	}

	return true;
}

/**
 * function to find the index of an element in an array using indexOf()
 *
 * @param {any[]} array - the array
 * @param {any} element - the element to find in the array
 *
 * @returns {number} the index of the element in the array, or -1 if the element is not found
 * */
export function findIndex1(array, element) {
	return array.indexOf(element);
}

/**
 * function to find the index of an element in an array using findIndex()
 *
 * @param {any[]} array - the array
 * @param {any} element - the element to find in the array
 *
 * @returns {number} the index of the element in the array, or -1 if the element is not found
 * */
export function findIndex2(array, element) {
	return array.findIndex((target) => target === element);
}

/**
 * function to find the index of an element in an array
 *
 * @param {any[]} array - the array
 * @param {any} element - the element to find in the array
 *
 * @returns {number} the index of the element in the array, or -1 if the element is not found
 * */
export function findIndex3(array, element) {
	for (let i = 0; i < array.length; i++) {
		if (array[i] === element) {
			return i;
		}
	}

	return -1;
}

/**
 * function to remove duplicates from an array
 *
 * @param {any[]} array - the array with duplicate elements
 *
 * @returns {any[]} an array with no duplicate element
 * */
export function removeDuplicates(array) {
	const uniques = {};	// unique elements in the array
	const result = [];

	for (let i = 0; i < array.length; i++) {
		// check if the element already exists in uniques
		if (!uniques[array[i]]) {
			uniques[array[i]] = true;

			result.push(array[i]);	//	add unique element to result
		}
	}

	return result;
}

/**
 * merges two sorted arrays into a single sorted array
 *
 * @param {number[]} firstArray - the first sorted array
 * @param {number[]} secondArray - the second sorted array
 *
 * @returns {number[]} a sorted array from the merged result of the input arrays
 * */
export function mergeSortedArrays(firstArray, secondArray) {
	const mergedArray = [];
	let i = 0;
	let j = 0;

	while (i < firstArray.length && j < secondArray.length) {
		if (firstArray[i] <= secondArray[j]) {
			mergedArray.push(firstArray[i]);
			i++;
		} else {
			mergedArray.push(secondArray[j]);
			j++;
		}
	}

	while (i < firstArray.length) {
		mergedArray.push(firstArray[i]);
		i++;
	}

	while (j < secondArray.length) {
		mergedArray.push(secondArray[j]);
		j++;
	}

	return mergedArray;
}

/**
 * calculates the sum of all elements in an array
 *
 * @param {number[]} array - the array whose elements are to be summed
 *
 * @return {number} the sum of all the elements in the array
 * */
export function calculateSum(array) {
	let sum = 0;

	for (let i = 0; i < array.length; i++) {
		sum += array[i];
	}

	return sum;
}

/**
 * counts the number of occurrences of a character in a string
 *
 * @param {string} word - the input string
 * @param {string} character - the input character, for example, "a"
 *
 * @returns {number} the number of times the input character is present in the string
 * */
export function countOccurrences(word, character) {
	let count = 0;

	for (let i = 0; i < word.length; i++) {
		if (word[i] === character) {
			count++;
		}
	}

	return count;
}
