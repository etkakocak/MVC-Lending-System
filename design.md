# Stuff Lending System OO-Design
This document describes the design of the assignment 2.

The application uses the model-view-controller (MVC) architectural pattern. The view is passive and gets called from the controller. The view may only read information from the model, not directly change it.

## Detailed Design
### Class Diagram
![Class diagram](/img/class_diagram.png)  

### Object Diagram
![Object diagram](/img/object_diagram.png)  
This object diagram corresponds to the sequence diagram scenario below. Two members (Etka and Sanaa) already exist, a new member named Aiman created an account and the system registered this account. 

### Sequence Diagram
![Sequence diagram](/img/sequence_diagram.png)  
This sequence diagram corresponds to a scenario where a new third member is added to the system where there two other members already exist. It also shows the whole flow of messages and methods. 