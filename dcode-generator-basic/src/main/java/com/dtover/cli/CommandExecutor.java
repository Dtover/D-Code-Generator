package com.dtover.cli;

import com.dtover.cli.command.ConfigCommand;
import com.dtover.cli.command.GenerateCommand;
import com.dtover.cli.command.ListCommand;
import picocli.CommandLine;

@CommandLine.Command(name = "dcode", mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable{

    private final CommandLine commandLine;

    {
        commandLine = new CommandLine(this)
                .addSubcommand(new GenerateCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }

    @Override
    public void run() {
        // 不输入子命令时，给出提示
        System.out.println("输入 --help 查看命令提示");
    }

    /**
     * 执行命令
     */
    public Integer doExecute(String[] args){
        return commandLine.execute(args);
    }
}
