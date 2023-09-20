package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Member;
import model.Admin;

public class AuthenticationController {
    private List<Member> members;
    private List<Admin> admins;

    public AuthenticationController() {
        members = new ArrayList<>();
        admins = new ArrayList<>();
    }

    public Member getMemberByUsername(String username) {
        for (Member member : members) {
            if (member.getUsername().equals(username)) {
                return member;
            }
        }
        return null;
    }

    public Admin getAdminByUsername(String username) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null;
    }

    public Admin validateAdmin(String username, String password) {
        Admin admin = getAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }

    public Member validateMember(String username, String password) {
        for (Member member : members) {
            if (member.getUsername().equals(username) && member.getPassword().equals(password)) {
                return member;
            }
        }
        return null;
    }
    
    public void createMemberAccount(String name, String email, String mobile, String username, String password) {
        Member newMember = new Member(name, email, mobile, username, password);
        members.add(newMember);
    }

    public void initializeUsers() {
        Member member1 = new Member("Etka", "etka@lending.com", "0031", "etka", "etka123");
        members.add(member1);
        Member member2 = new Member("Sanaa", "sanaa@lending.com", "0028", "sanaa", "sanaa123");
        members.add(member2);
        Member member3 = new Member("Aiman", "aiman@lending.com", "0062", "aiman", "aiman123");
        members.add(member3);

        Admin admin1 = new Admin("gadmin", "thegadmin03");
        admins.add(admin1);
    }
}
