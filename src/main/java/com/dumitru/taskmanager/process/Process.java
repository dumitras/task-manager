package com.dumitru.taskmanager.process;

import java.util.UUID;

public final class Process {

    private final UUID pid;
    private final Priority priority;
    private final long creationTimestamp;


    public Process(Priority priority) {
        this.pid = UUID.randomUUID();
        this.priority = priority;
        this.creationTimestamp = System.nanoTime();
    }

    public UUID getPid() {
        return pid;
    }

    public Priority getPriority() {
        return priority;
    }

    public void kill() {
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    @Override
    public String toString() {
        return "Process{" +
                "pid=" + pid +
                ", priority=" + priority +
                ", creationTimestamp=" + creationTimestamp +
                '}';
    }
}

