package com.hfnu.job.pojo;

import com.shark.generator.ChineseName;
import com.shark.generator.Condition;
import com.shark.generator.ExpressionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employment {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @ChineseName("就业合同号")
    String id;
    @Condition(expression = ExpressionType.NOT_NULL, logic = "greaterThanOrEqualTo")
    @ChineseName("入职时间")
    Date entryTime;
    @Condition(expression = ExpressionType.NOT_EMPTY)
    @ChineseName("工作岗位")
    String jobPosition;
    @ChineseName("薪资等级")
    String salaryLevel;
//    @OneToOne
    @ChineseName("学生姓名")
    String student;
//    @OneToOne
    @ChineseName("就业公司")
    String company;
    boolean beDeleted;
}
