package gadmin;

import org.apache.commons.lang.StringUtils;

public class StringUtil extends StringUtils{

    public static final String LINE = "\r\n";
    public static final String TAB = "\t";

    /**
     * 对象转整数
     * @return 转换异常返回 0
     */
    public static Long toLong(String string) {
        try {
            return Long.parseLong(string);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 包含大写字母
     * @param string
     * @return
     */
    public static boolean containsUpperCase(String string){
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if ( c > 64 && c<91){// 大写字母
                return true;
            }
        }
        return false;
    }

    /**
     * 包含小写字母
     * @param string
     * @return
     */
    public static boolean containsLowerCase(String string){
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if ( c > 96 && c<123){// 小写字母
                return true;
            }
        }
        return false;
    }

    /**
     * 包含字母
     * @param string
     * @return
     */
    public static boolean containsAlphabet(String string){
        return containsLowerCase(string) || containsUpperCase(string);
    }

    /**
     * 包含数字
     * @param string
     * @return
     */
    public static boolean containsNumeric(String string){
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if ( c > 47 && c<58){// 数字
                return true;
            }
        }
        return false;
    }

    /**
     * 两个字符串相加（jsp页面使用）
     * @return
     */
    public static String concat(String str1,String str2){
        return str1 + str2;
    }

    /** 首位变大写 */
    public static String firstToUpperCase(String string){
        char[] result = new char[string.length()];
        result[0] = Character.toUpperCase(string.charAt(0));
        System.arraycopy(string.toCharArray(),1,result,1,string.length()-1);
        return new String(result);
    }

    // 十进制字符串得到36进制的字符串（压缩字符串）
    public static String longTo36Str(String str){
        StringBuilder sb = new StringBuilder(10);
        int size = str.length() / 2 * 2;
        int mod = str.length() % 2;
        if(size>0){
            for (int i = 0; i < size; i=i+2) {
                int a = Integer.valueOf(str.substring(i,i+2));
                sb.append(Integer.toString(a,36));
            }
        }
        if(mod>0){
            sb.append(str.substring(str.length()-1));
        }
        return sb.toString();
    }

    public static String longTo36Str(long num){
        String str = Long.toString(num);
        return longTo36Str(str);
    }
}