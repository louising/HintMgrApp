package com.zero.core.tasks;

import com.zero.hintmgr.ServiceException;

public interface AppRunnable extends AppTask {
    void run() throws ServiceException;
}