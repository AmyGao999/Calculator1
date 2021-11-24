**Calculator**

**A take home project**

**Version 1.0.0**

This is a calculator web application using spring Boot.

This application has functions like Add, Subtract, Multiply, Divide.

**Features**
- Take string as input
- Support positive, negative, and decimal numbers
- Support +, -, *, and / operations, with standard mathematical order of operations
  - No more than 2 operators in series

  - The second can only be -

  - Note: this is deliberately different behavior than built in ‘eval’

- Support parentheses (multiple nesting levels)

**What includes in the project?**

- CalculatorApplication where to start to run the program
- CalculatorController using Restful API to get the webpage and also to post the result to the webpage.
- Calculator is by getting the string from the users, calculate it and return the result to the users. There are 
several steps to get the result:
  - parse the string to numbers and operators and save it to two stacks
  - calculate the result.
- Unit Test: to test the Calculator method, all the examples from the requirement are passed.

**TO-DO**

There are several things we can do in the future:

- Add the history calculation into the system to allow the users to check the previous calculations.
- As the requests increase, we will do the horizontal scaling to handle the large request.
- We can also add the rate limiter, circuit breaker to limit the requests.





