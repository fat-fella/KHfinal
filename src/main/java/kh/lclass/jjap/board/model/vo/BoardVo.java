package kh.lclass.jjap.board.model.vo;


import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BoardVo {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriteDate;
	private String mid;
	private int bref;
	private int breLevel;
	private int breStep;

	
	
}
