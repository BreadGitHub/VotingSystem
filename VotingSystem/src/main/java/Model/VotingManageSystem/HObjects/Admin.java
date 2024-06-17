package Model.VotingManageSystem.HObjects;

public class Admin {
    //  Control UserList, CECList, CandidateList (show, remove)
    //  create CEC (login:password)
    private String fullname;
    private String role;
    private String birthdate;
    private String login;
    private String password;

    public Admin(String fullname, String role, String birthdate, String login, String password) {
        this.fullname = fullname;
        this.role = role;
        this.birthdate = birthdate;
        this.login = login;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getRole() {
        return role;
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
}
