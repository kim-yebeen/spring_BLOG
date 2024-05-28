package kim.y.blog.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper //빈 컨테이너에 Mybatis 관리 파일로서 적재
public interface ConncetionTestRepository
{
    // getNow() 실행시 호출할 SQL 구문은 xml 파일 내부에 작성한다.
    String getNow();

}
