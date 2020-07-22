package com.dumitru.taskmanager.process.comparator;

import com.dumitru.taskmanager.process.Process;

import java.util.Comparator;

public class PidProcessComparator implements Comparator<Process> {
    @Override
    public int compare(Process o1, Process o2) {
        return o1.getPid().compareTo(o2.getPid());
    }
}
