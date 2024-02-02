package com.pro.tasker.exception.handler;

import com.pro.tasker.exception.category.TaskCategoryCreationException;
import com.pro.tasker.exception.category.TaskCategoryDeletionException;
import com.pro.tasker.exception.category.TaskCategoryNotFoundException;
import com.pro.tasker.exception.priority.TaskPriorityCreationException;
import com.pro.tasker.exception.priority.TaskPriorityDeletionException;
import com.pro.tasker.exception.task.TaskCreationException;
import com.pro.tasker.exception.task.TaskDeletionException;
import com.pro.tasker.exception.task.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskCreationException.class)
    public ResponseEntity<String> handleTaskCreationException(TaskCreationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TaskDeletionException.class)
    public ResponseEntity<String> handleTaskDeletionException(TaskDeletionException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TaskPriorityCreationException.class)
    public ResponseEntity<String> handleTaskPriorityCreationException(TaskPriorityCreationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TaskPriorityDeletionException.class)
    public ResponseEntity<String> handleTaskPriorityDeletionException(TaskPriorityDeletionException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TaskCategoryCreationException.class)
    public ResponseEntity<String> handleTaskCategoryCreationException(TaskCategoryCreationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TaskCategoryDeletionException.class)
    public ResponseEntity<String> handleTaskCategoryDeletionException(TaskCategoryDeletionException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TaskCategoryNotFoundException.class)
    public ResponseEntity<String> handleTaskCategoryNotFoundException(TaskCategoryNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}