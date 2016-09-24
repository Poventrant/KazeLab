package kaze.findJob.HW;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = null;
        Pattern namep = Pattern.compile("NAME=([^;|,]*)"),
                mp = Pattern.compile("MATH=([\\d]*)"),
                lp = Pattern.compile("LANG=([\\d]*)");
        String math = null, lang = null, name = null;
        List<Integer> mathList = new ArrayList<>(), langList = new ArrayList<>(), totalList = new ArrayList<>();
        Map<String, Student> map = new HashMap<>();
        while (in.hasNextLine()) {
            line = in.nextLine();
            Matcher m = namep.matcher(line);
            if (m.find()) {
                name = m.group(1);
            }
            if (line.startsWith("LST GRADE:NAME=")) {
                break;
            } else if (line.startsWith("LOD GRADE:NAME=")) {
                m = mp.matcher(line);
                if (m.find()) {
                    math = m.group(1);
                }
                m = lp.matcher(line);
                if (m.find()) {
                    lang = m.group(1);
                }
                int mgrade = Integer.valueOf(math), lgrade = Integer.valueOf(lang), total = mgrade + lgrade;

                mathList.add(mgrade);
                langList.add(lgrade);
                totalList.add(total);
                map.put(name, new Student(mgrade, lgrade));

            }
        }
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        };
        Collections.sort(mathList, comparator);
        Collections.sort(langList, comparator);
        Collections.sort(totalList, comparator);
        Student target = map.get(name);
        int total = 0;
        System.out.println(name + " " + target.mathGrade + " " + target.langGrade + " " +
                (total = target.mathGrade + target.langGrade) + " " + (mathList.indexOf(target.mathGrade) + 1) +
                " " + (langList.indexOf(target.langGrade) + 1) + " " + (totalList.indexOf(total) + 1));
    }

    static class Student {
        int mathGrade, langGrade;

        public Student(int mgrade, int lgrade) {
            mathGrade = mgrade;
            langGrade = lgrade;
        }
    }
}
