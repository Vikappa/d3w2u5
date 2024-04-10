package com.konstantinesoft.d3w2u5.services;

import com.konstantinesoft.d3w2u5.entities.BlogPost;
import com.konstantinesoft.d3w2u5.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    public List<BlogPost> findAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost findBlogPostById(int id) {
        Optional<BlogPost> blogPost = blogPostRepository.findById(id);
        if (blogPost.isPresent()) {
            return blogPost.get();
        } else {
            throw new RuntimeException("Post not found for id :: " + id);
        }
    }

    public BlogPost saveBlogPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public BlogPost deleteBlogPost(int id) {
        BlogPost foundPost = blogPostRepository.findById(id).get();
        blogPostRepository.deleteById(id);
        return foundPost;
    }

    public BlogPost findByIdAndReplace(int postID, BlogPost body) {
        BlogPost foundPost = this.findBlogPostById(postID);
        if (foundPost != null) {
            foundPost.setCategoria(body.getCategoria());
            foundPost.setTitolo(body.getTitolo());
            foundPost.setCover(body.getCover());
            foundPost.setContenuto(body.getContenuto());
            foundPost.setTempoLettura(body.getTempoLettura());
            this.saveBlogPost(foundPost);
            return foundPost;
        }
        else {
            throw new RuntimeException("Post not found for id :: " + postID);
        }
    }
}
