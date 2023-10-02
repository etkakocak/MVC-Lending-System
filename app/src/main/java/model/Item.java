package model;

public class Item {
    private String category;
    private String name;
    private String description;  
    private int creationDate;
    private int costPerDay;
    private Member owner;

    public Item(String category, String name, String description, int costPerDay, Member owner, Time creationDate) {
        this.category = category;
        this.name = name;
        this.description = description; 
        this.costPerDay = costPerDay;
        this.owner = owner;
        this.creationDate = creationDate.getDate();
    }

    public String getDescription() {  
        return description;
    }

    public void setDescription(String description) {  
        this.description = description;
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

    public int getCreationDate() {
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
