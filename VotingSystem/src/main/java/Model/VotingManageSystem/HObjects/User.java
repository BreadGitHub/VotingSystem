package Model.VotingManageSystem.HObjects;

public class User {
    //  registration (data)
    //  vote for
    //  show CandidateList
    //  VoteHistory
    private String fullname;
    private String birthdate;
    private String login;
    private String password;
    private boolean vote;
    private int id;

    public User(String fullname, String birthdate, String login, String password) {
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.login = login;
        this.password = password;
    }

    public User(int id, String fullname, String birthdate, String login, String password, boolean vote) {
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.login = login;
        this.password = password;
        this.id = id;
        this.vote = vote;
    }
    public String getName() {
        return fullname;
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
    public int getID() {return id;}

    public boolean isVoted() {
        return vote;
    }
    public void setVote(boolean vote) {
        this.vote = vote;
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

    public void setID(int id) {this.id = id;}
}
