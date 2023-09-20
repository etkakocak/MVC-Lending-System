package controller;
import java.util.List;
import model.Member;
import model.Time;


public class Daycontroller {
    private Time time;  // Antag att detta är din egen Time-klass, inte java.sql.Time
    private List<Member> members;  // Antag att detta är din egen Member-klass

    public Daycontroller(Time time, List<Member> members) {
        this.time = time;
        this.members = members;
    }

    public void advanceDayCounter() {
        System.out.println("Advancing the day counter...");
        time.advanceDay();  // Eftersom time redan är av typen Time, behöver du inte göra en typomvandling

        assignDailyCreditsToMembers();
    }

    private void assignDailyCreditsToMembers() {
        for (Member member : members) {
            member.addDailyCredits(1);  // Eftersom member redan är av typen Member, behöver du inte göra en typomvandling
        }
    }
    public int getCurrentDay() {
        return time.getCurrentDay();
    }
}



