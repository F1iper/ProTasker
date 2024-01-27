package com.pro.tasker.category.service.impl;

import com.pro.tasker.category.entity.TaskCategory;
import com.pro.tasker.category.repository.TaskCategoryRepository;
import com.pro.tasker.category.service.TaskCategoryService;
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
        return categoryRepository.save(category);
    }

    @Override
    public TaskCategory updateCategory(Long categoryId, TaskCategory updatedCategory) {
        // todo: Implement update logic
        return null;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
