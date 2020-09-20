package com.soft1851.coursecenter.service;

import com.soft1851.coursecenter.dao.CourseMapper;
import com.soft1851.coursecenter.domain.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName CourseService
 * @Description TODO
 * @date 2020-09-19 21:41
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseService {
    private final CourseMapper courseMapper;

    public List<Course> findAll(){
        return courseMapper.selectAll();
    }
}