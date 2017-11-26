package com.jhughes.todoapp.data.domain.repo;

import com.jhughes.todoapp.data.domain.model.Task;
import com.jhughes.todoapp.data.local.repo.TaskDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskRepository {

    private final TaskDataSource localDataSource;

    private Map<String, Task> tasksMap;

    public TaskRepository(TaskDataSource taskDataSource) {
        this.localDataSource = taskDataSource;

        localDataSource.getTasks(new GetTasksCallback() {
            @Override
            public void onComplete(List<Task> tasks) {
                refreshCache(tasks);
            }
        });
    }

    public void getTasks(final GetTasksCallback callback) {

        if (tasksMap != null) {
            callback.onComplete(new ArrayList<>(tasksMap.values()));
        } else {
            localDataSource.getTasks(new GetTasksCallback() {
                @Override
                public void onComplete(List<Task> tasks) {
                    refreshCache(tasks);
                }
            });
        }
    }

    public void getTask(int taskId, final GetTaskCallback callback) {

        if (tasksMap != null) {
            final String id = String.valueOf(taskId);

            if (tasksMap.containsKey(id)) {
                callback.onComplete(tasksMap.get(id));
            }
        } else {
            localDataSource.getTask(taskId, new GetTaskCallback() {
                @Override
                public void onComplete(Task task) {
                    callback.onComplete(task);
                }
            });
        }
    }

    public void addTask(Task task) {
        if(tasksMap == null) {
            tasksMap = new LinkedHashMap<>();
        }
        tasksMap.put(String.valueOf(task.getId()), task);
        localDataSource.saveTask(task);
    }

    public void completeTask(String taskId) {
        if (tasksMap != null && tasksMap.containsKey(taskId)) {
            Task task = tasksMap.get(taskId);
            task.setComplete(true);
            tasksMap.put(taskId, task);

            localDataSource.completeTask(task);
        }
    }

    public void activateTask(String taskId) {
        if (tasksMap != null && tasksMap.containsKey(taskId)) {
            Task task = tasksMap.get(taskId);
            task.setComplete(false);
            tasksMap.put(taskId, task);

            localDataSource.activateTask(task);
        }
    }

    public void clearTasks() {
        if (tasksMap != null) {
            tasksMap.clear();
        }
        localDataSource.clearTasks();
    }

    private void refreshCache(List<Task> tasks) {
        if (tasksMap == null) {
            tasksMap = new LinkedHashMap<>();
        }

        tasksMap.clear();

        for (Task task : tasks) {
            tasksMap.put(String.valueOf(task.getId()), task);
        }
    }

    public interface GetTasksCallback {
        void onComplete(List<Task> tasks);
    }

    public interface GetTaskCallback {
        void onComplete(Task task);
    }
}
