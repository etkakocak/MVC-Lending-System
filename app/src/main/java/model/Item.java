package model;

import java.util.Date;

public class Item {
    private String itemId;
    private String category;
    private String name;
    private Description description;  
    private Date creationDate;
    private int costPerDay;
    private Member owner;

    public Item(String category, String name, Description description, int costPerDay, Member owner) {
        this.category = category;
        this.name = name;
        this.description = description; 
        this.costPerDay = costPerDay;
        this.owner = owner;
        this.creationDate = new Date();
        generateItemId();
    }

    // This is draft, should be implemented

    public Description getDescription() {  
        return description;
    }

    public void setDescription(Description description) {  
        this.description = description;
    }

    private String generateItemId() {
        return "ABC123"; 
    }

    public String getItemId() {
        return itemId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(int costPerDay) {
        this.costPerDay = costPerDay;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public Description getContent() {
        return description;
    }

    public void setContent(Description description) {
        this.description = description;
    }
}
