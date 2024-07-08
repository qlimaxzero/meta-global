package com.xxx.core.util;

import org.junit.jupiter.api.Test;

class A1Test {

    @Test
    void test_bit_operator() throws Exception {

//        Web3j web3j = Web3j.build(new HttpService("https://data-seed-prebsc-1-s1.bnbchain.org:8545"));
//        CompletableFuture<Web3ClientVersion> future = web3j.web3ClientVersion().sendAsync();
//        future.whenComplete((x, e) -> {
//            String web3ClientVersion = x.getWeb3ClientVersion();
//            System.out.println("web3ClientVersion::" + web3ClientVersion);
//            System.out.println(e);
//        });
//
//        EthAccounts accounts = web3j.ethAccounts().send();
//        System.out.println(accounts.getAccounts());
        // 查询bnb余额
//        EthGetBalance send = web3j.ethGetBalance("0x87c1590a95afa0827f400319f439afea47447d19", DefaultBlockParameterName.LATEST).send();
//        System.out.println(send.getId() + " " + send.getBalance());

        /*Credentials credentials = Credentials.create("ec991af8562e387f61bb53d75997543570e8023a4df3ed245af0880970d70a68");
        DefaultGasProvider defaultGasProvider = new DefaultGasProvider();
        DPGContract dpg = DPGContract.load("0xe94C1DEB45eCdb26814d29F8a328711915E5e79B", web3j, credentials, defaultGasProvider);
        try {
            System.out.println("isValid::" + dpg.isValid());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        AssertUtil.isTrue(dpg.isValid(), ExceptionConstant.网络异常);

        CompletableFuture<BigInteger> totalSupplyFuture = dpg.totalSupply(new BigInteger("100")).sendAsync();
        totalSupplyFuture.whenComplete((x ,e) -> {
            String val = x.toString();
            System.out.println("val::" + val);
            System.out.println(e);
        });

        val send = dpg.exists(new BigInteger("100")).send();
        System.out.println("send = " + send);


        String owner = dpg.owner().send();
        CompletableFuture<TransactionReceipt> receiptCompletableFuture = dpg.mint(owner, BigInteger.ZERO, BigInteger.TEN, new byte[0]).sendAsync();
        receiptCompletableFuture.whenComplete((x, e) -> {
            String status = x.getStatus();
            String transactionHash = x.getTransactionHash();
            System.out.println("owner : " + owner);
            System.out.println("status : " + status);
            System.out.println("transactionHash : " + transactionHash);
        });*/

    }

}

