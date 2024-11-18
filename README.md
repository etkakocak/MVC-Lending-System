# Assignment 2 - 1DV607

**Project name:** Stuff Lending System

**Group Members:**  
Etka Kocak (ek223zf)  
Sanaa Abdoulkader (sa225hg)    
Aiman Daeef (ad223jd)  

[Click to see the architectural design of the application.](design.md)  
[Click to see the test report of the application.](testreport.md)  

## Usage of the application
A member initially has 0 credits. Members can share items at any time and receive 100 credits for each item they share. Members can use these credits when borrowing an item. However, if the member wants to delete one of their own shared items, these 100 credits will be taken back.  

A member can view all the available items and create a contract with other members by borrowing those items. Items have prices in credits per day. Members pay as many credits for how many days they want to borrow the item. These credits are deleted from the borrower member's account and sent to the lender member's account, which is the item's owner.  

Information on member accounts, the published items, and the created contracts can be viewed. Data of members and items can be updated. Members and items can also be completely deleted, if a member is deleted, all its items also get deleted, if an item is deleted, all its contracts also get deleted.  

We paid attention to preventing crashes in the application and error handling. It's easy to fix when you make wrong choices in the menus, it's easy to go back to the right menus in the app, and simply exit the app.  

You can also create as many new members as you want. What you should pay attention to when creating a new member account is that you should not enter the same email account or phone number as another member. The application does not accept this.  