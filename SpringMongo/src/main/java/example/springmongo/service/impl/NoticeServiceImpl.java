package example.springmongo.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.springmongo.domain.NoticeEntity;
import example.springmongo.dto.NoticeDTO;
import example.springmongo.repository.NoticeRepository;
import example.springmongo.service.NoticeService;
import example.springmongo.util.CmmUtil;
import example.springmongo.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public List<NoticeDTO> getNoticeList() {

        List<NoticeEntity> rList = noticeRepository.findAllByOrderByNoticeSeqDesc();

        List<NoticeDTO> nList = new ObjectMapper().convertValue(rList,
                new TypeReference<List<NoticeDTO>>() {
                });

        return nList;
    }

    @Override
    public NoticeDTO getNoticeInfo(NoticeDTO pDTO, boolean type) throws Exception {

        NoticeEntity rEntity = noticeRepository.findByNoticeSeq(pDTO.getNoticeSeq());

        if (type) {

            NoticeEntity pEntity = NoticeEntity.builder()
                    .noticeSeq(rEntity.getNoticeSeq()).title(rEntity.getTitle())
                    .noticeYn(rEntity.getNoticeYn()).contents(rEntity.getContents())
                    .userId(rEntity.getUserId()).readCnt(rEntity.getReadCnt() + 1)
                    .regId(rEntity.getRegId()).regDt(rEntity.getRegDt())
                    .chgId(rEntity.getChgId()).chgDt(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
                    .build();

            noticeRepository.save(pEntity);

            rEntity = noticeRepository.findByNoticeSeq(pDTO.getNoticeSeq());
        }

        NoticeDTO rDTO = new ObjectMapper().convertValue(rEntity, NoticeDTO.class);

        return rDTO;
    }

    @Override
    public void updateNoticeInfo(NoticeDTO pDTO) throws Exception {

        String noticeSeq = CmmUtil.nvl(pDTO.getNoticeSeq());
        String title = CmmUtil.nvl(pDTO.getTitle());
        String noticeYn = CmmUtil.nvl(pDTO.getNoticeYn());
        String contents = CmmUtil.nvl(pDTO.getContents());
        String userId = CmmUtil.nvl(pDTO.getUserId());

        log.info("noticeSeq : " + noticeSeq);

        NoticeEntity rEntity = noticeRepository.findByNoticeSeq(pDTO.getNoticeSeq());

        NoticeEntity pEntity = NoticeEntity.builder()
                .noticeSeq(noticeSeq).title(title)
                .userId(userId)
                .readCnt(rEntity.getReadCnt())
                .regId(rEntity.getRegId()).regDt(rEntity.getRegId())
                .chgId(userId).chgDt(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
                .build();

        noticeRepository.save(pEntity);

    }

    @Override
    public void deleteNoticeInfo(NoticeDTO pDTO) throws Exception {

        String noticeSeq = CmmUtil.nvl(pDTO.getNoticeSeq());

        noticeRepository.deleteById(noticeSeq);
    }

    @Override
    public void InsertNoticeInfo(NoticeDTO pDTO) throws Exception {

        String title = CmmUtil.nvl(pDTO.getTitle());
        String noticeYn = CmmUtil.nvl(pDTO.getNoticeYn());
        String contents = CmmUtil.nvl(pDTO.getContents());
        String userId = CmmUtil.nvl(pDTO.getUserId());

        NoticeEntity pEntity = NoticeEntity.builder()
                .title(title).noticeYn(noticeYn).contents(contents).userId(userId)
                .readCnt(0L).regId(userId).regDt(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
                .chgId(userId).chgDt(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
                .build();

        noticeRepository.save(pEntity);
    }
}
