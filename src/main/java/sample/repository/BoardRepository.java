package sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import sample.domain.Board;

/**
 * Mongo repository for the boards
 * @author pmincz
 *
 */
public interface BoardRepository extends MongoRepository<Board, String> {
	public Board findByBoardId(Long boardId);
	public Board findByTitle(String title);
}
