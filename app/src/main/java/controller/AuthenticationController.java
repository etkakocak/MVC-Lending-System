package controller;

import java.util.HashMap;

public class AuthenticationController {
    private HashMap<String, String> adminCredentials;
    private HashMap<String, String> memberCredentials;

    public AuthenticationController() {
        // Initializing the credentials maps
        adminCredentials = new HashMap<>();
        memberCredentials = new HashMap<>();

        // Setting hardcoded admin credentials
        adminCredentials.put("gadmin", "thegadmin03");
    }

    public boolean validateAdmin(String username, String password) {
        return adminCredentials.containsKey(username) && adminCredentials.get(username).equals(password);
    }

    public boolean validateMember(String username, String password) {
        return memberCredentials.containsKey(username) && memberCredentials.get(username).equals(password);
    }

    public void createMemberAccount(String username, String password) {
        memberCredentials.put(username, password);
    }
}
