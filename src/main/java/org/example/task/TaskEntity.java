package org.example.task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.task.core.BaseEntity;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.Date;

@Builder
@Entity
@Table(name = "T_TASK")
@Data
@NoArgsConstructor
@AllArgsConstructor
//  服务名.实体名 ( 条件 ) | 角色／用户组／用户 | 权限操作：view/update/delete/create/all
// auth.TaskEntity (  name *A* and number > 1 ) |  dept,admin | view,update
// auth.TaskEntity (  (startTime > {starDate}  and  endTime < {endTime}) or number >= 50 ) |  root | all
public class TaskEntity extends BaseEntity<Long> {


    private String name;

    private int number;

    private Date startTime ;

    private  Date endTime ;



}
