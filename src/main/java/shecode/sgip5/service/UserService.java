package shecode.sgip5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shecode.sgip5.model.School;
import shecode.sgip5.model.User;
import shecode.sgip5.repository.ISchoolRepository;
import shecode.sgip5.repository.IUserRepository;
import shecode.sgip5.util.GooglePojo;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ISchoolRepository schoolRepository;

    public User registerAccountFromGoogle(GooglePojo googlePojo) {
        User newUser = new User();
        if (googlePojo.getEmail().endsWith("edu.vn")) {
            School school = getUniversityByEmailDomain(googlePojo.getEmail());
            newUser.setSchool(school);
        }
        String[] temp = googlePojo.getEmail().split("@");
        String name = temp[0];
        //save to account
        newUser.setName(name);
        newUser.setEmail(googlePojo.getEmail());
        newUser.setAvatarUrl(googlePojo.getPicture());
        return userRepository.save(newUser);
    }

    public School getUniversityByEmailDomain(String email) {
        String[] temp = email.split("@");
        String[] uni = temp[temp.length - 1].split("\\.");
        String code = uni[uni.length - 3];
        return schoolRepository.getSchoolByCode(code);
    }

    public boolean checkExistMail(String email) {
        return userRepository.findUserByEmail(email) != null;
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
