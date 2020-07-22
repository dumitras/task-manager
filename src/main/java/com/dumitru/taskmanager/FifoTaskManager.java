package com.dumitru.taskmanager;

import com.dumitru.taskmanager.exception.ProcessLimitExceededException;
import com.dumitru.taskmanager.process.Process;

public class FifoTaskManager extends BaseTaskManager {

    @Override
    public void add(Process newProcess) {
        try {
            super.add(newProcess);
        } catch (ProcessLimitExceededException e) {
            replaceProcess(newProcess, getProcesses().getFirst());
        }
    }
}
