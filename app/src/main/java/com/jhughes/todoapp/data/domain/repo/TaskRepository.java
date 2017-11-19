package com.jhughes.todoapp.data.domain.repo;

import com.jhughes.todoapp.data.domain.model.Task;
import com.jhughes.todoapp.data.local.repo.TaskDataSource;

import java.util.List;

public class TaskRepository {

    // TODO: 19/11/2017 do local list of data to represent cache in the domain

    private final TaskDataSource taskDataSource;

    public TaskRepository(TaskDataSource taskDataSource) {
        this.taskDataSource = taskDataSource;
    }

    public void getTasks(final GetTasksCallback callback) {

        taskDataSource.getTasks(new GetTasksCallback() {
            @Override
            public void onComplete(List<Task> tasks) {
                callback.onComplete(tasks);
            }
        });
    }

    public void getTask(int taskId, final GetTaskCallback callback) {
        taskDataSource.getTask(taskId, new GetTaskCallback() {
            @Override
            public void onComplete(Task task) {
                callback.onComplete(task);
            }
        });
    }

    public void addTask(Task task) {
        taskDataSource.saveTask(task);
    }

    public void completeTask(Task task) {
        taskDataSource.completeTask(task);
    }

    public void clearTasks() {
        taskDataSource.clearTasks();
    }

    public interface GetTasksCallback {
        void onComplete(List<Task> tasks);
    }

    public interface GetTaskCallback {
        void onComplete(Task task);
    }
}
