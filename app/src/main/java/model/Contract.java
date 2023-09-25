package model;

public class Contract {
    private Time startDate;
    private Time endDate;
    private Item item;
    private Member lender;
    private Member owner;

    public Contract(Time startDate, Time endDate, Item item, Member lender) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.item = item;
        this.lender = lender;
        this.owner = item.getOwner();
    }

    public Time getStartDate() {
        return startDate;
    }

    public void setStartDate(Time startDate) {
        this.startDate = startDate;
    }

    public Time getEndDate() {
        return endDate;
    }

    public void setEndDate(Time endDate) {
        this.endDate = endDate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Member getLender() {
        return lender;
    }

    public void setLender(Member lender) {
        this.lender = lender;
    }

    public Member getOwner() {
        return owner;
    }

    public int calculateTotalCost() {
        long difference = endDate.getDay() - startDate.getTime();
        int days = (int) (difference / (1000*60*60*24));
        return days * item.getCostPerDay();
    }
}