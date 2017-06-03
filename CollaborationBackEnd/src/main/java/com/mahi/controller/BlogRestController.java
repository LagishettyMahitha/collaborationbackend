package com.mahi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mahi.dao.BlogDao;
import com.mahi.dao.SignUpDao;
import com.mahi.model.Blog;
import com.mahi.model.SignUp;

@RestController
public class BlogRestController
{

	@Autowired
	BlogDao blogDAO;
	
	@Autowired
	SignUpDao userDao;
	//-------------------Retrieve All Blogs--------------------------------------------------------
    
			@GetMapping(value="/blog/")
		    public ResponseEntity<List<Blog>> listAllBlogs() {
		        List<Blog> blogs = blogDAO.listBlogs();
		        if(blogs.isEmpty()){
		            return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
		    }
			
			//-------------------Retrieve All New Blogs--------------------------------------------------------
		    
			@GetMapping(value="/blog/new")
		    public ResponseEntity<List<Blog>> listAllNewBlogs() {
		        List<Blog> blogs = blogDAO.listNewBlogs();
		        if(blogs.isEmpty()){
		            return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
		    }
	
	
			
			//-------------------Retrieve Single Blog--------------------------------------------------------
		    
			@GetMapping(value="/blog/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
		    public ResponseEntity<Blog> getBlog(@PathVariable("id") long id) {
		        
		        Blog blog = blogDAO.getBlogId(id);
		        if (blog == null) {
		            
		            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		        }
		        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		    }
			
			
			//-------------------Create a Blog--------------------------------------------------------
		    
			@PostMapping(value = "/blog/{id}")
			   public ResponseEntity<Void> createBlog(@RequestBody Blog blog,@PathVariable Integer id) {
			SignUp user=userDao.getUserByUserId(id);
			   blog.setUser(user);
			 
			       blogDAO.addBlog(blog);
			 
			      
			       return new ResponseEntity<Void>(HttpStatus.CREATED);
			   }
			
			 //------------------- Update a User --------------------------------------------------------
		    
			@PutMapping(value = "/blog/{id}")
		    public ResponseEntity<Blog> updateBlog(@PathVariable("id") long id, @RequestBody Blog blog) {
		      
		          
		        Blog currentBlog = blogDAO.getBlogId(id);
		          
		        if (currentBlog==null) {
		            
		            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		        }
		  
		        currentBlog.setTitle(blog.getTitle());
		        currentBlog.setDescription(blog.getDescription());
		       
		        
		          
		        blogDAO.updateBlog(currentBlog);
		        return new ResponseEntity<Blog>(currentBlog, HttpStatus.OK);
		    }
		  
			
			//------------------- Delete a blog --------------------------------------------------------
		    @DeleteMapping(value = "/blog/{id}")
		    public ResponseEntity<Blog> deleteBlog(@PathVariable("id") long id) {
		        		  
		        Blog blog = blogDAO.getBlogId(id);
		        if (blog == null) {
		            
		            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		        }
		  
		        blogDAO.deleteBlog(blog);
		        return new ResponseEntity<Blog>(HttpStatus.NO_CONTENT);
		    }
		    
	
//-------------------Approve Blog--------------------------------------------------------
		    
			@GetMapping(value="/approveblog/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
		    public ResponseEntity<Blog> approveBlog(@PathVariable("id") long id) {
		        
		        Blog blog = blogDAO.getBlogId(id);
		        
		        if (blog == null) {
		            
		            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		        }
		        blog.setStatus("Approved");
		        blogDAO.updateBlog(blog);
		        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		    }
			
//-------------------Reject Blog--------------------------------------------------------
		    
			@GetMapping(value="/rejectblog/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
		    public ResponseEntity<Blog> rejectBlog(@PathVariable("id") long id) {
		        
		        Blog blog = blogDAO.getBlogId(id);
		        
		        if (blog == null) {
		            
		            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		        }
		        blog.setStatus("Rejected");
		        blogDAO.updateBlog(blog);
		        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		    }	
}
