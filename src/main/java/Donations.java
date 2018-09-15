/**

   Name: Nicholas Richardson
   Date: 2/14/2016
   Course/Section: IT 206.001
   Assignment: Programming Assignment 2

   Description: 
   
   This program allows for the simulation of a donation
   tracking system. There will be two campaigns that are tracked
   in this program. The first campaign is constructed with hardcoded values
   and the second campaign will be constructed via user input. The user is
   responsible for entering the name, type, goal amount, and then donations.
   The user is able to enter donations until they either reach the goal or choose
   to stop entering.
   
   Any numeric inputs will be validated, and if invalid inputs are entered,
   an error message will appear for the user. Campaigns that are entered as
   'For Profit' will incur a 5% fee on any donations that are made, whereas 
   'Non Profit' campaigns will not have any fees associated with them.
   
   After the details are gathered for both campaigns, a report will be printed to
   the user, listing the campaign names, total donation amounts, goal amounts,
   and how much over/under the goal that the campaigns were, as well as which campaign
   had the highest amount of donations. 
*/

import javax.swing.JOptionPane;

public class Donations {

 public static void main(String[] args) {


  Campaign campaign1 = new Campaign("IT Student Scholarship Fund", "Non Profit", 15000.0, 6855.0);


  String campName = promptUserForCampName();
  String campType = promptUserForCampType();
  double campGoal = promptUserForCampGoal();
  double donationTotal = promptUserForCampDonation(campGoal);

  Campaign campaign2 = new Campaign(campName, campType, campGoal, donationTotal);
  double donationMinusFee = campaign2.calculateFee(campType, donationTotal);
  campaign2.setDonationTotal(donationMinusFee);

  printReport(campaign1, campaign2);
 }


 /**Prompts the user for campaign name
 
@return the name of the campaign as a string

*/
 public static String promptUserForCampName() {
  String campName;

  campName = JOptionPane.showInputDialog("Enter the name of your campaign: ");
  return campName;
 }

  /**
 Continually prompts user for the campaign type until they choose one of two
 options for the campaign type(Non Profit, For Profit).

 @return the type of campaign (1: Non Profit; 2: For Profit)
   */
 public static String promptUserForCampType() {

  String campType;

  do {

   campType = JOptionPane.showInputDialog("Enter the type of campaign you have (Non Profit or For Profit)");
   if ((!campType.equalsIgnoreCase("Non Profit") && (!campType.equalsIgnoreCase("For Profit")))) {

    JOptionPane.showMessageDialog(null, "Error: Please enter either Non Profit or For Profit for your campaign type.");
   }

  } while (!campType.equalsIgnoreCase("Non Profit") && (!campType.equalsIgnoreCase("For Profit")));

  return campType;
 }

  /*
 Continually asks user if they want to use the default goal amount
 of $10000 until they choose one of two options('yes' or 'no').
 
 Continually prompts the user until they enter a valid number
 for the campaign goal(between 1 and 25000).
 
 @return the campaign goal
   */
 public static double promptUserForCampGoal() {

  double campGoal = 0.0;
  String defaultOrNo;
  boolean trigger = false;
  do{
   
  defaultOrNo = JOptionPane.showInputDialog("Would you like to use the default goal amount of $10000.0? Enter 'yes' or 'no'.");
  if(!defaultOrNo.equalsIgnoreCase("Yes") && (!defaultOrNo.equalsIgnoreCase("No"))){
      JOptionPane.showMessageDialog(null, "Error: Please enter either 'yes' or 'no'");
     }
  }while(!defaultOrNo.equalsIgnoreCase("Yes") && (!defaultOrNo.equalsIgnoreCase("No")));
  
  if (defaultOrNo.equalsIgnoreCase("Yes")) {
   campGoal = 10000.0;
  } else if (defaultOrNo.equalsIgnoreCase("No")) {

   do {

    try {

     campGoal = Double.parseDouble(JOptionPane.showInputDialog("Please enter your goal amount: "));

    } catch (NumberFormatException e) {

     campGoal = -1;

     JOptionPane.showMessageDialog(null, "Error: please enter a valid goal amount: ");

     trigger = true;

    }
    if (campGoal < 0.0 || campGoal > 25000.0) {
     JOptionPane.showMessageDialog(null, "Error: please enter a valid goal amount (1-25000)");
     trigger = true;
    }

   } while (trigger = true && (campGoal < 0.0 || campGoal > 25000.0));
  }
  return campGoal;
 }

