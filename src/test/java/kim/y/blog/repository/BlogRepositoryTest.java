package kim.y.blog.repository;

import kim.y.blog.entity.Blog;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)//DROP TABLE시 필요한 어노테이션
public class BlogRepositoryTest {

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach //각 테스트 전에 공통적으로 실행한 코드를 저장해두는 곳
    public void setBlogTable(){
        blogRepository.createBlogTable(); //blog테이블생성
        blogRepository.insertTestData();//생성된 blog테이블에 더미데이터 3개 입력
    }

    @Test
    @DisplayName("전채 행을 얻어오고, 그 중 자바 1번 인덱스 행만 추출해 번호 확인")
    public void findAllTest(){
        //given 2번요소 조회를 위한 fixture 선언
        int blogId = 1; //java 자료구조 인덱스는 0번부터

        //when DB에 있는 모든 데이터를 자바 엔터티로 역질렬화
        List<Blog> blogList = blogRepository.findAll();


        //then 더미데이터가 3개이므로 3개일것이라 단언
        assertEquals(3,blogList.size());

        //2번째 객체의 ID번호는 2번일 것이다.
        assertEquals(2, blogList.get(blogId).getBlogId());

    }

    @Test
    @DisplayName("4번째 행 데이터 저장 후, 행 저장여부 및 전달데이터 저장 여부 확인")
    public void saveTest(){
        //given : 저장을 위한 Blog Entity 생성 및 writer, blogTitle, blogContent
        //에 해당하는 fixture setter로 저장하기
        String writer = "추가할 작가명";
        String blogTitle = "추가할 제목";
        String blogContent = "추가할 본문";
        //Blog blog = new Blog();
        //blog.setWriter(writer);
        //blog.setBlogTitle(blogTitle);
        //blog.setBlogContent(blogContent);
        // blog 객체 생성 코드를 빌더패턴으로 리팩토링
        // 빌더 패턴 쓰는 법
        // 장점 : 파라미터 순서를 뒤바꿔서 집어넣어도 상관없음
        Blog blog = Blog.builder() //빌더패턴 시작
                .writer(writer)
                .blogTitle(blogTitle)
                .blogContent(blogContent)
                .build(); // 빌더 패턴 끝

        int blogId=3;//4번째 요소 조회(자바 인덱스는 0번부터 시작)
        //when : save() 메서드 호출하고, findAll()로 전체 데이터 가져오기
        blogRepository.save(blog);
        List<Blog> blogList = blogRepository.findAll();

        //then : 전체 데이터 개수가 4개인지,
        //그리고 방금 Insert 한 데이터의 writer, blogTitle, blogContent가
        //입력한대로 들어갔는지 단언문으로 확인
        assertEquals(4, blogList.size());
        assertEquals(writer, blogList.get(blogId).getWriter());
        assertEquals(blogTitle, blogList.get(blogId).getBlogTitle());
        assertEquals(blogContent, blogList.get(blogId).getBlogContent());
    }
    @Test
    @DisplayName("2번글을 조회했을 때, 제목과 글쓴이와 번호가 단언대로 받아와지는지 확인")
    public void findByIdTest(){
        //given: 조회할 id를 변수로 저장한다.
        long id =2;
        //when: 레포지토리에서 단일행 Blog를 얻어와 저장한다.
        Blog blog = blogRepository.findById(id);
        //then: 해당 객체의 writer 멤버변수는 "2번유저"이고 blogTitle은 "2번 제목"dlrh
        //blogId는 2이다.
        assertEquals("2번유저", blog.getWriter());
        assertEquals("2번제목", blog.getBlogTitle());
        assertEquals(2, blog.getBlogId());
    }

    @AfterEach //단위테ㅡ트 끝난 후에 실행할 구문을 작성
    public void dropBlogTable(){
        blogRepository.dropBlogTable(); //blog 테이블 지우기
    }
}
