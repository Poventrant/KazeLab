//package kaze.algorithm;
//
//public class Zhuanzhang implements IZhuanZhang{
//
//    private ZhuanzhangDao dao;
//    private Long money;
//    private String username;
//    private Object moniter = new Object();
//    //转出还是转入
//    private boolean action;
//
//    public void doAction() {
//        if(action) put();
//    }
//
//    @Override
//    public Long put() {
//        synchronized (moniter) {
//            Account account = dao.getAccount(username);
//            account.setTotalMoney(account.getTotalMoney() + money);
//            account.persist();      //同步到数据库
//        }
//        return money;
//    }
//
//    @Override
//    public long take() {
//        synchronized (moniter) {
//            Account account = dao.getAccount(username);
//            if(account.getTotalMoney() < money) return 0;
//            account.setTotalMoney(account.getTotalMoney() - money);
//            account.persist();      //同步到数据库
//        }
//        return -money;
//    }
//
//    public Long getMoney() {
//        return money;
//    }
//
//    public void setMoney(Long money) {
//        this.money = money;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public boolean isAction() {
//        return action;
//    }
//
//    public void setAction(boolean action) {
//        this.action = action;
//    }
//
//    public ZhuanzhangDao getDao() {
//        return dao;
//    }
//
//    public void setDao(ZhuanzhangDao dao) {
//        this.dao = dao;
//    }
//}
//
//interface IZhuanZhang {
//    Long put();
//    long take();
//}
//
//class ZhuanzhangDao {
//    //查询数据库，获取账号信息
//    public Account getAccount(String usernme) {
//        //query by username
//        return null;
//    }
//}
//
//
//class Account {
//    private String username;
//    private String password;
//    private Long totalMoney;
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public Long getTotalMoney() {
//        return totalMoney;
//    }
//
//    public void setTotalMoney(Long totalMoney) {
//        this.totalMoney = totalMoney;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
