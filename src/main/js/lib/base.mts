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