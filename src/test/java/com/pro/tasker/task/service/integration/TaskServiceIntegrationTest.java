package com.pro.tasker.task.service.integration;


import com.pro.tasker.task.entity.Task;
import com.pro.tasker.task.entity.TaskStatus;
import com.pro.tasker.task.repository.TaskRepository;
import com.pro.tasker.task.service.TaskService;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceIntegrationTest {

    @ClassRule
    public static PostgreSQLContainer postgres = new PostgreSQLContainer(
            "postgres:14.9")
            .withDatabaseName("protasker")
            .withUsername("postgres")
            .withPassword("admin");


    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void shouldCreateAndRetrieveTask() {
        //given
        Task taskToCreate = new Task(null, "test task", "description", TaskStatus.BACKLOG);

        //when
        Task createdTask = taskService.createTask(taskToCreate);

        //then
        Assertions.assertAll(
                () -> assertNotNull(createdTask.getId()),
                () -> assertEquals(taskToCreate.getTitle(), createdTask.getTitle()),
                () -> assertEquals(taskToCreate.getDescription(), createdTask.getDescription())
        );
    }
}
