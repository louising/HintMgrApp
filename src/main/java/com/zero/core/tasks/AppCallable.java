package com.zero.core.tasks;

import com.zero.hintmgr.ServiceException;

public interface AppCallable extends AppTask {
    Object run() throws Exception;
}
