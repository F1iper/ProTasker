package com.pro.tasker.task.service.integration;


import com.pro.tasker.task.entity.Task;
import com.pro.tasker.task.entity.TaskStatus;
import com.pro.tasker.task.repository.TaskRepository;
import com.pro.tasker.task.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
public class TaskServiceIntegrationTest {

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres")
            .withDatabaseName("protasker")
            .withUsername("postgres")
            .withPassword("admin");

//    @Autowired
//    private TaskService taskService;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("postgres", postgres::getUsername);
        registry.add("admin", postgres::getPassword);
    }
    @Autowired
    private TaskRepository taskRepository;


    @BeforeEach
    void setUp() {
        List<Task> taskList = List.of(new Task(null, "title", "description", TaskStatus.BACKLOG));
        taskRepository.saveAll(taskList);
    }

    @Test
    void shouldReturnTaskByTitle() {
        Task task = taskRepository.findByTitle("title");
        assertThat(task).isNotNull();
    }

    @Test
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();

    }

    //    @Test
//    public void shouldCreateAndRetrieveTask() {
//        //given
//        Task taskToCreate = new Task(null, "test task", "description", TaskStatus.BACKLOG);
//
//        //when
//        Task createdTask = taskService.createTask(taskToCreate);
//
//        //then
//        assertAll(
//                () -> Assertions.assertNotNull(createdTask.getId()),
//                () -> Assertions.assertEquals(taskToCreate.getTitle(), createdTask.getTitle()),
//                () -> Assertions.assertEquals(taskToCreate.getDescription(), createdTask.getDescription())
//        );
//    }
}
