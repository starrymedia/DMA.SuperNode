package org.elastos.record.manage.util;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.elastos.dma.base.entity.ela.Account;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

public class KeystoreFile {

    private static String DefaultKeystoreFile = "keystore.dat";

    /**
     * 创建账户
     *
     * @param account
     */
    public static void createAccount(String account) throws IOException {
        try {
            File file = getKeystorePath();
            FileWriter FW = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(FW);
            writer.write(account);
            writer.close();
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * 读取账户
     *
     * @return
     */
    public static String getAccountPrivateKey(String password) {
        try {
            File file = getKeystorePath();
            String content = FileUtils.readFileToString(file, "UTF-8");
            JSONObject jsonObject = JSONObject.parseObject(content);
            String address = jsonObject.getString("address");
            String encryptedPrivateKey = jsonObject.getString("encryptedPrivateKey");
            byte[] salt = Base64.getDecoder().decode(jsonObject.getString("salt"));
            JSONObject scrypt = jsonObject.getJSONObject("scrypt");
            int n = scrypt.getInteger("n");
            byte[] gcmDecodedPrivateKey = Account.getGcmDecodedPrivateKey(encryptedPrivateKey, password, address, salt, n);
            String privateKey = Account.parsePrivateKey(gcmDecodedPrivateKey);
            return privateKey;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    /**
     * 获取keystore路径
     *
     * @return
     */
    public static File getKeystorePath() {
        try {
            File directory = new File("");
            String courseFile = directory.getCanonicalPath();
            System.out.println(courseFile);
            File file = new File(courseFile + "/" + DefaultKeystoreFile);
            return file;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    /**
     * keystore文件是否存在
     *
     * @return
     */
    public static boolean isExistKeystoreFile() {
        File file = getKeystorePath();
        if (file.exists()) return true;
        return false;
    }
}

