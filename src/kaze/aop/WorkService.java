package kaze.aop;

public interface WorkService {
    public String work();
    public String sleep();
}

class WorkServiceImpl implements WorkService{
    @Override
    public String work() {
        return "work success";
    }

    @Override
    public String sleep() {
        return "sleep success";
    }
}