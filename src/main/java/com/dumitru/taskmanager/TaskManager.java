package com.dumitru.taskmanager;

import com.dumitru.taskmanager.exception.ProcessLimitExceededException;
import com.dumitru.taskmanager.process.Priority;
import com.dumitru.taskmanager.process.Process;

import java.util.List;

public interface TaskManager {
    int MAX_CAPACITY = 5;

    void add(Process process) throws ProcessLimitExceededException;

    void kill(Process process);

    void killGroup(Priority priority);

    void killAll();

    List<Process> list();

}
