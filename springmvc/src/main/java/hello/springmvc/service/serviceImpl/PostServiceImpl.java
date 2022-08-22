//package hello.springmvc.service.serviceImpl;
//
//import hello.springmvc.entity.Post;
//import hello.springmvc.entity.PostRepository;
//import hello.springmvc.service.PostService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class PostServiceImpl implements PostService {
//
//    private final PostRepository postRepository;
//
//    @Override
//    public boolean createPost(String title, String contents) {
//
//        Post post = Post.builder().title(title)
//                .contents(contents)
//                .build();
//
//        log.info(post.getPostId().toString());
//        postRepository.save(post);
//
//        return true;
//    }
//}
