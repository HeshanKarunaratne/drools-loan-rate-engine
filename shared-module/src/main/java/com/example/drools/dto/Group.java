package com.example.drools.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Group {
    private int groupAgeCycles;
    private String backupStatus;
    private double membersSavingMinimumPercentage;
    private double membersWithOutstandingLoansPercentage;
    private double steadyMembershipPercentage;
    private double attendanceRate;
    private double membersWithImagesPercentage;
}