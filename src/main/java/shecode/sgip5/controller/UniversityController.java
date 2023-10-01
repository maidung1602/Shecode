package shecode.sgip5.controller;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import shecode.sgip5.DTO.CommentDTO;
import shecode.sgip5.DTO.UniversityDTO;
import shecode.sgip5.model.*;
import shecode.sgip5.repository.*;
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
    @Autowired
    VoteTypeRepository voteTypeRepository;

    @GetMapping("/universities")
    public String getUniversity(Model model) {
        ModelMapper mapper = new ModelMapper();
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
    public String getUniversityDetails(@RequestParam("id") Integer id, Model model) {
        ModelMapper mapper = new ModelMapper();
        University university = universityService.getUniversityById(id);
        model.addAttribute("university", university);
        model.addAttribute("voteAve", voteRepository.calculateStarByUniversity(id));
        model.addAttribute("vote", voteRepository.calculateStarByUniversityAndVoteType(id));

        List<Comment> ListComment = commentRepository.findCommentByUniversity(id);
        List<CommentDTO> listCommentDTO = new ArrayList<>();
        ListComment.stream().forEach(comment -> {
            CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
            commentDTO.setSubComment(commentRepository.findSubComment(commentDTO.getId()));
            listCommentDTO.add(commentDTO);
        });
        model.addAttribute("comment", listCommentDTO);

        return "review-detail";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        ModelMapper mapper = new ModelMapper();
        List<University> ListUniversity = universityRepository.searchByNameOrCode(keyword);
        List<UniversityDTO> listUniversityDTO = new ArrayList<>();
        ListUniversity.stream().forEach(uni -> {
            UniversityDTO universityDTO = mapper.map(uni, UniversityDTO.class);
            universityDTO.setVoteAve(voteRepository.calculateStarByUniversity(uni.getId()));
            listUniversityDTO.add(universityDTO);
        });
        model.addAttribute("listUniversity", listUniversityDTO);
        return "review";
    }

    @GetMapping("/blog")
    public String blog(Model model, HttpSession session) {
        List<Blog> blogs = blogRepository.findAll();
        model.addAttribute("blogs", blogs);
        return "blog";
    }

    @PostMapping("/create-blog")
    public String createBlog(Blog blog, HttpSession session) {
        // Thêm blog vào cơ sở dữ liệu thông qua service
        User user = (User) session.getAttribute("user");
        blog.setUser(user);
        blogRepository.save(blog);
        return "redirect:/blog"; // Chuyển hướng về danh sách blogs hoặc trang khác tùy ý
    }

    @GetMapping("/blog-detail")
    public String showCreateBlog(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("blog", new Blog());
        if (user == null) {
            return "redirect:https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/login-google&response_type=code\n" +
                    "                                        &client_id=570524258444-ridflrjec1qvuq3oidpn67cc1gm3t8i6.apps.googleusercontent.com&approval_prompt=force";
        }
        return "blog-detail";
    }

    @PostMapping("/comment")
    public String comment(@RequestParam String content, @RequestParam Integer universityId,
                          @RequestParam Integer parentCommentId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("errmsg", "Bạn vui lòng đăng nhập để comment");
            ModelMapper mapper = new ModelMapper();
            University university = universityService.getUniversityById(universityId);
            model.addAttribute("university", university);
            model.addAttribute("voteAve", voteRepository.calculateStarByUniversity(universityId));
            model.addAttribute("vote", voteRepository.calculateStarByUniversityAndVoteType(universityId));

            List<Comment> ListComment = commentRepository.findCommentByUniversity(universityId);
            List<CommentDTO> listCommentDTO = new ArrayList<>();
            ListComment.stream().forEach(comment -> {
                CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
                commentDTO.setSubComment(commentRepository.findSubComment(commentDTO.getId()));
                listCommentDTO.add(commentDTO);
            });
            model.addAttribute("comment", listCommentDTO);
            return "review-detail";
        }
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUniversity(universityRepository.getById(universityId));
        comment.setUser(user);
        comment.setComment(commentRepository.getById(parentCommentId));
        commentRepository.save(comment);
        return "redirect:/university-details?id=" + universityId;
    }

    @PostMapping("/comment1")
    public String comment1(@RequestParam String content, @RequestParam Integer universityId,
                           Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("errmsg", "Bạn vui lòng đăng nhập để comment");
            ModelMapper mapper = new ModelMapper();
            University university = universityService.getUniversityById(universityId);
            model.addAttribute("university", university);
            model.addAttribute("voteAve", voteRepository.calculateStarByUniversity(universityId));
            model.addAttribute("vote", voteRepository.calculateStarByUniversityAndVoteType(universityId));

            List<Comment> ListComment = commentRepository.findCommentByUniversity(universityId);
            List<CommentDTO> listCommentDTO = new ArrayList<>();
            ListComment.stream().forEach(comment -> {
                CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
                commentDTO.setSubComment(commentRepository.findSubComment(commentDTO.getId()));
                listCommentDTO.add(commentDTO);
            });
            model.addAttribute("comment", listCommentDTO);
            return "review-detail";
        }
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUniversity(universityRepository.getById(universityId));
        comment.setUser(user);
        commentRepository.save(comment);
        return "redirect:/university-details?id=" + universityId;
    }

    @PostMapping("/vote")
    public String vote(@RequestParam Integer id, Model model, HttpSession session, WebRequest request) {
        try {
            Integer input0 = Integer.parseInt(request.getParameter("input0"));
            Integer input1 = Integer.parseInt(request.getParameter("input1"));
            Integer input2 = Integer.parseInt(request.getParameter("input2"));
            Integer input3 = Integer.parseInt(request.getParameter("input3"));
            Integer input4 = Integer.parseInt(request.getParameter("input4"));
            Integer input5 = Integer.parseInt(request.getParameter("input5"));

            User user = (User) session.getAttribute("user");
            if (user == null || user.getUniversity() == null) {
                model.addAttribute("err", "Bạn cần là sinh viên trường mới có thể vote");
                if (user == null)
                    model.addAttribute("err", "Bạn cần đăng nhập bằng email trường mới có thể vote");
                ModelMapper mapper = new ModelMapper();
                University university = universityService.getUniversityById(id);
                model.addAttribute("university", university);
                model.addAttribute("voteAve", voteRepository.calculateStarByUniversity(id));
                model.addAttribute("vote", voteRepository.calculateStarByUniversityAndVoteType(id));

                List<Comment> ListComment = commentRepository.findCommentByUniversity(id);
                List<CommentDTO> listCommentDTO = new ArrayList<>();
                ListComment.stream().forEach(comment -> {
                    CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
                    commentDTO.setSubComment(commentRepository.findSubComment(commentDTO.getId()));
                    listCommentDTO.add(commentDTO);
                });
                model.addAttribute("comment", listCommentDTO);

                return "review-detail";
            }
            University uni = universityRepository.getById(id);

            Vote vote1 = new Vote();
            vote1.setUniversity(uni);
            vote1.setUser(user);
            vote1.setVoteType(voteTypeRepository.getById(1));
            vote1.setStar(input0);

            Vote vote2 = new Vote();
            vote2.setUniversity(uni);
            vote2.setUser(user);
            vote2.setVoteType(voteTypeRepository.getById(2));
            vote2.setStar(input1);

            Vote vote3 = new Vote();
            vote3.setUniversity(uni);
            vote3.setUser(user);
            vote3.setVoteType(voteTypeRepository.getById(3));
            vote3.setStar(input2);

            Vote vote4 = new Vote();
            vote4.setUniversity(uni);
            vote4.setUser(user);
            vote4.setVoteType(voteTypeRepository.getById(4));
            vote4.setStar(input3);

            Vote vote5 = new Vote();
            vote5.setUniversity(uni);
            vote5.setUser(user);
            vote5.setVoteType(voteTypeRepository.getById(5));
            vote5.setStar(input4);

            Vote vote6 = new Vote();
            vote6.setUniversity(uni);
            vote6.setUser(user);
            vote6.setVoteType(voteTypeRepository.getById(6));
            vote6.setStar(input5);

            voteRepository.save(vote1);
            voteRepository.save(vote2);
            voteRepository.save(vote3);
            voteRepository.save(vote4);
            voteRepository.save(vote5);
            voteRepository.save(vote6);
        } catch (Exception e) {
            model.addAttribute("err", "Bạn cần vote hết toàn bộ hạng mục");
            ModelMapper mapper = new ModelMapper();
            University university = universityService.getUniversityById(id);
            model.addAttribute("university", university);
            model.addAttribute("voteAve", voteRepository.calculateStarByUniversity(id));
            model.addAttribute("vote", voteRepository.calculateStarByUniversityAndVoteType(id));

            List<Comment> ListComment = commentRepository.findCommentByUniversity(id);
            List<CommentDTO> listCommentDTO = new ArrayList<>();
            ListComment.stream().forEach(comment -> {
                CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
                commentDTO.setSubComment(commentRepository.findSubComment(commentDTO.getId()));
                listCommentDTO.add(commentDTO);
            });
            model.addAttribute("comment", listCommentDTO);

            return "review-detail";
        }
        return "redirect:/university-details?id=" + id;
    }


}
