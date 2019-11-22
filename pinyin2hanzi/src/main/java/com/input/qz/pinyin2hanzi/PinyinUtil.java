package com.input.qz.pinyin2hanzi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 拼音工具类
 */
public class PinyinUtil {

    //拼音
    private static Set<String> pinyin = new HashSet<String>(Arrays.asList("gu", "qiao", "qian", "qve", "ge", "gang", "ga", "lian", "liao", "rou", "zong",
            "tu", "seng", "yve", "ti", "te", "jve", "ta", "nong", "zhang", "fan", "ma", "gua", "die", "gui",
            "guo", "gun", "sang", "diu", "zi", "ze", "za", "chen", "zu", "ba", "dian", "diao", "nei", "suo",
            "sun", "zhao", "sui", "kuo", "kun", "kui", "cao", "zuan", "kua", "den", "lei", "neng", "men",
            "mei", "tiao", "geng", "chang", "cha", "che", "fen", "chi", "fei", "chu", "shui", "me", "tuan",
            "mo", "mi", "mu", "dei", "cai", "zhan", "zhai", "can", "ning", "wang", "pie", "beng", "zhuang",
            "tan", "tao", "tai", "song", "ping", "hou", "cuan", "lan", "lao", "fu", "fa", "jiong", "mai",
            "xiang", "mao", "man", "a", "jiang", "zun", "bing", "su", "si", "sa", "se", "ding", "xuan",
            "zei", "zen", "kong", "pang", "jie", "jia", "jin", "lo", "lai", "li", "peng", "jiu", "yi", "yo",
            "ya", "cen", "dan", "dao", "ye", "dai", "zhen", "bang", "nou", "yu", "weng", "en", "ei", "kang",
            "dia", "er", "ru", "keng", "re", "ren", "gou", "ri", "tian", "qi", "shua", "shun", "shuo", "qun",
            "yun", "xun", "fiao", "zan", "zao", "rang", "xi", "yong", "zai", "guan", "guai", "dong", "kuai",
            "ying", "kuan", "xu", "xia", "xie", "yin", "rong", "xin", "tou", "nian", "niao", "xiu", "fo",
            "kou", "niang", "hua", "hun", "huo", "hui", "shuan", "quan", "shuai", "chong", "bei", "ben",
            "kuang", "dang", "sai", "ang", "sao", "san", "reng", "ran", "rao", "ming", "null", "lie", "lia",
            "min", "pa", "lin", "mian", "mie", "liu", "zou", "miu", "nen", "kai", "kao", "kan", "ka", "ke",
            "yang", "ku", "deng", "dou", "shou", "chuang", "nang", "feng", "meng", "cheng", "di", "de", "da",
            "bao", "gei", "du", "gen", "qu", "shu", "sha", "she", "ban", "shi", "bai", "nun", "nuo", "sen", "lve",
            "kei", "fang", "teng", "xve", "lun", "luo", "ken", "wa", "wo", "ju", "tui", "wu", "le", "ji", "huang",
            "tuo", "cou", "la", "mang", "ci", "tun", "tong", "ca", "pou", "ce", "gong", "cu", "lv", "dun", "pu",
            "ting", "qie", "yao", "lu", "pi", "po", "suan", "chua", "chun", "chan", "chui", "gao", "gan", "zeng",
            "gai", "xiong", "tang", "pian", "piao", "cang", "heng", "xian", "xiao", "bian", "biao", "zhua", "duan",
            "cong", "zhui", "zhuo", "zhun", "hong", "shuang", "juan", "zhei", "pai", "shai", "shan", "shao", "pan",
            "pao", "nin", "hang", "nie", "zhuai", "zhuan", "yuan", "niu", "na", "miao", "guang", "ne", "hai", "han",
            "hao", "wei", "wen", "ruan", "cuo", "cun", "cui", "bin", "bie", "mou", "nve", "shen", "shei", "fou", "xing",
            "qiang", "nuan", "pen", "pei", "rui", "run", "ruo", "sheng", "dui", "bo", "bi", "bu", "chuan", "qing",
            "chuai", "duo", "o", "chou", "ou", "zui", "luan", "zuo", "jian", "jiao", "sou", "wan", "jing", "qiong",
            "wai", "long", "yan", "liang", "lou", "huan", "hen", "hei", "huai", "shang", "jun", "hu", "ling", "ha", "he",
            "zhu", "ceng", "zha", "zhe", "zhi", "qin", "pin", "ai", "chai", "qia", "chao", "ao", "an", "qiu", "ni", "zhong",
            "zang", "nai", "nan", "nao", "chuo", "tie", "you", "nu", "nv", "zheng", "leng", "zhou", "lang", "e"));

    // 声母
    private static Set<String> shengmu = new HashSet<String>(Arrays.asList("b","p","m","f","d","t","n","l",
            "g","k","h","j","q","x","zh","ch","sh","r","z","c","s"));
    // 韵母
    private static Set<String> single_yunmu = new HashSet<String>(Arrays.asList("a", "o", "e", "i", "u", "v"));
    private static Set<String> complex_yunmu = new HashSet<String>(Arrays.asList("ai","ei","ui","ao","ou",
            "iu","ie","ve","er","an","en","in","un","ang","eng","ing","ong"));

    public static boolean isPinyin(String v){
        return pinyin.contains(v);
    }

    public static boolean isShengmu(String v){
        return shengmu.contains(v);
    }

    public static boolean isSingleYunmu(String v){
        return single_yunmu.contains(v);
    }


    public static boolean isComplexYunmu(String v){
        return complex_yunmu.contains(v);
    }

    public static boolean isYunmu(String v){
        return isSingleYunmu(v) || isComplexYunmu(v);
    }

    public static String getShengmu(String one_py){
        if(null==one_py||one_py.length()== 0){
            return null;
        }else if(one_py.length()==1) {
            if(isShengmu(one_py)){
                return one_py;
            }else{
                return null;
            }
        }else{
            if(isShengmu(one_py.substring(0,2))){
                return one_py.substring(0,2);
            }else if(isShengmu(one_py.substring(0,1))){
                return one_py.substring(0,1);
            } else{
                return null;
            }
        }
    }


}
