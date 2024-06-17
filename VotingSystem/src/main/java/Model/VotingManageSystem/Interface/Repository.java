package Model.VotingManageSystem.Interface;

import Model.VotingManageSystem.HObjects.Vote;
import Model.VotingManageSystem.HObjects.CEC;
import Model.VotingManageSystem.HObjects.Candidate;
import Model.VotingManageSystem.HObjects.User;

import java.util.List;

public interface Repository {
    List<User> getUserList();
    List<Candidate> getCandidateList(); // from

    void toVote(int id, int candidate_id);

    List<CEC> getCECList();
    List<Vote> getVotingList();

    void addUser(String fullname, String birthdata, String login, String password);
    void removeUser(int id); // clear user vote!
    User FindUserByID (int id);

    void addCEC(String name, String login, String password);
    void removeCEC(int id);
    CEC FindCECByID(int id);

    void addCandidate(String fullname, String birthdata, String login, String password, int CECID);
    void removeCandidate(int CandidateID); //Убрать <кого?> <откуда?>
    Candidate FindCandidateByID(int id);
}
