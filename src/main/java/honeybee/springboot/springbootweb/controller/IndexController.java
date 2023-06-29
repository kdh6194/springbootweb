package honeybee.springboot.springbootweb.controller;

import honeybee.springboot.springbootweb.dto.PostsResponseDto;
import honeybee.springboot.springbootweb.service.PostsService;
import honeybee.springboot.springbootweb.vo.User;
import honeybee.springboot.springbootweb.webconfig.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final HttpSession httpSession;
    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
        SessionUser user = (SessionUser)httpSession.getAttribute("user");
        if (user != null){
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }
    @GetMapping("/post/save")
    public String postsSave(){
        return "posts-save";
    }
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
}
