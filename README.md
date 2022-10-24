# RotLA

### Authors

1. #### Rajagopal Anandan
    rajagopal.anandan@colorado.edu

1. #### Suresh Nayak 
     suresh.nayak@colorado.edu



Java version: openjdk 17.0.4.1 2022-08-12

## Assumptions:
1. We have assumed the numbering of 1st floor to be as follows:  
  <img width="244" alt="image" src="https://user-images.githubusercontent.com/42914453/192075159-9274e023-8b25-4a4c-8bc7-2ee4c3354768.png">

2. The bonus points awarded can take the dice output beyond 12.

3. In case of Portion, Trap and Portal treasures the treasure effect is immediate.
4. Finding a Portal treasure allows the adventurer to move to a different room immediately.
5. During a Careful search the adventurer has a 50% chance of avoiding Trap treasure. If avoided the trap is not removed from the room.
6. Treasures can be placed in any room including starting room 0-1-1.
7. Observer for tracking events is implemented using a push model.
8. All events are notified to registered subscribers. Tracker ignores any events that are not useful to it. 
9. Project Structure:  
  &nbsp; The main function is in RotLA.java.  
  &nbsp; All the files required to run the program are in src/main folder.  
  &nbsp; All the test files are in src/test folder.
10. JUnit test results are available in file - JUnitResults.png
11. Line chart result is available in file - RotLASummary.jpeg
12. Maven tool is used to build the tests and dependencies are listed under pom.xml
13. RotLA game is interactive and allows user to enter commands
14. Incorrect commands including typos will result in game termination
