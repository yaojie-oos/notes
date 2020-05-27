package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName
@ApiModel(value="定义实体对象POJO")
public class User {
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value="用户Id",example="1001",notes="用户主键定义")
	private Integer id; //主键自增
	@ApiModelProperty(value="用户名称",example="张三")
	private String name;
	@ApiModelProperty(value="用户年龄",example="18")
	private Integer age;
	@ApiModelProperty(value="用户性别",example="男")
	private String sex;
	
}
