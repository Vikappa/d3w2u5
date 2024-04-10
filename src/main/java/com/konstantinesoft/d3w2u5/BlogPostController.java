package com.konstantinesoft.d3w2u5;

import com.konstantinesoft.d3w2u5.entities.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
// Specializzazione di @Component, ci serve per definire una "collezione" di endpoints
// Ogni endpoint sarà un metodo di questa classe)
@RequestMapping("/blogpost")
// Serve per definire un prefisso comune nell'URL di tutti gli endpoints di questo controller
public class BlogPostController {

    // URL: baseUrl/blogpost
    @Autowired
    BlogPostService blogPostService;

    @GetMapping
    public List<BlogPost> getAllPosts(){
        return blogPostService.getAllPosts();
    }

    @GetMapping("/{postID}")
    private BlogPost getPostById(@PathVariable int postID){
        return this.blogPostService.findById(postID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Status Code 201
    private BlogPost saveNewPost(@RequestBody BlogPost body){
        return this.blogPostService.savePost(body);
    }

    @PutMapping("/{postID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String putExample(@RequestBody BlogPost body, @PathVariable int postID) {

        return this.blogPostService.findByIdAndReplace(postID, body);
    }

    @DeleteMapping("/{postID}")
    public String deleteBlogPost(@PathVariable int postID) {
        return "BlogPost deleted: \n"+blogPostService.deleteById(postID);
    }


    @GetMapping("/pathParamExample/{parametro}")
    public String pathParamExample(@PathVariable String parametro){ // parametro deve corrispondere come nome a {parametro} dichiarato nell'URL
        return "Il parametro che hai inserito è: " + parametro;
    }

    @GetMapping("/queryParamsExample")
    public String queryParamsExample(@RequestParam(required = false) String name, @RequestParam(required = false) String surname){
        return "I parametri query che hai inserito sono: " + name + " " + surname;
    }

    @PostMapping("/payloadExample")
    public BlogPost payloadExample(@RequestBody BlogPost body){
        System.out.println("Ecco il payload inviato:");
        System.out.println(body);
        return body;
    }

}
