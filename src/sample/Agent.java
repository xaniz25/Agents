//Created by Shanice Talan on September 11, 2019
//CMPP 264 Java - Day 6 Assignment: JavaFX/SceneBuilder Application that modifies Agent table
package sample;

public class Agent {
    //member variables
    private Integer agtId;
    private String agtFirstName;
    private String agtMidInitial;
    private String agtLastName;
    private String agtPhone;
    private String agtEmail;
    private String agtPosition;
    private Integer agencyId;

    //constructor
    public Agent(Integer id, String firstName, String initial, String lastName, String phone, String email, String position, Integer agcyId) {
        this.agtId = id;
        this.agtFirstName = firstName;
        this.agtMidInitial = initial;
        this.agtLastName = lastName;
        this.agtPhone = phone;
        this.agtEmail = email;
        this.agtPosition = position;
        this.agencyId = agcyId;
    }

    //getters and setters

    public Integer getAgtId() {
        return agtId;
    }

    public void setAgtId(Integer id) {
        this.agtId = id;
    }

    public String getAgtFirstName() { return agtFirstName; }

    public void setAgtFirstName(String firstName) {
        this.agtFirstName = firstName;
    }

    public String getAgtMidInitial() { return agtMidInitial; }

    public void setAgtMidInitial(String initial) { this.agtMidInitial = initial; }

    public String getAgtLastName() { return agtLastName; }

    public void setAgtLastName(String lastName) {
        this.agtLastName = lastName;
    }

    public String getAgtPhone() { return agtPhone; }

    public void setAgtPhone(String phone) {
        this.agtPhone = phone;
    }

    public String getAgtEmail() { return agtEmail; }

    public void setAgtEmail(String email) {
        this.agtEmail = email;
    }

    public String getAgtPosition() { return agtPosition; }

    public void setAgtPosition(String position) {
        this.agtPosition = position;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agcyId) {
        this.agencyId = agcyId;
    }

    //outputting only Agents' IDs and Names, to be used for the combobox
    //decided to also include names, as if it were a big company with lots of employees,
    //it will be hard to remember all names and their corresponding IDs
    @Override
    public String toString() {
        return agtId + " " + agtLastName + ", " + agtFirstName;
    }
}