 /*
 Continually prompts user for campaign donations until they
 enter a valid amount for a donation(greater than 0) and
 the loop will close when either the user enters -1 to stop entering donations,
 or when the goal amount is reached.
 
 @param campGoal the goal of a campaign(between 1 and 25000)
 @return the total amount of donations
  */
 public static double promptUserForCampDonation(double campGoal) {

  boolean finished = false;
  double donationAmount = 0.0;
  double donationTotal = 0.0;



  while (finished == false && donationTotal < campGoal) {

   try {
    donationAmount = Double.parseDouble(JOptionPane.showInputDialog("Please enter your donations, up to " + campGoal + " or enter -1 to exit."));
   } catch (NumberFormatException e) {
    donationAmount = -1;
   }

   if (donationAmount == -1) {
    finished = true;
   } else if (donationAmount < -1) {
    JOptionPane.showMessageDialog(null, "Error: please enter a valid donation amount.");
   } else {
    donationTotal += donationAmount; 
   }

  }

  return donationTotal;
 }


 /*
 Comparison between goal amounts and donation totals,
 adds to a report string  depending on which conditions are met.
 
 @param the instances of the Campaign class
 @return the report with all details gathered, as well as comparison outcomes
 */
 public static void printReport(Campaign campaign1, Campaign campaign2) {

  String report = "Donation Tracking System Report";
  report += "\n\nCampaign Name: " + campaign1.getCampName();
  report += "\nTotal amount of donations collected: " + campaign1.getDonationTotal();


  //Lists all of campaign1's goal threshold comparisons; adds to report if met
  if (campaign1.getDonationTotal() < campaign1.getCampGoal()) {

   report += "\n" + campaign1.getCampName() + " was below the goal amount by " + (campaign1.getCampGoal() - campaign1.getDonationTotal());

  } else if (campaign1.getDonationTotal() > campaign1.getCampGoal()) {

   report += "\n" + campaign1.getCampName() + " was above the goal amount by " + (campaign1.getDonationTotal() - campaign1.getCampGoal());

  } else if (campaign1.getDonationTotal() == campaign1.getCampGoal()) {

   report += "\n" + campaign1.getCampName() + " met the exact goal amount of " + campaign1.getCampGoal();
  }

  report += "\n\nCampaign Name: " + campaign2.getCampName();
  report += "\nTotal amount of donations collected: " + campaign2.getDonationTotal();

  //Lists all of campaign2's goal threshold comparisons; adds to report if met
  if (campaign2.getDonationTotal() < campaign1.getCampGoal()) {

   report += "\n" + campaign2.getCampName() + " was below the goal amount by " + (campaign2.getCampGoal() - campaign2.getDonationTotal());

  } else if (campaign2.getDonationTotal() > campaign1.getCampGoal()) {

   report += "\n" + campaign2.getCampName() + " was above the goal amount by " + (campaign2.getDonationTotal() - campaign2.getCampGoal());

  } else if (campaign1.getDonationTotal() == campaign1.getCampGoal()) {

   report += "\n" + campaign2.getCampName() + " met the exact goal amount of " + campaign2.getCampGoal();
  }


  /*Compares the two campaigns, determines and appends to the report
   which campaign has the highest donation amount.
  */
  if (campaign1.getDonationTotal() > campaign2.getDonationTotal()) {

   report += "\n\n" + campaign1.getCampName() + " collected the highest amount of donations.";

  } else if (campaign2.getDonationTotal() > campaign1.getDonationTotal()) {

   report += "\n\n" + campaign2.getCampName() + " collected the highest amount of donations.";
  } else if (campaign1.getDonationTotal() == campaign2.getDonationTotal()) {
   report += "\n\nBoth campaigns collected the same amount of donations.";
  }

  //Prints to the user with report 
  JOptionPane.showMessageDialog(null, report);
 }
}