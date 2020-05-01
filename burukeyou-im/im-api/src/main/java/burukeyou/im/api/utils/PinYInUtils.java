package burukeyou.im.api.utils;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinYInUtils {

    private PinYInUtils(){}

    /**
     *  中文转拼音简写： 如周杰伦 => zjl
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word); //中文转拼音: 周 => zhou
            if (pinyinArray != null)
                convert += pinyinArray[0].charAt(0);
             else
                convert += word;
        }
        return convert;
    }


    public static String getFirstLetter(String str){
        String fl = String.valueOf(str.charAt(0));
        if(fl.matches("[a-zA-Z]"))
            return fl;
        String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(str.charAt(0));
        if (pinyinArr != null)
            return String.valueOf(pinyinArr[0].charAt(0));
        else
            return "other";
    }
}
