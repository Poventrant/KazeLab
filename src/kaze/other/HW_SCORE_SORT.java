package kaze.other;

import java.util.*;

/*
http://www.nowcoder.com/practice/8e400fd9905747e4acc2aeed7240978b?tpId=37&tqId=21291&rp=&ru=/ta/huawei&qru=/ta/huawei/question-ranking

119 0 txrevyi 2 bshbreg 25 wx 71 xriyt 41 br 47 rbuqqvjic 100 ndpfojuz 34 eexjhre 21 rhictee 28 kanht 22 ejichusgdu 10 sp 65 ocuddtcdyd 83 qvh 75 uomeundng 5 fgswtdcm 88 inqkf 87 zfhnko 83 ojdpujwy 35 wzgyi 93 l 80 gdmfmjl 12 f 31 fdni 67 ytnlbefa 10 vdfpbizm 13 yror 91 e 14 cx 72 wxf 41 g 1 aln 80 zchmnjicb 28 mfk 64 fosazenls 87 dhzbotlm 32 pqvpag 82 myqqawf 42 nmwrcxloi 18 tz 67 hsyiykb 29 jlp 63 gnremcj 89 zsuzmnrvl 14 cos 5 dt 15 mqulq 31 bdox 19 hvhhblb 91 ogtdl 67 hy 45 wmwksobqs 44 dt 25 okatppi 4 txsqycc 89 djbmtfr 49 n 28 cca 43 kp 27 prnljksw 26 jb 6 zqrzs 8 bqoskic 7 mvgsdkg 2 dqvqxestjh 38 lnz 98 fedd 56 jauvkbai 98 ewpwdmegb 26 oeisemtup 16 lgn 46 ihznixw 3 oia 57 i 90 phqfg 60 oxkymaj 60 w 58 oawquiu 89 hfeprrtdar 24 wg 37 gwvprlhig 74 x 45 ntyo 90 rm 76 gbxphisqps 96 rrlbkwtrzc 88 gc 21 pefjinrzk 13 nuo 86 jnhsz 31 to 26 b 43 bueymur 18 jujwv 18 kf 0 l 61 dcryalrimv 20 oz 24 cpapxy 96 lndq 73 mjd 44 nrybszapvs 70 jip 77 olsttnf 64 ypb 75 zrksdy 17 jnng 87 y 0 ntckc 72 mraolqz 28 o 43 uutgjfh 66 fkedaaeagz 30 ajkmuprxvf 55 flxaqayh 33 hiqozhrcr 11 gcjg 93 jfuesm 55 xxwn 62
 */
public class HW_SCORE_SORT {

    private static class Score {
        int index;
        int score;
        String name;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Comparator<Score> comparator0 = new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                boolean flag = o1.score>o2.score || (o1.score==o2.score&&o1.index<o2.index);
                return flag ? -1 : 1;
            }
        };
        Comparator<Score> comparator1 = new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                boolean flag = o1.score>o2.score || (o1.score==o2.score&&o1.index>o2.index);
                return flag ? 1 : -1;
            }
        };

        while(input.hasNextInt()) {
            int n = input.nextInt();
            // 0 表示高到低， 1表示低到高
            int sortType = input.nextInt();
            List<Score> list = new ArrayList<>();
            Score score;
            for (int i = 0; i < n; i++) {
                score = new Score();
                score.index = i;
                score.name = input.next();
                score.score = input.nextInt();
                list.add(score);
            }
            Comparator<Score> comparator = null;
            if(sortType == 0) {
                comparator = comparator0;
            } else {
                comparator = comparator1;
            }
            Collections.sort(list, comparator);
            for (int i = 0; i < list.size(); i++) {
                System.out.println((score = list.get(i)).name + " " + score.score);
            }
        }
    }

}


/*
case通过率为50.00%
测试用例:
28
1
qhsq 15
ozslg 79
ncttmtsphb 71
a 39
eeiuyzsj 34
nmlrokx 21
pjizylo 90
ec 45
f 12
sh 31
fm 25
ptprphubqk 29
wxdiwv 0
uhlcpjtxad 60
w 20
zwktbpun 70
efzfkf 69
v 31
rsnrgtl 73
lhdo 76
wt 56
mcdwd 14
ydrnoyd 37
gmlfds 76
zx 1
dqx 98
gz 90
kvbzrwrrjj 13

对应输出应该为:

wxdiwv 0
zx 1
f 12
kvbzrwrrjj 13
mcdwd 14
qhsq 15
w 20
nmlrokx 21
fm 25
ptprphubqk 29
sh 31
v 31
eeiuyzsj 34
ydrnoyd 37
a 39
ec 45
wt 56
uhlcpjtxad 60
efzfkf 69
zwktbpun 70
ncttmtsphb 71
rsnrgtl 73
lhdo 76
gmlfds 76
ozslg 79
pjizylo 90
gz 90
dqx 98

你的输出为:

wxdiwv 0
zx 1
f 12
kvbzrwrrjj 13
mcdwd 14
qhsq 15
w 20
nmlrokx 21
fm 25
ptprphubqk 29
v 31
 */