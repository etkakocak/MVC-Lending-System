package model;

import java.util.Date;

public class Item {
    private String itemId;
    private String category;
    private String name;
    private String description;
    private Date creationDate;
    private int costPerDay;
    private Member owner;

    public Item(String category, String name, String description, int costPerDay, Member owner) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.costPerDay = costPerDay;
        this.owner = owner;
        this.creationDate = new Date();
        generateItemId();
    }

    // This is draft, should be implemented

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
