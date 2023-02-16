package pl.pedyk.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pedyk.post.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
