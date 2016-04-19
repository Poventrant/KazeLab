package kaze.aop;

import java.util.logging.Logger;

public class Test {
    public static Logger logger = Logger.getLogger(Test.class.getSimpleName());
    public static void main(String[] args) {

        BussinessService bs = LogInvoHandler.getProxyInstance(BussinessServiceImpl.class);
        bs.login("kaze", "123456");
        bs.find();

        logger.info("--------------------------------------");

        WorkService ws = LogInvoHandler.getProxyInstance(WorkServiceImpl.class);
        ws.work();
        ws.sleep();

        logger.info("--------------------------------------");

        BussinessService bss = LogInvoHandler.getProxyInstance(BussinessServiceImpl.class);
        bss.login("lisi", "654321");
        bss.find();

    }
}