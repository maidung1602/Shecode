package shecode.sgip5.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shecode.sgip5.DTO.CommentDTO;
import shecode.sgip5.DTO.UniversityDTO;
import shecode.sgip5.model.Blog;
import shecode.sgip5.model.Comment;
import shecode.sgip5.model.University;
import shecode.sgip5.repository.BlogRepository;
import shecode.sgip5.repository.CommentRepository;
import shecode.sgip5.repository.UniversityRepository;
import shecode.sgip5.repository.VoteRepository;
import shecode.sgip5.service.UniversityService;
import shecode.sgip5.service.UserService;

import java.util.ArrayList;
import java.util.List;


@Controller
public class UniversityController {
    @Autowired
    private UniversityService universityService;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    BlogRepository blogRepository;
    @GetMapping("/universities")
    public String getUniversity(Model model) {
        ModelMapper mapper = new ModelMapper();
//        University university = universityService.getUniversityById(1);
//        model.addAttribute("university", university);
//        model.addAttribute("voteAve", voteRepository.calculateStarByUniversity(1));
//        model.addAttribute("vote", voteRepository.calculateStarByUniversityAndVoteType(1));
//
//        List<Comment> ListComment = commentRepository.findCommentByUniversity(1);
//        List<CommentDTO> listCommentDTO = new ArrayList<>();
//        ListComment.stream().forEach(comment -> {
//            CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
//            commentDTO.setSubComment(commentRepository.findSubComment(commentDTO.getId()));
//            listCommentDTO.add(commentDTO);
//        });
//        model.addAttribute("comment", listCommentDTO);
//
//
//        List<University> ListUniversity = universityRepository.findAll();
//        List<UniversityDTO> listUniversityDTO = new ArrayList<>();
//        ListUniversity.stream().forEach(uni -> {
//            UniversityDTO universityDTO = mapper.map(uni, UniversityDTO.class);
//            universityDTO.setVoteAve(voteRepository.calculateStarByUniversity(uni.getId()));
//            listUniversityDTO.add(universityDTO);
//        });
//        model.addAttribute("listUniversity", listUniversityDTO);
//
//        List<Blog> ListBlog = blogRepository.findAll();
//        model.addAttribute("ListBlog", ListBlog);
//        Blog blog = blogRepository.getById(2);
//        model.addAttribute("ListBlog", ListBlog);
        List<University> ListUniversity = universityRepository.findAll();
        List<UniversityDTO> listUniversityDTO = new ArrayList<>();
        ListUniversity.stream().forEach(uni -> {
            UniversityDTO universityDTO = mapper.map(uni, UniversityDTO.class);
            universityDTO.setVoteAve(voteRepository.calculateStarByUniversity(uni.getId()));
            listUniversityDTO.add(universityDTO);
        });
        model.addAttribute("listUniversity", listUniversityDTO);
        return "review";
    }

    @GetMapping("/university-details")
    public String getUniversityDetails(Model model) {
        return "review-detail";
    }
}
