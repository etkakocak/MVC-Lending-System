# Assignment 2 - 1DV607

**Project name:** Stuff Lending System

**Group Members:**  
Etka Kocak (ek223zf)  
Sanaa Abdoulkader (sa225hg)    
Aiman Daeef (ad223jd)  

[Click to see the architectural design of the application.](design.md)  
[Click to see the test results of the application:](testreport.md)  

## Usage of the application

For the completion that comes in the new version of the application, we removed the system that required logging in by typing username and password in the past so that it would be easier to test the application. Likewise, we developed the application so that all features can be used in only one account, and therefore we removed the Admin class.

### What can be done in the application?

A member initially has 0 credits. Member can share items at any time and receives 100 credits for each item they shared. Members can use these credits when borrowing an item. However, if the member wants to delete one of their own shared items, these 100 credits will be taken back.  

A member can view all the available items and create a contract with other members by borrowing those items. Items have prices in credits per day. Members pays as many credits for how many days they want to borrow the item. These credits are deleted from the borrower member account and sent to the account of the lender member, the owner of the item.  

A member can view the information of their account, the items they shared, and the contracts they created. Members can update their own account information or the information of their items. Member can also completely delete their own items.  

The application also has a day counter and the logged-in member can simulate the application to the next day. The registration and creation dates of all created items, contracts, and members are always recorded and members can view them.  

We paid attention to preventing crashes in the application and error handling. It's easy to fix when you make wrong choices in the menus, it's easy to go back to the right menus in the app, and simply exit the app.  

You can view all members and items registered in the system and also can ban any member and remove any item from the system. When a member is banned, members owned items are also deleted.

You can also create as many new members as you want. What you should pay attention to when creating a new member account is that you should not enter the same email account or phone number as another member. The application does not accept this. 