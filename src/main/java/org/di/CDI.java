package org.di;

import org.di.mapper.CModule;

public final class CDI {

    private CDI() {
    }

    public static CDIFramework getFramework(final CModule cModule) {
        cModule.configure();
        return new CDIFramework(cModule);
    }
}
