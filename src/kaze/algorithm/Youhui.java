//package kaze.algorithm;
//
//
//import java.util.List;
//
//
//class youhuiController {
//    public void doGet(Shop shop, Goods ...goods) {
//        int total = 0;
//        int len = goods.length;
//        for (int i = 0; i < len; i++) {
//            total += goods[i].getPrice();
//        }
//        YouHuiService<Shop> youHuiService = new YouHuiService<Shop>();
//    }
//}
//
///**
// * 优惠
// */
//interface Youhui {
//
//    public abstract int isSatisfy(int total);
//
//    public abstract String doYouhui(int choice);
//
//}
//
//class YouhuiImpl implements Youhui{
//
//    @Override
//    public int isSatisfy(int total) {
//        return 0;
//    }
//
//    @Override
//    public String doYouhui(int choice) {
//
//    }
//}
//
//class YouHuiService<E extends Shop> {
//
//    private E shop;
//    public YouHuiService(E shop) {
//        this.shop = shop;
//    }
//
//    public String checkYouhui(int total) {
//        YouhuiImpl solve = null;
//        int max = 0;
//        for (YouhuiImpl l : shop.getRequire()) {
//            if(max < l.isSatisfy(total)) {
//                max = l.isSatisfy(total);
//                solve = l;
//            }
//        }
//        if(solve != null) return solve.doYouhui(max);
//        return null;
//    }
//
//}
//
//
///**
// * 店铺
// */
//class Shop {
//
//    private Integer id;
//    private String name;
//    private List<YouhuiImpl> require;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//
//    public List<YouhuiImpl> getRequire() {
//        return require;
//    }
//
//    public void setRequire(List<YouhuiImpl> require) {
//        this.require = require;
//    }
//}
//
///**
// * 商品
// */
//class Goods {
//    private int price;
//    //地点
//    private int place;
//    //邮费
//    private int shipping;
//
//    private Shop shop;
//
//    private String content;
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public int getPlace() {
//        return place;
//    }
//
//    public void setPlace(int place) {
//        this.place = place;
//    }
//
//    public int getShipping() {
//        return shipping;
//    }
//
//    public void setShipping(int shipping) {
//        this.shipping = shipping;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public Shop getShop() {
//        return shop;
//    }
//
//    public void setShop(Shop shop) {
//        this.shop = shop;
//    }
//}
