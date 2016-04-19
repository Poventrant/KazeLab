package kaze.aop;

import java.util.logging.Logger;

public interface BussinessService {
    public String login(String username, String password);
    public String find();
}

class BussinessServiceImpl implements BussinessService {
    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    public String login(String username, String password) {
        return "login success";
    }

    @Override
    public String find() {
        return "find success";
    }

}