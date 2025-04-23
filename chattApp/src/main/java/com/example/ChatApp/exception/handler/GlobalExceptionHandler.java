package com.example.ChatApp.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.ChatApp.exception.AuthenticationFailedException;
import com.example.ChatApp.exception.ChatIdNotFoundException;
import com.example.ChatApp.exception.ChatPreserveFailedException;
import com.example.ChatApp.exception.ChatSaveFailedException;
import com.example.ChatApp.exception.UserAlreadyExistsException;
import com.example.ChatApp.exception.UserIdNotFoundException;
import com.example.ChatApp.exception.UserIdPreserveFailedException;
import com.example.ChatApp.exception.UserNameFoundException;
import com.example.ChatApp.exception.UserSaveFailedException;

/*例外処理を行うクラス
 * 例外クラスあ呼び出された際に対応するステータスコードを返す*/

@RestControllerAdvice //例外クラス用のコントローラーであることを示す
public class GlobalExceptionHandler {

	/*指定されたユーザーまたはパスワードが存在しない場合にエラーメッセージを返す*/
	@ExceptionHandler(AuthenticationFailedException.class)
	public ResponseEntity<String> handleAuthenticationFailed(AuthenticationFailedException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ex.getMessage());

	}

	/*ユーザー名またはパスワードがnullの状態で渡された場合エラーメッセージを返す*/
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ex.getMessage());

	}

	/*すでに登録済みのユーザー名をクライアント側から受け取った場合エラーメッセージを返す*/
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(ex.getMessage());

	}

	@ExceptionHandler(ChatSaveFailedException.class)
	/*チャット内容をDBに保存ができなかった場合にエラーメッセージを返す*/
	public ResponseEntity<String> handleChatSaveFailed(ChatSaveFailedException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ex.getMessage());

	}

	@ExceptionHandler(UserIdNotFoundException.class)
	/*不明なユーザーからのチャット送信を受け取った場合にエラーメッセージを返す*/
	public ResponseEntity<String> handleUserNotFound(UserIdNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ex.getMessage());

	}

	@ExceptionHandler(UserNameFoundException.class)
	/*DBから指定されたユーザー名を取得できない場合にエラーメッセージを返す*/
	public ResponseEntity<String> handleUserNameFound(UserNameFoundException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ex.getMessage());

	}

	@ExceptionHandler(UserIdPreserveFailedException.class)
	/*チャット送信者のIDを取得できない場合にエラーメッセージを返す*/
	public ResponseEntity<String> handleUserIdPreserveFailed(UserIdPreserveFailedException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ex.getMessage());

	}

	@ExceptionHandler(ChatPreserveFailedException.class)
	/*チャット内容をDBから取得できない場合にエラーメッセージを返す*/
	public ResponseEntity<String> handleChatPreserveFailed(ChatPreserveFailedException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ex.getMessage());
	}

	@ExceptionHandler(ChatIdNotFoundException.class)
	/*DBからチャットIDを取得できない場合にエラーメッセージを返す*/
	public ResponseEntity<String> handleChatIdNotFound(ChatIdNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ex.getMessage());
	}

	@ExceptionHandler(UserSaveFailedException.class)
	/*ユーザ情報の登録失敗時にエラーメッセージを返す*/
	public ResponseEntity<String> handleUserSaveFailed(UserSaveFailedException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ex.getMessage());
	}
}