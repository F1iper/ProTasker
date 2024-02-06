package com.pro.tasker.category.service.unit;

import com.pro.tasker.category.entity.TaskCategory;
import com.pro.tasker.category.repository.TaskCategoryRepository;
import com.pro.tasker.category.service.impl.TaskCategoryServiceImpl;
import com.pro.tasker.exception.category.TaskCategoryNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskCategoryServiceTest {

    @Mock
    private TaskCategoryRepository categoryRepository;

    @InjectMocks
    private TaskCategoryServiceImpl categoryService;

    @Test
    void getAllCategories() {
        //given
        List<TaskCategory> categories = Arrays.asList(
                new TaskCategory(1L, "Category 1"),
                new TaskCategory(2L, "Category 2")
        );
        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<TaskCategory> result = categoryService.getAllCategories();

        //then
        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals(categories, result));
    }

    @Test
    void createCategory() {
        //given
        TaskCategory category = new TaskCategory(null, "New Category");

        when(categoryRepository.save(category)).thenReturn(category);

        //when
        TaskCategory createdCategory = categoryService.createCategory(category);

        //then
        assertAll(
                () -> assertEquals(category, createdCategory),
                () -> verify(categoryRepository, times(1)).save(any()));
    }

    @Test
    void updateCategory() {
        //given
        Long categoryId = 1L;
        TaskCategory updatedCategory = new TaskCategory(categoryId, "Updated Category");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(updatedCategory));
        when(categoryRepository.save(any(TaskCategory.class))).thenReturn(updatedCategory);

        //when
        TaskCategory result = categoryService.updateCategory(categoryId, updatedCategory);

        //then
        assertAll(
                () -> assertEquals(updatedCategory, result),
                () -> verify(categoryRepository, times(1)).findById(categoryId),
                () -> verify(categoryRepository, times(1)).save(updatedCategory));
    }

    @Test
    void updateCategory_NullCategoryId() {
        //given
        Long categoryId = null;
        TaskCategory updatedCategory = new TaskCategory(1L, "Updated Category");

        //when & then
        assertThrows(TaskCategoryNotFoundException.class, () -> categoryService.updateCategory(categoryId, updatedCategory));

        //verify
        assertAll(
                () -> verify(categoryRepository, times(1)).findById(any()),
                () -> verify(categoryRepository, never()).save(any(TaskCategory.class)));
    }

    @Test
    void deleteCategory() {
        //given
        Long categoryId = 1L;

        //when
        categoryService.deleteCategory(categoryId);

        //then
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}