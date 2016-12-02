/**
 * Created by AaronR on 12/1/16.
 */
public class ScheduleConflictException extends Exception {
    public void ScheduleConflictException(Course c1, Course c2) throws ScheduleConflictException {
        throw new ScheduleConflictException(c1.getName() + " conflicts with " + c2.getName());
    }
    private ScheduleConflictException(String error){
        super(error);
    }
}
