package com.dtover.cli.example;

import picocli.CommandLine;

import java.util.concurrent.Callable;

public class Login implements Callable<Integer> {

    @CommandLine.Option(names = {"-u", "--user"}, description = "User name")
    String user;

    @CommandLine.Option(names = {"-p", "--password"}, description = "Passphrase", interactive = true, arity = "0..1")
    String password;

    public Integer call() throws Exception {
        if(!"123".equals(password)){
            System.out.println("Wrong Password!");
        }else{
            System.out.println("Correct!");
        }
        return 0;
    }

    public static void main(String[] args) {
        new CommandLine(new Login()).execute("-u", "dtover", "-p");
    }
}
