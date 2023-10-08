# Assignment 2 - 1DV607

**Project name:** Stuff Lending System

**Group Members:**  
Etka Kocak (ek223zf)  
Sanaa Abdoulkader (sa225hg)    
Aiman Daeef (ad223jd)  

[Click to see the architectural design of the application.](design.md)  
[Click to see the test results of the application:](testreport.md)  

## Usage of the application

### Login
There is a login system in our application. You can log in as admin or member or create a new member account.  

What you should pay attention to when creating a new member account is that you should not enter the same email account or phone number as another member. The application does not accept this. You can change the information you entered when creating a new account at any time you want from the settings menu in the application.  

### What can a member do?

You can create your own member account but you can also log in as a member directly to test the application:
```  
Username: etka  
Password: etka123  
```  

A member initially has 0 credits. Member can share items at any time and receives 100 credits for each item they shared. Members can use these credits when borrowing an item. However, if the member wants to delete one of their own shared items, these 100 credits will be taken back.  

A member can view all the available items and create a contract with other members by borrowing those items. Items have prices in credits per day. Members pays as many credits for how many days they want to borrow the item. These credits are deleted from the borrower member account and sent to the account of the lender member, the owner of the item.  

A member can view the information of their account, the items they shared, and the contracts they created. Members can update their own account information or the information of their items. Member can also completely delete their own items.  

The application also has a day counter and the logged-in member can simulate the application to the next day. The registration and creation dates of all created items, contracts, and members are always recorded and members can view them.  

We paid attention to preventing crashes in the application and error handling. It's easy to fix when you make wrong choices in the menus, it's easy to go back to the right menus in the app, and simply exit the app.  

### What can an admin do? 

You can not create a new admin account.  

**Use them to log in as admin:**  
``` 
Username: gadmin  
Password: thegadmin03  
``` 

Admin can view all members and items registered in the system. Admin can ban any member and remove any item from the system. When a member is banned, members owned items are also deleted.