package com.soft1851.contentcenter.controller;

import com.soft1851.contentcenter.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Tao
 * @version 1.0
 * @ClassName CourseController
 * @Description TODO
 * @date 2020-09-24 21:11
 **/
@RestController
@RequestMapping(value = "/course")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseController {
    private final CourseService courseService;

    private final RestTemplate restTemplate;

    private DiscoveryClient discoveryClient;

    /**
     * 测试服务发现，证明内容中心总能找到用户中心
     *
     * @return
     */
    @GetMapping("/discovery")
    public List<ServiceInstance> getInstances() {
        return this.discoveryClient.getInstances("content-center");
    }


//    @GetMapping(value = "/all")
//    public ResponseResult getAll(){
//        List<Course> courses = courseService.findAll();
//        List<CourseDto> courseDtoList = new ArrayList<>();
//        courses.forEach(course -> {
//            int userId = course.getUserId();
//            UserDto userDto = restTemplate.getForObject("http://user-center/user/{id}", UserDto.class,userId);
//            assert userDto != null;
//            CourseDto courseDto = CourseDto.builder().course(course).userName(userDto.getUserName()).avatarUrl(userDto.getAvatarUrl()).build();
//            courseDtoList.add(courseDto);
//        });
//        return new ResponseResult(200,"请求成功",courseDtoList);
//    }

}
