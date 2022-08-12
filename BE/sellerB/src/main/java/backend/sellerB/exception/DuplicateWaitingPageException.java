package backend.sellerB.exception;

public class DuplicateWaitingPageException extends RuntimeException{

    public DuplicateWaitingPageException(){
        super("이미 대기화면이 등록된 제품입니다. 등록된 대기화면을 확인해주세요.");

    }
}
