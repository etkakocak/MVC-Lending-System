package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Member {
    private String memberId;
    private String name;
    private String email;
    private String mobile;
    private Date creationDate;
    private int credits;
    private List<Item> ownedItems;
    private String username;  
    private String password;  

    public Member(String name, String email, String mobile, String username, String password) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.username = username;  
        this.password = password;  
        this.creationDate = new Date();
        this.credits = 0; 
        this.ownedItems = new ArrayList<>();
        generateMemberId();
    }

    public String getUsername() {  
        return username;
    }

    public void setUsername(String username) {  
        this.username = username;
    }

    public String getPassword() {  
        return password;
    }

    public void setPassword(String password) {  
        this.password = password;
    }

    private void generateMemberId() {
        this.memberId = "ABC123"; 
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getCredits() {
        return credits;
    }

    public void addCredits(int credits) {
        this.credits += credits;
    }

    public void deductCredits(int credits) {
        this.credits -= credits;
    }

    public List<Item> getOwnedItems() {
        return ownedItems;
    }

    public void addOwnedItem(Item item) {
        this.ownedItems.add(item);
        addCredits(100); 
    }

    public void removeOwnedItem(Item item) {
        this.ownedItems.remove(item);
    }
}