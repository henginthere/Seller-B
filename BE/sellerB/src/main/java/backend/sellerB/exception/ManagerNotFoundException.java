package backend.sellerB.exception;

public class ManagerNotFoundException extends RuntimeException{

    public ManagerNotFoundException(String managerId){
        super(managerId+ "NotFoundException");
    }
}
