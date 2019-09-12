package sample;

public class Agent {
    private Integer agtId;
    private String agtFirstName;
    private String agtLastName;
    private String agtPhone;
    private String agtEmail;
    private String agtPosition;
    private Integer agencyId;

    public Agent(Integer id, String firstName, String lastName, String phone, String email, String position, Integer agcyId) {
        this.agtId = id;
        this.agtFirstName = firstName;
        this.agtLastName = lastName;
        this.agtPhone = phone;
        this.agtEmail = email;
        this.agtPosition = position;
        this.agencyId = agcyId;
    }

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

    @Override
    public String toString() {
        return agtId + ", " + agtFirstName + " " + agtLastName + ", " + agtPhone + ", " + agtEmail + ", " + agtPosition + ", " + agencyId;
    }
}
