package tuyen.novahub.assignment4.repository;

import org.springframework.data.repository.CrudRepository;

import tuyen.novahub.assignment4.model.Comment;
import tuyen.novahub.assignment4.model.CommentDelete;

public interface CommentDeleteRepository extends CrudRepository<Comment, Long>{
	public CommentDelete save(CommentDelete commentDelete);
}
