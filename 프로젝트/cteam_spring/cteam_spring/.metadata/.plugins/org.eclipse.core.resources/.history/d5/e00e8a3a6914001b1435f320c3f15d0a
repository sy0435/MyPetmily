package com.cteam.app.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.cteam.app.dao.BoardDAO;
import com.cteam.app.dao.CDao;
import com.cteam.app.dto.BoardselectDTO;

public class BoardSelectCommand implements AnCommand {

	@Override
	public void execute(Model model) {
		BoardDAO boarddao = new BoardDAO();
		ArrayList<BoardselectDTO> boardselectdto = boarddao.boardselect();
		
		//System.out.println(boardselectdto.get(0).getId());
		
		model.addAttribute("boardselect", boardselectdto);
		
	}

}
