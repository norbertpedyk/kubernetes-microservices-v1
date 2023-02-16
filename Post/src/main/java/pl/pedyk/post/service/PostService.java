package pl.pedyk.post.service;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.pedyk.post.dto.PostDto;
import pl.pedyk.post.dto.UserDto;
import pl.pedyk.post.model.Post;
import pl.pedyk.post.repository.PostRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper postModelMapper;
    private final RestTemplate restTemplate;

    @Value("${custom.user-service-url}")
    private String userUrl;

    public PostService(PostRepository postRepository, ModelMapper postModelMapper, RestTemplate restTemplate) {
        this.postRepository = postRepository;
        this.postModelMapper = postModelMapper;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public PostDto addPost(PostDto postDto) {
        PostDto savedPostDto = postModelMapper.map(postRepository.save(Post.builder()
                .topic(postDto.getTopic())
                .authorId(postDto.getAuthorId())
                .text(postDto.getText())
                .postedAt(Date.from(LocalDateTime.now()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .build()), PostDto.class);

        UserDto user = restTemplate.getForObject(
                userUrl + postDto.getAuthorId(),
                UserDto.class);

        assert user != null;
        if (user.getAmount() != null) {
            user.setAmount(String.valueOf((Long.parseLong(user.getAmount()) + 1)));
        } else {
            user.setAmount("1");
        }

        restTemplate.put(
                userUrl + user.getId(),
                user);
        return savedPostDto;
    }

    @Transactional
    public boolean deletePost(Long id) {
        if (postRepository.existsById(id)) {
            UserDto user = restTemplate.getForObject(
                    userUrl + postRepository.getReferenceById(id).getAuthorId(),
                    UserDto.class);

            assert user != null;
            if (user.getAmount() != null) {
                user.setAmount(String.valueOf((Long.parseLong(user.getAmount()) - 1)));
            } else {
                throw new IllegalStateException("Something wrong with numbers of posts");
            }

            restTemplate.put(
                    userUrl + user.getId(),
                    user);

            postRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public PostDto getPost(Long id) {
        return postRepository.findById(id)
                .map(post -> postModelMapper.map(post, PostDto.class))
                .orElse(null);
    }

    @Transactional
    public PostDto updatePost(Long id, PostDto post) {

        if (postRepository.existsById(id)) {
            return postModelMapper.map(postRepository.save(Post.builder()
                    .id(id)
                    .topic(post.getTopic())
                    .authorId(post.getAuthorId())
                    .text(post.getText())
                    .postedAt(Date.from(LocalDateTime.now()
                            .atZone(ZoneId.systemDefault())
                            .toInstant()))
                    .build()), PostDto.class);
        } else {
            return null;
        }
    }
}
