package com.example.easyexecldemo.po;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:         ww
 * Datetime:       2020\8\12 0012
 * Description:    测试模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreData {
    @ExcelProperty(value = "学号", index = 0)
    private int id;
    @ExcelProperty(value = "年级", index = 1)
    private String senior;
    @ExcelProperty(value = "学生", index = 2)
    private String name;
    @ExcelProperty(value = "语文", index = 3)
    private int chinaese;
    @ExcelProperty(value = "英语", index = 4)
    private int english;
    @ExcelProperty(value = "数学", index = 5)
    private int math;
    @ExcelProperty(value = "综合科", index = 6)
    private int comprehensive;
}
