package com.kstd.shinmy.registrationapi.biz;

import com.kstd.shinmy.registrationapi.dao.LectureDAO;
import com.kstd.shinmy.registrationapi.dao.RegistrationDAO;
import com.kstd.shinmy.registrationapi.dto.LectureDTO;
import com.kstd.shinmy.registrationapi.dto.RegistrationDTO;
import com.kstd.shinmy.registrationapi.entity.Lecture;
import com.kstd.shinmy.registrationapi.entity.Registration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RegistrationBiz {
    private final ModelMapper modelMapper;

    private final LectureDAO lectureDAO;
    private final RegistrationDAO registrationDAO;

    @Autowired
    public RegistrationBiz(LectureDAO lectureDAO, RegistrationDAO registrationDAO) {
        modelMapper = new ModelMapper();

        this.lectureDAO = lectureDAO;
        this.registrationDAO = registrationDAO;
    }

    public LectureDTO createLecture(LectureDTO lectureDTO) {
        Lecture lecture = lectureDAO.save(modelMapper.map(lectureDTO, Lecture.class));
        return modelMapper.map(lecture, LectureDTO.class);
    }

    public RegistrationDTO createRegistration(RegistrationDTO registrationDTO) {
        Registration registration = registrationDAO.save(modelMapper.map(registrationDTO, Registration.class));
        return modelMapper.map(registration, RegistrationDTO.class);
    }

    public List<LectureDTO> getAllLectureList() {
        List<Lecture> lectureList = lectureDAO.findAllList();
        return mapList(lectureList, LectureDTO.class);
    }

    public List<LectureDTO> getRegistrableList() {
        List<Lecture> lectureList = lectureDAO.findRegistrableList();
        return mapList(lectureList, LectureDTO.class);
    }

    public List<LectureDTO> getLectureListByEmployee(String employeeIdNumber) {
        List<Lecture> lectureList = lectureDAO.findListByEmployeeIdNumber(employeeIdNumber);
        return mapList(lectureList, LectureDTO.class);
    }

    public List<String> getEmployeeListForLecture(long lectureId) {
        List<Registration> registrationList = registrationDAO.findByLectureId(lectureId);
        return registrationList.stream().map(Registration::getEmployeeIdNumber).collect(Collectors.toList());
    }

    private <S, T> List<T> mapList(List<S> sourceList, Class<T> targetClass) {
        return sourceList.stream().map(source -> modelMapper.map(source, targetClass)).collect(Collectors.toList());
    }
}
