package com.konstantinesoft.d3w2u5;

import com.konstantinesoft.d3w2u5.entities.BlogPost;
import com.konstantinesoft.d3w2u5.entities.BlogPostTemporaneo; // Assuming you want to use the DTO for GET requests
import com.konstantinesoft.d3w2u5.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogpost")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping
    public Page<BlogPost> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return blogPostService.findAllBlogPosts(page, size);
    }

    @GetMapping("/{postID}")
    public BlogPostTemporaneo getPostById(@PathVariable int postID) {
        return blogPostService.findBlogPostById(postID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost createNewPost(@RequestBody BlogPost body) {
        return blogPostService.saveBlogPost(body);
    }

    @PutMapping("/{postID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BlogPost updatePost(@RequestBody BlogPost body, @PathVariable int postID) {
        body.setId(postID); // Ensure the ID is set to the path variable
        return blogPostService.updateBlogPost(body);
    }

    @DeleteMapping("/{postID}")
    public String deleteBlogPost(@PathVariable int postID) {
        blogPostService.deleteBlogPost(postID);
        return "BlogPost deleted successfully with ID: " + postID;
    }

    @GetMapping("/pathParamExample/{param}")
    public String pathParamExample(@PathVariable("param") String parametro) {
        return "The parameter you entered is: " + parametro;
    }

    @GetMapping("/queryParamsExample")
    public String queryParamsExample(
            @RequestParam(required = false, defaultValue = "Anonymous") String name,
            @RequestParam(required = false, defaultValue = "N/A") String surname
    ) {
        return "Query parameters received are: Name - " + name + ", Surname - " + surname;
    }

    @PostMapping("/payloadExample")
    public BlogPost payloadExample(@RequestBody BlogPost body) {
        System.out.println("Received payload:");
        System.out.println(body);
        return body;
    }
}
