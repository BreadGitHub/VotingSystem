package Model.VotingManageSystem.Service;

import Model.VotingManageSystem.HObjects.CEC;
import Model.VotingManageSystem.HObjects.Candidate;
import Model.VotingManageSystem.HObjects.User;
import Model.VotingManageSystem.HObjects.Vote;
import Model.VotingManageSystem.Interface.Repository;

import java.util.List;

public class VotingService {
    private final Repository repository;

    public void toVote(int id, int candidate_id){
        this.repository.toVote(id, candidate_id);
    }

    public VotingService(Repository repository) {
        this.repository = repository;
    }

    public List<User> getUserList() {
        return repository.getUserList();
    }

    public List<Candidate> getCandidateList() {
        return repository.getCandidateList();
    }

    public List<CEC> getCECList() {
        return repository.getCECList();
    }

    public List<Vote> getVotingList() {
        return repository.getVotingList();
    }

    public void addUser(String fullname, String birthdata, String login, String password) {
        repository.addUser(fullname, birthdata, login, password);
    }

    public void removeUser(int id)
    {
        repository.removeUser(id);
    }
    public void addCandidate(String fullname, String birthdata, String login, String password, int CECID) {
        repository.addCandidate(fullname, birthdata, login, password, CECID);
    }

    public void removeCandidate(int id) {
        repository.removeCandidate(id);
    }
    public void addCEC(String name, String login, String password) {
        repository.addCEC(name, login, password);
    }
    public void removeCEC(int id)
    {
        repository.removeCEC(id);
    }
    public User findUserByID(int id) {
        return repository.FindUserByID(id);
    }
    public Candidate findCandidateByID(int id) {
        return repository.FindCandidateByID(id);
    }
    public CEC findCECByID(int id) {
        return repository.FindCECByID(id);
    }



}
