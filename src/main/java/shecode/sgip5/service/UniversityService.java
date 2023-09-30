package shecode.sgip5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shecode.sgip5.model.University;
import shecode.sgip5.repository.UniversityRepository;
import shecode.sgip5.repository.UserRepository;

@Service
public class UniversityService {
    @Autowired
    private UniversityRepository universityRepository;

    public University getUniversityById(int id){
        return universityRepository.getById(id);
    }
}
