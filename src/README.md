### First step
- adding tests:
  - prevent ourselves from introducing bugs,
  - accidentally remove any of the existing logic
  - break the program
  - and the most importantly, they give us fast feedback and the confident that we're heading to the right direction
  - the tests are also the living document which developers can refer to to get a basic picture of the logic of the module and the system as a whole.

### Second step
- decomposing the statement function
  - extracting the switch statement  into its own function
  - run the tests
  - change the var "thisAmount" to "result" to make it clearer
  - run the tests
  - --> All the calculation logic has been moved out to a handful of supporting functions.
  - This makes it easier to understand each individual calculation as well as the overall flow of the report.
