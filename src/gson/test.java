package gson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Account.class, "users","account");

        Account account = new Account();
        account.setAccount("123456");
        account.setPassword("321645");
        account.setUsers(Arrays.asList(new User(1, "test"), new User(2, "pwq")));

        String json = JSON.toJSONString(account, filter);
        System.out.println(json.hashCode());
        String json2 = JSON.toJSONString(account, filter);
        System.out.println(json2.hashCode());

        String mdStr = "{\"branch\":\"trunk\",\"changeAt\":1453099597000,\"changeBy\":\"89247\",\"createAt\":1453099597000,\"createBy\":\"89247\",\"credentials\":\"7cecf791-1e52-4bff-83c6-e0a9caac4683\",\"disable\":false,\"entityId\":1,\"id\":1,\"labelId\":11,\"packComponents\":[],\"packSwitchs\":[],\"persistent\":true,\"repositoryType\":\"svn\",\"revision\":\"HEAD\",\"shellCmd\":\"cd ..\",\"templateName\":\"基线模板\",\"url\":\"https://svn.ucweb.local\"}";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(mdStr.getBytes());
            System.out.println( convertToHexString(md5.digest()) );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    static String convertToHexString(byte data[]) {
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            strBuffer.append(Integer.toHexString(0xff & data[i]));
        }
        return strBuffer.toString();
    }
}
