package com.pro.tasker.task.service;

import com.pro.tasker.task.entity.Task;
import com.pro.tasker.task.entity.TaskStatus;
import com.pro.tasker.task.repository.TaskRepository;
import com.pro.tasker.task.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    public void shouldRetrieveAllTasksFromDB() {
        //given
        List<Task> taskList = new ArrayList<>();
        Task task1 = new Task(1L, "task 1 title", "task 1 description", TaskStatus.BACKLOG);
        Task task2 = new Task(2L, "task 2 title", "task 2 description", TaskStatus.DONE);
        taskList.add(task1);
        taskList.add(task2);

        //when
        when(taskRepository.findAll()).thenReturn(taskList);
        List<Task> expectedTasks = taskService.getAllTasks();

        //then
        assertAll(
                () -> assertEquals(taskList.size(), expectedTasks.size()),
                () -> assertEquals(taskList.get(1).getStatus(), expectedTasks.get(1).getStatus()),
                () -> assertEquals(taskList.get(0).getDescription(), expectedTasks.get(0).getDescription()));
    }

    @Test
    public void shouldRetrieveTaskById() {
        //given
        long taskId = 1L;
        Task task = new Task(taskId, "task title", "task description", TaskStatus.IN_PROGRESS);

        //when
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        Task retrievedTask = taskService.getTaskById(taskId);

        //then
        assertEquals(task, retrievedTask);
    }

    @Test
    public void shouldSaveTask() {
        //given
        Task taskToSave = new Task(null, "new task title", "new task description", TaskStatus.BACKLOG);
        Task savedTask = new Task(1L, "new task title", "new task description", TaskStatus.BACKLOG);

        //when
        when(taskRepository.save(taskToSave)).thenReturn(savedTask);
        Task result = taskService.createTask(taskToSave);

        //then
        assertAll(
                () -> assertEquals(savedTask, result),
                () -> assertEquals(savedTask.getDescription(), result.getDescription()));
    }
}