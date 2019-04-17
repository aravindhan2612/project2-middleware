package com.coll.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coll.DAO.BlogPostLikesDao;
import com.coll.Model.BlogPost;
import com.coll.Model.BlogPostLikes;
import com.coll.Model.ErrorClazz;



@RestController
public class BlogPostLikesController 
{
	@Autowired
	private BlogPostLikesDao blogPostLikesDao;

	@RequestMapping(value="/hasuserlikedblogpost/{blogPostId}",method=RequestMethod.GET)
	public ResponseEntity<?> hasUserLikedBlogPost(@PathVariable int blogPostId,HttpSession session)
	{
		
			String email=(String)session.getAttribute("loginId");
			if(email==null)
			{
				ErrorClazz errorClazz=new ErrorClazz(5,"Please login..");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			BlogPostLikes blogPostLikes=blogPostLikesDao.hasUserLikedBlogPost(blogPostId, email);
			return new ResponseEntity<BlogPostLikes>(blogPostLikes,HttpStatus.OK);
	}


	@RequestMapping(value="/updatelikes/{blogPostId}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateLikes(@PathVariable int blogPostId,HttpSession session){
		
				String email=(String)session.getAttribute("loginId");
				if(email==null)
				{
					ErrorClazz errorClazz=new ErrorClazz(5,"Please login..");
					return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
				}
				BlogPost blogPost=blogPostLikesDao.updateLikes(blogPostId, email);
				return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}

}
