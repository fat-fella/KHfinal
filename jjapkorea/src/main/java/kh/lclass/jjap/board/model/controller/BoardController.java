package kh.lclass.jjap.board.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.lclass.jjap.board.model.service.Boardservice;
import kh.lclass.jjap.board.model.vo.BoardVo;


public class BoardController {
	@Autowired
	private Boardservice boardService;
	
	@GetMapping("/list")
	public ModelAndView list(ModelAndView mv) throws Exception{
		mv.addObject("boardList", boardService.selectList());
		mv.setViewName("board/list");
		return mv;
	}
	@GetMapping("/get")
	public String get(Model model, int bno, String a) throws Exception{ //jsp에서 controller로 데이터 전달
		System.out.println(bno);
		System.out.println(a);
		
		model.addAttribute("boardVo", boardService.selectOne(bno));
		return "board/get";
	}
	@GetMapping("/insert")
	public String insert() {
		//FlashAttribute를 위해 여기에 작성할것이 없음
//		return "board/insert"; 이렇게해도 되지만 코드의 일관성을 위해
		String viewPage = "board/insert";
		return viewPage; // 이렇게 작성
	}
	@PostMapping("/insert")
	public String insertDo(
			RedirectAttributes redirectAttr, BoardVo vo, String btitle) {
		String viewPage = "redirect:/";
		System.out.println(vo);
		System.out.println(btitle);
		
		vo.setMid("jiin0960");
		
		int result; //1: 글 등록 성공, 0: 글 등록 실패
		try {
			result = boardService.insert(vo);
			if(result < 1) {
				redirectAttr.addAttribute("msg2", "msg2"); //url에 뿌려짐 ?msg2=msg2&n1=n1
				redirectAttr.addFlashAttribute("msg", "글 등록에 실패했습니다. 다시 입력해세요."); // 1회성으로 뿌려짐
				viewPage = "redirect:/board/insert";
			}else {
				redirectAttr.addAttribute("msg2", "msg2");
				redirectAttr.addAttribute("msg3", "msg3");
				redirectAttr.addFlashAttribute("msg", "글 등록되었습니다.");
				viewPage = "redirect:/board/list?n1=v1&n2=v2";
			}
		}catch(Exception e) {
			//오류 발생시
			redirectAttr.addAttribute("msg2", "msg2");
			redirectAttr.addFlashAttribute("msg", "예기치 못한 오류로 글등록에 실패했습니다. 다시 시도해주세요.");
			viewPage = "redirect:/board/list";
		}
		return "viewPage";
	}
}
