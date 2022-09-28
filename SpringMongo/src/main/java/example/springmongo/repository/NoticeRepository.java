package example.springmongo.repository;

import example.springmongo.domain.NoticeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends MongoRepository<NoticeEntity, String> {

    List<NoticeEntity> findAllByOrderByNoticeSeqDesc();

    NoticeEntity findByNoticeSeq(String noticeSeq);

}