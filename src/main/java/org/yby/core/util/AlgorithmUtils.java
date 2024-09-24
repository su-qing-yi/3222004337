package org.yby.core.util;

/**
 * 这个类用来设计查重算法需要用到的算法
 */
public final class AlgorithmUtils {
    //工具类
    private AlgorithmUtils(){}


    /**
     * 匹配两个字符串的匹配程度
     * @param checked 被匹配字符串
     * @param target 目标匹配字符串
     * @return 两个字符串的匹配程度
     */
    public static float fuzzyMatch(String target,String checked){
        /* 算法过程 权重取[0.2 0.7 0.1]
             1.统计两个字符相同字数的数量占据target的百分比
             2.统计连续相同的字符数量(字符数>=2)占据target的百分比
             3.比较两个字符的长度，如果长度差越大，那么两个字符串一致的概率越小
         */

        int targetLen = target.length();
        int checkedLen = checked.length();

        //System.out.println("target总长度:"+targetLen+" checked总长度:"+checkedLen);

        float[] items = new float[3];

        //统计item1
        for (int i = 0; i < targetLen; i++) {
            for (int j = 0; j < checkedLen; j++) {
                if(target.charAt(i) == checked.charAt(j)){
                    items[0]++;
                    break;
                }
            }
        }
        //System.out.println("相同字的数量："+items[0]);
        items[0] = items[0] / targetLen;

        //统计item2
        items[1] = findAllTheSameStrLength(target,checked);
        //System.out.println("相同词组的数量："+items[1]);
        items[1] = items[1] / targetLen;

        //统计item3
        items[2] = Math.abs(targetLen - checkedLen) / (float)targetLen;


        //标准化
//        MathUtils.standardization(items);
//        MathUtils.normalization(items);
//        System.out.println(Arrays.toString(items));

        float rt = items[0] * 0.4f + items[1] * 0.6f - items[2]*0.1f;
        return  rt<0 ? 0.001f : rt>1 ? 0.99f : rt;
    }

    /**
     * 找出str1与str2所有完全一样的字符子串（每个子串不能再含有子串）
     * @return 字符子串数量的总长度
     */
    public static int findAllTheSameStrLength(String str1, String str2){
        String max = str1.length()>str2.length() ? str1 : str2;
        String min = max.equals(str1) ? str2 : str1;
        int minIdx=0,maxIdx=0,result=0;
        while (minIdx<min.length()){
            int temp = minIdx;
            if(minIdx+1<min.length() && maxIdx+1<max.length() && min.charAt(minIdx) == max.charAt(maxIdx) && min.charAt(minIdx+1) == max.charAt(maxIdx+1)){
                maxIdx+=2; minIdx+=2;
                while (minIdx<min.length() && maxIdx<max.length() && min.charAt(minIdx) == max.charAt(maxIdx)){
                    maxIdx++;minIdx++;
                }
            }
            if(minIdx!=temp){
                result+=minIdx-temp;
            }
            if(++maxIdx > max.length()){maxIdx=minIdx;minIdx++;}
        }

        return result;
    }
}
