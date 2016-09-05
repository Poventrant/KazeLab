package kaze.alitest;

import java.util.Date;
import java.util.List;

/**
 * Created by 枫叶 on 2016/5/10.
 */
public class BuyDetail {
    public Integer id;
    public Date time;
    public List<Product> productList;
    //实际需要付的钱，包含了打折的部分
    public Integer price;
    public Integer userId;
    public String content;
}
