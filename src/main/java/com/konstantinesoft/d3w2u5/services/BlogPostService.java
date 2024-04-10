package com.konstantinesoft.d3w2u5.services;

import com.konstantinesoft.d3w2u5.entities.Author;
import com.konstantinesoft.d3w2u5.entities.BlogPost;
import com.konstantinesoft.d3w2u5.entities.BlogPostTemporaneo;
import com.konstantinesoft.d3w2u5.repository.AuthorRepository;
import com.konstantinesoft.d3w2u5.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Page<BlogPost> findAllBlogPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("postID").descending());
        return blogPostRepository.findAll(pageable);
    }

    public BlogPostTemporaneo findBlogPostById(int id) {
        Optional<BlogPost> blogPost = blogPostRepository.findById(id);
        return blogPost.map(this::convertToBlogPostTemporaneo)
                .orElseThrow(() -> new RuntimeException("Blogpost not found for id :: " + id));
    }

    private BlogPostTemporaneo convertToBlogPostTemporaneo(BlogPost post) {
        BlogPostTemporaneo temp = new BlogPostTemporaneo();
        temp.setId(post.getId());
        temp.setCategoria(post.getCategoria());
        temp.setTitolo(post.getTitolo());
        temp.setCover(post.getCover());
        temp.setContenuto(post.getContenuto());
        temp.setTempoLettura(post.getTempoLettura());
        temp.setAuthorName(post.getAuthor() != null ? post.getAuthor().getNome() : "Author not available");
        return temp;
    }

    public BlogPost saveBlogPost(BlogPost blogPost) {
        validateAuthor(blogPost.getAuthor());
        return blogPostRepository.save(blogPost);
    }

    private void validateAuthor(Author author) {
        if (author == null || author.getId() == 0) {
            throw new RuntimeException("Author is not specified or invalid");
        }
        authorRepository.findById(author.getId())
                .orElseThrow(() -> new RuntimeException("Author with id " + author.getId() + " not found"));
    }

    public BlogPost deleteBlogPost(int id) {
        BlogPost foundPost = findBlogPostByIdForDelete(id);
        blogPostRepository.deleteById(id);
        return foundPost;
    }

    private BlogPost findBlogPostByIdForDelete(int id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blogpost not found for id :: " + id));
    }

    public BlogPost updateBlogPost(BlogPost newBlogPost) {
        BlogPost existingBlogPost = blogPostRepository.findById(newBlogPost.getId())
                .orElseThrow(() -> new RuntimeException("Post not found for id :: " + newBlogPost.getId()));
        updateExistingBlogPost(existingBlogPost, newBlogPost);
        return blogPostRepository.save(existingBlogPost);
    }

    private void updateExistingBlogPost(BlogPost existingBlogPost, BlogPost newBlogPost) {
        existingBlogPost.setCategoria(newBlogPost.getCategoria());
        existingBlogPost.setTitolo(newBlogPost.getTitolo());
        existingBlogPost.setCover(newBlogPost.getCover());
        existingBlogPost.setContenuto(newBlogPost.getContenuto());
        existingBlogPost.setTempoLettura(newBlogPost.getTempoLettura());
        validateAuthor(newBlogPost.getAuthor());
        existingBlogPost.setAuthor(newBlogPost.getAuthor());
    }
}
