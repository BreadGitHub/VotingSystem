package Model.VotingManageSystem.HObjects;

import java.util.List;

public class Candidate {
    private String fullname;
    private int id;
    private String birthdate;
    private String login;
    private String password;
    private int CECID;

    public Candidate(String fullname, String birthdate, String login, String password) {
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.login = login;
        this.password = password;

    }

    public Candidate(int id, String fullname, String birthdate, String login, String password, int CECID) {
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.login = login;
        this.password = password;
        this.id = id;
    }

    public String getName() {
        return fullname;
    }

    public int getCECID() {
        return CECID;
    }

    public void setCECID(int CECID) {
        this.CECID = CECID;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
