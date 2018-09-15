import javax.swing.JOptionPane;

public class Campaign {
 private String campName;
 private String campType;
 private double campGoal = 10000.0;
 private double donationAmount;
 private double donationTotal;


 Campaign(String campName, String campType, double campGoal, double donationTotal) {
  this.campName = campName;
  this.campType = campType;
  this.campGoal = campGoal;
  this.donationTotal = donationTotal;
 }

 public String getCampName() {
  return campName;
 }

 public String getCampType() {
  return campType;
 }

 public double getCampGoal() {
  return campGoal;
 }

 public double getDonationAmount() {
  return donationAmount;
 }
 
 public void setCampName(String campName) {
  this.campName = campName;
 }

 /*
 Validates campType input to make sure it is
 either a 'non profit' or 'for profit' campaign.

 @param campType checks if campaign type entered is 
 either 'Non Profit' or 'For Profit'

 @return a boolean true or false is returned to the campaign
 type
 */
 public boolean setCampType(String campType) {
  if (campType.equalsIgnoreCase("Non Profit") || (campType.equalsIgnoreCase("For Profit"))) {
   this.campType = campType;
   return true;

  } else {

   return false;
  }
 }

 /*
 Validating mutator that checks if the campaign goal
 is greater than 0 and less than 25000; if so, 
 returns a true value, otherwise false.

 @param campGoal it takes the value from the
 campaign goal amount.
 
 @return a boolean true or false is returned to the campaign goal method.
 */
 public boolean setCampGoal(double campGoal) {
  if (campGoal > 0 && campGoal < 25000) {
   this.campGoal = campGoal;
   return true;

  } else {

   return false;
  }
 }

 public double getDonationTotal() {
  return donationTotal;
 }

 public void setDonationTotal(double donationAmount) {

  this.donationTotal = donationAmount;

 }

 /*
 If a campaign is 'For Profit', then the donations are subject
 to a 5% administrative fee. If 'Non Profit', no calculations
 are made.
 
 @param campType the type of campaign(Non Profit, For Profit)
 @param donationTotal the amount of donations collected for a campaign
 
 @return the donation total
 */
 public double calculateFee(String campType, double donationTotal) {
  if (campType.equalsIgnoreCase("For Profit")) {
   return donationTotal = donationTotal * 0.95;
  } else if (campType.equalsIgnoreCase("Non Profit")) {
   return donationTotal;
  } else {
   return donationTotal;
  }
 }
}