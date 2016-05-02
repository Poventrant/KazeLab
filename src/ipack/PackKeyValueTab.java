package ipack;

import play.db.jpa.Model;

/**
 * 功能描述:
 * <p> 版权所有：优视科技 - 超能战队</p>
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 </p>
 *
 * @author <a href="mailto:wb-pwq174842@alibaba-inc.com">庞文全</a>
 * @version 1.0.0
 * @create on: 2016/4/25
 */
public abstract class PackKeyValueTab extends Model {

    public boolean equals(PackKeyValueTab packKeyValueTab) {
        if (this.getKey().equals(packKeyValueTab.getKey()) && this.getKey().equals(packKeyValueTab.getKey())) {
            return true;
        } else {
            return false;
        }
    }

    abstract public String getName();

    abstract public  void setName(String name);

    abstract public String getValue();

    abstract public void setValue(String value);

    abstract public String getKey();

    abstract public void setKey(String key);

    abstract public String getDescription();

    abstract public void setDescription(String description);

    abstract public boolean isDeleted();

    abstract public void setDeleted(boolean deleted);

    abstract public boolean isSetTop();

    abstract public boolean getSetTop();

    abstract public void setSetTop(boolean setTop);

    abstract public PackKeyValueTab clone();

    abstract public String getSetTopDate();

    abstract public void setSetTopDate(String setTopDate);

    abstract public String getKeyField();

    abstract public String getValueField();
}
