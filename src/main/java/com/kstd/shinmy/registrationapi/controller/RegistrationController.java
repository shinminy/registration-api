package com.kstd.shinmy.registrationapi.controller;

import com.kstd.shinmy.registrationapi.biz.RegistrationBiz;
import com.kstd.shinmy.registrationapi.dto.LectureDTO;
import com.kstd.shinmy.registrationapi.dto.RegistrationDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration-api/registration")
public class RegistrationController {
    private final RegistrationBiz registrationBiz;

    @Autowired
    public RegistrationController(RegistrationBiz registrationBiz) {
        this.registrationBiz = registrationBiz;
    }

    @GetMapping("/employees/{employeeIdNumber}")
    public ResponseEntity<Object> getLectureListByEmployee(@PathVariable String employeeIdNumber) {
        LoggerFactory.getLogger(this.getClass()).info("getLectureListByEmployee employeeIdNumber: {}", employeeIdNumber);
        if (employeeIdNumber.length() != 5) {
            LoggerFactory.getLogger(this.getClass()).error("getLectureListByEmployee Invalid employeeIdNumber's length!");
            return new ResponseEntity<>("Invalid employeeIdNumber: " + employeeIdNumber, HttpStatus.BAD_REQUEST);
        }

        try {
            List<LectureDTO> list = registrationBiz.getLectureListByEmployee(employeeIdNumber);
            LoggerFactory.getLogger(this.getClass()).info("getLectureListByEmployee list: {}", list);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("getLectureListByEmployee", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/lectures/{lectureId}")
    public ResponseEntity<Object> getEmployeeListForLecture(@PathVariable long lectureId) {
        LoggerFactory.getLogger(this.getClass()).info("getEmployeeListForLecture lectureId: {}", lectureId);
        try {
            List<String> list = registrationBiz.getEmployeeListForLecture(lectureId);
            LoggerFactory.getLogger(this.getClass()).info("getEmployeeListForLecture list: {}", list);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("getEmployeeListForLecture", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerEmployeeForLecture(@RequestBody RegistrationDTO request) {
        LoggerFactory.getLogger(this.getClass()).info("registerEmployeeForLecture request: {}", request);
        if (request.getEmployeeIdNumber().length() != 5) {
            LoggerFactory.getLogger(this.getClass()).error("registerEmployeeForLecture Invalid employeeIdNumber's length!");
            return new ResponseEntity<>("Invalid employeeIdNumber: " + request.getEmployeeIdNumber(), HttpStatus.BAD_REQUEST);
        }

        try {
            RegistrationDTO registrationDTO = registrationBiz.createRegistration(request);
            LoggerFactory.getLogger(this.getClass()).info("registerEmployeeForLecture registrationDTO: {}", registrationDTO);
            return new ResponseEntity<>(registrationDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("registerEmployeeForLecture", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
