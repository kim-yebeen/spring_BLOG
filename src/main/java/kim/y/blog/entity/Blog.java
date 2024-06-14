package kim.y.blog.entity;

import lombok.*;

import java.util.Date;

//역질렬화(DB->자바객체) 가 가능하도록 blog 테이블 구조에 맞춰서 멤버 변수 선언
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor @Builder //빌더패턴 생성자를 쓸 수 있게 해줌
public class Blog {
    private long blogId; //숫자는 어지간하면 long형을 사용한다.
    private String writer;
    private String blogTitle;
    private String blogContent;
    private Date publishedAt;
    private Date updatedAt;
    private int blog_conunt;

}
