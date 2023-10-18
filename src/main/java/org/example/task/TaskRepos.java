package org.example.task;

import org.example.task.core.JpaSpecRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepos extends JpaSpecRepository<TaskEntity,Long> {
}
