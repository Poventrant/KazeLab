package kaze.huaweioj;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            try {
                String expr = in.nextLine();
                expr = expr.replaceAll("\\{", "(");
                expr = expr.replaceAll("\\[", "(");
                expr = expr.replaceAll("}", ")");
                expr = expr.replaceAll("]", ")");
                System.out.println((int) engine.eval(expr));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}