package Model.VotingManageSystem.HObjects;

public class Vote {
    private int id; // vote id
    private int userID; // who voted
    private String userName;
    private int candidateID;
    private String candidateName;

    public Vote(int userID, String userName, int candidateID, String candidateName) {
        this.userID = userID;
        this.userName = userName;
        this.candidateID = candidateID;
        this.candidateName = candidateName;
    }

    public Vote(int id, int userID, String userName, int candidateID, String candidateName) {
        this.id = id;
        this.userID = userID;
        this.userName = userName;
        this.candidateID = candidateID;
        this.candidateName = candidateName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(int candidateID) {
        this.candidateID = candidateID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
