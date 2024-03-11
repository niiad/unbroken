/**
 * calculate the area of a circle given its radius.
 *
 * @param {number} radius - the radius of the circle.
 *
 * @returns {number} the area of the circle.
 *
 * @throws {Error} if the provided radius is not a non-negative number.
 */
export function areaOfCircle(radius: number): number {
	if (radius < 0) {
		throw new Error("radius must be greater than 0!!");
	}

	const pi: number = 3.14159;

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
export function celsiusToFahrenheit(celsius: number) {
	if (isNaN(celsius)) {
		throw new Error("Invalid value!!");
	}

	return celsius * (9 / 5) + 32;
}