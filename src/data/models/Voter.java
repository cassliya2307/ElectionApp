package data.models;

public class Voter extends User {
    private int voterId;
    private String phoneNumber;

    public Voter(String username, int age, String phoneNumber, boolean isAdmin) {
        super(username, age, isAdmin);
        voterId = voterId + 1;
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }


    private void validatePhoneNumber(String phoneNumber){
        if(phoneNumber.length() != 11)
            throw new IllegalArgumentException("Phone number must have 11 digits and numbers");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
}
