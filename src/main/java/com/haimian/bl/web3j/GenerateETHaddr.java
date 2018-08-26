package com.haimian.bl.web3j;

import com.haimian.bl.bean.UserBean;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;

public class GenerateETHaddr {

    public static UserBean createAccount(String password) {
        String filePath = "C:\\Users\\Administrator\\Desktop\\web3j\\keyStore";
        try {
            String fileName = WalletUtils.generateNewWalletFile(password, new File(filePath), false);
            String finalPath = filePath + "\\" + fileName;
            System.out.println("keystore名字" + fileName);
            Credentials gredentials = WalletUtils.loadCredentials(password, filePath + "\\" + fileName);
            System.out.println("以太坊地址" + gredentials.getAddress());
            System.out.println("以太坊公钥" + gredentials.getEcKeyPair().getPublicKey().toString(16));
            System.out.println("以太坊私钥" + gredentials.getEcKeyPair().getPrivateKey().toString(16) );

//            编码成Base64
//            String strPriv = gredentials.getEcKeyPair().getPrivateKey().toString();
//            byte[] encodeBase64 = Base64.getEncoder().encode(strPriv.getBytes());
//            System.out.println(new String(encodeBase64));
            UserBean userBean = new UserBean();
            userBean.setEth_address(gredentials.getAddress());
            userBean.setEth_key_store(filePath + "\\" + fileName);
            userBean.setEth_private_key(gredentials.getEcKeyPair().getPrivateKey().toString(16));
            userBean.setEth_public_key(gredentials.getEcKeyPair().getPublicKey().toString(16));

            return userBean;
        }  catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }

    public static void main(String[] args) {
        createAccount("wo");

//        String str = "Hello World";
//        byte[] x = str.getBytes();
//
//        byte[] encodeBase64 = Base64.getEncoder().encode(x);
//        System.out.println( new String(encodeBase64));
    }
}


