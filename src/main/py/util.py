import time


def timer(function):
    """
    A decorator function to measure the execution time of another function.

    :param function: The function to be wrapped and measured.
    :type function: function
    :return: The wrapped function, which measures and prints the execution time.
    :rtype: function
    """

    def wrapper(*args, **kwargs):
        """
        Wrapper function to measure the execution time of the decorated function.

        :param args: Positional arguments to be passed to the decorated function.
        :type args: tuple
        :param kwargs: Keyword arguments to be passed to the decorated function.
        :type kwargs: dict
        :return: The result of the decorated function.
        :rtype: Any
        """
        start_time = time.time()

        # Calling the decorated function
        result = function(*args, **kwargs)

        stop_time = time.time()

        elapsed_time = stop_time - start_time
        print(f"Time taken to execute '{function.__name__}': {elapsed_time:.6f} seconds")

        return result

    return wrapper
