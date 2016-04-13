package gson;

import java.util.List;

/**
 * Created by uc on 2016/3/30.
 */
public class Account {

    private String account;
    private String password;
    private List<User> users;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
