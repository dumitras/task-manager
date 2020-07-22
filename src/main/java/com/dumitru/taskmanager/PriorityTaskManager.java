package com.dumitru.taskmanager;

import com.dumitru.taskmanager.exception.ProcessLimitExceededException;
import com.dumitru.taskmanager.process.Process;

import java.util.Comparator;
import java.util.LinkedList;


public class PriorityTaskManager extends BaseTaskManager {

    @Override
    public void add(Process process) {
        try {
            super.add(process);
        } catch (ProcessLimitExceededException e) {
            Process oldestLowestPriorityProcess = getOldestWithLowestPriority();
            if (isProcessPriorityHigher(process, oldestLowestPriorityProcess)) {
                replaceProcess(process, oldestLowestPriorityProcess);
            }
        }
    }

    private Process getOldestWithLowestPriority() {
        LinkedList<Process> processes = getProcesses();
        Comparator<Process> processComparator = Comparator.comparing(Process::getPriority)
                .thenComparing(Process::getCreationTimestamp);
        processes.sort(processComparator);
        return processes.getFirst();
    }

    private boolean isProcessPriorityHigher(Process process1, Process process2) {
        return process1.getPriority().ordinal() > (process2.getPriority()).ordinal();
    }


}
