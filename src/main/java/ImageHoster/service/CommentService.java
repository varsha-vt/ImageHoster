package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    //This is the method in the Comment service class that calls the class in the repository method inorder to save the class
    public void createComment(Comment newComment) {
        commentRepository.createComment(newComment);
    }
}
