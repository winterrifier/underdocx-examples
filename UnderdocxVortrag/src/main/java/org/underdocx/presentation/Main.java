package org.underdocx.presentation;

import org.apache.commons.lang3.ArrayUtils;
import org.underdocx.common.cli.UnderdocxEngineRunner;
import org.underdocx.environment.UnderdocxEnv;
import org.underdocx.presentation.extensions.Presentation;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        boolean debug = args.length > 0 && args[0].equals("debug");
        UnderdocxEnv.getInstance().tryNotToScanIgnoredNodes = true;
        UnderdocxEnv.getInstance().isDebug = debug;
        String engineProviderName = Presentation.class.getName();
        String[] newArgs = !debug
                ? ArrayUtils.addFirst(args, engineProviderName)
                : ArrayUtils.addFirst(Arrays.copyOfRange(args, 1, args.length), engineProviderName);
        UnderdocxEngineRunner.main(newArgs);
        System.exit(0);
    }
}