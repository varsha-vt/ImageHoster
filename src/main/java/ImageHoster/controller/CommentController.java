package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    //This method is called when the user submits a comment
    //This method sets the necessary values to the comment object and calls the Comment Service class inorder to save the comment into the DB
    //If no comment is entered but the submit button is clicked, then the page is reloaded and no comments are saved in the DB
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, @RequestParam("comment") String comment, Comment newComment, HttpSession session) {
        if (comment.isEmpty()) {
            return "redirect:/images/" + imageId + "/" + imageTitle;
        }
        newComment.setCreatedDate(LocalDate.now());
        User user = (User) session.getAttribute("loggeduser");
        newComment.setUser(user);
        newComment.setImage(imageService.getImage(imageId));
        newComment.setText(comment);
        commentService.createComment(newComment);
        return "redirect:/images/" + imageId + "/" + imageTitle;
    }
}
