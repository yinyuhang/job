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

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @ChineseName("公司编号")
    String id;
    @Condition(expression = ExpressionType.NOT_EMPTY, logic = "like")
    @ChineseName("公司名称")
    String name;
    @Condition(expression = ExpressionType.NOT_EMPTY, logic = "like")
    @ChineseName("公司地址")
    String location;
    @Condition(expression = ExpressionType.NOT_EMPTY)
    @ChineseName("行业")
    String industry;
    boolean beDeleted;
}
