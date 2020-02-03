package com.bigdata.coreweb.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.alibaba.fastjson.JSONArray;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ProvincePlatformCodeUtil {
    //加密盐值
    private static String salt() {
        String salt = "auth";//盐值
        return salt;
    }

    //加密密码
    private static String encryptPasswd(String account, String userpasswd) {
        int hashIterations = 2;//加密的次数
        String salt = salt();//盐值
        Object credentials = userpasswd;//密码
        String hashAlgorithmName = "MD5";//加密方式
        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        String password = simpleHash.toString();
        return password;
    }

    public static void main(String[] args) {
//        String pwd = "aa111111";
//        String telephone = "18842641866";
//        System.out.println("明文密码------>" + pwd);
//        System.out.println("加密后的passwd值----->" + encryptPasswd("", pwd));
//        System.out.println("加密后的telephont值----->" + encryptPasswd("", telephone));
//
//        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdbjXagBUfYQcxnUD/kA1v3yYeCm7jh6C5R9qwJkSiMpgPrJQB/GQnEU/P7ZcaYF0okysOxflQ+1k0MBms9c4V78H4IZs0nCnzbSByH3v5AtxIHR1K6R3rr6xAkp7E6f09SzyB//lXXMnrdmOEtHnUyYb5j6zit4vSl8VeQzIS1QIDAQAB";
//        RSA rsa = new RSA(null, publicKey);
//        byte[] byte1 = rsa.encrypt(StrUtil.bytes("18842641866", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
////        String str = StrUtil.str(byte1, CharsetUtil.defaultCharset());
////        String sss = new String(byte1,"utf-8");
//        String encode = Base64.encode(byte1);
//
//        System.out.println(encode);
////        Console.log("rsa 加密的telephone0 :",encode);
        Map<String, Object> map = new HashMap<String, Object>();
        String idCard=rsaByPublicKey("1111111");
        String tel=rsaByPublicKey("187921224111188348");
        String passwd=md5Encode("aa11112111");
        String name="1212";
        map.put("username", name);
        map.put("userpasswd", passwd);
        map.put("credentials_number", idCard);
        map.put("mobile_telephone", tel);
        ArrayList<HashMap<String, Object>> userInfos = new ArrayList<>();

//        JSONObject json = new JSONObject(userInfos);
        JSONArray objects = new JSONArray(Collections.singletonList(userInfos));
        String s = objects.toJSONString();
        Console.log(s);


    }

    /**
     * 省政务平台MD5 加密
     *
     * @param value
     * @return
     */
    public static String md5Encode(String value) {
        return encryptPasswd("", value);
    }

    /**
     * 使用省政务平台key rsa 加密value
     *
     * @return
     */
    public static String rsaByPublicKey(String value) {
        /**
         * 省publickey
         */
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdbjXagBUfYQcxnUD/kA1v3yYeCm7jh6C5R9qwJkSiMpgPrJQB/GQnEU/P7ZcaYF0okysOxflQ+1k0MBms9c4V78H4IZs0nCnzbSByH3v5AtxIHR1K6R3rr6xAkp7E6f09SzyB//lXXMnrdmOEtHnUyYb5j6zit4vSl8VeQzIS1QIDAQAB";
        RSA rsa = new RSA(null, publicKey);
        byte[] byte1 = rsa.encrypt(StrUtil.bytes(value, CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        String encode = Base64.encode(byte1);
        return encode;
    }
}
