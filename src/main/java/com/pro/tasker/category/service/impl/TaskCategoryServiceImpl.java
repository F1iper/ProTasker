package com.pro.tasker.category.service.impl;

import com.pro.tasker.category.entity.TaskCategory;
import com.pro.tasker.category.repository.TaskCategoryRepository;
import com.pro.tasker.category.service.TaskCategoryService;
import com.pro.tasker.exception.category.TaskCategoryCreationException;
import com.pro.tasker.exception.category.TaskCategoryDeletionException;
import com.pro.tasker.exception.category.TaskCategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCategoryServiceImpl implements TaskCategoryService {

    private final TaskCategoryRepository categoryRepository;

    @Autowired
    public TaskCategoryServiceImpl(TaskCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<TaskCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public TaskCategory createCategory(TaskCategory category) {
        try {
            return categoryRepository.save(category);
        } catch (TaskCategoryCreationException e) {
            throw new TaskCategoryCreationException("Error creating task category", e);
        }
    }

    @Override
    public TaskCategory updateCategory(Long categoryId, TaskCategory updatedCategory) {
        TaskCategory existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new TaskCategoryNotFoundException(categoryId));

        existingCategory.setName(updatedCategory.getName());

        try {
            return categoryRepository.save(existingCategory);
        } catch (TaskCategoryCreationException e) {
            throw new TaskCategoryCreationException("Error updating task category", e);
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        try {
            categoryRepository.deleteById(categoryId);
        } catch (TaskCategoryDeletionException e) {
            throw new TaskCategoryDeletionException("Error deleting task category with ID: [" + categoryId + "]", e);
        }
    }
}
