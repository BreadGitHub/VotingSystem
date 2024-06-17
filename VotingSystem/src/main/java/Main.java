import Model.VotingManageSystem.DataBase.Connector;
import Model.VotingManageSystem.DataBase.VotingRepositoryBD;
import Model.VotingManageSystem.HObjects.CEC;
import Model.VotingManageSystem.HObjects.Candidate;
import Model.VotingManageSystem.HObjects.User;
import Model.VotingManageSystem.Service.VotingService;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Connection connection = new Connector().getConnection();
    static VotingService service = new VotingService(new VotingRepositoryBD(connection));
    static boolean logged = false;
    static boolean loggedCEC = true;
    static int UserID;
    public static void startmenu(){
        System.out.println("Тип пользователя:");
        System.out.println("1. Администратор:");
        System.out.println("2. Пользователь:");
        System.out.println("3. Комиссия:");
        System.out.println("4. Кандидат:");
        Scanner scanner = new Scanner(System.in);
        int answer = scanner.nextInt();
        int action = 1;
        switch (answer) {
            case 1:
                startadmin(scanner);
            case 2:
                startuser(scanner);
            case 3:
                startCEC(scanner);
            case 4:
                startCandidate(scanner);
        }

    }

    private static void startCandidate(Scanner scanner) {
        startmenu();
    }

    private static void startCEC(Scanner scanner) {
        if (!loggedCEC) {
            System.out.println("1. Регистрация");
            System.out.println("2. Авторизация");
            int answer = scanner.nextInt();
            switch (answer) {
                case 1:
                    reg(scanner);
                case 2:
                    auth(scanner);
            }
        } else {
            System.out.println("1. Добавить кандидата");
            System.out.println("2. Удалить кандидата");
            System.out.println("3. Отчет");
            int answer = scanner.nextInt();
            switch (answer) {
                case 1:
                    System.out.println("Имя кандидата:");
                    String name = scanner.next();
                    System.out.println("Дата рождения:");
                    String birthdate = scanner.next();
                    System.out.println("Новый логин:");
                    String login = scanner.next();
                    System.out.println("Новый пароль:");
                    String password = scanner.next();
                    System.out.println("Группа:");
                    int CEC = scanner.nextInt(); //id
                    service.addCandidate(name, birthdate, login, password, CEC);
                case 2:
                    System.out.println("Список кандидатов:");
                    List<Candidate> listcandidates2 = service.getCandidateList();
                    for (Candidate candidate : listcandidates2) {
                        System.out.println("id: " + candidate.getId() + " Название: " + candidate.getName() + " из " + candidate.getCECID());
                    }
                    System.out.println("id, кого удалить:");
                    answer = scanner.nextInt();
                    service.removeCandidate(answer);
            }
        }
    }

    private static void startuser(Scanner scanner) {
        if (!logged) {
            System.out.println("1. Регистрация");
            System.out.println("2. Авторизация");
            int answer = scanner.nextInt();
            switch (answer) {
                case 1:
                    reg(scanner);
                case 2:
                    auth(scanner);
            }
        } else {
            System.out.println("1. Список кандидатов");
            System.out.println("1. Дать голос");
            int answer = scanner.nextInt();
            switch (answer) {
                case 1:
                    System.out.println("Список кандидатов:");
                    List<Candidate> listcandidates = service.getCandidateList();
                    for (Candidate candidate : listcandidates) {
                        System.out.println("id: " + candidate.getId() + " Название: " + candidate.getName() + " из " + candidate.getCECID());
                    }
                case 2:
                    System.out.println("Список кандидатов:");
                    List<Candidate> listcandidates2 = service.getCandidateList();
                    for (Candidate candidate : listcandidates2) {
                        System.out.println("id: " + candidate.getId() + " Название: " + candidate.getName() + " из " + candidate.getCECID());
                    }
                    System.out.println("id, за кого отдать голос:");
                    answer = scanner.nextInt();
                    service.toVote(UserID, answer);
            }

        }


    }

    private static void auth(Scanner scanner) {
        System.out.println("логин:");
        String login = scanner.next();
        System.out.println("пароль:");
        String password = scanner.next();
        List<User> userlist = service.getUserList();
        for (User user : userlist) {
            System.out.println("Сравниваю " + user.getPassword() + " и " + password);
            if ( (Objects.equals(user.getLogin(), login))) {
                System.out.println("Успешно");
                logged = true;
                userID = user.getID();
            }
        }
        startuser(scanner);
    }

    private static void reg(Scanner scanner) {
        System.out.println("Новое имя:");
        String name2 = scanner.next();
        System.out.println("Дата рождения:");
        String birthdate = scanner.next();
        System.out.println("Новый логин:");
        String login2 = scanner.next();
        System.out.println("Новый пароль:");
        String password2 = scanner.next();
        service.addUser(name2, birthdate, login2, password2);
        logged = true;
    }

    private static void startadmin(Scanner scanner) {
        System.out.println("Что сделать?");
        System.out.println("1. Добавить комиссию.");
        System.out.println("2. Удалить комиссию.");
        System.out.println("3. Список комиссий.");
        System.out.println("4. Список пользователей.");
        System.out.println("5. Список кандидатов.");
        System.out.println("6. Удалить кандидата.");
        System.out.println("7. Удалить пользователя.");
        int answer = scanner.nextInt();
        switch (answer) {
            case 1:
                System.out.print("Название комиссии:");
                    String cec_name = scanner.next();
                    System.out.println();
                    System.out.print("Новый логин:");
                    String cec_login = scanner.next();
                    System.out.println();
                    System.out.print("Новый пароль:");
                    String cec_password = scanner.next();
                    service.addCEC(cec_name, cec_login,cec_password);
                    break;
            case 2:
                System.out.println("Список комиссий:");
                List<CEC> list = service.getCECList();
                for (CEC cec : list) {
                    System.out.println("id: " + cec.getId() + " Название: " + cec.getName());
                }
                System.out.println("ID комиссии которую нужно удалить:");
                answer = scanner.nextInt();
                service.removeCEC(answer);
                break;
            case 3:
                System.out.println("Список комиссий:");
                List<CEC> list2 = service.getCECList();
                for (CEC cec : list2) {
                    System.out.println("id: " + cec.getId() + " Название: " + cec.getName());
                }
                break;
            case 4:
                System.out.println("Список пользователей:");
                List<User> listuser = service.getUserList();
                for (User user : listuser) {
                    System.out.println("id: " + user.getID() + " Название: " + user.getName());
                }
                break;
            case 5:
                System.out.println("Список кандидатов:");
                List<Candidate> listcandidates = service.getCandidateList();
                for (Candidate candidate : listcandidates) {
                    System.out.println("id: " + candidate.getId() + " Название: " + candidate.getName() + " из " + candidate.getCECID());
                }
                break;
            case 6:
                System.out.println("Список кандидатов:");
                List<Candidate> listcandidates2 = service.getCandidateList();
                for (Candidate candidate : listcandidates2) {
                    System.out.println("id: " + candidate.getId() + " Название: " + candidate.getName() + " из " + candidate.getCECID());
                }
                System.out.println("ID которого удалить...");
                answer = scanner.nextInt();
                service.removeCandidate(answer);
            case 7:
                System.out.println("Список пользователей:");
                List<User> listuser2 = service.getUserList();
                for (User user : listuser2) {
                    System.out.println("id: " + user.getID() + " Название: " + user.getName());
                }
                System.out.println("ID которого удалить...");
                answer = scanner.nextInt();
                service.removeUser(answer);
                break;
        }
    }

    public static void main (String[] args) {

        startmenu();

//            case 2:
//                boolean logged = false;
//                do {
//                    System.out.println("Что сделать?");
//                    System.out.println("1. Авторизация");
//                    System.out.println("2. Регистрация");
//                    answer = scanner.nextInt();
//                    switch (answer) {
//                        case 1:
//                            System.out.println("логин:");
//                            String login = scanner.next();
//                            System.out.println("пароль:");
//                            String password = scanner.next();
//                            List<User> userlist = service.getUserList();
//                            for (User user : userlist) {
//                                if (Objects.equals(user.getLogin(), login) && Objects.equals(user.getPassword(), password)) {
//                                    System.out.println("Успешно");
//                                    logged = true;
//                                    break;
//                                } else {
//                                    System.out.println("Неверные данные");
//
//                                    break;
//                                }
//                            } break;
//                        case 2:
//                            System.out.println("Новое имя:");
//                            String name2 = scanner.next();
//                            System.out.println("Дата рождения:");
//                            String birthdate = scanner.next();
//                            System.out.println("Новый логин:");
//                            String login2 = scanner.next();
//                            System.out.println("Новый пароль:");
//                            String password2 = scanner.next();
//                            service.addUser(name2, birthdate, login2, password2);
//                            logged = true;
//
//                        case 0:
//                    }
//                } while (logged == false);
//
//            case 3:
//
//            case 4:
//        }
    }
}
