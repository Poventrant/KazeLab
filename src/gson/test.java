package gson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class test {
    public static void main(String[] args) {
        Account account = new Account();
        account.setAccount("123456");
        account.setPassword("321645");
        account.setUsers(Arrays.asList(new User(1, "test"), new User(2, "pwq")));

        String [] ignoreFields = ignoreFields(Account.class, "account");
        String json = toJsonByIgnoreField(account,  ignoreFields);
        for(String f : ignoreFields) {
            System.out.println(f);
        }
        System.out.println(json);

        String mdStr = "{\"branch\":\"trunk\",\"changeAt\":1453099597000,\"changeBy\":\"89247\",\"createAt\":1453099597000,\"createBy\":\"89247\",\"credentials\":\"7cecf791-1e52-4bff-83c6-e0a9caac4683\",\"disable\":false,\"entityId\":1,\"id\":1,\"labelId\":11,\"packComponents\":[],\"packSwitchs\":[],\"persistent\":true,\"repositoryType\":\"svn\",\"revision\":\"HEAD\",\"shellCmd\":\"cd ..\",\"templateName\":\"基线模板\",\"url\":\"https://svn.ucweb.local\"}";
        mdStr = encodeMD5(mdStr);
        System.out.println(mdStr);


        List<User> users = Arrays.asList(new User(1, "test"), new User(2, "pwq"), new User(2, "pwqweq"));

        //按照switchKey进行排序
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (User user : users) {
            System.out.println(user.getName());
        }

        Boolean flag = true;
        changeBoolean(flag);
        System.out.println(flag);
    }

    public static void changeBoolean(Boolean b) {
        b = false;
    }

    public static String [] ignoreFields(Class clazz, String ... neededFields) {
        String result = "";
        Field[] allFields = clazz.getDeclaredFields();
        String fieldName;
        boolean exist;
        for(Field field : allFields) {
            fieldName = field.getName();
            exist = false;
            for(String neededField : neededFields) {
                if(fieldName.equals(neededField)) {
                    exist = true;
                    break;
                }
            }
            if(!exist) {
                result += fieldName + ",";
            }
        }
        return result.split(",");
    }

    public static String encodeMD5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            return convertToHexString(md5.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String toJsonByIgnoreField(Object o, String [] fields) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(o.getClass(), fields);
        String json = JSON.toJSONString(o, filter);
        return json;
    }

    private static String convertToHexString(byte data[]) {
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            strBuffer.append(Integer.toHexString(0xff & data[i]));
        }
        return strBuffer.toString();
    }
}
