package kaze.alitest;

/**
 * Created by 枫叶 on 2016/5/10.
 */
public class Disccount {
    public Integer id;
    public Integer shopId;
    //打折的类型,可以表示打折的优先级
    public Integer type;
    //折扣说明
    public String discription;
    //实际折扣了多少钱
    public Integer discount;

    public void doDisccount(BuyDetail buyDetail) {
        buyDetail.price -= this.discount;
        buyDetail.content = this.discription;
    }

}
