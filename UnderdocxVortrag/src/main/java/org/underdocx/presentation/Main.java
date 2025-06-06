package org.underdocx.presentation;

import org.apache.commons.lang3.ArrayUtils;
import org.underdocx.common.cli.UnderdocxEngineRunner;
import org.underdocx.environment.UnderdocxEnv;
import org.underdocx.presentation.extensions.Presentation;


public class Main {
    public static void main(String[] args) {
        UnderdocxEnv.getInstance().tryNotToScanIgnoredNodes = true;
        String engineProviderName = Presentation.class.getName();
        String[] newArgs = ArrayUtils.addFirst(args, engineProviderName);
        UnderdocxEngineRunner.main(newArgs);
        System.exit(0);
    }
}