/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.example.test;

import com.example.entity.Report;
import com.example.entity.Subject;
import com.example.service.Reports;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mahdi
 */
public class ReportTest {
    
    private static final Reports reports = new Reports();
    private static final List<Report> reportlist = new ArrayList<>();
    
    static {
        reportlist.add(new Report(0, 1, Subject.sensitive, "sensitive content", "sdf", new Date()));
        reportlist.add(new Report(0, 2, Subject.sensitive, "sensitive content", "2", new Date()));
        reportlist.add(new Report(0, 2, Subject.score_manipulation, "score manipulation", "Tournament score has been manipulated", new Date()));
    }
    
    public static void insertAll() {
        for (Report r : reportlist) {
            if (reports.insert(r)) {
                System.out.println("Report inserted");
            }
        }
    }
    
    public static void main() {
        for (Report r : reports.find(0, 2, null, "reporter_id ASC")) {
            System.out.println(r);
        }
        
        if (reports.delete("reporter_id=1")) {
            System.out.println("Reports deleted");
        }
    }
}