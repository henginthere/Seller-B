package backend.sellerB.exception;

public class DuplicateManagerException extends RuntimeException{

    public DuplicateManagerException(String managerId){
        super(managerId+ "는 이미 가입된 아이디입니다.");
    }
}

