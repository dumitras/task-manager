package com.dumitru.taskmanager;

import com.dumitru.taskmanager.exception.ProcessLimitExceededException;
import com.dumitru.taskmanager.process.Priority;
import com.dumitru.taskmanager.process.Process;
import com.dumitru.taskmanager.process.comparator.TimestampProcessComparator;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;


public class BaseTaskManager implements TaskManager {
    final Logger logger = Logger.getLogger(BaseTaskManager.class.getName());
    protected final LinkedList<Process> processes = new LinkedList<>();

    public void add(Process process) throws ProcessLimitExceededException {
        if (isAtMaxCapacity()) {
            logger.info(String.format("Failed to add process with pid=%s", process.getPid()));
            throw new ProcessLimitExceededException(String.format("Maximum number of %d processes is reached.", MAX_CAPACITY));
        }
        processes.add(process);
    }

    @Override
    public void kill(Process process) {
        processes.remove(process);
        process.kill();
    }

    @Override
    public void killGroup(Priority priority) {
        processes.removeIf(p -> p.getPriority().equals(priority));
    }

    @Override
    public void killAll() {
        for (Process p : processes) {
            p.kill();
        }
        processes.clear();
    }

    @Override
    public List<Process> list() {
        List<Process> orderedProcesses = new LinkedList<>(processes);
        orderedProcesses.sort(new TimestampProcessComparator());
        return orderedProcesses;
    }

    protected void replaceProcess(Process newProcess, Process oldProcess) {
        logger.info(String.format("Replacing process %s with %s", oldProcess.getPid(), newProcess.getPid()));
        kill(oldProcess);
        try {
            add(newProcess);
        } catch (ProcessLimitExceededException e) {
            logger.info(String.format("Failed to add new process: %s", e.getMessage()));
        }
    }

    public LinkedList<Process> getProcesses() {
        return processes;
    }

    private boolean isAtMaxCapacity() {
        return processes.size() == MAX_CAPACITY;
    }
}
