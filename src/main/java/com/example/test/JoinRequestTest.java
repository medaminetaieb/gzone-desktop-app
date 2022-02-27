/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test;

import com.example.entity.JoinRequest;
import com.example.service.JoinRequests;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author samib
 */
public class JoinRequestTest {
    
    private final static JoinRequests joinRequests = new JoinRequests();
    private final static List<JoinRequest> joinrequestlist = new ArrayList<>();
    
    static {
        joinrequestlist.add(new JoinRequest(null, 1, 1, null, "I want to join your team1", new Date(), null, null, true));
        joinrequestlist.add(new JoinRequest(null, null, 1, 2, "I want to join your team2", new Date(), null, null, false));
        joinrequestlist.add(new JoinRequest(null, null, 1, 2, "I want to join your team3", new Date(), null, null, true));
        joinrequestlist.add(new JoinRequest(null, 1, 1, null, "I want to join your team4", new Date(), null, null, false));
    }
    
    public static void insertAll() {
        for (JoinRequest jr : joinrequestlist) {
            if (joinRequests.insert(jr)) {
                System.out.println("JoinRequest inserted");
            }
        }
    }
    
    public static void main() {
        for (JoinRequest jr : joinRequests.findAll(null, "user_id DESC")) {
            System.out.println(jr);
        }
        if (joinRequests.respondToJoinRequestById(5, true)) {
            System.out.println("JoinRequest updated");
        }
        if (joinRequests.delete("invitation=false")) {
            System.out.println("JoinRequests deleted");
        }
    }
}
    

      

     
    

