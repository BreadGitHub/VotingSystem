package Model.VotingManageSystem.HObjects;

import java.util.List;

public class CEC {
    //  Create Voting (whenStart, whenEnd) & Create Candidate (login:password)
    //  Sorting, choose group and print results
    private int id;
    private  String name;
    private String password;
    private String login;
    private List<Candidate> CandidateList; // remove it later

    public CEC(String name, String password, String login) {
        this.name = name;
        this.login = login;
        this.password = password;
    }
    public CEC(int id, String name, String password, String login) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }
}
