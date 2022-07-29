package com.kstd.shinmy.registrationapi.controller;

import com.kstd.shinmy.registrationapi.biz.RegistrationBiz;
import com.kstd.shinmy.registrationapi.dto.LectureDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration-api/lectures")
public class LectureController {
    private final RegistrationBiz registrationBiz;

    @Autowired
    public LectureController(RegistrationBiz registrationBiz) {
        this.registrationBiz = registrationBiz;
    }

    @GetMapping("/all-list")
    public ResponseEntity<Object> getAllLectureList() {
        try {
            List<LectureDTO> list = registrationBiz.getAllLectureList();
            LoggerFactory.getLogger(this.getClass()).info("getAllLectureList list: {}", list);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("getAllLectureList", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/registrable-list")
    public ResponseEntity<Object> getRegistrableList() {
        try {
            List<LectureDTO> list = registrationBiz.getRegistrableList();
            LoggerFactory.getLogger(this.getClass()).info("getRegistrableList list: {}", list);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("getRegistrableList", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createLecture(@RequestBody LectureDTO request) {
        LoggerFactory.getLogger(this.getClass()).info("createLecture request: {}", request);
        try {
            LectureDTO lectureDTO = registrationBiz.createLecture(request);
            LoggerFactory.getLogger(this.getClass()).info("createLecture lectureDTO: {}", lectureDTO);
            return new ResponseEntity<>(lectureDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("createLecture", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
