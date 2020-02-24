# atm-app

Data Storage:
- Used JSON file for now.

Rest API (Suggested):
- Used main class and static methods to execute the operation
- Input is from Command Line
- Should use REST APIs to get the input and process the remaining. 

What if we want to add more denominations?
1. Add enum and proceed. 
Pros:
- If we want to store some other meta information like name, display name etc enum would be good choice.

2. Configuration Model:
- Store denominations in DB or JSON file and change the denominations dynamically. 
- Faster and no code change to add the denominations.


Locking:
 - Since only one transaction happens at one time we can simply go with this approach. 
 - In multi threaded environment, we can lock the table record(DB).  