package backend.sellerB.exception;

public class DuplicateUserException extends RuntimeException{

    public DuplicateUserException(String id){
        super(id+ "는 이미 가입된 아이디입니다.");
    }
}