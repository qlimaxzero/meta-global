package com.shitouren.core.service.web3j;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Service
public class Web3jContractServiceImpl implements Web3jContractService {

    public String getContractAddress() {
//        Web3j web3j = Web3j.build(new HttpService(""));
//        CompletableFuture<Web3ClientVersion> future = web3j.web3ClientVersion().sendAsync();
//        future.whenComplete((x, e) -> {
//            String web3ClientVersion = x.getWeb3ClientVersion();
//            System.out.println(web3ClientVersion);
//            log.error("", e);
//        });

//        Credentials credentials = Credentials.create("private_key");
//        DefaultGasProvider defaultGasProvider = new DefaultGasProvider();
//        DPGContract dpg = DPGContract.load("", web3j, credentials, defaultGasProvider);
//        try {
//            AssertUtil.isTrue(dpg.isValid(), ExceptionConstant.网络异常);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return "0x0";
    }

}
