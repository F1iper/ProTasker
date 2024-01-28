package com.pro.tasker.task.service;

import com.pro.tasker.task.entity.Task;
import com.pro.tasker.task.entity.TaskStatus;
import com.pro.tasker.task.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void shouldRetrieveAllTasksFromDB() {
        //given
        List<Task> taskList = new ArrayList<>();
        Task task1 = new Task(1L, "task 1 title", "task 1 description", TaskStatus.BACKLOG);
        Task task2 = new Task(2L, "task 2 title", "task 2 description", TaskStatus.DONE);
        taskList.add(task1);
        taskList.add(task2);

        //when
        List<Task> expectedTasks = taskService.getAllTasks();

        //then
        Assertions.assertAll(
                () -> assertEquals(taskList.size(), expectedTasks.size()),
                () -> assertEquals(taskList.get(0).getDescription(), expectedTasks.get(0).getDescription()));
    }
}