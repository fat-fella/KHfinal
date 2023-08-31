package kh.lclass.jjap.board.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@GetMapping("/insert")
	public String insert() {
		String viewPage = "board/insert";
		return viewPage;
	}

	@PostMapping("/insert")
	public String insertDo(RedirectAttributes redirectAttr, BoardVo vo) {
	    String viewPage = "redirect:/";
	    vo.setMid("jiin0960");
	    int result;
	    try {
	        result = boardService.insert(vo);
	        if (result < 1) {
	            redirectAttr.addFlashAttribute("msg", "글 등록에 실패했습니다. 다시 입력해세요.");
	            viewPage = "redirect:/board/insert";
	        } else {
	            redirectAttr.addFlashAttribute("msg", "글 등록되었습니다.");
	            viewPage = "redirect:/board/list";
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	        redirectAttr.addFlashAttribute("msg", "예기치 못한 오류로 글 등록에 실패했습니다. 다시 시도해주세요.");
	        viewPage = "redirect:/board/insert";
	    }
	    return viewPage;
	}
	@GetMapping("/update")
	public String update(Model model, BoardVo vo) throws Exception{
		model.addAttribute("dto", boardService.update(vo));
	    return "board/update";
	}
	@PostMapping("/update")
	public String updateDo(RedirectAttributes redirectAttr, BoardVo vo){
		String viewPage = "redirect:/";
		int result;
	    try {
	        result = boardService.update(vo);
	        if (result < 1) {
	            redirectAttr.addFlashAttribute("msg", "글 수정에 실패했습니다. 다시 입력해세요.");
	            viewPage = "redirect:/board/update";
	        } else {
	            redirectAttr.addFlashAttribute("msg", "글 수정되었습니다.");
	            viewPage = "redirect:/board/get";
	        }
	    } catch (Exception e) {
	        redirectAttr.addFlashAttribute("msg", "<<예기치 못한 오류로 글 수정에 실패했습니다.>>");
	        e.printStackTrace();
	        viewPage = "redirect:/board/get";
	    }
	    return viewPage;
	}	
}
