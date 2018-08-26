package com.haimian.bl.web3j;

import com.haimian.bl.bean.UserBean;
import com.haimian.bl.config.ProductionConfig;
import org.springframework.stereotype.Component;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

import java.io.IOException;
import java.math.BigInteger;

@Component
public class Web3jOpera {

    private static Web3j web3j;
    static {
         web3j = Web3j.build(new HttpService(ProductionConfig.ethNetworkAddress()));
     }

    //获取证书
    private Credentials getCredentials() throws IOException, CipherException {
//        Credentials credentials =
//                WalletUtils.loadCredentials(ProductionConfig.ownerPassword(), ProductionConfig.keyStorePath());
          Credentials credentials =
                  Credentials.create("321b51cd5561646b5f1b06f4289abaf37a4a95918736b07d88af4baea63f8694");
        return credentials;
    }

    public boolean createUser(UserBean userBean, String reallPassword)  {
        System.out.println("获取了UserBean" + userBean.getEth_address());
//        System.out.println("ownerPassword" + ProductionConfig.ownerPassword());
//        System.out.println("keyStorePath" + ProductionConfig.keyStorePath());

        Credentials credentials = null;
        try {
            credentials = getCredentials();
            SolidityReputation solidityReputation = SolidityReputation.load(ProductionConfig.contractAddress(), web3j, credentials, ManagedTransaction.GAS_PRICE,Contract.GAS_LIMIT);
            System.out.println("地址:" + userBean.getEth_address() );
            System.out.println("身份证号:" + userBean.getId_card() );
            System.out.println("密码：:" + reallPassword );
            solidityReputation.ownerSetUser(userBean.getEth_address(), userBean.getId_card(), reallPassword).send();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean ownerSetScore(UserBean userBean, BigInteger score) throws Exception {
        Credentials credentials = getCredentials();
        SolidityReputation solidityReputation = SolidityReputation.load(ProductionConfig.contractAddress(), web3j, credentials, ManagedTransaction.GAS_PRICE,Contract.GAS_LIMIT);
        solidityReputation.ownerSetScore(userBean.getEth_address(),score).send();
        return true;
    }

    public Integer ownGetScore(String address) throws Exception {
        Credentials credentials = getCredentials();
        SolidityReputation solidityReputation = SolidityReputation.load(ProductionConfig.contractAddress(), web3j, credentials, ManagedTransaction.GAS_PRICE,Contract.GAS_LIMIT);
        BigInteger score = solidityReputation.ownerGetScore(address).send();
        Integer intScore = new Integer(String.valueOf(score));
        return intScore;
    }


}
