package store.kirinit.communityfeed.acceptance.utils;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("test")
@Component
@Slf4j
public class DatabaseCleanUp implements InitializingBean {

    @PersistenceContext
    private EntityManager entityManager;
    private List<String> tableNames;
    private List<String> notGeneratedIdTableNames;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 정상적으로 스프링 빈으로 등록된 것들을 불러온다
        tableNames = entityManager.getMetamodel().getEntities().stream()
            .filter(entity -> entity.getJavaType().getAnnotation(Entity.class) != null)
            .map(entity -> entity.getJavaType().getAnnotation(Table.class).name())
            .toList();

        // id가 자동 생성되지 않는 테이블은 제외한다
        notGeneratedIdTableNames = List.of("community_user_relation", "community_like");
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        // foreign key 제약을 무시하고 테이블을 비운다
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        for (String tableName : tableNames) {
            // id가 자동 생성되지 않는 테이블은 id를 1부터 다시 시작하도록 한다
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            if (!notGeneratedIdTableNames.contains(tableName)) {
                entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
            }
        }
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
