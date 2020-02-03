package com.bigdata.coreweb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author root
 * @since 2020-02-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("district")
public class District extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("name")
    private String name;

    @TableField("code")
    private String code;

    @TableField("sort")
    private Integer sort;
    @TableField("parent_id")
    private  String parentId;
    @TableField(exist = false)
    private List<District> children;
    public District() {
        this.children = new ArrayList<>();
    }

}
