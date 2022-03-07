package com.example.test;

import com.example.entity.Match;
import com.example.entity.Membership;

import com.example.service.Memberships;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class MembershipTest {
    private static final Memberships memberships = new Memberships();
    private static final List<Membership> membershiplist = new ArrayList<>();

    static{
        membershiplist.add(new Membership(null,3,2,null));
        membershiplist.add(new Membership(null,4,2,null));
        membershiplist.add(new Membership(null,1,2,null));
        membershiplist.add(new Membership(null,4,15,null));
        membershiplist.add(new Membership(null,5,24,null));
        membershiplist.add(new Membership(null,6,36,null));

    }
    public static void insertAll(){

        for(Membership m :membershiplist){
            if(memberships.insert(m)){
                System.out.println("Membership inserted");
            }
        }
    }
   /* public static void main() {
        for (Membership m : memberships.find(2, 3, null, null)) {
            System.out.println(m);
        }
    }*/
}
