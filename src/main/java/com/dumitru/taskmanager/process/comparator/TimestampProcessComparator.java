package com.dumitru.taskmanager.process.comparator;

import com.dumitru.taskmanager.process.Process;

import java.util.Comparator;

public class TimestampProcessComparator implements Comparator<Process> {
    @Override
    public int compare(Process o1, Process o2) {
        return (int) (o1.getCreationTimestamp() - o2.getCreationTimestamp());
    }
}
