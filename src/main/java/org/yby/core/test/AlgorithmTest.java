package org.yby.core.test;

import org.yby.core.util.AlgorithmUtils;
import org.yby.core.util.FileHandUtils;

import java.util.HashMap;

/**
 * 测试算法的可行性
 */
public class AlgorithmTest {

    //测试案例
    private static final HashMap<String,String> DEMOS = new HashMap<>();

    private static final String originTxtPath = "D:\\Programmings\\IDEA_Programming\\PracticeProjects\\SoftwareHomework\\src\\main\\resources\\orig.txt";
    private static final String testTxtPath = "D:\\Programmings\\IDEA_Programming\\PracticeProjects\\SoftwareHomework\\src\\main\\resources\\orig_0.8_add.txt";

    private static final String outPutPath = "F:\\Temp\\ans.txt";

    private static void initData(){
        DEMOS.put(
                "认真对待每一天,因为它不会再回来。勇气不是没有恐惧,而是战胜了恐惧。",
                "认真对待每一天,因为它会再回来。勇气不是没有恐惧,不是战胜了恐惧。");
        DEMOS.put("活出自己的生命,而不是活成别人的模样。",
                "活出自己生命,活成别人的模样。");
        DEMOS.put("好事不必做准备，来了就来了，最多是个喜出望外。享用谁还不会；可对坏事总无思想准备，一旦突如其来，就会措手不及，天塌地陷。",
                "好事不必做准备，来了就来了，是个喜出望外。可对坏事总无思想准备，一旦突如其来，就会措手不及，天塌地陷。");
        DEMOS.put("夜幕降临，星星点点布满了夜空，月亮悄然升起，洒下一片温柔的光芒，照亮了整个夜晚。",
                "夜幕降临，星光闪烁，夜空璀璨夺目，皓月缓缓升起，洒下柔和光芒，照亮了整个夜晚。");
        DEMOS.put("这时野地里最热闹的，莫过于那些伶俐矫健的小麻雀了。",
                "这时野地里最不热闹的，莫过于那些伶俐矫健的大麻雀了吧。");
        DEMOS.put("日子总是像从指尖渡过的细纱，在不经意间悄然滑落。那些往日的忧愁和误用伤，在似水流年的荡涤下随波轻轻地逝去。",
                "日子总是像从指尖渡过的细纱，在不经意间悄然滑落，在似水流年的荡涤下随波轻轻地逝去。");
        DEMOS.put("人生中最大的乐趣是奉献；思维中最美的花朵是智慧之花；前进中最快的脚步是继续；朋友中最好的记忆是笑声！",
                "人生中最大的乐趣是奉献；思维中最美的花朵是智慧之花；可是，小心啊，切勿让其变幻了你评判的目光。");
        DEMOS.put("未来的东西就是这样：当你墨守成规时，它永远与你的昨天一样；当你积极进取时，就会化作灿烂无比的春光。",
                "未来的东西就是这样：当你积极进取时，就会化作灿烂无比的春光；当你墨守成规时，它永远与你的昨天一样。");
        DEMOS.put("人生中最艰难的是选择；工作中最困难的是创新；生活中最痛苦的是自扰；做人中最苦恼的是委屈。",
                "生活中最痛苦的是自扰；做人中最苦恼的是委屈；人生中最艰难的是选择；工作中最困难的是创新。");
        DEMOS.put("柔和的阳光斜挂在苍松翠柏不凋的枝叶上，显得那么安静肃穆",
                "柔和的阳光斜挂在苍松翠柏不凋的枝叶上，显得那么冷清高雅");
        DEMOS.put("重重叠叠的高山，看不见一个村庄，看不见一块稻田",
                "重重叠叠的高山，看不见一个村庄和一块稻田");
    }

    public static void test1(){
        initData();
        System.out.println("  正向检查   反向检查 ");
        DEMOS.forEach((k,v)->{
            String target = TextUtils.getFullCharacter(k);
            String checked = TextUtils.getFullCharacter(v);

            System.out.println(" "+ AlgorithmUtils.fuzzyMatch(target,checked)+" "+AlgorithmUtils.fuzzyMatch(checked,target));
        });
    }

    public static void test2(){
        String target = TextUtils.getFullCharacter(FileHandUtils.readAllSentence(originTxtPath));
        String checked = TextUtils.getFullCharacter(FileHandUtils.readAllSentence(testTxtPath));
        float rt = AlgorithmUtils.fuzzyMatch(target, checked);
        FileHandUtils.write(Float.toString(rt),outPutPath);
        System.out.println(rt);
        System.out.println(MathUtils.keep2b(rt));
    }
}