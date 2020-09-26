package com.soft1851.contentcenter.domain.dto;

import com.soft1851.contentcenter.domain.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tao
 * @version 1.0
 * @ClassName CourseDto
 * @Description TODO
 * @date 2020-09-24 20:46
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
    private Course course;
    private String userName;
    private String avatarUrl;
}
