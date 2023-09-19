package model;

import java.util.Date;

public class Contract {
    private String contractId;
    private Date startDate;
    private Date endDate;
    private Item item;
    private Member lender;
    private Member owner;

    public Contract(Date startDate, Date endDate, Item item, Member lender) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.item = item;
        this.lender = lender;
        this.owner = item.getOwner();
    }

    public String getContractId() {
        return contractId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
        long difference = endDate.getTime() - startDate.getTime();
        int days = (int) (difference / (1000*60*60*24));
        return days * item.getCostPerDay();
    }
}