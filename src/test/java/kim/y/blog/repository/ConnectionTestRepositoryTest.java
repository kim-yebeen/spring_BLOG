package kim.y.blog.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest //@Mapper로 적재된 마이바티스 인터페이스 인식을 위해 어노테이션 생성
public class ConnectionTestRepositoryTest {

    @Autowired //testcode는 필드주입을 해도 괜찮은 경우가 많음
    ConncetionTestRepository conncetionTestRepository;

    @Test
    public void getNowTest(){
        System.out.println("얻어온 현재 시간 : " +conncetionTestRepository.getNow());
    }
}
