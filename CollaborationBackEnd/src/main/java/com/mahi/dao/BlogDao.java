package com.mahi.dao;

import java.util.List;

import com.mahi.model.Blog;

public interface BlogDao 
{

	public void addBlog(Blog blog);
	public void updateBlog(Blog blog);
	public void deleteBlog(Blog blog);
	public Blog getBlogId(long blogId);
	public List<Blog> listBlogs();
	public List<Blog> listNewBlogs();
	
}
