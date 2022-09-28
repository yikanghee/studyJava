package example.springmongo.controller;

import example.springmongo.dto.NoticeDTO;
import example.springmongo.service.NoticeService;
import example.springmongo.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping(value = "/notice")
@Controller
public class NoticeController {

    @Resource(name = "NoticeService")
    private NoticeService noticeService;

    @GetMapping(value = "noticeList")
    public String noticeList(ModelMap model) {

        List<NoticeDTO> rList = noticeService.getNoticeList();

        if (rList == null) {
            rList = new ArrayList<NoticeDTO>();
        }

        model.addAttribute("rList", rList);

        rList = null;

        return "/notice/NoticeList";
    }

    @GetMapping(value = "noticeInfo")
    public String noticeInfo(HttpServletRequest request, ModelMap model) throws Exception {

        String nSeq = CmmUtil.nvl(request.getParameter("nSeq"));

        NoticeDTO pDTO = new NoticeDTO();
        pDTO.setNoticeSeq(nSeq);

        NoticeDTO rDTO = noticeService.getNoticeInfo(pDTO, true);

        if (rDTO == null) {
            rDTO = new NoticeDTO();
        }

        model.addAttribute("rDTO", rDTO);

        return "/notice/NoticeInfo";
    }

    @GetMapping(value = "noticeEditInfo")
    public String noticeEditInfo(HttpServletRequest request, ModelMap model) throws Exception {

        String msg = "";

        try {
            String nSeq = CmmUtil.nvl(request.getParameter("nSeq"));

            NoticeDTO pDTO = new NoticeDTO();
            pDTO.setNoticeSeq(nSeq);

            NoticeDTO rDTO = noticeService.getNoticeInfo(pDTO, false);

            if (rDTO == null) {
                rDTO = new NoticeDTO();
            }

            model.addAttribute("rDTO", rDTO);

        } catch (Exception e) {
            msg = "실패하였습니다 : " + e.getMessage();
            e.printStackTrace();
        } finally {
            model.addAttribute("msg", msg);
        }
        return "/notice/NoticeEditInfo";
    }

    @PostMapping(value = "noticeUpdate")
    public String NoticeUpdate(HttpSession session, HttpServletRequest request, ModelMap model) {

        String msg = "";

        try {
            String user_id = CmmUtil.nvl((String) session.getAttribute("SESSION_USER_ID"));
            String nSeq = CmmUtil.nvl(request.getParameter("nSeq"));
            String title = CmmUtil.nvl(request.getParameter("title"));
            String noticeYn = CmmUtil.nvl(request.getParameter("noticeYn"));
            String contents = CmmUtil.nvl(request.getParameter("contents"));

            NoticeDTO pDTO = new NoticeDTO();

            pDTO.setUserId(user_id);
            pDTO.setNoticeSeq(nSeq);
            pDTO.setTitle(title);
            pDTO.setNoticeYn(noticeYn);
            pDTO.setContents(contents);

            noticeService.updateNoticeInfo(pDTO);

            msg = "수정되었습니다";
        } catch (Exception e) {
            msg = "실패하였습니다";
        } finally {
            model.addAttribute("msg", msg);
        }
        return "/notice/MsgToList";
    }

    @GetMapping(value = "noticeDelete")
    public String noticeDelete(HttpServletRequest request, ModelMap model) {

        String msg = "";

        try {
            String nSeq = CmmUtil.nvl(request.getParameter("nSeq"));

            NoticeDTO pDTO = new NoticeDTO();

            pDTO.setNoticeSeq(nSeq);

            noticeService.deleteNoticeInfo(pDTO);

            msg = "삭제되었습니다";
        } catch (Exception e) {
            msg = "실패하였습니다";
        } finally {
            model.addAttribute("msg", msg);
        }

        return "/notice/MsgToList";
    }

    @GetMapping(value = "noticeReg")
    public String noticeReg() {
        return "/notice/NoticeReg";
    }

    @PostMapping(value = "noticeInsert")
    public String noticeInsert(HttpSession session, HttpServletRequest request, ModelMap model) {

        String msg = "";

        try {
            String user_id = CmmUtil.nvl((String) session.getAttribute("SESSION_USER_ID"));
            String title = CmmUtil.nvl(request.getParameter("title"));
            String noticeYn = CmmUtil.nvl(request.getParameter("noticeYn"));
            String contents = CmmUtil.nvl(request.getParameter("contents"));

            NoticeDTO pDTO = new NoticeDTO();
            pDTO.setUserId(user_id);
            pDTO.setTitle(title);
            pDTO.setNoticeYn(noticeYn);
            pDTO.setContents(contents);

            noticeService.InsertNoticeInfo(pDTO);

            msg = "등록되었습니다";
        } catch (Exception e) {
            msg = "실패하였습니다";
        } finally {
            model.addAttribute("msg", msg);
        }

        return "/notice/MsgToList";
    }
}
