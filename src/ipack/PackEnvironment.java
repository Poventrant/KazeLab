package ipack;

import org.hibernate.annotations.Index;

import javax.persistence.Entity;

/**
 * 功能描述：
 * 定义打包环境module
 * <p> 版权所有：优视科技 </p>
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 </p>
 *
 * @author <a href="mailto:wb-pwq174842@alibaba-inc.com">庞文全</a>
 * @version 1.0.0
 * @since 1.0.0
 * create on: 2016/04/25
 */

@Entity
public class PackEnvironment extends PackKeyValueTab {

    //打包中文名
    @Index(name = "environmentName", columnNames = {"environment_Key", "environment_Value"})
    public String environmentName;

    //环境值
    public String environmentValue;

    //环境名
    public String environmentKey;

    //开光说明
    public String description;

    //是否删除
    public boolean isDeleted = false;

    //是否置顶
    public boolean setTop = false;

    //用于记录置顶的时间，作为排序依据
    public String setTopDate;

    public PackEnvironment() {

    }

    /**
     * 构造函数
     *
     * @param environmentKey   环境名称
     * @param environmentValue 环境值
     * @param environmentName  环境中文名
     * @param description 环境描述
     * @author <a href="mailto:lizhidong.lizd@alibaba-inc.com">李之栋</a>
     * @version 1.0.0
     */
    public PackEnvironment(String environmentKey, String environmentValue, String environmentName, String description) {
        this.environmentName = environmentName;
        this.environmentKey = environmentKey;
        this.environmentValue = environmentValue;
        this.description = description;
    }

    @Override
    public String toString() {
        return "PackEnvironment{" +
                "environmentName='" + environmentName + '\'' +
                ", environmentValue='" + environmentValue + '\'' +
                ", environmentKey='" + environmentKey + '\'' +
                ", description='" + description + '\'' +
                ", isDeleted=" + isDeleted +
                ", setTop=" + setTop +
                '}';
    }

    @Override
    public String getName() {
        return environmentName;
    }

    @Override
    public void setName(String name) {
        this.environmentName = name;
    }

    @Override
    public String getValue() {
        return environmentValue;
    }

    @Override
    public void setValue(String value) {
        this.environmentValue = value;
    }

    @Override
    public String getKey() {
        return environmentKey;
    }

    @Override
    public void setKey(String key) {
        this.environmentKey = key;
    }

    public String getSetTopDate() {
        return setTopDate;
    }

    public void setSetTopDate(String setTopDate) {
        this.setTopDate = setTopDate;
    }

    @Override
    public String getKeyField() {
        return "environment_key";
    }

    @Override
    public String getValueField() {
        return "environment_value";
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public String getEnvironmentValue() {
        return environmentValue;
    }

    public void setEnvironmentValue(String environmentValue) {
        this.environmentValue = environmentValue;
    }

    public String getEnvironmentKey() {
        return environmentKey;
    }

    public void setEnvironmentKey(String environmentKey) {
        this.environmentKey = environmentKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isSetTop() {
        return setTop;
    }

    @Override
    public boolean getSetTop() {
        return setTop;
    }

    public void setSetTop(boolean setTop) {
        this.setTop = setTop;
    }

    @Override
    public PackKeyValueTab clone() {
        PackEnvironment newEnvironment = new PackEnvironment();
        newEnvironment.setDeleted(this.isDeleted);
        newEnvironment.setDescription(this.description);
        newEnvironment.setSetTop(this.setTop);
        newEnvironment.setEnvironmentKey(this.environmentKey);
        newEnvironment.setEnvironmentName(this.environmentName);
        newEnvironment.setEnvironmentValue(this.environmentValue);
        newEnvironment.setTopDate = this.setTopDate;
        newEnvironment.save();
        return newEnvironment;
    }

    public static PackEnvironment copyPackEnvironment(PackEnvironment packEnvironment) {
        PackEnvironment newEnvironment = new PackEnvironment();
        newEnvironment.setDeleted(packEnvironment.isDeleted);
        newEnvironment.setDescription(packEnvironment.description);
        newEnvironment.setSetTop(packEnvironment.setTop);
        newEnvironment.setEnvironmentKey(packEnvironment.environmentKey);
        newEnvironment.setEnvironmentName(packEnvironment.environmentName);
        newEnvironment.setEnvironmentValue(packEnvironment.environmentValue);
        newEnvironment.setTopDate = packEnvironment.setTopDate;
        newEnvironment.save();
        return newEnvironment;
    }
}

