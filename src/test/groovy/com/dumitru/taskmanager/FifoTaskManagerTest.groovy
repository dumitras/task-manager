package com.dumitru.taskmanager

import com.dumitru.taskmanager.process.Priority
import com.dumitru.taskmanager.process.Process

class FifoTaskManagerTest extends BaseTaskManagerTest {


    def 'add process test'() {
        setup:
        taskManager = setupProcessList(max_capacity - 1)
        def process = new Process(Priority.MEDIUM)

        when:
        taskManager.add(process)

        then:
        max_capacity == taskManager.getProcesses().size()
        process == taskManager.getProcesses().getLast()
    }

    def 'add process replace oldest process when maximum capacity is reached test'() {
        given:
        def process = new Process(Priority.MEDIUM)
        taskManager = setupProcessList(max_capacity)
        def oldestProcess = taskManager.getProcesses().getFirst()

        when:
        taskManager.add(process)

        then:
        !taskManager.getProcesses().contains(oldestProcess)
        taskManager.getProcesses().contains(process)
    }

    private FifoTaskManager setupProcessList(int size) {
        def taskManager = new FifoTaskManager()
        def index = 0
        while (taskManager.getProcesses().size() < size) {
            taskManager.add(processList.get(index))
            index++
        }
        taskManager
    }
}
