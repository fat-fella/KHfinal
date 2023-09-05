package kh.lclass.jjap.board.model.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.lclass.jjap.board.model.service.BoardService;
import kh.lclass.jjap.board.model.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/list")
	public ModelAndView list(ModelAndView mv) throws Exception{
		mv.addObject("boardList", boardService.selectList());
		mv.setViewName("board/list");
		return mv;
	}
	
	@GetMapping("/get")
	public ModelAndView get(ModelAndView mv, int bno) throws Exception{ //jsp에서 controller로 데이터 전달
		mv.addObject("bvo", boardService.selectOne(bno));
		mv.setViewName("board/get"); // http://localhost:8090/jjap/board/get?bno=3
		return mv;
	}

	@GetMapping("commentList")
	public ModelAndView CommentList(ModelAndView mv, BoardVo vo) throws Exception{
		mv.addObject("bct", boardService.selectCommentList(vo));
		mv.setViewName("board/commentList");
		return mv;
		
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public Integer delete(int bno) {
		Integer result = 0;
		try {
			result = boardService.delete(bno);
		} catch (Exception e) {
			e.printStackTrace();
			result = -1;
		}
		return result;
	}
	
	@GetMapping("/insert")
	public String insert() {
		return "board/insert";
	}
	@PostMapping("/insert")
	@ResponseBody
	public Integer insertDo(BoardVo vo) {
	    vo.setMid("jiin0960");
	    Integer result = 0;
	    try {
	        result = boardService.insert(vo);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return result;
	}
	@PostMapping("/insertReply")
	@ResponseBody
	public Integer insertReply(BoardVo vo, @RequestParam("bno") int bno){
		vo.setMid("jiin0960");
		Integer result = null;
		try {
			boardService.insertReply(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@GetMapping("/update")
	public String update(Model model, int bno) throws Exception{
		BoardVo vo = boardService.selectOne(bno); // 글 정보를 가져옴
        model.addAttribute("dto", vo);
	    return "board/update";
	}
	@PostMapping("/update")
	public String updateDo(RedirectAttributes redirectAttr, BoardVo vo){
		String viewPage = "redirect:/";
		int result;
	    try {
	        result = boardService.update(vo);
	        if (result < 1) {
	            redirectAttr.addFlashAttribute("msg2", "글 수정에 실패했습니다. 다시 시도해주세요.");
	            viewPage = "redirect:/board/get?bno="+vo.getBno();
	        } else {
	            redirectAttr.addFlashAttribute("msg2", "글 수정되었습니다.");
	            viewPage = "redirect:/board/get?bno="+vo.getBno();
	        }
	    } catch (Exception e) {
	        redirectAttr.addFlashAttribute("msg2", "예기치 못한 오류로 글 수정에 실패했습니다.");
	        e.printStackTrace();
	        viewPage = "redirect:/board/list";
	    }
	    return viewPage;
	}	

}
