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

/**
 * checks if two strings are anagrams. anagrams are two words that have the same characters in a
 * different order.
 *
 * @param {string} firstWord - the first input string
 * @param {string} secondWord - the second input string
 *
 * @return {boolean} 'true' if the two input strings are anagrams, 'false' otherwise
 * */
export function areAnagrams(firstWord, secondWord) {
	// remove non-alphanumeric characters and convert to lowercase
	firstWord = firstWord.replace(/[^a-zA-Z0-9]/g, "").toLowerCase();
	secondWord = secondWord.replace(/[^a-zA-Z0-9]/g, "").toLowerCase();

	if (firstWord.length !== secondWord.length) {
		return false;
	}

	const firstWordCharCount = {};
	const secondWordCharCount = {};

	for (let char of firstWord) {
		firstWordCharCount[char] = (firstWordCharCount[char] || 0) + 1;
	}

	for (let char of secondWord) {
		secondWordCharCount[char] = (secondWordCharCount[char] || 0) + 1;
	}

	for (let char in firstWordCharCount) {
		if (firstWordCharCount[char] !== secondWordCharCount[char]) {
			return false;
		}
	}

	return true;
}

/**
 * function to find the maximum and minimum values in an array of numbers
 *
 * @param {number[]} numbers - the array of numbers
 *
 * @return {Object} an object with the 'max' and 'min' properties of the array, 'null' for empty array
 * */
export function findMaxAndMin(numbers) {
	if (numbers.length === 0) {
		return null;
	}

	let max = numbers[0];
	let min = numbers[0];

	for (let i = 1; i < numbers.length; i++) {
		const current = numbers[i];

		if (current > max) {
			max = current;
		}

		if (current < min) {
			min = current;
		}
	}

	return { max, min };
}

/**
 * finds the longest word in a sentence
 *
 * @param {string} sentence - the input sentence
 *
 * @return {string} the longest word in the sentence, an empty string for an empty sentence
 * */
export function findLongestWord(sentence) {
	const words = sentence.split("");

	let longestWord = "";

	for (let word of words) {
		const cleanedWord = word.replace(/[^a-zA-Z0-9]/g, "");

		if (cleanedWord.length > longestWord.length) {
			longestWord = cleanedWord;
		}
	}

	return longestWord;
}

/**
 * function to calculate the power of a number
 *
 * @param {number} base - the base number
 * @param {number} exponent - the exponent to which the base will be raised.
 *
 * @return {number} the result of the base being raised to the power of the exponent.
 * */
export function calculatePower(base, exponent) {
	if (exponent === 0) {
		return 1;
	}

	if (exponent < 0) {
		return 1 / calculatePower(base, -exponent);
	}

	let result = 1;

	for (let i = 1; i <= exponent; i++) {
		result *= base;
	}

	return result;
}

/**
 * swaps two numbers without using a temporary variable
 *
 * @param {number} number1 - the first number to be swapped
 * @param {number} number2 - the second number to be swapped
 *
 * @return {Array} an array containing the two swapped numbers
 * */
export function swapUsingAddSubtract(number1, number2) {
	number1 = number1 + number2;
	number2 = number1 - number2;
	number1 = number1 - number2;

	return [number1, number2];
}

/**
 * swaps two numbers without using a temporary variable
 *
 * @param {number} number1 - the first number to be swapped
 * @param {number} number2 - the second number to be swapped
 *
 * @return {Array} an array containing the two swapped numbers
 * */
export function swapUsingBitwiseXOR(number1, number2) {
	number1 = number1 ^ number2;
	number2 = number1 ^ number2;
	number1 = number1 ^ number2;

	return [number1, number2];
}

/**
 * finds the intersection of two arrays
 *
 * @param {any[]} firstArray - the first input array
 * @param {any[]} secondArray - the second input array
 *
 * @return {any[]} an array containing the intersection of the two input arrays
 * */
export function findIntersection(firstArray, secondArray) {
	const firstSet = new Set(firstArray);

	const intersection = [];

	for (let item of secondArray) {
		if (firstSet.has(item)) {
			intersection.push(item);
			firstSet.delete(item);
		}
	}

	return intersection;
}

/**
 * function that converts a decimal number to binary
 *
 * @param {number} decimal - the input decimal number
 *
 * @return {string} the binary form of the input decimal number
 * */
export function decimalToBinary(decimal) {
	if (!Number.isInteger(decimal) || decimal < 0) {
		throw new Error("decimal must be non-negative");
	}

	if (decimal === 0) {
		return "0";
	}

	let binary = "";

	while (decimal > 0) {
		binary = (decimal % 2) + binary;
		decimal = Math.floor(decimal / 2);
	}

	return binary;
}