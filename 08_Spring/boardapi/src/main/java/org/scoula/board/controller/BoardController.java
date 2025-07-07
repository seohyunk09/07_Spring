package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.service.BoardService;
import org.scoula.board.domain.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/board"
)
@RequiredArgsConstructor
@Log4j2
public class BoardController {
    private final BoardService service;
}

@GetMapping("")
public List<BoardDTO> getList() {
    return service.getList();
}
@GetMapping("/{no}")
public ResponseEntity<BoardDTO> get(@PathVariable Long no) {
    return ResponseEntity.ok(service.get(no));
}
@PostMapping("")
public ResponseEntity<BoardDTO> create(@RequestBody BoardDTO board) {
    return ResponseEntity.ok(service.create(board));
}

@PutMapping("/{no}")
public ResponseEntity<BoardDTO> update(@PathVariable Long no, @RequestBody BoardDTO board) {
    return ResponseEntity.ok(service.update(board));
}

@DeleteMapping("/{no}")
public ResponseEntity<BoardDTO> delete(@PathVariable Long no) {
    return ResponseEntity.ok(service.delete(no));
}