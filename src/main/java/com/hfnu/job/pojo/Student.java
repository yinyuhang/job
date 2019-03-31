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
public class Student {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @ChineseName("学号")
    String id;
    @ChineseName("姓名")
    String name;
    @ChineseName("生日")
    Date birthday;
    @Condition(expression = ExpressionType.NOT_NULL)
    @ChineseName("入学年份")
    Date admissionTime;
    @Condition(expression = ExpressionType.NOT_EMPTY)
    @ChineseName("专业")
    String major;
    boolean beDeleted;
}
