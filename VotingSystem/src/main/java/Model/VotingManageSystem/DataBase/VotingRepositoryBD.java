package Model.VotingManageSystem.DataBase;

import Model.VotingManageSystem.HObjects.CEC;
import Model.VotingManageSystem.HObjects.Candidate;
import Model.VotingManageSystem.HObjects.User;
import Model.VotingManageSystem.HObjects.Vote;
import Model.VotingManageSystem.Interface.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VotingRepositoryBD implements Repository {

    private Connection connection;

    public VotingRepositoryBD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getUserList() {
        List<User> users = new ArrayList<>();

        String sql = "SELECT user_id, user_name, birthdate, login, password, isvote FROM \"VoteData\".\"Users\"";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                int user_id = resultSet.getInt("user_id");
                String user_name = resultSet.getString("user_name");
                String birthdate = resultSet.getString("birthdate");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                boolean isVoted = resultSet.getBoolean("isvote");

                users.add(new User(user_id, user_name, birthdate, login, password, isVoted));
                System.out.println("name: " + user_name + " id: " +user_id + " voted?: " + isVoted);
            } return users;
        } catch (SQLException e) {System.out.println(e.getMessage());}
        return null;

    }

    @Override
    public List<Candidate> getCandidateList() { //CEC id
        List<Candidate> candidates = new ArrayList<>();

        String sql = "SELECT candidate_id, candidate_name, birthdate, password, login, cec_id FROM \"VoteData\".\"Candidates\"";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                int candidateID = resultSet.getInt("candidate_id");
                String candidate_name = resultSet.getString("candidate_name");
                String birthdate = resultSet.getString("birthdate");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int cec_id = resultSet.getInt("cec_id");

                candidates.add(new Candidate(candidateID, candidate_name, birthdate, login, password, cec_id));
            }
        } catch (SQLException e) {System.out.println(e.getMessage());}
        return candidates;
    }
    @Override
    public void toVote(int id, int candidate_id) { //Кто (user) проголосовал за кого (candidate)
        String sql = "INSERT into \"VoteData\".\"VoteHistory\" (user_id, user_name, candidate_id, candidate_name) VALUES (?, ?, ?, ?)"; // Добавляем новый vote в vote history
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, FindUserByID(id).getName());
            statement.setInt(3, candidate_id);
            statement.setString(4, FindCandidateByID(candidate_id).getName());
            statement.executeUpdate();
            System.out.println("User " + id + " vote for " + candidate_id);
            UserUpdate(id, true);
            System.out.println("Vote is done");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void UserUpdate(int id, boolean vote) {
        String sql = "UPDATE \"VoteData\".\"Users\" SET isvote = "+ vote +" WHERE user_id = " + id; // Ставим isVote для user
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.executeUpdate();
            System.out.println("User is vote");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CEC> getCECList() {
        List<CEC> CECs = new ArrayList<>();

        String sql = "SELECT cec_id, cec_name, login, password FROM \"VoteData\".\"CECs\"";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                int id = resultSet.getInt("cec_id");
                String  cec_name = resultSet.getString("cec_name");
                String login = resultSet.getString("login");
                String  password = resultSet.getString("password");

                CECs.add(new CEC(id, cec_name, login, password));
            }
        } catch (SQLException e) {System.out.println(e.getMessage());}
        return CECs;
    }

    @Override
    public List<Vote> getVotingList() {
        List<Vote> votes = new ArrayList<>();

        String sql = "SELECT VoteID, user_id, user_name, CandidateID, candidate_name FROM \"VoteData\".\"VoteHistory\"";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                int id = resultSet.getInt("VoteID");
                int  user_id = resultSet.getInt("user_id");
                String user_name = resultSet.getString("user_name");
                int candidateID = resultSet.getInt("CandidateID");
                String candidate_name = resultSet.getString("candidate_name");

                votes.add(new Vote(id, user_id, user_name, candidateID, candidate_name));
            }
        } catch (SQLException e) {System.out.println(e.getMessage());}
        return votes;
    }

    @Override
    public void addUser(String fullname, String birthdata, String login, String password) {
        String sql = "INSERT INTO \"VoteData\".\"Users\" (user_name, birthdate, login, password, isvote) VALUES (?,?,?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
             statement.setString(1, fullname);
             statement.setString(2, birthdata);
             statement.setString(3, login);
             statement.setString(4, password);
             statement.setBoolean(5, false);
             statement.executeUpdate();
             System.out.println("User " + fullname + " added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUser(int id) {
        String sql = "DELETE FROM \"VoteData\".\"Users\" WHERE user_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("User by id " + id + " removed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User FindUserByID(int id) {
        List<User> users = getUserList();
        for (User user: users) {
            if (id == user.getID()){
                System.out.println("User name:" + user.getName());
                return user;
            }
        } return null;
    }

    @Override
    public void addCEC(String name, String login, String password) {
        String sql = "INSERT INTO \"VoteData\".\"CECs\" (cec_name, login, password) VALUES (?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setString(3, password);
            statement.executeUpdate();
            System.out.println("CIC " + name + " added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeCEC(int id) {
        String sql = "DELETE FROM \"VoteData\".\"CECs\" WHERE cec_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("CEC by id " + id + " removed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CEC FindCECByID(int id) {
        List<CEC> cecs = getCECList();
        for (CEC cec: cecs) {
            if (id == cec.getId()){
                System.out.println("patient name:" + cec.getName());
                return cec;
            }
        } return null;
    }

    @Override
    public void addCandidate(String fullname, String birthdata, String login, String password, int cec_id) {
        String sql = "INSERT INTO \"VoteData\".\"Candidates\" (candidate_name, birthdate, login, password, cec_id) VALUES (?,?,?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, fullname);
            statement.setString(2, birthdata);
            statement.setString(3, login);
            statement.setString(4, password);
            statement.setInt(5, cec_id);
            statement.executeUpdate();
            System.out.println("Candidate " + fullname + " added in CEC "+ cec_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeCandidate(int id) {
        String sql = "DELETE FROM \"VoteData\".\"Candidates\" WHERE user_id = ?, cec_id = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Candidate by id " + id + " removed from CEC");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Candidate FindCandidateByID(int id) {
        List<Candidate> candidates = getCandidateList();
        for (Candidate candidate: candidates) {
            if (id == candidate.getId()){
                System.out.println("patient name:" + candidate.getName());
                return candidate;
            }
        } return null;
    }
}
