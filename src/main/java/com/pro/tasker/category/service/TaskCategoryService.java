package com.pro.tasker.category.service;

import com.pro.tasker.category.entity.TaskCategory;

import java.util.List;

public interface TaskCategoryService {

    List<TaskCategory> getAllCategories();

    TaskCategory createCategory(TaskCategory category);

    TaskCategory updateCategory(Long categoryId, TaskCategory updatedCategory);

    void deleteCategory(Long categoryId);
}
