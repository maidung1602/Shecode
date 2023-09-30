package shecode.sgip5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    @GetMapping("/info")
    public String getUniversityInfo() {
        // Thay đổi đoạn mã này để trả về thông tin của các trường đại học
        return "Thông tin về các trường đại học";
    }
}
